package app.eventostaw.dao;

import app.eventostaw.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Integer> {
    @Query("SELECT e from Evento e where upper(e.titulo) LIKE CONCAT('%', upper(:busqueda) , '%')" +
            "or upper(e.descripcion) LIKE CONCAT('%', upper(:busqueda), '%')")
    List<Evento> findByBusqueda(@Param("busqueda") String busqueda);
}