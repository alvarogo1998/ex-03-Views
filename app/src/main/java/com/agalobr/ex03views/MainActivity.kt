package com.agalobr.ex03views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.agalobr.ex03views.databinding.ActivityEx01dogBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpDogView()
    }

    fun setUpDogView() {
        val binding = ActivityEx01dogBinding.inflate(layoutInflater)
        val view = binding.root
        val dogButtom = findViewById<Button>(R.id.action_dogEx01)
        dogButtom.setOnClickListener {
            setContentView(view)
        }
    }
}


