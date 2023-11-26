package com.aviva.marveltestapp.data.model


data class ApiResponse(
    val code: Int, // El código de estado HTTP
    val status: String, // Descripción del estado de la respuesta
    val etag: String, // Un identificador único para esta respuesta
    val data: DataContainer // El contenedor de datos real que contiene los resultados
)


data class DataContainer(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Character> // Lista de personajes
)

// Modelo para un personaje de Marvel
data class Character(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Image,
    val comics: ComicList
)

data class ComicList(
    val available: Int,
    val items: List<ComicSummary>
)

data class ComicSummary(
    val name: String
)

// Modelo para las imágenes
data class Image(
    val path: String,
    val extension: String
) {
    fun getUrl(): String = "$path.$extension"
}