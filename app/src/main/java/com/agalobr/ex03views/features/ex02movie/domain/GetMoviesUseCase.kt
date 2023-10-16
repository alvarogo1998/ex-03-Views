package com.agalobr.ex03views.features.ex02movie.domain

import com.agalobr.ex03views.app.Either
import com.agalobr.ex03views.app.ErrorApp

class GetMoviesUseCase(private val repository: MovieRepository) {
    operator fun invoke():Either<ErrorApp, List<Movie>>{
        return repository.get()
    }
}