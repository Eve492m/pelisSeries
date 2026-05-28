package com.example.diarioentrenamiento

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.diarioentrenamiento.ui.screens.PeliculasScreen
import com.example.diarioentrenamiento.ui.viewmodel.PeliculasViewModel
import com.example.diarioentrenamiento.ui.theme.DiarioEntrenamientoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val peliculasViewModel: PeliculasViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiarioEntrenamientoTheme {
                val listaPeliculas by peliculasViewModel.listaPeliculas.collectAsState()

                PeliculasScreen(
                    peliculas = listaPeliculas,
                    onAddPelicula = { peliculasViewModel.addPelicula(it) },
                    onUpdatePelicula = { peliculasViewModel.updatePelicula(it) },
                    onRemovePelicula = { peliculasViewModel.removePelicula(it) }
                )
            }
        }
    }
}
