package com.i11usion.core.data.network

import com.i11usion.core.data.model.CoursesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CourseService {

    @GET("uc")
    suspend fun getCourses(
        @Query("id") id: String = "15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q",
        @Query("export") export: String = "download"
    ): CoursesResponse
}