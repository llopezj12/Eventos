package app.eventostaw.dao;

import app.eventostaw.entity.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensajeRepository extends JpaRepository<Mensaje,Integer> {
}
