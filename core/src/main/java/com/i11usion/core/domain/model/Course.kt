package com.i11usion.core.domain.model

data class Course(
    val id: Long,
    val title: String,
    val description: String,
    val price: Int,
    val rate: Double,
    val startDate: String,
    val isFavorite: Boolean,
    val publishDate: String
)

