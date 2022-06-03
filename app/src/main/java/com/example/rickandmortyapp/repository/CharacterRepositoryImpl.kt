@file:Suppress("UNREACHABLE_CODE")

package com.example.rickandmortyapp.repository

import android.util.Log
import com.example.rickandmortyapp.data.local.CharacterDBMapper
import com.example.rickandmortyapp.data.local.entities.CharacterDao
import com.example.rickandmortyapp.data.remote.CharacterNetMapper
import com.example.rickandmortyapp.data.remote.NetworkResult
import com.example.rickandmortyapp.data.remote.retrofit.ApiService
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject


class CharacterRepositoryImpl @Inject constructor(
    private val characterDao: CharacterDao,
    private val apiService: ApiService,
    private val characterDBMapper: CharacterDBMapper,
    private val characterNetMapper: CharacterNetMapper
): CharacterRepository {

    override suspend fun getCharacters(): NetworkResult<List<Character>> = withContext(Dispatchers.IO) {
        try {
            //return@withContext (NetworkResult.Loading)
            if (characterDao.getAllCharacters().isNullOrEmpty()) {
                val remoteCharacter = apiService.getCharacter()
                Log.d(TAG, "Hello. API Call with ${remoteCharacter.code()} @ URL ${remoteCharacter.raw().request.url} ")

                characterNetMapper.mapFromEntityList(remoteCharacter.body()?.results!!).forEach {
                    characterDao.insert(characterDBMapper.mapToEntity(it))
                }
                val cacheCharacter = characterDao.getAllCharacters()
                return@withContext (NetworkResult.Success(characterDBMapper.mapFromEntityList(cacheCharacter)))
            } else {
                val cacheCharacter = characterDao.getAllCharacters()
                return@withContext (NetworkResult.Success(characterDBMapper.mapFromEntityList(cacheCharacter)))
            }

        } catch (e: Exception) {
            return@withContext (NetworkResult.Error(e))
        }
    }

    companion object {
        private const val TAG = "CharacterRepositoryImpl"
    }
}