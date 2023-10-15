package com.agalobr.ex03views.features.ex01dog.data

import com.agalobr.ex03views.app.Either
import com.agalobr.ex03views.app.ErrorApp
import com.agalobr.ex03views.features.ex01dog.data.local.XmlDogLocalDataSource
import com.agalobr.ex03views.features.ex01dog.domain.Dog
import com.agalobr.ex03views.features.ex01dog.domain.DogRepository

class DogDataRepository(private val localDataRepository: XmlDogLocalDataSource) : DogRepository {

    override fun save(dog: Dog): Either<ErrorApp, Dog> {
        return localDataRepository.saveDog(dog)
    }

    override fun get(): Either<ErrorApp, List<Dog>> {
        return localDataRepository.getDog()
    }
}