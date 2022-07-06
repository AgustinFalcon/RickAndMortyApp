package com.example.rickandmortyapp

import android.app.Application
import com.example.rickandmortyapp.repository.CharacterRepository
import com.example.rickandmortyapp.ui.di.ServiceLocator
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    val characterRepository: CharacterRepository get() = ServiceLocator.provideCharacterRepository(this)

}