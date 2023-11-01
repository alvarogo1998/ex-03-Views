package com.agalobr.ex03views.features.ex04Lodging.domain

import com.agalobr.ex03views.app.Either
import com.agalobr.ex03views.app.ErrorApp

class GetLodgingUseCase(private val repository: LodgingRepository) {

    suspend operator fun invoke(): Either<ErrorApp, Lodging>{
        return repository.getLodging()
    }
}