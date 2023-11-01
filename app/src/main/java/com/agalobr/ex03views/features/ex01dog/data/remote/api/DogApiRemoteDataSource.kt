package com.agalobr.ex03views.features.ex01dog.data.remote.api

import com.agalobr.ex03views.app.Either
import com.agalobr.ex03views.app.ErrorApp
import com.agalobr.ex03views.app.right
import com.agalobr.ex03views.features.ex01dog.data.remote.DogApiClient
import com.agalobr.ex03views.features.ex01dog.data.remote.DogRemoteDataSource
import com.agalobr.ex03views.features.ex01dog.domain.Dog

class DogApiRemoteDataSource(private val apiClient: DogApiClient):
    DogRemoteDataSource {
    override fun getDog(): Either<ErrorApp, List<Dog>> {
        val dog = apiClient.getDogList()
        return dog.map {dogApiModel->
            dogApiModel.toDomain()
        }.right()
    }
}