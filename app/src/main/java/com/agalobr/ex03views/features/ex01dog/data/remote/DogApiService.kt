package com.agalobr.ex03views.features.ex01dog.data.remote

import com.agalobr.ex03views.features.ex01dog.data.remote.api.DogApiModel
import retrofit2.Call
import retrofit2.http.GET

interface DogApiService {
    @GET("huellas-view.json")
    fun getDog(): Call<List<DogApiModel>>

}