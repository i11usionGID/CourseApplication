package com.i11usion.core.domain.model

data class Course(
    val id: Int,
    val title: String,
    val description: String,
    val price: Int,
    val rate: Float,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String
)


