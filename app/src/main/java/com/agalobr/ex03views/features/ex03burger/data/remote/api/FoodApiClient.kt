package com.agalobr.ex03views.features.ex03burger.data.remote.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class FoodApiClient {

    private val apiService: FoodApiService

    private val baseEndPoint: String = "https://dam.sitehub.es/curso-2023-2024/api/"

    init {
        apiService = buildApiEndPoint()
    }

    private fun createRetrofitClient() = Retrofit.Builder()
        .baseUrl(baseEndPoint)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private fun buildApiEndPoint() = createRetrofitClient().create(FoodApiService::class.java)

    fun getFoodList(): List<FoodApiModel> {
        val call = apiService.getFood()
        val response = call.execute()
        if (response.isSuccessful) {
            return response.body() ?: emptyList()
        }
        return emptyList()
    }
}