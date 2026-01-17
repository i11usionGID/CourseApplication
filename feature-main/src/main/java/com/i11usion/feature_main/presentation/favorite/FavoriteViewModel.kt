package com.i11usion.feature_main.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.i11usion.core.domain.model.Course
import com.i11usion.feature_main.domain.usecase.GetFavoriteCoursesUseCase
import com.i11usion.feature_main.domain.usecase.SwitchFavoriteStatusUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val getFavoriteCoursesUseCase: GetFavoriteCoursesUseCase,
    private val switchFavoriteStatusUseCase: SwitchFavoriteStatusUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(FavoritesFragmentState(emptyList()))
    val state = _state.asStateFlow()

    init {
        loadFavorites()
    }

    fun loadFavorites() {
        viewModelScope.launch {
            val favoriteCourses = getFavoriteCoursesUseCase()
            _state.value = FavoritesFragmentState(courses = favoriteCourses)
        }
    }

    fun onFavoriteClick(course: Course) {
        viewModelScope.launch {
            switchFavoriteStatusUseCase(course.id)
            loadFavorites()
        }
    }
}


@JvmInline
value class FavoritesFragmentState(
    val courses: List<Course>
)