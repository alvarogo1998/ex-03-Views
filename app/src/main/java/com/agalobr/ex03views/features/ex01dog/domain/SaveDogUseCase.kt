package com.agalobr.ex03views.features.ex01dog.domain

import com.agalobr.ex03views.app.Either
import com.agalobr.ex03views.app.ErrorApp

class SaveDogUseCase(val repository: DogRepository) {

    operator fun invoke(dog: Dog): Either<ErrorApp, Dog> {
        return repository.save(dog)
    }
}