package com.i11usion.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.i11usion.core.data.local.model.CourseDbModel

@Dao
interface CourseDao {

    @Query("SELECT * FROM courses")
    suspend fun getCourses(): List<CourseDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourses(courses: List<CourseDbModel>)

    @Query("UPDATE courses SET hasLike = NOT hasLike WHERE id =:courseId")
    suspend fun changeFavoriteStatus(courseId: Int)

    @Query("SELECT * FROM courses ORDER BY publishDate DESC")
    suspend fun sortCoursesByDate(): List<CourseDbModel>

}
