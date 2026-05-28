package com.example.diarioentrenamiento.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "peliculas_tbl")
data class Pelicula(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    val titulo: String,
    val categoria: CategoriaPelicula,
    val calificacion: Float,           // 0.0 - 10.0 puntuación del usuario
    val fechaEstreno: String,          // Año o fecha de estreno / emisión
    val ultimaVez: String,             // Última vez que la viste
    val genero: String,                // Acción, Drama, Comedia, etc.
    val plataforma: String,            // Netflix, Disney+, HBO, etc.
    val estado: EstadoPelicula,        // Vista, Pendiente, En progreso
    val comentario: String,            // Reseña corta personal
    val imagenUri: String = ""         // URI local de la imagen del póster
)

enum class CategoriaPelicula {
    PELICULA,
    SERIE
}

enum class EstadoPelicula {
    VISTA,
    PENDIENTE,
    EN_PROGRESO
}
