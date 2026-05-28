# 🎬 Tkromas - PelisSeries 
---

# 📌 Descripción del Proyecto
Este es un proyecto grupal desarrollado en Java orientado a la gestión eficiente, organizada y centralizada de catálogos de películas y series. La aplicación resuelve de forma directa la necesidad de registrar y administrar contenido audiovisual estructurado mediante una interfaz limpia,:

---

# Descripción del Problema

A. Descripción del Problema
En el contexto actual del consumo de contenido multimedia, los usuarios y administradores se enfrentan a la dispersión de la información técnica de películas y series. La falta de un sistema centralizado local genera los siguientes inconvenientes:

# Planteamiento del Problema
Para resolver esta problemática, nos planteamos la siguiente interrogante: ¿Cómo diseñar e implementar una arquitectura de software orientada a objetos que permita gestionar un catálogo multimedia de manera persistente, garantizando la integridad idiomática de los datos en español y facilitando el desarrollo colaborativo seguro

# Solución 
La solución implementada consiste en una aplicación de escritorio desarrollada en Java a través de Apache NetBeans IDE 25, con una arquitectura conectada a servicios locales y remotos. Para la mitigación de errores de codificación, se realizó una configuración a nivel de Máquina Virtual de Java y en las fuentes del entorno de desarrollo utilizando el estándar UTF-8, complementado con un esquema de base de datos configurado específicamente bajo la colación utf8mb4_spanish_ci. La persistencia relacional eficiente se logró mediante el acoplamiento con controladores JDBC a un motor MySQL 8.4.3 gestionado por Laragon, lo que garantiza transacciones seguras y el borrado en cascada controlado de registros mediante dependencias lógicas en las tablas de usuarios y favoritos. Finalmente, el flujo colaborativo técnico se estructuró mediante el control total del ciclo de vida del software con Git y una autenticación federada segura con GitHub, permitiendo la trazabilidad absoluta de cada actualización en la rama principal.


# 👨‍💻 Integrantes del Equipo

| 👤 Nombre Completo | 🆔 Carnet |
|---|---|
| Evelyn Stacy Sánchez Lizama | SL100426 |
| Jennifer Raquel Magaña Asensio | MA101626 |
| Dayana Nicole López Ventura | LV100426 |
| Diego Alexander Hernandez Flores | HF100626 |



---


# 🏗️ Arquitectura e Interconexión del Sistema

Para asegurar la consistencia, el correcto funcionamiento multinivel de la aplicación y un flujo formal de
despliegue, el ecosistema de desarrollo se compone de múltiples tecnologías integradas
secuencialmente, tal como se detalla en el mapa estructural del proyecto: 

<img width="1080" height="619" alt="image" src="https://github.com/user-attachments/assets/d41cccf2-15cf-46a6-af26-7ac843cffad1" />

---

# Diagrama entidad-relación 👩🏻‍💻

<img width="866" height="636" alt="image" src="https://github.com/user-attachments/assets/655bb99b-7be0-4aec-8ba4-314756230cf3" />

---

# ⚙️ Configuración del Entorno y Codificación
Con el fin de evitar errores en la interpretación de caracteres especiales (como tildes, eñes y diéresis),
el proyecto se ejecuta utilizando de manera estricta el estándar de codificación UTF-8 tanto en el IDE
como en la máquina virtual de Java (JVM).

<img width="1066" height="711" alt="image" src="https://github.com/user-attachments/assets/664ee367-07bb-4794-9e84-7df78648901d" />


---

## 🔧Especificación del Codec en Fuentes de Origen
De igual forma, en las opciones globales de fuentes (Sources), se ha verificado que la codificación base
(Encoding) esté configurada por defecto en UTF-8 , asegurando la portabilidad del código fuente entre
los equipos de todos los desarrolladores.

<img width="1056" height="717" alt="image" src="https://github.com/user-attachments/assets/f5d6cf69-08e8-4b8b-91a7-50ad4e41fcdd" />

---

# 🗄️ Entorno Local de Base de Datos

El sistema utiliza una base de datos relacional robusta administrada localmente. Para facilitar la
integración, se utiliza el entorno de desarrollo local Laragon v8.6.1 que provee los servicios necesarios
---

<img width="1165" height="666" alt="image" src="https://github.com/user-attachments/assets/f6960727-1be9-491b-898d-40ef81d37f30" />


| Herramienta | Función |
|---|---|
| Apache 2.4.66 | Servidor web local |
| MySQL 8.4.3 | Persistencia de películas, series y usuarios |
| Mailpit 1.22.3 | Sandbox de correos |
| phpMyAdmin | Administración SQL |

---

# 🛠️ Estructura SQL 

 <img width="1325" height="708" alt="image" src="https://github.com/user-attachments/assets/811ce32a-b6a3-42a3-b635-f1af06631f07" />

---
# Paso 1:
Se accedió a la ruta del proyecto guardado en el almacenamiento local y se procedió a iniciar el
repositorio Git local. Se configuraron de manera global las credenciales oficiales de la desarrolladora
responsable del despliegue original:

<img width="920" height="943" alt="image" src="https://github.com/user-attachments/assets/17665ea3-5d47-4413-8164-80093e92326d" />

# Paso 2:
 Se estructuró el archivo de exclusión .gitignore , se agregaron los componentes base del proyecto
(incluyendo el archivo de configuración de dependencias de Maven pom.xml y las clases principales) y
se realizaron las confirmaciones oficiales en el historial local: 

<img width="920" height="1093" alt="image" src="https://github.com/user-attachments/assets/5f7b1a84-6684-4040-9735-cd30fda0df6c" />

---

# 👤 Paso 3: Autenticación Segura mediante Git Credential Manager

Para establecer un canal de comunicación seguro y cifrado con los servidores remotos de GitHub, el
sistema invocó el módulo de autenticación federada Web. Este proceso garantiza que las llaves de
acceso SSH o tokens permanezcan protegidos en el llavero del sistema operativo.

<img width="891" height="1008" alt="image" src="https://github.com/user-attachments/assets/cefa3f74-8be7-4635-8b2d-83ac1222995c" />

<img width="921" height="811" alt="image" src="https://github.com/user-attachments/assets/a9074c5f-f62c-46a3-86a5-80f28cd50ee5" />


# Código fuente👩🏻‍💻

´´´
// ============================================================
//  CONFIGURACION DE BASE DE DATOS
// ============================================================

private static final String BD_URL      = "jdbc:mysql://localhost:3306/pelisseries_db";
private static final String BD_USUARIO  = "root";
private static final String BD_PASSWORD = "";

// ============================================================
//  VARIABLES GLOBALES DE SESION
// ============================================================

static Scanner scanner = new Scanner(System.in);
static int sesionId = -1;
static String sesionNombre = "";

// ============================================================
//  MAIN
// ============================================================

public static void main(String[] args) {

    limpiarConsola();
    mostrarBienvenida();

    if (!verificarConexion()) {
        System.out.println("\n⚠️ No se pudo conectar a MySQL.");
        return;
    }

    while (sesionId == -1) {
        menuAutenticacion();
    }

    menuPrincipal();
}

// ============================================================
//  MENU PRINCIPAL
// ============================================================

static void menuPrincipal() {

    int opcion;

    do {

        System.out.println("\n========== TKromas ==========");
        System.out.println("1. Agregar");
        System.out.println("2. Ver todos");
        System.out.println("3. Buscar por titulo");
        System.out.println("4. Buscar por genero");
        System.out.println("5. Filtrar calificacion");
        System.out.println("6. Ver basados en libros");
        System.out.println("7. Modificar");
        System.out.println("8. Eliminar");
        System.out.println("9. Estadisticas");
        System.out.println("0. Salir");

        opcion = leerEnteroSeguro("Seleccione: ", 0, 9);

        switch (opcion) {

            case 1:
                agregarItem();
                break;

            case 2:
                verTodos();
                break;

            case 3:
                buscarPorTitulo();
                break;

            case 4:
                buscarPorGenero();
                break;

            case 5:
                filtrarCalificacion();
                break;

            case 6:
                mostrarBasadosEnLibros();
                break;

            case 7:
                modificarItem();
                break;

            case 8:
                eliminarItem();
                break;

            case 9:
                mostrarEstadisticas();
                break;

            case 0:
                System.out.println("Sesion finalizada.");
                break;
        }

    } while (opcion != 0);
}

// ============================================================
//  CONEXION
// ============================================================

static Connection conectar() throws SQLException {
    return DriverManager.getConnection(BD_URL, BD_USUARIO, BD_PASSWORD);
}

static boolean verificarConexion() {

    try (Connection con = conectar()) {
        return con != null;
    } catch (Exception e) {
        return false;
    }
}

// ============================================================
//  LOGIN
// ============================================================

static void menuAutenticacion() {

    System.out.println("\n===== LOGIN =====");
    System.out.println("1. Iniciar sesion");
    System.out.println("2. Registrarse");
    System.out.println("0. Salir");

    int op = leerEnteroSeguro("Seleccione: ", 0, 2);

    switch (op) {

        case 1:
            iniciarSesion();
            break;

        case 2:
            registrarUsuario();
            break;

        case 0:
            System.exit(0);
            break;
    }
}

static void iniciarSesion() {

    scanner.nextLine();

    System.out.print("Usuario: ");
    String usuario = scanner.nextLine();

    System.out.print("Password: ");
    String password = scanner.nextLine();

    String sql = "SELECT * FROM usuarios WHERE usuario=? AND password=MD5(?)";

    try (
        Connection con = conectar();
        PreparedStatement ps = con.prepareStatement(sql)
    ) {

        ps.setString(1, usuario);
        ps.setString(2, password);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            sesionId = rs.getInt("id");
            sesionNombre = rs.getString("usuario");

            System.out.println("Bienvenido " + sesionNombre);

        } else {

            System.out.println("Credenciales incorrectas");
        }

    } catch (Exception e) {

        System.out.println("Error: " + e.getMessage());
    }
}

static void registrarUsuario() {

    scanner.nextLine();

    System.out.print("Nuevo usuario: ");
    String usuario = scanner.nextLine();

    System.out.print("Password: ");
    String pass1 = scanner.nextLine();

    System.out.print("Confirmar password: ");
    String pass2 = scanner.nextLine();

    if (!pass1.equals(pass2)) {

        System.out.println("Las contraseñas no coinciden");
        return;
    }

    String sql = "INSERT INTO usuarios(usuario,password) VALUES(?,MD5(?))";

    try (
        Connection con = conectar();
        PreparedStatement ps = con.prepareStatement(sql)
    ) {

        ps.setString(1, usuario);
        ps.setString(2, pass1);

        ps.executeUpdate();

        System.out.println("Usuario creado");

    } catch (Exception e) {

        System.out.println("Error: " + e.getMessage());
    }
}

// ============================================================
//  AGREGAR
// ============================================================

static void agregarItem() {

    scanner.nextLine();

    System.out.print("Titulo: ");
    String titulo = scanner.nextLine();

    System.out.print("Tipo (pelicula/serie): ");
    String tipo = scanner.nextLine();

    System.out.print("Genero: ");
    String genero = scanner.nextLine();

    int anio = leerEnteroSeguro("Anio: ", 1888, 2026);

    int calificacion = leerEnteroSeguro("Calificacion 1-5: ", 1, 5);

    System.out.print("Basado en libro (s/n): ");
    String libro = scanner.nextLine();

    int basadoLibro = libro.equalsIgnoreCase("s") ? 1 : 0;

    String sql = """
        INSERT INTO favoritos
        (usuario_id,titulo,tipo,genero,anio,calificacion,basado_libro)
        VALUES(?,?,?,?,?,?,?)
    """;

    try (
        Connection con = conectar();
        PreparedStatement ps = con.prepareStatement(sql)
    ) {

        ps.setInt(1, sesionId);
        ps.setString(2, titulo);
        ps.setString(3, tipo);
        ps.setString(4, genero);
        ps.setInt(5, anio);
        ps.setInt(6, calificacion);
        ps.setInt(7, basadoLibro);

        ps.executeUpdate();

        System.out.println("Registro agregado");

    } catch (Exception e) {

        System.out.println("Error: " + e.getMessage());
    }
}

// ============================================================
//  VER TODOS
// ============================================================

static void verTodos() {

    String sql = "SELECT * FROM favoritos WHERE usuario_id=?";

    try (
        Connection con = conectar();
        PreparedStatement ps = con.prepareStatement(sql)
    ) {

        ps.setInt(1, sesionId);

        ResultSet rs = ps.executeQuery();

        mostrarResultados(rs);

    } catch (Exception e) {

        System.out.println("Error: " + e.getMessage());
    }
}

// ============================================================
//  BUSCAR TITULO
// ============================================================

static void buscarPorTitulo() {

    scanner.nextLine();

    System.out.print("Buscar titulo: ");
    String titulo = scanner.nextLine();

    String sql = """
        SELECT * FROM favoritos
        WHERE usuario_id=? AND titulo LIKE ?
    """;

    try (
        Connection con = conectar();
        PreparedStatement ps = con.prepareStatement(sql)
    ) {

        ps.setInt(1, sesionId);
        ps.setString(2, "%" + titulo + "%");

        ResultSet rs = ps.executeQuery();

        mostrarResultados(rs);

    } catch (Exception e) {

        System.out.println("Error: " + e.getMessage());
    }
}

// ============================================================
//  BUSCAR GENERO
// ============================================================

static void buscarPorGenero() {

    scanner.nextLine();

    System.out.print("Genero: ");
    String genero = scanner.nextLine();

    String sql = """
        SELECT * FROM favoritos
        WHERE usuario_id=? AND genero=?
    """;

    try (
        Connection con = conectar();
        PreparedStatement ps = con.prepareStatement(sql)
    ) {

        ps.setInt(1, sesionId);
        ps.setString(2, genero);

        ResultSet rs = ps.executeQuery();

        mostrarResultados(rs);

    } catch (Exception e) {

        System.out.println("Error: " + e.getMessage());
    }
}

// ============================================================
//  MODIFICAR
// ============================================================

static void modificarItem() {

    int id = leerEnteroSeguro("ID a modificar: ", 1, 99999);

    scanner.nextLine();

    System.out.print("Nuevo titulo: ");
    String titulo = scanner.nextLine();

    String sql = """
        UPDATE favoritos
        SET titulo=?
        WHERE id=? AND usuario_id=?
    """;

    try (
        Connection con = conectar();
        PreparedStatement ps = con.prepareStatement(sql)
    ) {

        ps.setString(1, titulo);
        ps.setInt(2, id);
        ps.setInt(3, sesionId);

        int filas = ps.executeUpdate();

        if (filas > 0) {
            System.out.println("Registro actualizado");
        } else {
            System.out.println("No encontrado");
        }

    } catch (Exception e) {

        System.out.println("Error: " + e.getMessage());
    }
}

// ============================================================
//  ELIMINAR
// ============================================================

static void eliminarItem() {

    int id = leerEnteroSeguro("ID a eliminar: ", 1, 99999);

    scanner.nextLine();

    System.out.print("Confirmar (s/n): ");
    String confirmar = scanner.nextLine();

    if (!confirmar.equalsIgnoreCase("s")) {
        return;
    }

    String sql = """
        DELETE FROM favoritos
        WHERE id=? AND usuario_id=?
    """;

    try (
        Connection con = conectar();
        PreparedStatement ps = con.prepareStatement(sql)
    ) {

        ps.setInt(1, id);
        ps.setInt(2, sesionId);

        int filas = ps.executeUpdate();

        if (filas > 0) {
            System.out.println("Eliminado");
        } else {
            System.out.println("No encontrado");
        }

    } catch (Exception e) {

        System.out.println("Error: " + e.getMessage());
    }
}

// ============================================================
//  ESTADISTICAS
// ============================================================

static void mostrarEstadisticas() {

    String sql = """
        SELECT
        COUNT(*) total,
        AVG(calificacion) promedio
        FROM favoritos
        WHERE usuario_id=?
    """;

    try (
        Connection con = conectar();
        PreparedStatement ps = con.prepareStatement(sql)
    ) {

        ps.setInt(1, sesionId);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            System.out.println("\n===== ESTADISTICAS =====");
            System.out.println("Total: " + rs.getInt("total"));
            System.out.println("Promedio: " + rs.getDouble("promedio"));
        }

    } catch (Exception e) {

        System.out.println("Error: " + e.getMessage());
    }
}

// ============================================================
//  MOSTRAR RESULTADOS
// ============================================================

static void mostrarResultados(ResultSet rs) throws SQLException {

    while (rs.next()) {

        System.out.println("\nID: " + rs.getInt("id"));
        System.out.println("Titulo: " + rs.getString("titulo"));
        System.out.println("Tipo: " + rs.getString("tipo"));
        System.out.println("Genero: " + rs.getString("genero"));
        System.out.println("Anio: " + rs.getInt("anio"));
        System.out.println("Calificacion: " + rs.getInt("calificacion"));
    }
}

// ============================================================
//  UTILIDADES
// ============================================================

static int leerEnteroSeguro(String mensaje, int min, int max) {

    int numero;

    while (true) {

        try {

            System.out.print(mensaje);
            numero = Integer.parseInt(scanner.nextLine());

            if (numero >= min && numero <= max) {
                return numero;
            }

            System.out.println("Fuera de rango");

        } catch (Exception e) {

            System.out.println("Ingrese un numero valido");
        }
    }
}

static void limpiarConsola() {

    for (int i = 0; i < 20; i++) {
        System.out.println();
    }
}

static void mostrarBienvenida() {

    System.out.println("==================================");
    System.out.println("       TKromas - pelisSeries");
    System.out.println("==================================");
}

static void filtrarCalificacion() {
    System.out.println("Funcion en desarrollo");
}

static void mostrarBasadosEnLibros() {
    System.out.println("Funcion en desarrollo");
}
´´´

## Issues activos

- [#3](../../issues/3) Año máximo hardcoded — hacer dinámico
- [#2](../../issues/2) Implementar exportar lista a .txt
- [#1](../../issues/1) Cancelar modificación igual actualiza BD
