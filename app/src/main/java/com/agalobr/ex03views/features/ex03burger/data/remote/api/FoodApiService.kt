package com.agalobr.ex03views.features.ex03burger.data.remote.api

import retrofit2.Call
import retrofit2.http.GET

interface FoodApiService {

    @GET("burguer-view")
    fun getFood(): Call<List<FoodApiModel>>

}