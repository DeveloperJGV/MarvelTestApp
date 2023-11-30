package com.aviva.marveltestapp.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.aviva.marveltestapp.data.repository.SuperheroRepository
import kotlinx.coroutines.Dispatchers
import com.aviva.marveltestapp.util.Result


class HeroDetailViewModel(private val superheroRepository: SuperheroRepository, private val characterId: Int) : ViewModel() {

    val characterDetails = liveData(Dispatchers.IO) {
        emit(Result.Loading) // Emite el estado de carga
        try {
            val data = superheroRepository.getCharacterDetails(characterId)

            emit(Result.Success(data)) // Emite el resultado exitoso con los datos
        } catch (e: Exception) {
            emit(Result.Error(e)) // Emite un error si algo sale mal
        }
    }
}
