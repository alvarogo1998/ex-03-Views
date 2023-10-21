package com.agalobr.ex03views.features.ex03burger.data.remote

import com.agalobr.ex03views.app.Either
import com.agalobr.ex03views.app.ErrorApp
import com.agalobr.ex03views.features.ex03burger.domain.Food

interface FoodRemoteDataSource {

    fun getFood(): Either<ErrorApp, List<Food>>
}