package com.i11usion.feature_main.di

import com.i11usion.core.di.ApplicationScope
import com.i11usion.feature_main.data.repository.CoursesRepositoryImpl
import com.i11usion.feature_main.domain.repository.CoursesRepository
import dagger.Binds
import dagger.Module

@Module
abstract class MainModule {

    @ApplicationScope
    @Binds
    abstract fun bindCoursesRepository(
        impl: CoursesRepositoryImpl
    ): CoursesRepository
}
