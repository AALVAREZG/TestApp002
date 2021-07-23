package com.example.android.testapp002.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.testapp002.R
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.i("LOG - Main Activity content setting")
        setContentView(R.layout.activity_main)

    }
}