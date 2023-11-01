package com.agalobr.ex03views.features.ex04Lodging.data.remote.api

import com.google.gson.annotations.SerializedName

data class LogingApiModel (
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("url_image") val urlImage: String
)