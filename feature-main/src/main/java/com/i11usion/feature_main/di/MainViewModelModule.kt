package com.i11usion.feature_main.di

import androidx.lifecycle.ViewModel
import com.i11usion.core.di.ViewModelKey
import com.i11usion.feature_main.presentation.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}
