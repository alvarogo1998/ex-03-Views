package com.agalobr.ex03views.features.ex01dog.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.agalobr.ex03views.R
import com.agalobr.ex03views.app.extensions.GsonSerialization
import com.agalobr.ex03views.app.extensions.loadUrl
import com.agalobr.ex03views.databinding.ActivityEx01dogBinding
import com.agalobr.ex03views.features.ex01dog.data.DogDataRepository
import com.agalobr.ex03views.features.ex01dog.data.local.XmlDogLocalDataSource
import com.agalobr.ex03views.features.ex01dog.data.remote.api.DogApiClient
import com.agalobr.ex03views.features.ex01dog.data.remote.api.DogRemoteDataSource
import com.agalobr.ex03views.features.ex01dog.domain.Dog
import com.agalobr.ex03views.features.ex01dog.domain.GetDogUseCase

class Ex01DogActivityMain : AppCompatActivity() {

    private val viewModel: Ex01DogViewModel by lazy {
        Ex01DogViewModel(
            GetDogUseCase(
                DogDataRepository(
                    XmlDogLocalDataSource(this, GsonSerialization()),
                    DogRemoteDataSource(
                        DogApiClient()
                    )
                )
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ex01dog)
        setUpObserver()
        viewModel.loadDog()
    }

    private fun setUpObserver() {
        val observer = Observer<Ex01DogViewModel.Uistate> {
            it.dog?.apply {
                bindView(this)
            }
        }
        viewModel.uiState.observe(this, observer)
    }

    private fun bindView(dog: Dog) {
        val binding = ActivityEx01dogBinding.inflate(layoutInflater).apply {
            textName.text = dog.name
            dogimage.loadUrl(dog.image)
            textDescription.text = dog.description
            textGender.text = dog.gender
            textDate.text = dog.date
        }
        val view = binding.root
        setContentView(view)
    }
}