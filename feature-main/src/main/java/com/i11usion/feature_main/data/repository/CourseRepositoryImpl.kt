package com.i11usion.feature_main.data.repository

import com.i11usion.core.data.mapper.dtoToDomain
import com.i11usion.core.data.network.CourseService
import com.i11usion.core.domain.model.Course
import com.i11usion.feature_main.domain.repository.CoursesRepository
import javax.inject.Inject

class CoursesRepositoryImpl @Inject constructor(
    private val service: CourseService
) : CoursesRepository {

    override suspend fun getCourses(): List<Course> =
        service.getCourses()
            .courses
            .map { it.dtoToDomain() }
            .sortedByDescending { it.publishDate }
}
