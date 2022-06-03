package com.example.rickandmortyapp.repository

import com.example.rickandmortyapp.data.remote.NetworkResult


interface CharacterRepository {
    suspend fun getCharacters(): NetworkResult<List<Character>>
}