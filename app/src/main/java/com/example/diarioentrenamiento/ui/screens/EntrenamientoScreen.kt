package com.example.diarioentrenamiento.ui.screens

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.diarioentrenamiento.models.CategoriaPelicula
import com.example.diarioentrenamiento.models.EstadoPelicula
import com.example.diarioentrenamiento.models.Pelicula
import kotlinx.coroutines.launch

// ─── Paleta de colores ────────────────────────────────────────────────────────
private val BgDark        = Color(0xFF0D0D1A)
private val CardBg        = Color(0xFF1A1A2E)
private val CardBorder    = Color(0xFF2A2A4A)
private val AccentGold    = Color(0xFFFFD700)
private val AccentPurple  = Color(0xFF7C3AED)
private val AccentPink    = Color(0xFFEC4899)
private val AccentBlue    = Color(0xFF3B82F6)
private val TextPrimary   = Color(0xFFF1F5F9)
private val TextSecondary = Color(0xFF94A3B8)
private val GreenOk       = Color(0xFF22C55E)
private val OrangeWip     = Color(0xFFF97316)
private val RedPending    = Color(0xFFEF4444)

// ─── Screen principal ─────────────────────────────────────────────────────────
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PeliculasScreen(
    peliculas: List<Pelicula>,
    onAddPelicula: (Pelicula) -> Unit,
    onUpdatePelicula: (Pelicula) -> Unit,
    onRemovePelicula: (Pelicula) -> Unit
) {
    var showAddSheet   by remember { mutableStateOf(false) }
    var peliculaEdit   by remember { mutableStateOf<Pelicula?>(null) }

    Scaffold(
        containerColor = BgDark,
        topBar = { CineToolBar() },
        floatingActionButton = {
            Box(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .shadow(12.dp, CircleShape)
                    .background(
                        brush = Brush.linearGradient(listOf(AccentPurple, AccentPink)),
                        shape = CircleShape
                    )
                    .size(64.dp)
                    .clickable { showAddSheet = true },
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Agregar", tint = Color.White, modifier = Modifier.size(32.dp))
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        PeliculasContent(
            peliculas          = peliculas,
            modifier           = Modifier.padding(innerPadding),
            onRemovePelicula   = onRemovePelicula,
            onEditPelicula     = { peliculaEdit = it }
        )
    }

    // Sheet: Agregar nueva
    if (showAddSheet) {
        PeliculaFormSheet(
            titulo       = "Agregar Título",
            inicial      = null,
            onDismiss    = { showAddSheet = false },
            onConfirm    = { p -> onAddPelicula(p); showAddSheet = false }
        )
    }

    // Sheet: Editar existente
    peliculaEdit?.let { p ->
        PeliculaFormSheet(
            titulo    = "Editar Título",
            inicial   = p,
            onDismiss = { peliculaEdit = null },
            onConfirm = { updated -> onUpdatePelicula(updated); peliculaEdit = null }
        )
    }
}

// ─── TopBar ───────────────────────────────────────────────────────────────────
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CineToolBar() {
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("CineLog", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                Spacer(Modifier.width(8.dp))
                Column {
                    Text("CineLog", fontWeight = FontWeight.ExtraBold, fontSize = 20.sp, color = TextPrimary)
                    Text("Tu diario de películas & series", fontSize = 11.sp, color = TextSecondary)
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF12122A))
    )
}

// ─── Contenido principal ──────────────────────────────────────────────────────
@Composable
fun PeliculasContent(
    peliculas: List<Pelicula>,
    modifier: Modifier,
    onRemovePelicula: (Pelicula) -> Unit,
    onEditPelicula: (Pelicula) -> Unit
) {
    // 0 = Todo, 1 = Películas, 2 = Series, 3 = Mejor calificadas
    var filtro by remember { mutableStateOf(0) }

    val listaFiltrada = when (filtro) {
        1    -> peliculas.filter { it.categoria == CategoriaPelicula.PELICULA }
        2    -> peliculas.filter { it.categoria == CategoriaPelicula.SERIE }
        3    -> peliculas.sortedByDescending { it.calificacion }
        else -> peliculas
    }

    Column(modifier = modifier.fillMaxSize().background(BgDark).padding(horizontal = 12.dp)) {

        Spacer(Modifier.height(12.dp))

        // ── Chips de filtro ──
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            listOf("Todo", "Películas", "Series", "Top").forEachIndexed { idx, label ->
                val selected = filtro == idx
                Surface(
                    shape  = RoundedCornerShape(50),
                    color  = if (selected) AccentPurple else Color(0xFF1E1E35),
                    modifier = Modifier
                        .weight(1f)
                        .clickable { filtro = idx }
                        .border(1.dp, if (selected) AccentPurple else CardBorder, RoundedCornerShape(50))
                ) {
                    Text(
                        label,
                        fontSize  = 11.sp,
                        color     = if (selected) Color.White else TextSecondary,
                        fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                        modifier  = Modifier.padding(vertical = 8.dp, horizontal = 4.dp),
                        maxLines  = 1
                    )
                }
            }
        }

        Spacer(Modifier.height(8.dp))

        // ── Estadísticas rápidas ──
        StatsBar(peliculas)

        Spacer(Modifier.height(12.dp))

        if (listaFiltrada.isEmpty()) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Sin títulos", color = TextSecondary, fontSize = 18.sp)
                    Spacer(Modifier.height(12.dp))
                    Text("No hay títulos aún", color = TextSecondary, fontSize = 16.sp)
                    Text("Pulsa + para agregar uno", color = TextSecondary, fontSize = 13.sp)
                }
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding      = PaddingValues(bottom = 100.dp)
            ) {
                items(listaFiltrada, key = { it.id }) { pelicula ->
                    AnimatedVisibility(
                        visible = true,
                        enter   = fadeIn() + slideInVertically()
                    ) {
                        PeliculaCard(
                            pelicula         = pelicula,
                            onEdit           = { onEditPelicula(pelicula) },
                            onDelete         = { onRemovePelicula(pelicula) }
                        )
                    }
                }
            }
        }
    }
}

// ─── Barra de estadísticas ────────────────────────────────────────────────────
@Composable
fun StatsBar(peliculas: List<Pelicula>) {
    val totalPeliculas = peliculas.count { it.categoria == CategoriaPelicula.PELICULA }
    val totalSeries    = peliculas.count { it.categoria == CategoriaPelicula.SERIE }
    val promedio       = if (peliculas.isEmpty()) 0f else peliculas.map { it.calificacion }.average().toFloat()

    Row(
        modifier            = Modifier
            .fillMaxWidth()
            .background(Color(0xFF16162C), RoundedCornerShape(14.dp))
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        StatItem("FILMS", "$totalPeliculas", "Películas")
        Divider(modifier = Modifier.height(36.dp).width(1.dp), color = CardBorder)
        StatItem("SERIES", "$totalSeries", "Series")
        Divider(modifier = Modifier.height(36.dp).width(1.dp), color = CardBorder)
        StatItem("AVG", "%.1f".format(promedio), "Promedio")
    }
}

@Composable
fun StatItem(emoji: String, valor: String, etiqueta: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(emoji,   fontSize = 18.sp)
        Text(valor,   color = TextPrimary,   fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Text(etiqueta, color = TextSecondary, fontSize = 10.sp)
    }
}

// ─── Card de película/serie ───────────────────────────────────────────────────
@Composable
fun PeliculaCard(
    pelicula: Pelicula,
    onEdit:   () -> Unit,
    onDelete: () -> Unit
) {
    var showConfirm by remember { mutableStateOf(false) }

    val accentColor = if (pelicula.categoria == CategoriaPelicula.PELICULA) AccentBlue else AccentPink
    val estadoColor = when (pelicula.estado) {
        EstadoPelicula.VISTA       -> GreenOk
        EstadoPelicula.EN_PROGRESO -> OrangeWip
        EstadoPelicula.PENDIENTE   -> RedPending
    }
    val estadoLabel = when (pelicula.estado) {
        EstadoPelicula.VISTA       -> "Vista"
        EstadoPelicula.EN_PROGRESO -> "En progreso"
        EstadoPelicula.PENDIENTE   -> "Pendiente"
    }

    Surface(
        shape  = RoundedCornerShape(18.dp),
        color  = CardBg,
        modifier = Modifier
            .fillMaxWidth()
            .shadow(6.dp, RoundedCornerShape(18.dp))
            .border(1.dp, CardBorder, RoundedCornerShape(18.dp))
    ) {
        Column {
            // Franja de color arriba
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .background(brush = Brush.horizontalGradient(listOf(accentColor, AccentPurple)))
            )

            Row(modifier = Modifier.padding(12.dp)) {

                // ── Póster ──
                PosterImage(
                    uri    = pelicula.imagenUri,
                    modifier = Modifier
                        .size(width = 80.dp, height = 115.dp)
                        .clip(RoundedCornerShape(12.dp))
                )

                Spacer(Modifier.width(12.dp))

                // ── Información ──
                Column(modifier = Modifier.weight(1f)) {

                    // Título + badge categoría
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            pelicula.titulo,
                            color      = TextPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize   = 16.sp,
                            maxLines   = 2,
                            overflow   = TextOverflow.Ellipsis,
                            modifier   = Modifier.weight(1f)
                        )
                        Spacer(Modifier.width(6.dp))
                        Badge(
                            containerColor = accentColor.copy(alpha = 0.2f)
                        ) {
                            Text(
                                if (pelicula.categoria == CategoriaPelicula.PELICULA) "FILM" else "SERIE",
                                color    = accentColor,
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                            )
                        }
                    }

                    Spacer(Modifier.height(4.dp))

                    // Estrellas de calificación
                    StarRatingDisplay(calificacion = pelicula.calificacion)

                    Spacer(Modifier.height(6.dp))

                    // Info rows
                    InfoRow("", "Género",       pelicula.genero)
                    InfoRow("", "Estreno",      pelicula.fechaEstreno)
                    InfoRow("", "Última vez",   pelicula.ultimaVez)
                    InfoRow("", "Plataforma",   pelicula.plataforma)

                    Spacer(Modifier.height(6.dp))

                    // Estado
                    Surface(
                        shape = RoundedCornerShape(50),
                        color = estadoColor.copy(alpha = 0.15f)
                    ) {
                        Text(
                            estadoLabel,
                            color      = estadoColor,
                            fontSize   = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier   = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                        )
                    }

                    // Comentario
                    if (pelicula.comentario.isNotBlank()) {
                        Spacer(Modifier.height(6.dp))
                        Text(
                            "\"${pelicula.comentario}\"",
                            color      = TextSecondary,
                            fontSize   = 11.sp,
                            fontStyle  = FontStyle.Italic,
                            maxLines   = 2,
                            overflow   = TextOverflow.Ellipsis
                        )
                    }

                    Spacer(Modifier.height(8.dp))

                    // Botones editar / eliminar
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        OutlinedButton(
                            onClick  = onEdit,
                            shape    = RoundedCornerShape(10.dp),
                            colors   = ButtonDefaults.outlinedButtonColors(contentColor = AccentBlue),
                            border   = androidx.compose.foundation.BorderStroke(1.dp, AccentBlue),
                            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                            modifier = Modifier.height(34.dp)
                        ) {
                            Icon(Icons.Filled.Edit,   null, Modifier.size(14.dp))
                            Spacer(Modifier.width(4.dp))
                            Text("Editar", fontSize = 12.sp)
                        }

                        OutlinedButton(
                            onClick  = { showConfirm = true },
                            shape    = RoundedCornerShape(10.dp),
                            colors   = ButtonDefaults.outlinedButtonColors(contentColor = RedPending),
                            border   = androidx.compose.foundation.BorderStroke(1.dp, RedPending),
                            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                            modifier = Modifier.height(34.dp)
                        ) {
                            Icon(Icons.Filled.Delete, null, Modifier.size(14.dp))
                            Spacer(Modifier.width(4.dp))
                            Text("Eliminar", fontSize = 12.sp)
                        }
                    }
                }
            }
        }
    }

    // Diálogo de confirmación de borrado
    if (showConfirm) {
        AlertDialog(
            onDismissRequest = { showConfirm = false },
            containerColor   = CardBg,
            title = { Text("¿Eliminar?", color = TextPrimary, fontWeight = FontWeight.Bold) },
            text  = { Text("Se eliminará \"${pelicula.titulo}\" de tu lista.", color = TextSecondary) },
            confirmButton = {
                TextButton(onClick = { onDelete(); showConfirm = false }) {
                    Text("Eliminar", color = RedPending, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showConfirm = false }) {
                    Text("Cancelar", color = TextSecondary)
                }
            }
        )
    }
}

// ─── Póster con placeholder ───────────────────────────────────────────────────
@Composable
fun PosterImage(uri: String, modifier: Modifier = Modifier) {
    if (uri.isNotBlank()) {
        AsyncImage(
            model              = ImageRequest.Builder(LocalContext.current).data(uri).crossfade(true).build(),
            contentDescription = "Póster",
            contentScale       = ContentScale.Crop,
            modifier           = modifier
        )
    } else {
        Box(
            modifier           = modifier.background(Color(0xFF252540)),
            contentAlignment   = Alignment.Center
        ) {
            Text("?", fontSize = 28.sp, color = TextSecondary)
        }
    }
}

// ─── Fila de info ─────────────────────────────────────────────────────────────
@Composable
fun InfoRow(emoji: String, label: String, value: String) {
    if (value.isBlank()) return
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 1.dp)) {
        Text("$label: ", color = TextSecondary, fontSize = 11.sp)
        Text(value, color = TextPrimary, fontSize = 11.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
    }
}

// ─── Estrellas visuales ───────────────────────────────────────────────────────
@Composable
fun StarRatingDisplay(calificacion: Float) {
    val stars = (calificacion / 2f).coerceIn(0f, 5f) // convierte 0-10 → 0-5 estrellas
    Row(verticalAlignment = Alignment.CenterVertically) {
        repeat(5) { idx ->
            Icon(
                imageVector = if (idx < stars.toInt()) Icons.Filled.Star else Icons.Outlined.StarOutline,
                contentDescription = null,
                tint     = AccentGold,
                modifier = Modifier.size(15.dp)
            )
        }
        Spacer(Modifier.width(6.dp))
        Text(
            "%.1f".format(calificacion) + "/10",
            color      = AccentGold,
            fontSize   = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

// ─── BottomSheet: formulario agregar/editar ───────────────────────────────────
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PeliculaFormSheet(
    titulo:    String,
    inicial:   Pelicula?,
    onDismiss: () -> Unit,
    onConfirm: (Pelicula) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope      = rememberCoroutineScope()

    // Estados del formulario
    var tituloVal      by remember { mutableStateOf(inicial?.titulo       ?: "") }
    var categoria      by remember { mutableStateOf(inicial?.categoria    ?: CategoriaPelicula.PELICULA) }
    var calificacion   by remember { mutableStateOf(inicial?.calificacion ?: 7f) }
    var fechaEstreno   by remember { mutableStateOf(inicial?.fechaEstreno ?: "") }
    var ultimaVez      by remember { mutableStateOf(inicial?.ultimaVez    ?: "") }
    var genero         by remember { mutableStateOf(inicial?.genero       ?: "") }
    var plataforma     by remember { mutableStateOf(inicial?.plataforma   ?: "") }
    var estado         by remember { mutableStateOf(inicial?.estado       ?: EstadoPelicula.VISTA) }
    var comentario     by remember { mutableStateOf(inicial?.comentario   ?: "") }
    var imagenUri      by remember { mutableStateOf(inicial?.imagenUri    ?: "") }

    val imagePicker = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let { imagenUri = it.toString() }
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState       = sheetState,
        containerColor   = Color(0xFF12122A),
        dragHandle       = {
            Box(
                Modifier
                    .padding(vertical = 10.dp)
                    .size(width = 40.dp, height = 4.dp)
                    .background(CardBorder, RoundedCornerShape(50))
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
                .padding(bottom = 40.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Text(
                titulo,
                color      = TextPrimary,
                fontWeight = FontWeight.ExtraBold,
                fontSize   = 20.sp
            )

            // Selector de categoría (Película / Serie)
            Row(
                modifier              = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                listOf(CategoriaPelicula.PELICULA to "Película", CategoriaPelicula.SERIE to "Serie").forEach { (cat, label) ->
                    val sel = categoria == cat
                    Surface(
                        shape    = RoundedCornerShape(12.dp),
                        color    = if (sel) AccentPurple else Color(0xFF1E1E35),
                        modifier = Modifier
                            .weight(1f)
                            .clickable { categoria = cat }
                            .border(1.dp, if (sel) AccentPurple else CardBorder, RoundedCornerShape(12.dp))
                    ) {
                        Text(
                            label,
                            color      = if (sel) Color.White else TextSecondary,
                            fontWeight = if (sel) FontWeight.Bold else FontWeight.Normal,
                            modifier   = Modifier.padding(vertical = 12.dp, horizontal = 8.dp),
                            fontSize   = 14.sp
                        )
                    }
                }
            }

            // Título
            CineTextField(value = tituloVal,   onValueChange = { tituloVal = it },   label = "Título *")
            CineTextField(value = genero,       onValueChange = { genero = it },       label = "Género  (ej. Acción, Drama)")
            CineTextField(value = plataforma,   onValueChange = { plataforma = it },   label = "Plataforma  (ej. Netflix, HBO)")
            CineTextField(value = fechaEstreno, onValueChange = { fechaEstreno = it }, label = "Fecha de estreno / emisión")
            CineTextField(value = ultimaVez,    onValueChange = { ultimaVez = it },    label = "Última vez que la viste")
            CineTextField(value = comentario,   onValueChange = { comentario = it },   label = "Reseña personal (opcional)", maxLines = 3)

            // Calificación con slider
            Column {
                Row(
                    modifier              = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment     = Alignment.CenterVertically
                ) {
                    Text("Calificación", color = TextSecondary, fontSize = 13.sp)
                    Text(
                        "%.1f / 10".format(calificacion),
                        color      = AccentGold,
                        fontWeight = FontWeight.Bold,
                        fontSize   = 16.sp
                    )
                }
                Slider(
                    value         = calificacion,
                    onValueChange = { calificacion = it },
                    valueRange    = 0f..10f,
                    steps         = 19,
                    colors        = SliderDefaults.colors(
                        thumbColor        = AccentGold,
                        activeTrackColor  = AccentGold,
                        inactiveTrackColor = CardBorder
                    )
                )
            }

            // Estado
            Text("Estado", color = TextSecondary, fontSize = 13.sp)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf(
                    EstadoPelicula.VISTA       to "Vista",
                    EstadoPelicula.EN_PROGRESO to "En progreso",
                    EstadoPelicula.PENDIENTE   to "Pendiente"
                ).forEach { (est, label) ->
                    val sel = estado == est
                    val col = when (est) {
                        EstadoPelicula.VISTA       -> GreenOk
                        EstadoPelicula.EN_PROGRESO -> OrangeWip
                        EstadoPelicula.PENDIENTE   -> RedPending
                    }
                    Surface(
                        shape    = RoundedCornerShape(50),
                        color    = if (sel) col.copy(alpha = 0.2f) else Color(0xFF1E1E35),
                        modifier = Modifier
                            .clickable { estado = est }
                            .border(1.dp, if (sel) col else CardBorder, RoundedCornerShape(50))
                    ) {
                        Text(
                            label,
                            color    = if (sel) col else TextSecondary,
                            fontSize = 12.sp,
                            fontWeight = if (sel) FontWeight.Bold else FontWeight.Normal,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                        )
                    }
                }
            }

            // Imagen del póster
            Row(
                modifier          = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                PosterImage(
                    uri      = imagenUri,
                    modifier = Modifier
                        .size(70.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .border(1.dp, CardBorder, RoundedCornerShape(10.dp))
                )
                OutlinedButton(
                    onClick  = { imagePicker.launch("image/*") },
                    shape    = RoundedCornerShape(10.dp),
                    colors   = ButtonDefaults.outlinedButtonColors(contentColor = AccentPurple),
                    border   = androidx.compose.foundation.BorderStroke(1.dp, AccentPurple)
                ) {
                    Text("Seleccionar póster")
                }
            }

            // Botón guardar
            Button(
                onClick  = {
                    if (tituloVal.isNotBlank()) {
                        onConfirm(
                            Pelicula(
                                id           = inicial?.id ?: java.util.UUID.randomUUID(),
                                titulo       = tituloVal.trim(),
                                categoria    = categoria,
                                calificacion = calificacion,
                                fechaEstreno = fechaEstreno.trim(),
                                ultimaVez    = ultimaVez.trim(),
                                genero       = genero.trim(),
                                plataforma   = plataforma.trim(),
                                estado       = estado,
                                comentario   = comentario.trim(),
                                imagenUri    = imagenUri
                            )
                        )
                        scope.launch { sheetState.hide() }
                    }
                },
                shape    = RoundedCornerShape(14.dp),
                colors   = ButtonDefaults.buttonColors(containerColor = AccentPurple),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
            ) {
                Text(
                    if (inicial == null) "Guardar título" else "Guardar cambios",
                    fontWeight = FontWeight.Bold,
                    fontSize   = 16.sp
                )
            }
        }
    }
}

// ─── TextField con estilo oscuro ──────────────────────────────────────────────
@Composable
fun CineTextField(
    value:         String,
    onValueChange: (String) -> Unit,
    label:         String,
    maxLines:      Int = 1
) {
    OutlinedTextField(
        value          = value,
        onValueChange  = onValueChange,
        label          = { Text(label, color = TextSecondary, fontSize = 13.sp) },
        maxLines       = maxLines,
        modifier       = Modifier.fillMaxWidth(),
        shape          = RoundedCornerShape(12.dp),
        colors         = OutlinedTextFieldDefaults.colors(
            focusedBorderColor   = AccentPurple,
            unfocusedBorderColor = CardBorder,
            focusedTextColor     = TextPrimary,
            unfocusedTextColor   = TextPrimary,
            cursorColor          = AccentPurple,
            focusedLabelColor    = AccentPurple
        )
    )
}
