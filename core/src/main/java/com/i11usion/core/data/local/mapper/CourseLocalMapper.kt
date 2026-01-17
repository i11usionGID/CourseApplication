package com.i11usion.core.data.local.mapper

import com.i11usion.core.data.local.model.CourseDbModel
import com.i11usion.core.data.network.model.CourseDto
import com.i11usion.core.domain.model.Course

fun CourseDto.dtoToDbModel(): CourseDbModel =
    CourseDbModel(
        id = id,
        title = title,
        description = text,
        price = price
            .replace(" ", "")
            .toIntOrNull() ?: 0,
        rate = rate.toFloatOrNull() ?: 0f,
        startDate = startDate,
        hasLike = false,
        publishDate = publishDate
    )

fun CourseDbModel.dbModelToDomain(): Course =
    Course(
        id = id,
        title = title,
        description = description,
        price = price,
        rate = rate,
        startDate = startDate,
        hasLike = hasLike,
        publishDate = publishDate,
    )
