package com.agalobr.ex03views.features.ex04Lodging.data.remote.api

import retrofit2.Response
import retrofit2.http.GET

interface LodgingApiService {

    @GET("huellas-view.json")
    suspend fun getLodging(): Response<LogingApiModel>
}