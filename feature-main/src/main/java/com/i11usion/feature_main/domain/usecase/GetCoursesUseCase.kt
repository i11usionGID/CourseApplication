package com.i11usion.feature_main.domain.usecase

import com.i11usion.core.domain.model.Course
import com.i11usion.feature_main.domain.repository.CoursesRepository
import javax.inject.Inject

class GetCoursesUseCase @Inject constructor(
    private val repository: CoursesRepository
) {
    suspend operator fun invoke(): List<Course> =
        repository.getCourses()
}
