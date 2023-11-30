package com.aviva.marveltestapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.aviva.marveltestapp.data.repository.SuperheroRepository
import com.aviva.marveltestapp.util.Result
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val superheroRepository: SuperheroRepository) : ViewModel() {

    val superheroes = liveData(Dispatchers.IO) {
        emit(Result.Loading) // Emite el estado de carga
        try {
            val data = superheroRepository.getSuperheroes()
            emit(Result.Success(data)) // Emite el resultado exitoso con los datos
        } catch (e: Exception) {
            emit(Result.Error(e)) // Emite un error si algo sale mal
        }
    }
}