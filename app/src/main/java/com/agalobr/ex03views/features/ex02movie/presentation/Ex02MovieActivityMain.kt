package com.agalobr.ex03views.features.ex02movie.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.agalobr.ex03views.databinding.ActivityEx02moviesBinding
import com.agalobr.ex03views.features.ex02movie.data.MoviesDataRepository
import com.agalobr.ex03views.features.ex02movie.data.local.XmlMoviesLocalDataSource
import com.agalobr.ex03views.features.ex02movie.data.remote.ApiMoviesRemoteSource
import com.agalobr.ex03views.features.ex02movie.domain.GetMoviesUseCase
import com.agalobr.ex03views.features.ex02movie.presentation.adapter.Ex02MoviesAdapter

class Ex02MovieActivityMain : AppCompatActivity() {

    private val viewModel: Ex02MovieViewModel by lazy {
        Ex02MovieViewModel(
            GetMoviesUseCase(
                MoviesDataRepository(
                    XmlMoviesLocalDataSource(this),
                    ApiMoviesRemoteSource.moviesList
                )
            )
        )
    }

    private lateinit var binding: ActivityEx02moviesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEx02moviesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpObserver()
        viewModel.getMovie()
    }

    private fun setUpObserver() {
        val observer = Observer<Ex02MovieViewModel.UiState> {
            binding.apply {
                listMovies.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = Ex02MoviesAdapter(it.movie)
                }
            }
        }
        viewModel.uiState.observe(this, observer)
    }
}