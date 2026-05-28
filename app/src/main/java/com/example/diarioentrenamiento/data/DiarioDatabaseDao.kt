package com.example.diarioentrenamiento.data

import androidx.room.*
import com.example.diarioentrenamiento.models.Pelicula
import kotlinx.coroutines.flow.Flow

@Dao
interface PeliculasDatabaseDao {

    @Query("SELECT * FROM peliculas_tbl ORDER BY calificacion DESC")
    fun getPeliculas(): Flow<List<Pelicula>>

    @Query("SELECT * FROM peliculas_tbl WHERE id = :id")
    suspend fun getPeliculaPorId(id: String): Pelicula

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarPelicula(pelicula: Pelicula)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun actualizarPelicula(pelicula: Pelicula)

    @Delete
    suspend fun eliminarPelicula(pelicula: Pelicula)

    @Query("DELETE FROM peliculas_tbl")
    suspend fun eliminarTodasLasPeliculas()
}
