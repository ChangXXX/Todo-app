package com.todo.app.api

import com.todo.app.data.model.Todo
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TodoService {

    @GET("todo.json")
    suspend fun getTodos(): Map<String, Todo>

    @POST("todo.json")
    suspend fun addTodo(@Body todo: Todo)

    companion object {

        private val baseUrl =
            "https://todolist-app-d0b55-default-rtdb.firebaseio.com/"

        fun create(): TodoService {

            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TodoService::class.java)
        }
    }
}