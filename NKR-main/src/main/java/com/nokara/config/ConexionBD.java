package com.nokara.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL =
            "jdbc:mariadb://localhost:3306/nokara";

    private static final String USUARIO = "root";

    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se encuentra el controlador de MariaDB en tiempo de ejecución.", e);
        }

        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }
}
