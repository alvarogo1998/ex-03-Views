package com.agalobr.ex03views.features.ex01dog.domain

import com.agalobr.ex03views.app.Either
import com.agalobr.ex03views.app.ErrorApp

interface DogRepository {

    fun get(): Either<ErrorApp, List<Dog>>
}