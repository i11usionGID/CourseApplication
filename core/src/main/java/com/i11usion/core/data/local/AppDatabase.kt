package com.i11usion.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.i11usion.core.data.local.dao.CourseDao
import com.i11usion.core.data.local.model.CourseDbModel

@Database(
    entities = [CourseDbModel::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun courseDao(): CourseDao
}
