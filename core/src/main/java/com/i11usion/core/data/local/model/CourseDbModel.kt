package com.i11usion.core.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "courses")
data class CourseDbModel(
    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
    val price: Int,
    val rate: Float,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String
)
