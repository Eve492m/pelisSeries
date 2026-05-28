package com.example.diarioentrenamiento.utils

import androidx.room.TypeConverter
import com.example.diarioentrenamiento.models.CategoriaPelicula
import com.example.diarioentrenamiento.models.EstadoPelicula

class CategoriaPeliculaConverter {
    @TypeConverter
    fun fromCategoria(cat: CategoriaPelicula): String = cat.name

    @TypeConverter
    fun toCategoria(value: String): CategoriaPelicula = CategoriaPelicula.valueOf(value)
}

class EstadoPeliculaConverter {
    @TypeConverter
    fun fromEstado(est: EstadoPelicula): String = est.name

    @TypeConverter
    fun toEstado(value: String): EstadoPelicula = EstadoPelicula.valueOf(value)
}
