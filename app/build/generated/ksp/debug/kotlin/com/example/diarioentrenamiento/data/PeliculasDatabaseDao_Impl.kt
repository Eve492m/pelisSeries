package com.example.diarioentrenamiento.`data`

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.convertByteToUUID
import androidx.room.util.convertUUIDToByte
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.example.diarioentrenamiento.models.CategoriaPelicula
import com.example.diarioentrenamiento.models.EstadoPelicula
import com.example.diarioentrenamiento.models.Pelicula
import com.example.diarioentrenamiento.utils.CategoriaPeliculaConverter
import com.example.diarioentrenamiento.utils.EstadoPeliculaConverter
import java.util.UUID
import javax.`annotation`.processing.Generated
import kotlin.Float
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class PeliculasDatabaseDao_Impl(
  __db: RoomDatabase,
) : PeliculasDatabaseDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfPelicula: EntityInsertAdapter<Pelicula>

  private val __categoriaPeliculaConverter: CategoriaPeliculaConverter =
      CategoriaPeliculaConverter()

  private val __estadoPeliculaConverter: EstadoPeliculaConverter = EstadoPeliculaConverter()

  private val __deleteAdapterOfPelicula: EntityDeleteOrUpdateAdapter<Pelicula>

  private val __updateAdapterOfPelicula: EntityDeleteOrUpdateAdapter<Pelicula>
  init {
    this.__db = __db
    this.__insertAdapterOfPelicula = object : EntityInsertAdapter<Pelicula>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `peliculas_tbl` (`id`,`titulo`,`categoria`,`calificacion`,`fechaEstreno`,`ultimaVez`,`genero`,`plataforma`,`estado`,`comentario`,`imagenUri`) VALUES (?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: Pelicula) {
        statement.bindBlob(1, convertUUIDToByte(entity.id))
        statement.bindText(2, entity.titulo)
        val _tmp: String = __categoriaPeliculaConverter.fromCategoria(entity.categoria)
        statement.bindText(3, _tmp)
        statement.bindDouble(4, entity.calificacion.toDouble())
        statement.bindText(5, entity.fechaEstreno)
        statement.bindText(6, entity.ultimaVez)
        statement.bindText(7, entity.genero)
        statement.bindText(8, entity.plataforma)
        val _tmp_1: String = __estadoPeliculaConverter.fromEstado(entity.estado)
        statement.bindText(9, _tmp_1)
        statement.bindText(10, entity.comentario)
        statement.bindText(11, entity.imagenUri)
      }
    }
    this.__deleteAdapterOfPelicula = object : EntityDeleteOrUpdateAdapter<Pelicula>() {
      protected override fun createQuery(): String = "DELETE FROM `peliculas_tbl` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: Pelicula) {
        statement.bindBlob(1, convertUUIDToByte(entity.id))
      }
    }
    this.__updateAdapterOfPelicula = object : EntityDeleteOrUpdateAdapter<Pelicula>() {
      protected override fun createQuery(): String = "UPDATE OR REPLACE `peliculas_tbl` SET `id` = ?,`titulo` = ?,`categoria` = ?,`calificacion` = ?,`fechaEstreno` = ?,`ultimaVez` = ?,`genero` = ?,`plataforma` = ?,`estado` = ?,`comentario` = ?,`imagenUri` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: Pelicula) {
        statement.bindBlob(1, convertUUIDToByte(entity.id))
        statement.bindText(2, entity.titulo)
        val _tmp: String = __categoriaPeliculaConverter.fromCategoria(entity.categoria)
        statement.bindText(3, _tmp)
        statement.bindDouble(4, entity.calificacion.toDouble())
        statement.bindText(5, entity.fechaEstreno)
        statement.bindText(6, entity.ultimaVez)
        statement.bindText(7, entity.genero)
        statement.bindText(8, entity.plataforma)
        val _tmp_1: String = __estadoPeliculaConverter.fromEstado(entity.estado)
        statement.bindText(9, _tmp_1)
        statement.bindText(10, entity.comentario)
        statement.bindText(11, entity.imagenUri)
        statement.bindBlob(12, convertUUIDToByte(entity.id))
      }
    }
  }

  public override suspend fun insertarPelicula(pelicula: Pelicula): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfPelicula.insert(_connection, pelicula)
  }

  public override suspend fun eliminarPelicula(pelicula: Pelicula): Unit = performSuspending(__db, false, true) { _connection ->
    __deleteAdapterOfPelicula.handle(_connection, pelicula)
  }

  public override suspend fun actualizarPelicula(pelicula: Pelicula): Unit = performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfPelicula.handle(_connection, pelicula)
  }

  public override fun getPeliculas(): Flow<List<Pelicula>> {
    val _sql: String = "SELECT * FROM peliculas_tbl ORDER BY calificacion DESC"
    return createFlow(__db, false, arrayOf("peliculas_tbl")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitulo: Int = getColumnIndexOrThrow(_stmt, "titulo")
        val _columnIndexOfCategoria: Int = getColumnIndexOrThrow(_stmt, "categoria")
        val _columnIndexOfCalificacion: Int = getColumnIndexOrThrow(_stmt, "calificacion")
        val _columnIndexOfFechaEstreno: Int = getColumnIndexOrThrow(_stmt, "fechaEstreno")
        val _columnIndexOfUltimaVez: Int = getColumnIndexOrThrow(_stmt, "ultimaVez")
        val _columnIndexOfGenero: Int = getColumnIndexOrThrow(_stmt, "genero")
        val _columnIndexOfPlataforma: Int = getColumnIndexOrThrow(_stmt, "plataforma")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfComentario: Int = getColumnIndexOrThrow(_stmt, "comentario")
        val _columnIndexOfImagenUri: Int = getColumnIndexOrThrow(_stmt, "imagenUri")
        val _result: MutableList<Pelicula> = mutableListOf()
        while (_stmt.step()) {
          val _item: Pelicula
          val _tmpId: UUID
          _tmpId = convertByteToUUID(_stmt.getBlob(_columnIndexOfId))
          val _tmpTitulo: String
          _tmpTitulo = _stmt.getText(_columnIndexOfTitulo)
          val _tmpCategoria: CategoriaPelicula
          val _tmp: String
          _tmp = _stmt.getText(_columnIndexOfCategoria)
          _tmpCategoria = __categoriaPeliculaConverter.toCategoria(_tmp)
          val _tmpCalificacion: Float
          _tmpCalificacion = _stmt.getDouble(_columnIndexOfCalificacion).toFloat()
          val _tmpFechaEstreno: String
          _tmpFechaEstreno = _stmt.getText(_columnIndexOfFechaEstreno)
          val _tmpUltimaVez: String
          _tmpUltimaVez = _stmt.getText(_columnIndexOfUltimaVez)
          val _tmpGenero: String
          _tmpGenero = _stmt.getText(_columnIndexOfGenero)
          val _tmpPlataforma: String
          _tmpPlataforma = _stmt.getText(_columnIndexOfPlataforma)
          val _tmpEstado: EstadoPelicula
          val _tmp_1: String
          _tmp_1 = _stmt.getText(_columnIndexOfEstado)
          _tmpEstado = __estadoPeliculaConverter.toEstado(_tmp_1)
          val _tmpComentario: String
          _tmpComentario = _stmt.getText(_columnIndexOfComentario)
          val _tmpImagenUri: String
          _tmpImagenUri = _stmt.getText(_columnIndexOfImagenUri)
          _item = Pelicula(_tmpId,_tmpTitulo,_tmpCategoria,_tmpCalificacion,_tmpFechaEstreno,_tmpUltimaVez,_tmpGenero,_tmpPlataforma,_tmpEstado,_tmpComentario,_tmpImagenUri)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPeliculaPorId(id: String): Pelicula {
    val _sql: String = "SELECT * FROM peliculas_tbl WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitulo: Int = getColumnIndexOrThrow(_stmt, "titulo")
        val _columnIndexOfCategoria: Int = getColumnIndexOrThrow(_stmt, "categoria")
        val _columnIndexOfCalificacion: Int = getColumnIndexOrThrow(_stmt, "calificacion")
        val _columnIndexOfFechaEstreno: Int = getColumnIndexOrThrow(_stmt, "fechaEstreno")
        val _columnIndexOfUltimaVez: Int = getColumnIndexOrThrow(_stmt, "ultimaVez")
        val _columnIndexOfGenero: Int = getColumnIndexOrThrow(_stmt, "genero")
        val _columnIndexOfPlataforma: Int = getColumnIndexOrThrow(_stmt, "plataforma")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfComentario: Int = getColumnIndexOrThrow(_stmt, "comentario")
        val _columnIndexOfImagenUri: Int = getColumnIndexOrThrow(_stmt, "imagenUri")
        val _result: Pelicula
        if (_stmt.step()) {
          val _tmpId: UUID
          _tmpId = convertByteToUUID(_stmt.getBlob(_columnIndexOfId))
          val _tmpTitulo: String
          _tmpTitulo = _stmt.getText(_columnIndexOfTitulo)
          val _tmpCategoria: CategoriaPelicula
          val _tmp: String
          _tmp = _stmt.getText(_columnIndexOfCategoria)
          _tmpCategoria = __categoriaPeliculaConverter.toCategoria(_tmp)
          val _tmpCalificacion: Float
          _tmpCalificacion = _stmt.getDouble(_columnIndexOfCalificacion).toFloat()
          val _tmpFechaEstreno: String
          _tmpFechaEstreno = _stmt.getText(_columnIndexOfFechaEstreno)
          val _tmpUltimaVez: String
          _tmpUltimaVez = _stmt.getText(_columnIndexOfUltimaVez)
          val _tmpGenero: String
          _tmpGenero = _stmt.getText(_columnIndexOfGenero)
          val _tmpPlataforma: String
          _tmpPlataforma = _stmt.getText(_columnIndexOfPlataforma)
          val _tmpEstado: EstadoPelicula
          val _tmp_1: String
          _tmp_1 = _stmt.getText(_columnIndexOfEstado)
          _tmpEstado = __estadoPeliculaConverter.toEstado(_tmp_1)
          val _tmpComentario: String
          _tmpComentario = _stmt.getText(_columnIndexOfComentario)
          val _tmpImagenUri: String
          _tmpImagenUri = _stmt.getText(_columnIndexOfImagenUri)
          _result = Pelicula(_tmpId,_tmpTitulo,_tmpCategoria,_tmpCalificacion,_tmpFechaEstreno,_tmpUltimaVez,_tmpGenero,_tmpPlataforma,_tmpEstado,_tmpComentario,_tmpImagenUri)
        } else {
          error("The query result was empty, but expected a single row to return a NON-NULL object of type 'com.example.diarioentrenamiento.models.Pelicula'.")
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun eliminarTodasLasPeliculas() {
    val _sql: String = "DELETE FROM peliculas_tbl"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
