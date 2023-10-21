package com.agalobr.ex03views.features.ex01dog.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agalobr.ex03views.app.ErrorApp
import com.agalobr.ex03views.features.ex01dog.domain.Dog
import com.agalobr.ex03views.features.ex01dog.domain.GetDogUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Ex01DogViewModel(
    private val getDogUseCase: GetDogUseCase,
) : ViewModel() {

    private val _uiState = MutableLiveData<Uistate>()
    val uiState: LiveData<Uistate> = _uiState

    fun loadDog() {
        viewModelScope.launch(Dispatchers.IO) {
            getDogUseCase.invoke().fold(
                { responseError(it) },
                { responseGetDogSuccess() }
            )
        }
    }

    private fun responseError(errorApp: ErrorApp) {
        _uiState.postValue(Uistate(errorApp = errorApp))
    }

    private fun responseGetDogSuccess() {
        _uiState.postValue(Uistate())
    }

    data class Uistate(
        val errorApp: ErrorApp? = null,
        val isLoading: Boolean = false,
        val dog: Dog? = null
    )
}