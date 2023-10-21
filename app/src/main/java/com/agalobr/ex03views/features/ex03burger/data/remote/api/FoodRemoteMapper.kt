package com.agalobr.ex03views.features.ex03burger.data.remote.api

import com.agalobr.ex03views.features.ex03burger.domain.Food

fun FoodApiModel.toDomain(): Food{
    return Food(
        this.urlImage,
        this.rate,
        this.eta,
        this.discount,
        this.title
    )
}