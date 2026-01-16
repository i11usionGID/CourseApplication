package com.i11usion.feature_main.presentation.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.i11usion.core.di.ViewModelFactoryProvider
import com.i11usion.feature_main.R
import javax.inject.Inject

class MainFragment : Fragment(R.layout.fragment_main) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val factoryProvider = requireActivity().application as ViewModelFactoryProvider
        viewModelFactory = factoryProvider.provideViewModelFactory()
    }
}
