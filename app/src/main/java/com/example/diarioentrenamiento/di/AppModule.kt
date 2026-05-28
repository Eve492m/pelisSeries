package com.example.diarioentrenamiento.di

import android.content.Context
import androidx.room.Room
import com.example.diarioentrenamiento.data.PeliculasDatabase
import com.example.diarioentrenamiento.data.PeliculasDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): PeliculasDatabase =
        Room.databaseBuilder(
            context,
            PeliculasDatabase::class.java,
            "peliculas_db"
        )
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun providePeliculasDao(db: PeliculasDatabase): PeliculasDatabaseDao =
        db.peliculasDao()
}
