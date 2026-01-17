package com.i11usion.feature_main.domain.usecase

import com.i11usion.feature_main.domain.repository.CoursesRepository
import javax.inject.Inject

class InsertCoursesUseCase @Inject constructor(
    private val repository: CoursesRepository
) {

    suspend operator fun invoke() {
        repository.insertCourses()
    }
}