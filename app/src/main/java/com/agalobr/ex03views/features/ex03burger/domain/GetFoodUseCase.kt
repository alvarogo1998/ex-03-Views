package com.agalobr.ex03views.features.ex03burger.domain

import com.agalobr.ex03views.app.Either
import com.agalobr.ex03views.app.ErrorApp

class GetFoodUseCase(private val repository: FoodRepository) {

    operator fun invoke(): Either<ErrorApp, List<Food>> = repository.get()
}