package com.i11usion.courseapplication.di

import android.content.Context
import com.i11usion.core.di.ApplicationScope
import com.i11usion.core.di.NetworkModule
import com.i11usion.core.di.ViewModelFactoryModule
import com.i11usion.courseapplication.CourseApplication
import com.i11usion.feature_auth.di.LoginViewModelModule
import com.i11usion.feature_auth.presentation.LoginFragment
import com.i11usion.feature_main.di.MainModule
import com.i11usion.feature_main.di.MainViewModelModule
import com.i11usion.feature_main.presentation.main.MainFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        NetworkModule::class,
        MainModule::class,
        ViewModelFactoryModule::class,
        LoginViewModelModule::class,
        MainViewModelModule::class
    ]
)
interface AppComponent {

    fun inject(app: CourseApplication)

    fun inject(fragment: MainFragment)

    fun inject(fragment: LoginFragment)


    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}
