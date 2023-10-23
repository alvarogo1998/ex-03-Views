package com.agalobr.ex03views.features.ex01dog.data.remote.api

import retrofit2.Response
import retrofit2.http.GET

interface DogApiService {
    @GET("huellas-view.json")
    fun getDog(): Response<List<DogApiModel>>

}