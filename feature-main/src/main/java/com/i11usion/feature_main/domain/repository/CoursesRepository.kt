package com.i11usion.feature_main.domain.repository

import com.i11usion.core.domain.model.Course

interface CoursesRepository {
    suspend fun getCourses(): List<Course>
}
