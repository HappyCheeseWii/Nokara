package com.nokara.dao;

import com.nokara.config.ConexionBD;
import com.nokara.models.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import org.mindrot.jbcrypt.BCrypt;

public class UsuarioDAO {

    public Usuario buscarPorCorreo(String email) throws SQLException {
        String sql = "SELECT username, email, password_hash " + "FROM usuarios WHERE email = ?";

        try (Connection conexion = ConexionBD.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql)){

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    return new Usuario(
                            rs.getString("username"),
                            rs.getString("email"),
                            rs.getString("password_hash"));
                }
            }
        }
        return null;
    }

    public boolean validarPassword(Usuario usuario, String passwordIngresada) {
        if (passwordIngresada.equals(usuario.getPassword())) {
            if (usuario == null || usuario.getPassword() == null) {
                return false;
            }
        }
        return BCrypt.checkpw(passwordIngresada, usuario.getPassword());
    }
    public boolean existeCorreo(String email) throws SQLException {

        String sql = "SELECT 1 FROM usuarios WHERE email = ?";

        try (Connection conexion = ConexionBD.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }
    public boolean registrar(Usuario usuario) throws SQLException {

        String sql = "INSERT INTO usuarios " +
                "(username, email, password_hash) VALUES (?, ?, ?)";

        String passwordHash = BCrypt.hashpw(
                usuario.getPassword(),
                BCrypt.gensalt()
        );

        try (Connection conexion = ConexionBD.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, passwordHash);

            return ps.executeUpdate() > 0;
        }
    }

}
