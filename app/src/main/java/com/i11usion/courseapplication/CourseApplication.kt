package com.i11usion.courseapplication

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.i11usion.core.di.ViewModelFactoryProvider
import com.i11usion.courseapplication.di.AppComponent
import com.i11usion.courseapplication.di.DaggerAppComponent
import javax.inject.Inject
import kotlin.getValue

class CourseApplication : Application(), ViewModelFactoryProvider {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }

    override fun provideViewModelFactory(): ViewModelProvider.Factory =
        viewModelFactory
}

