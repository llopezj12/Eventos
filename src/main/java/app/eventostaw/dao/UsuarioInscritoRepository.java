package app.eventostaw.dao;

import app.eventostaw.entity.UsuarioInscrito;
import app.eventostaw.entity.UsuarioInscritoPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioInscritoRepository extends JpaRepository<UsuarioInscrito, UsuarioInscritoPK> {
    List<UsuarioInscrito> findAllUsuarioInscritoByIdUsuario(int idUsuario);
}