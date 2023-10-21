package com.agalobr.ex03views.features.ex03burger.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.agalobr.ex03views.app.extensions.loadUrl
import com.agalobr.ex03views.databinding.ItemEx03burgerBinding
import com.agalobr.ex03views.features.ex03burger.domain.Food

class Ex03BurgerViewHolder(binding: ItemEx03burgerBinding) : RecyclerView.ViewHolder(binding.root) {

    private val binding = ItemEx03burgerBinding.bind(binding.root)

    fun bind(food: Food) {
        binding.apply {
            imageBurger.loadUrl(food.imageFood)
            burgerTextDiscount.text = food.discount
            burgerPercentageLike.text = food.rate
            burgerTime.text = food.eta
        }
    }

}