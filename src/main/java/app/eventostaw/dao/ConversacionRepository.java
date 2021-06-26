package app.eventostaw.dao;

import app.eventostaw.entity.Conversacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConversacionRepository extends JpaRepository<Conversacion,Integer> {
    Conversacion findByIdConversacion(Integer id);

    @Query("SELECT c from Conversacion c where c.usuarioByIdUsuario1.nombre LIKE CONCAT('%', :busqueda, '%')" +
            "or c.usuarioByIdUsuario1.apellidos LIKE CONCAT('%', :busqueda, '%')" +
            "or c.usuarioByIdUsuario2.nombre LIKE CONCAT('%', :busqueda, '%')" +
            "or c.usuarioByIdUsuario2.apellidos LIKE CONCAT('%', :busqueda, '%')")
    List<Conversacion> findByBusqueda(@Param("busqueda") String busqueda);


}
