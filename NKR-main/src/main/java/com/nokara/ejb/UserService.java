
package com.nokara.ejb;

import com.nokara.models.Usuario;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.mindrot.jbcrypt.BCrypt;

@Stateless
public class UserService {

    @PersistenceContext(unitName = "NokaraPU")
    private EntityManager em;

    public Usuario buscarPorCorreo(String email) {
        return em.createQuery(
                        "SELECT u FROM Usuario u WHERE u.email = :email",
                        Usuario.class
                )
                .setParameter("email", email)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    public boolean validarPassword(Usuario usuario, String password) {
        if (usuario == null ||
                usuario.getPassword() == null ||
                password == null) {
            return false;
        }

        return BCrypt.checkpw(password, usuario.getPassword());
    }

    public boolean existeCorreo(String email) {
        Long cantidad = em.createQuery(
                        "SELECT COUNT(u) FROM Usuario u WHERE u.email = :email",
                        Long.class
                )
                .setParameter("email", email)
                .getSingleResult();

        return cantidad > 0;
    }

    public boolean registrar(Usuario usuario) {
        if (usuario == null || existeCorreo(usuario.getEmail())) {
            return false;
        }

        String passwordHash = BCrypt.hashpw(
                usuario.getPassword(),
                BCrypt.gensalt()
        );

        usuario.setPassword(passwordHash);
        em.persist(usuario);

        return true;
    }
}
