package com.agalobr.ex03views.features.ex03burger.domain

import com.agalobr.ex03views.app.Either
import com.agalobr.ex03views.app.ErrorApp

interface FoodRepository {

    fun get(): Either<ErrorApp, List<Food>>
}