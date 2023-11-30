package com.aviva.marveltestapp.data.repository

import com.aviva.marveltestapp.data.api.MarvelApiService
import com.aviva.marveltestapp.util.generateMarvelHash
import com.aviva.marveltestapp.data.model.Character
import com.aviva.marveltestapp.BuildConfig



class SuperheroRepository(private val apiService: MarvelApiService) {

    suspend fun getSuperheroes(): List<Character> {
        val timestamp = System.currentTimeMillis().toString()
        val hash = generateMarvelHash(timestamp, BuildConfig.MARVEL_API_KEY_PUBLIC, BuildConfig.MARVEL_API_KEY_PRIVATE)
        val response = apiService.getCharacters(BuildConfig.MARVEL_API_KEY_PUBLIC, timestamp, hash)
        if (response.code == 200) {
            return response.data.results
        }
        // Manejar los casos en que la respuesta no sea 200 OK
        throw Exception("Error fetching superheroes: ${response.status}")
    }

    suspend fun getCharacterDetails(characterId: Int): Character {
        val timestamp = System.currentTimeMillis().toString()
        val hash = generateMarvelHash(timestamp, BuildConfig.MARVEL_API_KEY_PUBLIC, BuildConfig.MARVEL_API_KEY_PRIVATE)
        val response = apiService.getCharacterDetails(characterId, BuildConfig.MARVEL_API_KEY_PUBLIC, timestamp, hash)
        if (response.code == 200) {
            return response.data.results[0]
        }
        // Manejar los casos en que la respuesta no sea 200 OK
        throw Exception("Error fetching character details: ${response.status}")
    }
}
