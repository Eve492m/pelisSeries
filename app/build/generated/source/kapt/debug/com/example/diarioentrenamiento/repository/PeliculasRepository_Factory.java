package com.example.diarioentrenamiento.repository;

import com.example.diarioentrenamiento.data.PeliculasDatabaseDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class PeliculasRepository_Factory implements Factory<PeliculasRepository> {
  private final Provider<PeliculasDatabaseDao> peliculasDaoProvider;

  private PeliculasRepository_Factory(Provider<PeliculasDatabaseDao> peliculasDaoProvider) {
    this.peliculasDaoProvider = peliculasDaoProvider;
  }

  @Override
  public PeliculasRepository get() {
    return newInstance(peliculasDaoProvider.get());
  }

  public static PeliculasRepository_Factory create(
      Provider<PeliculasDatabaseDao> peliculasDaoProvider) {
    return new PeliculasRepository_Factory(peliculasDaoProvider);
  }

  public static PeliculasRepository newInstance(PeliculasDatabaseDao peliculasDao) {
    return new PeliculasRepository(peliculasDao);
  }
}
