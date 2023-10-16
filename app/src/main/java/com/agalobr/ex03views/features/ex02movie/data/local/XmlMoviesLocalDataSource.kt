package com.agalobr.ex03views.features.ex02movie.data.local

import android.content.Context
import com.agalobr.ex03views.app.Either
import com.agalobr.ex03views.app.ErrorApp
import com.agalobr.ex03views.app.left
import com.agalobr.ex03views.app.right
import com.agalobr.ex03views.features.ex02movie.domain.Movie
import com.google.gson.Gson

class XmlMoviesLocalDataSource(context: Context) {

    private val sharedPref = context.getSharedPreferences("Movie", Context.MODE_PRIVATE)
    private val editor = sharedPref.edit()
    private val gson = Gson()

    fun saveMovie(movie: List<Movie>): Either<ErrorApp, List<Movie>> {

        return try {
            movie.forEach { Movie ->
                editor.putString(Movie.id.toString(), gson.toJson(Movie))
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
                gson.fromJson(jsonMovie, Movie::class.java)
            }.right()
        } catch (ex: Exception) {
            ErrorApp.UnKonowError.left()
        }
    }
}