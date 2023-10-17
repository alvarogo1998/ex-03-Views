package com.agalobr.ex03views.features.ex02movie.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.agalobr.ex03views.R
import com.agalobr.ex03views.features.ex02movie.domain.Movie

class Ex02MoviesAdapter(private val listMovies: List<Movie>): RecyclerView.Adapter<Ex02MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Ex02MoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return Ex02MoviesViewHolder(layoutInflater.inflate(R.layout.item_ex02movies, parent, false))
    }

    override fun getItemCount(): Int = listMovies.size

    override fun onBindViewHolder(holder: Ex02MoviesViewHolder, position: Int) {
        val item= listMovies[position]
        holder.render(item)
    }

}