package com.example.tr0.communication

import com.example.tr0.data.ReturnAnswers
import com.example.tr0.data.SendAnswers
import com.example.tr0.data.StartGameJSON
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

private const val DEV_URL = "http://dam.inspedralbes.cat:24569"

//private const val DEV_URL = "http://localhost:3000"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
    .baseUrl(DEV_URL)
    .build()

interface QuestionsApiService {

    @GET("start")
    suspend fun getPreguntes(): StartGameJSON

    @POST("end")
    suspend fun postAnswers(@Body request: SendAnswers): ReturnAnswers
}

object QuestionsApi {
    val retrofitService : QuestionsApiService by lazy {
        retrofit.create(QuestionsApiService::class.java)
    }
}