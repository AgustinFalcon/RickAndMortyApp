package com.example.rickandmortyapp.ui.di

import android.content.Context
import androidx.room.Room
import com.example.rickandmortyapp.data.local.entities.CharacterDao
import com.example.rickandmortyapp.data.local.room.HelperDataBase
import com.example.rickandmortyapp.data.remote.CharacterNetMapper
import com.example.rickandmortyapp.data.local.CharacterDBMapper
import com.example.rickandmortyapp.data.remote.retrofit.ApiService
import com.example.rickandmortyapp.repository.CharacterRepository
import com.example.rickandmortyapp.repository.CharacterRepositoryImpl


object ServiceLocator {

    private var dataBase: HelperDataBase? = null
    @Volatile
    var characterRepository :CharacterRepository? = null

    fun createDataBase(context: Context): HelperDataBase {
        val result = Room.databaseBuilder(context.applicationContext, HelperDataBase::class.java, HelperDataBase.DATABASE_NAME).build()
        dataBase = result
        return result
    }


    fun provideCharacterRepository(context: Context): CharacterRepository {
        synchronized(this) {
            return characterRepository ?: CharacterRepositoryImpl(provideCharacterDao(context), provideApiService(), provideMapperBD(), provideMapperNet())
        }
    }


    private fun provideCharacterDao(context: Context): CharacterDao = createDataBase(context).characterDao()
    private fun provideApiService(): ApiService = ApiService.createApiService()
    private fun provideMapperNet(): CharacterNetMapper =  CharacterNetMapper()
    private fun provideMapperBD(): CharacterDBMapper = CharacterDBMapper()



}