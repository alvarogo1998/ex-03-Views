package com.agalobr.ex03views.features.ex04Lodging.domain

import com.agalobr.ex03views.app.Either
import com.agalobr.ex03views.app.ErrorApp

interface LodgingRepository {

    suspend fun getLodging(): Either<ErrorApp, Lodging>
}