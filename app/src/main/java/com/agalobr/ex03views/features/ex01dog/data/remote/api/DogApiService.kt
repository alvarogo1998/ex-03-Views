package com.agalobr.ex03views.features.ex01dog.data.remote.api

import retrofit2.Call
import retrofit2.http.GET

interface DogApiService {
    @GET("huellas-view")
    fun getDog(): Call<List<DogApiModel>>

}