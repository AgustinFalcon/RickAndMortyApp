package com.example.rickandmortyapp.ui.viewmodel

import com.example.rickandmortyapp.repository.CharacterRepository


object ViewModelInjector {

    fun provideViewModelFactory(repository: CharacterRepository): ViewModelFactory {
        return ViewModelFactory(repository)
    }

}