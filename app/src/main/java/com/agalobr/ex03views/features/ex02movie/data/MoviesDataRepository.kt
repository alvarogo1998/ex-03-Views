package com.agalobr.ex03views.features.ex02movie.data

import com.agalobr.ex03views.app.Either
import com.agalobr.ex03views.app.ErrorApp
import com.agalobr.ex03views.features.ex02movie.data.local.XmlMoviesLocalDataSource
import com.agalobr.ex03views.features.ex02movie.data.remote.ApiMoviesRemoteSource
import com.agalobr.ex03views.features.ex02movie.domain.Movie
import com.agalobr.ex03views.features.ex02movie.domain.MovieRepository

class MoviesDataRepository(
    private val localDataSource: XmlMoviesLocalDataSource,
    private val remoteDataSource: List<Movie> = ApiMoviesRemoteSource.moviesList
) : MovieRepository {

    override fun get(): Either<ErrorApp, List<Movie>> {
        val list = localDataSource.getMovies()
        return if (list.isLeft() || list.get().isEmpty()) {
            localDataSource.saveMovie(remoteDataSource)
        } else {
            list
        }
    }
}