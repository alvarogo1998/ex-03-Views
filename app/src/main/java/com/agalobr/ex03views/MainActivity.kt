package com.agalobr.ex03views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.agalobr.ex03views.R
import com.agalobr.ex03views.features.ex01dog.presentation.Ex01DogActivityMain

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpDogView()
    }

    fun setUpDogView(){
        val dogButtom = findViewById<Button>(R.id.action_dogEx01)
        dogButtom.setOnClickListener {
            setContentView(R.layout.activity_ex01dog)
        }
    }
}


