package com.agalobr.ex03views.features.ex01dog.data.local

import android.content.Context
import com.agalobr.ex03views.app.Either
import com.agalobr.ex03views.app.ErrorApp
import com.agalobr.ex03views.app.left
import com.agalobr.ex03views.app.right
import com.agalobr.ex03views.features.ex01dog.domain.Dog
import com.google.gson.Gson

class XmlDogLocalDataSource(context: Context) {

    private val sharedPref = context.getSharedPreferences("Dog", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveDog(dog: Dog): Either<ErrorApp, Dog> {
            return try {
                with(sharedPref.edit()) {
                    putString(dog.name, gson.toJson(dog)).apply()
                }
                dog.right()
            } catch (ex: Exception) {
                ErrorApp.UnKonowError.left()
            }
    }

    fun getDog(): Either<ErrorApp, List<Dog>> {

        return try {
            val mapDog = sharedPref.all as Map<String, String>
            return mapDog.values.map { jsonDog ->
                gson.fromJson(jsonDog, Dog::class.java)
            }.right()
        } catch (ex: Exception) {
            ErrorApp.UnKonowError.left()
        }
    }
}