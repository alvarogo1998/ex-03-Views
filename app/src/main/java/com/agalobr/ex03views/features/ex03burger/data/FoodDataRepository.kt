package com.agalobr.ex03views.features.ex03burger.data

import com.agalobr.ex03views.app.Either
import com.agalobr.ex03views.app.ErrorApp
import com.agalobr.ex03views.features.ex03burger.data.local.XmlFoodLocalDataSource
import com.agalobr.ex03views.features.ex03burger.data.remote.api.FoodRemoteDataSource
import com.agalobr.ex03views.features.ex03burger.domain.Food
import com.agalobr.ex03views.features.ex03burger.domain.FoodRepository

class FoodDataRepository(
    private val localDataSource: XmlFoodLocalDataSource,
    private val remoteDataSource: FoodRemoteDataSource
) : FoodRepository {

    override fun get(): Either<ErrorApp, List<Food>> {
        val list = localDataSource.getFood()
        return if (list.isLeft() || list.get().isEmpty()) {
            remoteDataSource.getFood().map {foodFromRemote->
                localDataSource.delete()
                localDataSource.saveFood(foodFromRemote)
                foodFromRemote
            }
        } else {
            list
        }
    }
}