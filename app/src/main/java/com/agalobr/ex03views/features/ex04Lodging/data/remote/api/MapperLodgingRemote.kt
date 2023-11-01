package com.agalobr.ex03views.features.ex04Lodging.data.remote.api

import com.agalobr.ex03views.features.ex04Lodging.domain.Lodging

fun LogingApiModel.toDomain(): Lodging =
    Lodging(
        this.title, this.description, this.urlImage
    )