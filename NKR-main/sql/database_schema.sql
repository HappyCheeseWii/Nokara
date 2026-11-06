-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS nokara;
USE nokara;

-- Tabla de Usuarios (Login y Registro)
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL
    );

-- Tabla de Casos
CREATE TABLE IF NOT EXISTS cases (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

-- Tabla de Documentos / Archivos
CREATE TABLE IF NOT EXISTS documents (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    case_id BIGINT NOT NULL,
    original_file_name VARCHAR(255) NOT NULL,
    custom_title VARCHAR(150) NOT NULL,
    file_path VARCHAR(255) NOT NULL,
    file_description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_case_document FOREIGN KEY (case_id) REFERENCES cases(id) ON DELETE CASCADE
    );