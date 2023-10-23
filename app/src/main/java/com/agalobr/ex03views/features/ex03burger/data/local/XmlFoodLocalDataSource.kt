package com.agalobr.ex03views.features.ex03burger.data.local

import android.content.Context
import com.agalobr.ex03views.app.Either
import com.agalobr.ex03views.app.ErrorApp
import com.agalobr.ex03views.app.extensions.JsonSerialization
import com.agalobr.ex03views.app.left
import com.agalobr.ex03views.app.right
import com.agalobr.ex03views.features.ex03burger.domain.Food

class XmlFoodLocalDataSource(
    private val context: Context,
    private val serialization: JsonSerialization
) {

    private val sharedPref = context.getSharedPreferences("Food", Context.MODE_PRIVATE)
    private val editor = sharedPref.edit()

    fun saveFood(food: List<Food>): Either<ErrorApp, List<Food>> {

        return try {
            food.forEach { food ->
                editor.putString(
                    food.title,
                    serialization.toJson(food, Food::class.java)
                )
            }
            editor.apply()
            food.right()
        } catch (ex: Exception) {
            ErrorApp.DatabaseErrorApp.left()
        }
    }

    fun getFood(): Either<ErrorApp, List<Food>> {
        return try {
            val mapFood = sharedPref.all as Map<String, String>
            return mapFood.values.map { jsonFood ->
                serialization.fromJson(jsonFood, Food::class.java)
            }.right()
        } catch (ex: Exception) {
            ErrorApp.DatabaseErrorApp.left()
        }
    }

    fun delete(): Either<ErrorApp, Boolean> {
        editor.clear().apply()
        return true.right()
    }
}