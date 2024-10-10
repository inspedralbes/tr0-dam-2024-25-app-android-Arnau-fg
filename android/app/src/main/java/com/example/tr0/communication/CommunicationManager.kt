package com.example.tr0.communication

import com.example.tr0.data.StartGameJSON
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

private const val DEV_URL = "http://dam.inspedralbes.cat:24568"

//private const val DEV_URL = "http://localhost:3000"

private val json = Json {
    ignoreUnknownKeys = true // If there are unknown fields in the JSON, ignore them
    isLenient = true         // Allows lenient parsing of the JSON
    prettyPrint = true       // For pretty printing
    encodeDefaults = true    // Encodes default values
}

private val retrofit = Retrofit.Builder()
//    .addConverterFactory(json.asConverterFactory("application/json; charset=utf-8".toMediaType()))
    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
    .baseUrl(DEV_URL)
    .build()

interface QuestionsApiService {
    @GET("start")
    suspend fun getPreguntes(@Body token: String = ""): StartGameJSON

    @POST("finalitza")
    suspend fun postAnswers()
}

object QuestionsApi {
    val retrofitService : QuestionsApiService by lazy {
        retrofit.create(QuestionsApiService::class.java)
    }
}