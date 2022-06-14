package com.albertomier.klikinwaylettest.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Thread.sleep(5_000)  // wait for 1 second
        startActivity(Intent(this, MainActivity::class.java))
    }
}