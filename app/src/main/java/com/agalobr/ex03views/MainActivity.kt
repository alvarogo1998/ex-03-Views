package com.agalobr.ex03views

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.agalobr.ex03views.features.ex01dog.presentation.Ex01DogActivityMain
import com.agalobr.ex03views.features.ex02movie.presentation.Ex02MovieActivityMain
import com.agalobr.ex03views.features.ex03burger.presentation.Ex03FoodActivityMain
import com.agalobr.ex03views.features.ex04Lodging.presentation.Ex04LodgingActivity

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
            startActivity(Intent(this, Ex02MovieActivityMain::class.java))
        }
        findViewById<Button>(R.id.action_burgerEx03).setOnClickListener {
            startActivity(Intent(this, Ex03FoodActivityMain::class.java))
        }
        findViewById<Button>(R.id.action_lodgingEx04).setOnClickListener {
            startActivity(Intent(this, Ex04LodgingActivity::class.java))
        }
    }
}


