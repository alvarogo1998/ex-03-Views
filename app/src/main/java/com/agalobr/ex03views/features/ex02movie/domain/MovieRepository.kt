package com.agalobr.ex03views.features.ex02movie.domain

import com.agalobr.ex03views.app.Either
import com.agalobr.ex03views.app.ErrorApp

interface MovieRepository {

    fun get(): Either<ErrorApp, List<Movie>>
}