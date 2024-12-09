package com.example.lab_5.L5

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object RetrofitInstance {
    private val json = Json { ignoreUnknownKeys = true }

    val api: RickAndMortyApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(RickAndMortyApi::class.java)
    }
}