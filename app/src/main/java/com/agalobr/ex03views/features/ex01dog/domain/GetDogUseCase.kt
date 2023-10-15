package com.agalobr.ex03views.features.ex01dog.domain

import com.agalobr.ex03views.app.Either
import com.agalobr.ex03views.app.ErrorApp

class GetDogUseCase(val repository: DogRepository) {
    operator fun invoke(): Either<ErrorApp, List<Dog>> {
        return repository.get()
    }
}