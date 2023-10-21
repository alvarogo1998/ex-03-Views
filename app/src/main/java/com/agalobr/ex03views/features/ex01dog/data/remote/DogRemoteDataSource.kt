package com.agalobr.ex03views.features.ex01dog.data.remote

import com.agalobr.ex03views.app.Either
import com.agalobr.ex03views.app.ErrorApp
import com.agalobr.ex03views.features.ex01dog.domain.Dog

interface DogRemoteDataSource {
    fun getDog(): Either<ErrorApp, List<Dog>>
}