package com.example.rickandmortyapp.data.remote

import java.lang.Exception


sealed class NetworkResult<out T> {
    data class Success<out T>(val data: T): NetworkResult<T>()
    data class Error(val error: Exception): NetworkResult<Nothing>()
    object Loading: NetworkResult<Nothing>()
}