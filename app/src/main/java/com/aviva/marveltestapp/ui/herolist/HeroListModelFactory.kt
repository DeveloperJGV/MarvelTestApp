package com.aviva.marveltestapp.ui.herolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aviva.marveltestapp.data.repository.SuperheroRepository

class HeroListModelFactory(private val superheroRepository: SuperheroRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HeroListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HeroListViewModel(superheroRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}