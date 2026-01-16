package com.i11usion.feature_main.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.i11usion.core.domain.model.Course
import com.i11usion.feature_main.domain.usecase.GetCoursesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getCourses: GetCoursesUseCase
) : ViewModel() {

    val courses = MutableStateFlow<List<Course>>(emptyList())

    fun loadCourses() {
        viewModelScope.launch {
            courses.value = getCourses()
        }
    }
}
