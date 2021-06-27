package app.eventostaw.dao;

import app.eventostaw.entity.Evento;
import app.eventostaw.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Integer> {
    @Query("SELECT e from Evento e where upper(e.titulo) LIKE CONCAT('%', upper(:busqueda) , '%')" +
            "or upper(e.descripcion) LIKE CONCAT('%', upper(:busqueda), '%')")
    List<Evento> findByBusqueda(@Param("busqueda") String busqueda);

    @Query("SELECT e FROM Evento e WHERE e.idCreador = :idCreador")
    public List<Evento> findByIdCreador(@Param("idCreador") Integer idCreador);

    @Query("SELECT e FROM Evento e WHERE e.descripcion LIKE CONCAT('%', :descripcion , '%')")
    public List<Evento> findBySimilarDescripcion(String descripcion);

    @Query("SELECT e FROM Evento e WHERE e.coste >= :filtroMin")
    public List<Evento> findByRangoMinPrecio(Integer filtroMin);

    @Query("SELECT e FROM Evento e WHERE e.coste <= :filtroMax")
    public List<Evento> findByRangoMaxPrecio(Integer filtroMax);

    @Query("SELECT e FROM Evento e WHERE e.titulo LIKE CONCAT('%', :filtroTitulo , '%')")
    public List<Evento> findBySimilarTitulo(String filtroTitulo);

    @Query("SELECT e FROM Evento e ORDER BY e.idEvento")
    public List<Evento> findAllByIDOrdeando();
}