package com.i11usion.feature_auth.di

import androidx.lifecycle.ViewModel
import com.i11usion.core.di.ViewModelKey
import com.i11usion.feature_auth.presentation.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface LoginViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun bindAuthViewModel(viewModel: LoginViewModel): ViewModel
}
