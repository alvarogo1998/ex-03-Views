package com.agalobr.ex03views.features.ex04Lodging.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.agalobr.ex03views.app.extensions.GsonSerialization
import com.agalobr.ex03views.app.extensions.loadUrl
import com.agalobr.ex03views.databinding.ActivityEx04lodgingBinding
import com.agalobr.ex03views.features.ex04Lodging.data.DataSourceRepository
import com.agalobr.ex03views.features.ex04Lodging.data.local.XmlLodgingLocalDataSource
import com.agalobr.ex03views.features.ex04Lodging.data.remote.api.LodgingApiClient

import com.agalobr.ex03views.features.ex04Lodging.domain.GetLodgingUseCase
import com.agalobr.ex03views.features.ex04Lodging.domain.Lodging

class Ex04LodgingActivity: AppCompatActivity(){

    private lateinit var binding: ActivityEx04lodgingBinding

    private val viewModel: Ex04LodgingViewModel by lazy {
        Ex04LodgingViewModel(
            GetLodgingUseCase(DataSourceRepository(XmlLodgingLocalDataSource(this, GsonSerialization()),
                LodgingApiClient()
            ))
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityEx04lodgingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpObservers()
        viewModel.loadLodging()
    }

    private fun setUpObservers(){
        val observer = Observer<Ex04LodgingViewModel.UiState>{
            it.lodging?.apply {
                bindView(this)
            }
        }
        viewModel.uiState.observe(this, observer)
    }
    private fun bindView(lodging: Lodging){
        binding.apply {
            imageLodging.loadUrl(lodging.urlImage)
            textTitleLodging.text = lodging.title
            textDescriptionLodging.text = lodging.description
        }
    }
}