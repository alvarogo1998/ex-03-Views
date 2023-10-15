package com.agalobr.ex03views.features.ex01dog.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agalobr.ex03views.app.ErrorApp
import com.agalobr.ex03views.features.ex01dog.domain.Dog
import com.agalobr.ex03views.features.ex01dog.domain.GetDogUseCase
import com.agalobr.ex03views.features.ex01dog.domain.SaveDogUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Ex01DogViewModel (private val getDogUseCase: GetDogUseCase,
    private val saveDogUseCase: SaveDogUseCase):ViewModel() {

    private val _uiState = MutableLiveData<Uistate>()
    val uiState : LiveData<Uistate> = _uiState

    fun save(dog: Dog){
        viewModelScope.launch(Dispatchers.IO) {
            saveDogUseCase.invoke(dog).fold(
                {responseError(it)},
                {responseSaveDogSuccess(it)}
            )
        }
    }

    fun get(){
        viewModelScope.launch (Dispatchers.IO){
            getDogUseCase.invoke().fold(
                {responseError(it)},
                {responseGetDogSuccess(it)}
            )
        }
    }

    private fun responseError(errorApp: ErrorApp) {

    }

    private fun responseSaveDogSuccess(dog: Dog) {
        _uiState.postValue(Uistate(dog = dog))
    }
    private fun responseGetDogSuccess(dog : List<Dog>) {
        _uiState.postValue(Uistate())
    }

    data class Uistate(
        val errorApp: ErrorApp? = null,
        val isLoading: Boolean = false,
        val dog: Dog? = null
    )
}