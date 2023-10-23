package com.agalobr.ex03views.features.ex03burger.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.agalobr.ex03views.R
import com.agalobr.ex03views.app.ErrorApp
import com.agalobr.ex03views.app.extensions.GsonSerialization
import com.agalobr.ex03views.databinding.ActivityEx03burgerBinding
import com.agalobr.ex03views.features.ex03burger.data.FoodDataRepository
import com.agalobr.ex03views.features.ex03burger.data.local.XmlFoodLocalDataSource
import com.agalobr.ex03views.features.ex03burger.data.remote.api.FoodApiClient
import com.agalobr.ex03views.features.ex03burger.data.remote.api.FoodRemoteDataSource
import com.agalobr.ex03views.features.ex03burger.domain.GetFoodUseCase
import com.agalobr.ex03views.features.ex03burger.presentation.adapter.Ex03BurgerAdapter
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.google.android.material.snackbar.Snackbar

class Ex03FoodActivityMain : AppCompatActivity() {

    private lateinit var binding: ActivityEx03burgerBinding

    private val skeleton: Skeleton by lazy {
        binding.listburger.applySkeleton(R.layout.item_ex03burger, 5)
    }

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
        val observer = Observer<Ex03FoodViewModel.UiState> { uiStateFood ->
            if (uiStateFood.isLoading) {
                skeleton.showSkeleton()
            } else {
                skeleton.showOriginal()
                if (uiStateFood.errorApp != null) {
                    showError(uiStateFood.errorApp)
                } else {
                    binding.apply {
                        listburger.apply {
                            layoutManager = LinearLayoutManager(context)
                            adapter = Ex03BurgerAdapter(uiStateFood.food)
                        }
                    }
                }
            }
        }
        viewModel.uiState.observe(this, observer)
    }

    private fun showError(error: ErrorApp) {
        when (error) {
            ErrorApp.DatabaseErrorApp -> errorDatabase()
            ErrorApp.InternetErrorApp -> errorInternet()
            ErrorApp.UnKnownError -> errorUnknown()
        }
    }

    private fun errorUnknown() = Snackbar.make(binding.root, "Unknown Error", Snackbar.LENGTH_LONG)
    private fun errorDatabase() = Snackbar.make(binding.root, "DataBase Error", Snackbar.LENGTH_LONG)
    private fun errorInternet() = Snackbar.make(binding.root, "Internet Error", Snackbar.LENGTH_LONG)
}