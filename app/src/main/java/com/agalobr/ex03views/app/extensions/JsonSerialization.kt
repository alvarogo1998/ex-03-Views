package com.agalobr.ex03views.app.extensions

interface JsonSerialization {

    fun <T> toJson(obj: T, typeClass: Class<T>): String

    fun <T> fromJson(json: String, typeClass: Class<T>): T

}