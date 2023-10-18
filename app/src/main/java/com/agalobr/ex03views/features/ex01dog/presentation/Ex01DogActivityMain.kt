package com.agalobr.ex03views.features.ex01dog.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.agalobr.ex03views.R
import com.agalobr.ex03views.app.extensions.loadUrl
import com.agalobr.ex03views.databinding.ActivityEx01dogBinding
import com.agalobr.ex03views.features.ex01dog.data.DogDataRepository
import com.agalobr.ex03views.features.ex01dog.data.local.XmlDogLocalDataSource
import com.agalobr.ex03views.features.ex01dog.domain.Dog
import com.agalobr.ex03views.features.ex01dog.domain.GetDogUseCase
import com.agalobr.ex03views.features.ex01dog.domain.SaveDogUseCase

class Ex01DogActivityMain : AppCompatActivity() {

    private val viewModel: Ex01DogViewModel by lazy {
        Ex01DogViewModel(
            GetDogUseCase(DogDataRepository(XmlDogLocalDataSource(this))),
            SaveDogUseCase(DogDataRepository(XmlDogLocalDataSource(this)))
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ex01dog)
        setUpDogView()
        setUpObserver()
    }

    private fun setUpDogView() {
        val dog1 = Dog(
            "Akai",
            "Una gran dama",
            "Female",
            "02-06-2020",
            "https://humanidades.com/wp-content/uploads/2017/02/perro-3-e1561679226953.jpg"
        )
        viewModel.save(dog1)
        viewModel.get()
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