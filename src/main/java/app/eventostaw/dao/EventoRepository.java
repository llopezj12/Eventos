package app.eventostaw.dao;

import app.eventostaw.entity.Evento;
import app.eventostaw.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Integer> {
    @Query("SELECT e FROM Evento e WHERE e.idCreador = :idCreador")
    public List<Evento> findByIdCreador(@Param("idCreador") Integer idCreador);
}