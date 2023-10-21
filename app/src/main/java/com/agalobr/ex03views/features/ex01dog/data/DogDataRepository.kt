package com.agalobr.ex03views.features.ex01dog.data

import com.agalobr.ex03views.app.Either
import com.agalobr.ex03views.app.ErrorApp
import com.agalobr.ex03views.features.ex01dog.data.local.XmlDogLocalDataSource
import com.agalobr.ex03views.features.ex01dog.data.remote.api.DogRemoteDataSource
import com.agalobr.ex03views.features.ex01dog.domain.Dog
import com.agalobr.ex03views.features.ex01dog.domain.DogRepository

class DogDataRepository(
    private val localDataRepository: XmlDogLocalDataSource,
    private val remoteDataSource: DogRemoteDataSource
) : DogRepository {

    override fun get(): Either<ErrorApp, List<Dog>> {
        val doglist = localDataRepository.getDog()
        return if (doglist.isLeft() || doglist.get().isEmpty()) {
            remoteDataSource.getDog().map { dogFromRemote ->
                localDataRepository.delete()
                localDataRepository.saveDog(dogFromRemote)
                dogFromRemote
            }
        } else {
            doglist
        }
    }
}