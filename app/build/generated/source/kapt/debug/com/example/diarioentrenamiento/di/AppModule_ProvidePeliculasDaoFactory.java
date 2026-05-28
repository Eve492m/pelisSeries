package com.example.diarioentrenamiento.di;

import com.example.diarioentrenamiento.data.PeliculasDatabase;
import com.example.diarioentrenamiento.data.PeliculasDatabaseDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
public final class AppModule_ProvidePeliculasDaoFactory implements Factory<PeliculasDatabaseDao> {
  private final Provider<PeliculasDatabase> dbProvider;

  private AppModule_ProvidePeliculasDaoFactory(Provider<PeliculasDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public PeliculasDatabaseDao get() {
    return providePeliculasDao(dbProvider.get());
  }

  public static AppModule_ProvidePeliculasDaoFactory create(
      Provider<PeliculasDatabase> dbProvider) {
    return new AppModule_ProvidePeliculasDaoFactory(dbProvider);
  }

  public static PeliculasDatabaseDao providePeliculasDao(PeliculasDatabase db) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.providePeliculasDao(db));
  }
}
