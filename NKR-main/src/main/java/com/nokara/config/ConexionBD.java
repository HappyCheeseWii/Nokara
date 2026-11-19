package com.nokara.config;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConexionBD {

    public static Connection getConnection() throws SQLException {
        try {
            DataSource dataSource = InitialContext.doLookup("java:comp/env/jdbc/NokaraDB");
            return dataSource.getConnection();
        } catch (NamingException e) {
            throw new SQLException("No se pudo encontrar NokaraDB en TomEE.", e);
        }
    }
}
