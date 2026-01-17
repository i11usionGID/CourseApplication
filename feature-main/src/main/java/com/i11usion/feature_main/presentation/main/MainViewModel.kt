package com.i11usion.feature_main.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.i11usion.core.domain.model.Course
import com.i11usion.feature_main.domain.usecase.GetCoursesUseCase
import com.i11usion.feature_main.domain.usecase.InsertCoursesUseCase
import com.i11usion.feature_main.domain.usecase.SortCoursesByDateUseCase
import com.i11usion.feature_main.domain.usecase.SwitchFavoriteStatusUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getCoursesUseCase: GetCoursesUseCase,
    private val insertCoursesUseCase: InsertCoursesUseCase,
    private val switchFavoriteStatusUseCase: SwitchFavoriteStatusUseCase,
    private val sortCoursesByDateUseCase: SortCoursesByDateUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MainFragmentState(emptyList()))
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            insertCoursesUseCase()
            val courses = getCoursesUseCase()
            _state.value = MainFragmentState(courses = courses)
        }
    }

    fun onFavoriteClick(courseId: Int) {
        viewModelScope.launch {
            switchFavoriteStatusUseCase(courseId)
            val oldCourses = _state.value.courses
            val newCourses = oldCourses.map {
                if (it.id == courseId) {
                    it.copy(hasLike = !it.hasLike)
                } else {
                    it
                }
            }
            _state.value = MainFragmentState(newCourses)
        }
    }

    fun sortCoursesByDate() {
        viewModelScope.launch {
            val sortCourses = sortCoursesByDateUseCase()
            _state.value = MainFragmentState(courses = sortCourses, needToScroll = true)
        }
    }
}

data class MainFragmentState(
    val courses: List<Course>,
    val needToScroll: Boolean = false
)
