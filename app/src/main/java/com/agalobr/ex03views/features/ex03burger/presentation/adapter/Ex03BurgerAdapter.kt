package com.agalobr.ex03views.features.ex03burger.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.agalobr.ex03views.databinding.ItemEx03burgerBinding
import com.agalobr.ex03views.features.ex03burger.domain.Food

class Ex03BurgerAdapter(private val listFood: List<Food>) :
    RecyclerView.Adapter<Ex03BurgerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Ex03BurgerViewHolder {
        val binding = ItemEx03burgerBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return Ex03BurgerViewHolder(binding)
    }

    override fun getItemCount(): Int = listFood.size

    override fun onBindViewHolder(holder: Ex03BurgerViewHolder, position: Int) =
        holder.bind(listFood[position])

}