package com.agalobr.ex03views.features.ex04Lodging.data.remote.api

import com.agalobr.ex03views.app.Either
import com.agalobr.ex03views.app.ErrorApp
import com.agalobr.ex03views.app.left
import com.agalobr.ex03views.app.right
import com.agalobr.ex03views.features.ex04Lodging.domain.Lodging
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LodgingApiClient {

    private val baseUrl = "https://dam.sitehub.es/curso-2023-2024/api/"

    suspend fun getLodging(): Either<ErrorApp, Lodging> {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(LodgingApiService::class.java)

        return try {
            val respon: Response<LogingApiModel> = service.getLodging()
            if (respon.isSuccessful) {
                respon.body()!!.toDomain().right()
            } else {
                ErrorApp.InternetErrorApp.left()
            }
        } catch (ex: Exception) {
            ErrorApp.InternetErrorApp.left()
        }
    }
}