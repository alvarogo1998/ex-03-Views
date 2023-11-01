package com.agalobr.ex03views.features.ex01dog.data.remote

import com.agalobr.ex03views.features.ex01dog.data.remote.api.DogApiModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DogApiClient {

    private val apiService: DogApiService

    private val baseEndPoint = "https://dam.sitehub.es/curso-2023-2024/api/"

    init {
        apiService = buildApiEndPoint()
    }

    private fun createRetrofitClient() = Retrofit.Builder()
        .baseUrl(baseEndPoint)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private fun buildApiEndPoint() = createRetrofitClient().create(DogApiService::class.java)

    fun getDogList(): List<DogApiModel> {
        val call = apiService.getDog()
        val response = call.execute()
        if (response.isSuccessful) {
            return response.body() ?: emptyList()
        }
        return emptyList()
    }
}