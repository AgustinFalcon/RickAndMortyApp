package com.example.rickandmortyapp.data.local.entities

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: CharacterEntity)

    @Query("SELECT * FROM character_entity ORDER BY id ASC")
    suspend fun getAllCharacters() : List<CharacterEntity>

    @Query("DELETE FROM character_entity where id = :id")
    suspend fun deleteCharacter(id: Int)

}