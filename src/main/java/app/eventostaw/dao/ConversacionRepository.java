package app.eventostaw.dao;

import app.eventostaw.entity.Conversacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConversacionRepository extends JpaRepository<Conversacion,Integer> {
    Conversacion findByIdConversacion(Integer id);

    @Query("SELECT c from Conversacion c where upper(c.usuarioByIdUsuario1.nombre) LIKE CONCAT('%', upper(:busqueda), '%')" +
            "or upper(c.usuarioByIdUsuario1.apellidos) LIKE CONCAT('%', upper(:busqueda), '%')" +
            "or upper(c.usuarioByIdUsuario2.nombre) LIKE CONCAT('%', upper(:busqueda), '%')" +
            "or upper(c.usuarioByIdUsuario2.apellidos) LIKE CONCAT('%', upper(:busqueda), '%')")
    List<Conversacion> findByBusqueda(@Param("busqueda") String busqueda);


}
