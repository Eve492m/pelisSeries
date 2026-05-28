package com.example.diarioentrenamiento.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diarioentrenamiento.models.Pelicula
import com.example.diarioentrenamiento.repository.PeliculasRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeliculasViewModel @Inject constructor(
    private val repo: PeliculasRepository
) : ViewModel() {

    private val _listaPeliculas = MutableStateFlow<List<Pelicula>>(emptyList())
    val listaPeliculas = _listaPeliculas.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getAllPeliculas()
                .distinctUntilChanged()
                .collect { lista -> _listaPeliculas.value = lista }
        }
    }

    fun addPelicula(pelicula: Pelicula) = viewModelScope.launch { repo.addPelicula(pelicula) }
    fun updatePelicula(pelicula: Pelicula) = viewModelScope.launch { repo.updatePelicula(pelicula) }
    fun removePelicula(pelicula: Pelicula) = viewModelScope.launch { repo.deletePelicula(pelicula) }
}
