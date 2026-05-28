# 🔧 Manual Técnico — TKromas / pelisSeries

## Arquitectura del sistema

```
PelisSeries (consola Java)
│
├── Capa de presentación     → Menús y entrada de datos por consola
├── Capa de lógica           → Validaciones, búsqueda, estadísticas
└── Capa de datos            → JDBC → MySQL (Laragon)
```

## Requisitos del sistema

| Componente | Versión mínima |
|-----------|----------------|
| Java JDK | 17+ |
| MySQL | 8.0 |
| Laragon | 6.0+ |
| Maven | 3.8+ |
| Git | 2.x |

## Instalación

### 1. Clonar el repositorio
```bash
git clone https://github.com/Eve492m/pelisSeries.git
cd pelisSeries
```

### 2. Configurar base de datos
1. Iniciar Laragon → encender MySQL
2. Abrir phpMyAdmin
   > Si aparece **403 Forbidden**: mover phpMyAdmin a `C:/etc/apps/phpMyAdmin`
3. Crear base de datos `bd_pelisseries`
4. Importar `sql/bd_pelisseries.sql`

### 3. Configurar conexión JDBC
```java
private static final String URL  = "jdbc:mysql://localhost:3306/bd_pelisseries";
private static final String USER = "root";
private static final String PASS = "";  // Laragon: vacío por defecto
```

### 4. Compilar y ejecutar
```bash
mvn clean install
mvn exec:java
```

### Mostrar emojis en NetBeans
Agregar en VM Options:
```
-Dfile.encoding=UTF-8
```

## Estructura del proyecto

```
pelisSeries/
├── src/main/java/com/mycompany/pelisseries/
│   └── PelisSeries.java       ← clase principal
├── docs/
│   ├── manual_usuario.md
│   ├── manual_tecnico.md
│   └── diagramas/
├── sql/
│   └── bd_pelisseries.sql
├── pom.xml
├── README.md
└── .gitignore
```

## Issues conocidos

| # | Tipo | Descripción | Estado |
|---|------|-------------|--------|
| #1 | 🐛 bug | Al cancelar modificación (Enter en todos los campos) igual actualiza BD | Abierto |
| #2 | ✨ feature | Implementar exportar lista a archivo .txt | Abierto |
| #3 | 🔧 enhancement | Año máximo hardcoded 2025 — hacer dinámico con `YEAR(CURDATE())` | Abierto |
