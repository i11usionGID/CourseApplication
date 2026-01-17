package com.i11usion.core.utils.viewBinding

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.viewbinding.ViewBinding
import androidx.lifecycle.LifecycleOwner
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ViewBindingDelegate<T : ViewBinding>(
    private val fragment: Fragment,
    private val viewBindingFactory: (View) -> T
) : ReadOnlyProperty<Fragment, T> {

    private var binding: T? = null

    init {
        fragment.lifecycle.addObserver(object : DefaultLifecycleObserver {

            override fun onCreate(owner: LifecycleOwner) {
                fragment.viewLifecycleOwnerLiveData.observe(fragment) { viewLifecycleOwner ->
                    viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {

                        override fun onDestroy(owner: LifecycleOwner) {
                            binding = null
                        }
                    })
                }
            }
        })
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        val view = fragment.view
            ?: throw IllegalStateException(
                "Cannot access view bindings. View is null."
            )

        return binding ?: viewBindingFactory(view).also { binding = it }
    }
}
