package com.agalobr.ex03views.features.ex04Lodging.data.local

import android.content.Context
import com.agalobr.ex03views.app.Either
import com.agalobr.ex03views.app.ErrorApp
import com.agalobr.ex03views.app.extensions.JsonSerialization
import com.agalobr.ex03views.app.left
import com.agalobr.ex03views.app.right
import com.agalobr.ex03views.features.ex04Lodging.domain.Lodging

class XmlLodgingLocalDataSource(context: Context, private val serialization: JsonSerialization) {

    private val sharedPref = context.getSharedPreferences("Lodging", Context.MODE_PRIVATE)

    fun saveLodging(lodging: Lodging): Either<ErrorApp, Lodging> {
        return try {
            sharedPref.edit()
                .putString(lodging.title, serialization.toJson(lodging, Lodging::class.java))
                .apply()
            lodging.right()
        } catch (ex: Exception) {
            ErrorApp.DatabaseErrorApp.left()
        }
    }

    fun getLodging(): Either<ErrorApp, Lodging> {
        val jsonLodging = sharedPref.getString("Alojamiento", null)
        jsonLodging?.let{
            return serialization.fromJson(it, Lodging::class.java).right()
        }
        return ErrorApp.DatabaseErrorApp.left()
    }

    fun delete(): Either<ErrorApp, Boolean>{
        sharedPref.edit().clear().apply()
        return true.right()
    }
}