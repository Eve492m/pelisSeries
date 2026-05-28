-- ============================================================
-- PelisSeries — Script de base de datos
-- Versión: 0.0.1 Alpha
-- Equipo TKromas
-- ============================================================

CREATE DATABASE IF NOT EXISTS bd_pelisseries
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE bd_pelisseries;

-- ------------------------------------------------------------
-- Tabla: usuarios
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS usuarios (
  id           INT AUTO_INCREMENT PRIMARY KEY,
  nombre       VARCHAR(100) NOT NULL,
  username     VARCHAR(50)  NOT NULL UNIQUE,
  password_hash VARCHAR(255) NOT NULL,
  creado_en    TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ------------------------------------------------------------
-- Tabla: peliculas
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS peliculas (
  id           INT AUTO_INCREMENT PRIMARY KEY,
  titulo       VARCHAR(200) NOT NULL,
  genero       VARCHAR(100),
  anio         INT CHECK (anio BETWEEN 1888 AND YEAR(CURDATE())),
  calificacion DECIMAL(2,1) CHECK (calificacion BETWEEN 1 AND 5),
  usuario_id   INT,
  creado_en    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);

-- ------------------------------------------------------------
-- Tabla: series
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS series (
  id           INT AUTO_INCREMENT PRIMARY KEY,
  titulo       VARCHAR(200) NOT NULL,
  genero       VARCHAR(100),
  anio_inicio  INT CHECK (anio_inicio BETWEEN 1888 AND YEAR(CURDATE())),
  temporadas   INT DEFAULT 1,
  calificacion DECIMAL(2,1) CHECK (calificacion BETWEEN 1 AND 5),
  usuario_id   INT,
  creado_en    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);

-- ------------------------------------------------------------
-- Tabla: favoritos
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS favoritos (
  id           INT AUTO_INCREMENT PRIMARY KEY,
  usuario_id   INT NOT NULL,
  tipo         ENUM('pelicula', 'serie') NOT NULL,
  contenido_id INT NOT NULL,
  agregado_en  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);

-- ------------------------------------------------------------
-- Datos de prueba
-- ------------------------------------------------------------
INSERT INTO usuarios (nombre, username, password_hash) VALUES
  ('Evelyn Sánchez', 'evelyn', SHA2('1234', 256)),
  ('Diego Hernandez', 'diego', SHA2('1234', 256));

INSERT INTO peliculas (titulo, genero, anio, calificacion, usuario_id) VALUES
  ('Inception', 'Ciencia Ficción', 2010, 5.0, 1),
  ('The Dark Knight', 'Acción', 2008, 5.0, 1),
  ('Interstellar', 'Ciencia Ficción', 2014, 4.5, 2);

INSERT INTO series (titulo, genero, anio_inicio, temporadas, calificacion, usuario_id) VALUES
  ('Breaking Bad', 'Drama', 2008, 5, 5.0, 1),
  ('Stranger Things', 'Terror', 2016, 4, 4.5, 2);
