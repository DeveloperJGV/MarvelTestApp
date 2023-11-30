package com.aviva.marveltestapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aviva.marveltestapp.data.repository.SuperheroRepository

class MainViewModelFactory(private val superheroRepository: SuperheroRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(superheroRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}