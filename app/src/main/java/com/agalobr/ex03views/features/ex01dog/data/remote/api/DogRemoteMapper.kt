package com.agalobr.ex03views.features.ex01dog.data.remote.api

import com.agalobr.ex03views.features.ex01dog.domain.Dog

fun DogApiModel.toDomain(): Dog {
    return Dog(
        this.name,
        this.description,
        this.gender,
        this.date,
        this.image
    )
}

