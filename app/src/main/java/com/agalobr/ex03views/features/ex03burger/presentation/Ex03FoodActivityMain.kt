package com.agalobr.ex03views.features.ex03burger.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.agalobr.ex03views.app.extensions.GsonSerialization
import com.agalobr.ex03views.databinding.ActivityEx03burgerBinding
import com.agalobr.ex03views.features.ex03burger.data.FoodDataRepository
import com.agalobr.ex03views.features.ex03burger.data.local.XmlFoodLocalDataSource
import com.agalobr.ex03views.features.ex03burger.data.remote.api.FoodApiClient
import com.agalobr.ex03views.features.ex03burger.data.remote.api.FoodRemoteDataSource
import com.agalobr.ex03views.features.ex03burger.domain.GetFoodUseCase
import com.agalobr.ex03views.features.ex03burger.presentation.adapter.Ex03BurgerAdapter

class Ex03FoodActivityMain : AppCompatActivity() {

    private lateinit var binding: ActivityEx03burgerBinding

    private val viewModel: Ex03FoodViewModel by lazy {
        Ex03FoodViewModel(
            GetFoodUseCase(
                FoodDataRepository(
                    XmlFoodLocalDataSource(
                        this,
                        GsonSerialization()
                    ),
                    FoodRemoteDataSource(FoodApiClient())
                )
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEx03burgerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpObserver()
        viewModel.loadFood()
    }

    private fun setUpObserver() {
        val observer = Observer<Ex03FoodViewModel.UiState> {
            binding.apply {
                listburger.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = Ex03BurgerAdapter(it.food)
                }
            }
        }
        viewModel.uiState.observe(this, observer)
    }
}