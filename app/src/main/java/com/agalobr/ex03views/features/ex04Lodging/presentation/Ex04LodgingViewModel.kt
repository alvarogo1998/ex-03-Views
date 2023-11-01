package com.agalobr.ex03views.features.ex04Lodging.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agalobr.ex03views.app.ErrorApp
import com.agalobr.ex03views.features.ex04Lodging.domain.GetLodgingUseCase
import com.agalobr.ex03views.features.ex04Lodging.domain.Lodging
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Ex04LodgingViewModel(private val getLodgingUseCase: GetLodgingUseCase) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun loadLodging(){
        viewModelScope.launch(Dispatchers.IO) {
            getLodgingUseCase.invoke().fold(
                {responseError(it)},
                {responseSuccess(it)}
            )
        }
    }

    private fun responseError(errorApp: ErrorApp) {
        _uiState.postValue(UiState(errorApp = errorApp))
    }

    private fun responseSuccess(lodging: Lodging){
        _uiState.postValue(UiState(lodging = lodging))
    }

    data class UiState(
        val errorApp: ErrorApp? = null,
        val isLoading: Boolean = false,
        val lodging: Lodging? = null
    )
}

