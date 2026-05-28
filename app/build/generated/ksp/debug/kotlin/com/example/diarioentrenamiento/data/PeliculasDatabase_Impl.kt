package com.example.diarioentrenamiento.`data`

import androidx.room.InvalidationTracker
import androidx.room.RoomOpenDelegate
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.room.util.TableInfo
import androidx.room.util.TableInfo.Companion.read
import androidx.room.util.dropFtsSyncTriggers
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import javax.`annotation`.processing.Generated
import kotlin.Lazy
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.MutableSet
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class PeliculasDatabase_Impl : PeliculasDatabase() {
  private val _peliculasDatabaseDao: Lazy<PeliculasDatabaseDao> = lazy {
    PeliculasDatabaseDao_Impl(this)
  }

  protected override fun createOpenDelegate(): RoomOpenDelegate {
    val _openDelegate: RoomOpenDelegate = object : RoomOpenDelegate(1, "ad98f55e7ec7911e37b454fa6f29b1fe", "c5f92d6caf4c72198d40c08fbd662ddf") {
      public override fun createAllTables(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS `peliculas_tbl` (`id` BLOB NOT NULL, `titulo` TEXT NOT NULL, `categoria` TEXT NOT NULL, `calificacion` REAL NOT NULL, `fechaEstreno` TEXT NOT NULL, `ultimaVez` TEXT NOT NULL, `genero` TEXT NOT NULL, `plataforma` TEXT NOT NULL, `estado` TEXT NOT NULL, `comentario` TEXT NOT NULL, `imagenUri` TEXT NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        connection.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'ad98f55e7ec7911e37b454fa6f29b1fe')")
      }

      public override fun dropAllTables(connection: SQLiteConnection) {
        connection.execSQL("DROP TABLE IF EXISTS `peliculas_tbl`")
      }

      public override fun onCreate(connection: SQLiteConnection) {
      }

      public override fun onOpen(connection: SQLiteConnection) {
        internalInitInvalidationTracker(connection)
      }

      public override fun onPreMigrate(connection: SQLiteConnection) {
        dropFtsSyncTriggers(connection)
      }

      public override fun onPostMigrate(connection: SQLiteConnection) {
      }

      public override fun onValidateSchema(connection: SQLiteConnection): RoomOpenDelegate.ValidationResult {
        val _columnsPeliculasTbl: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsPeliculasTbl.put("id", TableInfo.Column("id", "BLOB", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPeliculasTbl.put("titulo", TableInfo.Column("titulo", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPeliculasTbl.put("categoria", TableInfo.Column("categoria", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPeliculasTbl.put("calificacion", TableInfo.Column("calificacion", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPeliculasTbl.put("fechaEstreno", TableInfo.Column("fechaEstreno", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPeliculasTbl.put("ultimaVez", TableInfo.Column("ultimaVez", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPeliculasTbl.put("genero", TableInfo.Column("genero", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPeliculasTbl.put("plataforma", TableInfo.Column("plataforma", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPeliculasTbl.put("estado", TableInfo.Column("estado", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPeliculasTbl.put("comentario", TableInfo.Column("comentario", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPeliculasTbl.put("imagenUri", TableInfo.Column("imagenUri", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysPeliculasTbl: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesPeliculasTbl: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoPeliculasTbl: TableInfo = TableInfo("peliculas_tbl", _columnsPeliculasTbl, _foreignKeysPeliculasTbl, _indicesPeliculasTbl)
        val _existingPeliculasTbl: TableInfo = read(connection, "peliculas_tbl")
        if (!_infoPeliculasTbl.equals(_existingPeliculasTbl)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |peliculas_tbl(com.example.diarioentrenamiento.models.Pelicula).
              | Expected:
              |""".trimMargin() + _infoPeliculasTbl + """
              |
              | Found:
              |""".trimMargin() + _existingPeliculasTbl)
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }
    }
    return _openDelegate
  }

  protected override fun createInvalidationTracker(): InvalidationTracker {
    val _shadowTablesMap: MutableMap<String, String> = mutableMapOf()
    val _viewTables: MutableMap<String, Set<String>> = mutableMapOf()
    return InvalidationTracker(this, _shadowTablesMap, _viewTables, "peliculas_tbl")
  }

  public override fun clearAllTables() {
    super.performClear(false, "peliculas_tbl")
  }

  protected override fun getRequiredTypeConverterClasses(): Map<KClass<*>, List<KClass<*>>> {
    val _typeConvertersMap: MutableMap<KClass<*>, List<KClass<*>>> = mutableMapOf()
    _typeConvertersMap.put(PeliculasDatabaseDao::class, PeliculasDatabaseDao_Impl.getRequiredConverters())
    return _typeConvertersMap
  }

  public override fun getRequiredAutoMigrationSpecClasses(): Set<KClass<out AutoMigrationSpec>> {
    val _autoMigrationSpecsSet: MutableSet<KClass<out AutoMigrationSpec>> = mutableSetOf()
    return _autoMigrationSpecsSet
  }

  public override fun createAutoMigrations(autoMigrationSpecs: Map<KClass<out AutoMigrationSpec>, AutoMigrationSpec>): List<Migration> {
    val _autoMigrations: MutableList<Migration> = mutableListOf()
    return _autoMigrations
  }

  public override fun peliculasDao(): PeliculasDatabaseDao = _peliculasDatabaseDao.value
}
