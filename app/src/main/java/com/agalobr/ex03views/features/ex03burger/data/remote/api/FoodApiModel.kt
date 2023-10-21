package com.agalobr.ex03views.features.ex03burger.data.remote.api

import com.google.gson.annotations.SerializedName

data class FoodApiModel (

    @SerializedName("title") val title: String,
    @SerializedName("discount") val discount: String,
    @SerializedName("rate") val rate: String,
    @SerializedName("eta") val eta: String,
    @SerializedName("url_image") val urlImage: String
)