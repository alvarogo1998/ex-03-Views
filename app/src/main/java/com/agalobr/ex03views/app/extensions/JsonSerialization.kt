package com.agalobr.ex03views.app.extensions

import com.agalobr.ex03views.features.ex02movie.domain.Movie

interface JsonSerialization {

    fun <T> toJson(obj: T, typeClass: Class<T>): String

    fun <T> toJsonOut(obj: T, typeClass: Class<out Movie>): String

    fun <T> fromJson(json: String, typeClass: Class<T>): T


}