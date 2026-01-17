package com.i11usion.feature_main.domain.repository

import com.i11usion.core.domain.model.Course

interface CoursesRepository {
    suspend fun getCourses(): List<Course>

    suspend fun getFavoriteCourses(): List<Course>

    suspend fun insertCourses()

    suspend fun changeFavoriteStatus(courseId: Int)

    suspend fun sortCoursesByDate(): List<Course>
}
