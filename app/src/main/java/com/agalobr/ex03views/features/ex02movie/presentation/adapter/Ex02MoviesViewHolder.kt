package com.agalobr.ex03views.features.ex02movie.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.agalobr.ex03views.app.extensions.loadUrl
import com.agalobr.ex03views.databinding.ItemEx02moviesBinding
import com.agalobr.ex03views.features.ex02movie.domain.Movie

class Ex02MoviesViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val binding = ItemEx02moviesBinding.bind(view)

    fun render(movie: Movie){
        binding.apply {
            imageMovie.loadUrl(movie.cover)
            movieTextName.text = movie.name
            moviePegi.text = movie.pegi
            movieDataWeight.text = movie.dataWeight
            movieDuration.text = movie.duration
        }
    }
}