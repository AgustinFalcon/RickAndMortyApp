package com.example.rickandmortyapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.data.remote.NetworkResult
import com.example.rickandmortyapp.repository.Character
import com.example.rickandmortyapp.repository.CharacterRepository
import com.example.rickandmortyapp.repository.CharacterRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class CharacterViewModel @Inject constructor(private val repository: CharacterRepository): ViewModel() {

    private val _characterViewState = MutableLiveData<CharacterViewStates>()
    val characterViewState: LiveData<CharacterViewStates> get() = _characterViewState

    fun getCharacters() {
        viewModelScope.launch {
            when (val result = repository.getCharacters()) {
                is NetworkResult.Success -> {
                    _characterViewState.postValue(CharacterViewStates.Success(result.data))
                }
                is NetworkResult.Error -> {
                    _characterViewState.postValue(CharacterViewStates.Error(result.error))
                }
                NetworkResult.Loading -> {
                    _characterViewState.postValue(CharacterViewStates.Loading)
                }
                else -> {
                    Log.d("CharacterViewModel", "Error in the call http")
                }
            }
        }
    }

}

sealed class CharacterViewStates {
    data class Success(val data: List<Character>): CharacterViewStates()
    data class Error(val error: Exception): CharacterViewStates()
    object Loading: CharacterViewStates()
}