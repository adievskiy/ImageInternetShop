package com.example.imageinternetshop

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var logoTV: TextView
    private lateinit var logoIV: ImageView
    private lateinit var enterBTN: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        logoTV = findViewById(R.id.logoTV)
        logoIV = findViewById(R.id.logoIV)
        enterBTN = findViewById(R.id.enterBTN)

        val animMoveDown = AnimationUtils.loadAnimation(applicationContext, R.anim.move_down)
        val animMoveUp = AnimationUtils.loadAnimation(applicationContext, R.anim.move_up)

        logoTV.startAnimation(animMoveDown)
        logoIV.startAnimation(animMoveDown)
        enterBTN.startAnimation(animMoveUp)

        enterBTN.setOnClickListener {
            startActivity(Intent(this, ShopActivity::class.java))
        }
    }
}