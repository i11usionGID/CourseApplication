package com.i11usion.core.data.mapper

import com.i11usion.core.data.model.CourseDto
import com.i11usion.core.domain.model.Course

fun CourseDto.dtoToDomain(): Course =
    Course(
        id = id,
        title = title,
        description = text,
        price = price,
        rate = rate,
        startDate = startDate,
        isFavorite = hasLike,
        publishDate = publishDate
    )
