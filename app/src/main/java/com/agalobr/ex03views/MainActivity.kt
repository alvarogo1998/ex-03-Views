package com.agalobr.ex03views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.agalobr.ex03views.features.ex01dog.presentation.Ex01DogActivityMain
import com.agalobr.ex03views.features.ex02movie.presentation.Ex02MoviewActivityMain

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpView()
    }

    private fun setUpView() {
        findViewById<Button>(R.id.action_dogEx01).setOnClickListener {
            startActivity(Intent(this, Ex01DogActivityMain::class.java))
        }
        findViewById<Button>(R.id.action_movieEx02).setOnClickListener {
            startActivity(Intent(this, Ex02MoviewActivityMain::class.java))
        }
    }
}


