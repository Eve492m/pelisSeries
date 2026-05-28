package com.example.diarioentrenamiento.repository

import com.example.diarioentrenamiento.data.PeliculasDatabaseDao
import com.example.diarioentrenamiento.models.Pelicula
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn

class PeliculasRepository @Inject constructor(
    private val peliculasDao: PeliculasDatabaseDao
) {
    suspend fun addPelicula(pelicula: Pelicula) = peliculasDao.insertarPelicula(pelicula)
    suspend fun updatePelicula(pelicula: Pelicula) = peliculasDao.actualizarPelicula(pelicula)
    suspend fun deletePelicula(pelicula: Pelicula) = peliculasDao.eliminarPelicula(pelicula)
    suspend fun deleteAll() = peliculasDao.eliminarTodasLasPeliculas()
    fun getAllPeliculas(): Flow<List<Pelicula>> =
        peliculasDao.getPeliculas().flowOn(Dispatchers.IO).conflate()
}
