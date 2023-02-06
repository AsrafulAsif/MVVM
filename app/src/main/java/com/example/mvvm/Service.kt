package com.example.mvvm

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("type?")
    fun getAllCategories(
        @Query("type") type: String,
        @Query("isParent") boolean: Boolean = true,
    ): Call<Data>

}