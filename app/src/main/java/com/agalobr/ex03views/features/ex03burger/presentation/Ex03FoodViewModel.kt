package com.agalobr.ex03views.features.ex03burger.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agalobr.ex03views.app.ErrorApp
import com.agalobr.ex03views.features.ex03burger.domain.Food
import com.agalobr.ex03views.features.ex03burger.domain.GetFoodUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Ex03FoodViewModel( private val getFoodUseCase: GetFoodUseCase):ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun loadFood(){
        _uiState.value = UiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO){
            delay(5000)
            getFoodUseCase.invoke().fold(
                {responseError(it)},
                {responseGetFoodSuccess()}
            )
        }
    }

    private fun responseGetFoodSuccess() {
        _uiState.postValue(UiState(isLoading = false, food = getFoodUseCase.invoke().get()))
    }

    private fun responseError(errorApp: ErrorApp) {
        _uiState.postValue(UiState(errorApp = errorApp, isLoading = false))
    }

    data class UiState(
        val errorApp: ErrorApp? = null,
        val isLoading: Boolean = false,
        val food: List<Food> = emptyList()
    )
}