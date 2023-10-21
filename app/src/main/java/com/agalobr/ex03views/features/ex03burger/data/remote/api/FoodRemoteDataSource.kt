package com.agalobr.ex03views.features.ex03burger.data.remote.api

import com.agalobr.ex03views.app.Either
import com.agalobr.ex03views.app.ErrorApp
import com.agalobr.ex03views.app.right
import com.agalobr.ex03views.features.ex03burger.data.remote.FoodRemoteDataSource
import com.agalobr.ex03views.features.ex03burger.domain.Food

class FoodRemoteDataSource(private val apiClient: FoodApiClient): FoodRemoteDataSource {
    override fun getFood(): Either<ErrorApp, List<Food>> {
        val food = apiClient.getFoodList()
        return food.map {foodApiModel->
            foodApiModel.toDomain()
        }.right()
    }
}