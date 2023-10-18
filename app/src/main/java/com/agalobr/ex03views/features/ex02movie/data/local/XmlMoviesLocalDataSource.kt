package com.agalobr.ex03views.features.ex02movie.data.local

import android.content.Context
import com.agalobr.ex03views.app.Either
import com.agalobr.ex03views.app.ErrorApp
import com.agalobr.ex03views.app.extensions.JsonSerialization
import com.agalobr.ex03views.app.left
import com.agalobr.ex03views.app.right
import com.agalobr.ex03views.features.ex02movie.domain.Movie

class XmlMoviesLocalDataSource(
    private val context: Context,
    private val serialization: JsonSerialization
) {

    private val sharedPref = context.getSharedPreferences("Movie", Context.MODE_PRIVATE)
    private val editor = sharedPref.edit()


    fun saveMovie(movie: List<Movie>): Either<ErrorApp, List<Movie>> {

        return try {
            movie.forEach {
                editor.putString(it.id.toString(), serialization.toJsonOut(it, it::class.java))
            }
            editor.apply()
            movie.right()
        } catch (ex: Exception) {
            ErrorApp.UnKonowError.left()
        }
    }

    fun getMovies(): Either<ErrorApp, List<Movie>> {
        return try {
            val mapMovie = sharedPref.all as Map<String, String>
            return mapMovie.values.map { jsonMovie ->
                serialization.fromJson(jsonMovie, Movie::class.java)
            }.right()
        } catch (ex: Exception) {
            ErrorApp.UnKonowError.left()
        }
    }
}