package com.example.diarioentrenamiento.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.diarioentrenamiento.models.Pelicula
import com.example.diarioentrenamiento.utils.CategoriaPeliculaConverter
import com.example.diarioentrenamiento.utils.EstadoPeliculaConverter

@Database(entities = [Pelicula::class], version = 1, exportSchema = false)
@TypeConverters(CategoriaPeliculaConverter::class, EstadoPeliculaConverter::class)
abstract class PeliculasDatabase : RoomDatabase() {
    abstract fun peliculasDao(): PeliculasDatabaseDao
}
