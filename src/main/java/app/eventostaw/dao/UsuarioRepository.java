package app.eventostaw.dao;

import app.eventostaw.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByEmail(String email);

    @Query("SELECT u from Usuario u where u.idUsuario = :id")
    public Usuario findByIdUsuario(@Param("id") Integer id);
}