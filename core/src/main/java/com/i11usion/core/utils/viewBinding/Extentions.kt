package com.i11usion.core.utils.viewBinding

import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty

fun <T : ViewBinding> Fragment.viewBinding(
    factory: (View) -> T
): ReadOnlyProperty<Fragment, T> =
    ViewBindingDelegate(this, factory)
