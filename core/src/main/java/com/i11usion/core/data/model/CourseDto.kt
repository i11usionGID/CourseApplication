package com.i11usion.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CourseDto(
    val id: Long,
    val title: String,
    val text: String,
    val price: Int,
    val rate: Double,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String
)
