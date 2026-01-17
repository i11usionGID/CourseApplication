package com.i11usion.feature_main.domain.usecase

import com.i11usion.feature_main.domain.repository.CoursesRepository
import javax.inject.Inject

class SwitchFavoriteStatusUseCase @Inject constructor(
    private val repository: CoursesRepository
) {

    suspend operator fun invoke(courseId: Int) {
        return repository.changeFavoriteStatus(courseId)
    }
}