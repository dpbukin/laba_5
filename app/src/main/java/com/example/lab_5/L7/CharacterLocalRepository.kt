package com.example.lab_5.L7

import kotlinx.coroutines.flow.Flow

class CharacterLocalRepository(private val characterDao: CharacterDao) {
    fun getAllCharacters(): Flow<List<CharacterEntity>> = characterDao.getAllCharacters()

    suspend fun saveCharacters(characters: List<CharacterEntity>) {
        characterDao.insertCharacters(characters)
    }

    suspend fun clearDatabase() {
        characterDao.clearCharacters()
    }
}