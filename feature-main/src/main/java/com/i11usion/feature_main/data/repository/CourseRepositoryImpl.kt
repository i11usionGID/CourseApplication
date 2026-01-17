package com.i11usion.feature_main.data.repository

import com.i11usion.core.data.local.dao.CourseDao
import com.i11usion.core.data.local.mapper.dbModelToDomain
import com.i11usion.core.data.local.mapper.dtoToDbModel
import com.i11usion.core.data.network.CourseService
import com.i11usion.core.domain.model.Course
import com.i11usion.feature_main.domain.repository.CoursesRepository
import javax.inject.Inject

class CoursesRepositoryImpl @Inject constructor(
    private val service: CourseService,
    private val dao: CourseDao
) : CoursesRepository {

    override suspend fun getCourses(): List<Course> {
        return dao.getCourses().map { courses ->
            courses.dbModelToDomain()
        }
    }

    override suspend fun getFavoriteCourses(): List<Course> {
        return dao.getFavoriteCourses().map { courses ->
            courses.dbModelToDomain()
        }
    }

    override suspend fun insertCourses() {
        val entities = service.getCourses()
            .courses
            .map { it.dtoToDbModel() }

        dao.insertCourses(entities)
    }

    override suspend fun changeFavoriteStatus(courseId: Int) {
        dao.changeFavoriteStatus(courseId)
    }

    override suspend fun sortCoursesByDate(): List<Course> {
        return dao.sortCoursesByDate().map { courses ->
            courses.dbModelToDomain()
        }
    }
}


