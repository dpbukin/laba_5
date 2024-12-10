package com.example.lab_5.L7

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Query("SELECT * FROM characters")
    fun getAllCharacters(): Flow<List<CharacterEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<CharacterEntity>): List<Long> // Room ожидает List<Long>

    @Query("DELETE FROM characters")
    suspend fun clearCharacters(): Int // Возвращает количество удаленных строк
}
