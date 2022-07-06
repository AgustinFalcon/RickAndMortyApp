package com.example.rickandmortyapp.data.remote.retrofit

import com.example.rickandmortyapp.data.remote.parsedata.RemoteCharacter
import com.example.rickandmortyapp.data.remote.parsedata.ResponseRemoteCharacter
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface ApiService {
    @GET("character")
    suspend fun getCharacter(): Response<ResponseRemoteCharacter>

    companion object FactoryApiService {
        private fun getClientBuilder(): OkHttpClient.Builder = OkHttpClient.Builder()

        fun createApiService(): ApiService {
            return Retrofit.Builder()
                .client(getClientBuilder().build())
                .baseUrl("https://rickandmortyapi.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiService::class.java)
        }
    }
}