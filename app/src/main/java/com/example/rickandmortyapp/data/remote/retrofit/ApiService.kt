package com.example.rickandmortyapp.data.remote.retrofit

import com.example.rickandmortyapp.data.remote.parsedata.RemoteCharacter
import com.example.rickandmortyapp.data.remote.parsedata.ResponseRemoteCharacter
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {
    @GET("character")
    suspend fun getCharacter(): Response<ResponseRemoteCharacter>
}