package com.i11usion.feature_main.domain.repository

import com.i11usion.core.domain.model.Course
import kotlinx.coroutines.flow.Flow

interface CoursesRepository {
    suspend fun getCourses(): List<Course>

    suspend fun insertCourses()

    suspend fun changeFavoriteStatus(courseId: Int)

    suspend fun sortCoursesByDate(): List<Course>
}
