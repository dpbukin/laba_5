package com.example.lab_5.L5

class CharacterRepository(private val apiService: RickAndMortyApi) {
    suspend fun getCharacters(page: Int): CharacterResponse {
        val response = apiService.getCharacters(page)
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Empty response")
        } else {
            throw Exception("API Error: ${response.code()}")
        }
    }
}