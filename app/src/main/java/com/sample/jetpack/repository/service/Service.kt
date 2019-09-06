package com.sample.jetpack.repository.service

import com.sample.jetpack.repository.database.entity.Post
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface Service {

    @GET("/posts")
    suspend fun getPosts(): List<Post>

    companion object {
        fun getInstance(): Service {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(Service::class.java)
        }
    }

}