package com.agalobr.ex03views.features.ex01dog.data.remote.api

import com.google.gson.annotations.SerializedName

data class DogApiModel(
    @SerializedName("name") val name: String,
    @SerializedName("short_description") val description: String,
    @SerializedName("sex") val gender: String,
    @SerializedName("date_birth") val date: String,
    @SerializedName("url_image") val image: String
)