package com.agalobr.ex03views.features.ex02movie.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agalobr.ex03views.app.ErrorApp
import com.agalobr.ex03views.features.ex02movie.domain.GetMoviesUseCase
import com.agalobr.ex03views.features.ex02movie.domain.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Ex02MovieViewModel(private val getMoviesUseCase: GetMoviesUseCase) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    private val feedMovies = getMoviesUseCase.invoke()

    fun getMovie(){
        viewModelScope.launch(Dispatchers.IO) {
            feedMovies.fold(
                { responseError(it) },
                { responseGetMovieSuccess() }
            )
        }
    }

    private fun responseError(errorApp: ErrorApp) {

    }

    private fun responseGetMovieSuccess() {
        _uiState.postValue(UiState(movie = feedMovies.get()))
    }

    data class UiState(
        val errorApp: ErrorApp? = null,
        val isLoading: Boolean = false,
        val movie: List<Movie> = emptyList()
    )
}