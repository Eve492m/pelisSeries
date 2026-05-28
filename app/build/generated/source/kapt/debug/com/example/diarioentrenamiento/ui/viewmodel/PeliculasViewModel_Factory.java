package com.example.diarioentrenamiento.ui.viewmodel;

import com.example.diarioentrenamiento.repository.PeliculasRepository;
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
public final class PeliculasViewModel_Factory implements Factory<PeliculasViewModel> {
  private final Provider<PeliculasRepository> repoProvider;

  private PeliculasViewModel_Factory(Provider<PeliculasRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public PeliculasViewModel get() {
    return newInstance(repoProvider.get());
  }

  public static PeliculasViewModel_Factory create(Provider<PeliculasRepository> repoProvider) {
    return new PeliculasViewModel_Factory(repoProvider);
  }

  public static PeliculasViewModel newInstance(PeliculasRepository repo) {
    return new PeliculasViewModel(repo);
  }
}
