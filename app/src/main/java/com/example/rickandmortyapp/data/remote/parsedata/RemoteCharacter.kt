package com.example.rickandmortyapp.data.remote.parsedata



data class RemoteCharacter(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String
)