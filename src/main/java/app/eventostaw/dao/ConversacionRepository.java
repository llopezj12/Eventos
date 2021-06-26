package app.eventostaw.dao;

import app.eventostaw.entity.Conversacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversacionRepository extends JpaRepository<Conversacion,Integer> {
    Conversacion findByIdConversacion(Integer id);
}
