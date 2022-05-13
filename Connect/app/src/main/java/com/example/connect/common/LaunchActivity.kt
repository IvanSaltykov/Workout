package com.example.connect.common

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.connect.R

class LaunchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
        Handler().postDelayed(
            {
                startActivity(Intent(this, SignInActivity::class.java))
                finish()
            }, 5000
        )
    }
}