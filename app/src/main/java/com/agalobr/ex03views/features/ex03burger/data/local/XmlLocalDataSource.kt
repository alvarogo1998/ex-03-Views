package com.agalobr.ex03views.features.ex03burger.data.local

import android.content.Context
import com.agalobr.ex03views.app.Either
import com.agalobr.ex03views.app.ErrorApp
import com.agalobr.ex03views.app.left
import com.agalobr.ex03views.app.right
import com.agalobr.ex03views.features.ex03burger.domain.Food
import com.google.gson.Gson

class XmlLocalDataSource(context: Context) {

    private val sharedPref = context.getSharedPreferences("Food", Context.MODE_PRIVATE)
    private val editor = sharedPref.edit()
    private val gson = Gson()

    fun save(food: List<Food>):Either<ErrorApp, List<Food>>{

        return try {
            food.forEach {food->
                editor.putString(food.id.toString(), gson.toJson(food))
            }
            editor.apply()
            food.right()
        }catch (ex: Exception){
            ErrorApp.UnKonowError.left()
        }
    }


}