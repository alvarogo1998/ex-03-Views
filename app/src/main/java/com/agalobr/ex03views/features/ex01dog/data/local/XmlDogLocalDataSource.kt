package com.agalobr.ex03views.features.ex01dog.data.local

import android.content.Context
import com.agalobr.ex03views.app.Either
import com.agalobr.ex03views.app.ErrorApp
import com.agalobr.ex03views.app.extensions.JsonSerialization
import com.agalobr.ex03views.app.left
import com.agalobr.ex03views.app.right
import com.agalobr.ex03views.features.ex01dog.domain.Dog

class XmlDogLocalDataSource(context: Context, private val serialization: JsonSerialization) {

    private val sharedPref = context.getSharedPreferences("Dog", Context.MODE_PRIVATE)

    fun saveDog(dog: List<Dog>): Either<ErrorApp, List<Dog>> {

        return try {
            dog.forEach { dog ->
                sharedPref.edit().putString(dog.name, serialization.toJson(dog, Dog::class.java))
            }
            sharedPref.edit().apply()
            dog.right()
        } catch (ex: Exception) {
            ErrorApp.UnKonowError.left()
        }
    }

    fun getDog(): Either<ErrorApp, List<Dog>> {

        return try {
            val mapDog = sharedPref.all as Map<String, String>
            return mapDog.values.map { jsonDog ->
                serialization.fromJson(jsonDog, Dog::class.java)
            }.right()
        } catch (ex: Exception) {
            ErrorApp.UnKonowError.left()
        }
    }

    fun delete(): Either<ErrorApp, Boolean> {
        sharedPref.edit().clear().apply()
        return true.right()
    }
}