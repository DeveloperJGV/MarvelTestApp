package com.aviva.marveltestapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aviva.marveltestapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // No se necesita más lógica aquí, ya que MainFragment manejará la UI
    }
}
