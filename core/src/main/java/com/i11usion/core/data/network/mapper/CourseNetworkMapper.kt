package com.i11usion.core.data.network.mapper

import com.i11usion.core.data.network.model.CourseDto
import com.i11usion.core.domain.model.Course

fun CourseDto.dtoToDomain(): Course =
    Course(
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
