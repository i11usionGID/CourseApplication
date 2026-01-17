package com.i11usion.core.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class CourseResponse(
    val courses: List<CourseDto>
)
