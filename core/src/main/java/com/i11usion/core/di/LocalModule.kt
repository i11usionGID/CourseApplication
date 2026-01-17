package com.i11usion.core.di

import android.content.Context
import androidx.room.Room
import com.i11usion.core.data.local.AppDatabase
import com.i11usion.core.data.local.dao.CourseDao
import dagger.Module
import dagger.Provides

@Module
object LocalModule {

    @ApplicationScope
    @Provides
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "courses.db"
        ).build()
    }

    @Provides
    fun provideCourseDao(db: AppDatabase): CourseDao {
        return db.courseDao()
    }
}
