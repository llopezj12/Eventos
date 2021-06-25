package app.eventostaw;

import app.eventostaw.dao.UsuarioRepository;
import app.eventostaw.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EventosTawApplication {
    @Autowired
    private static UsuarioRepository usuarioRepository;
    public static void main(String[] args) {
        SpringApplication.run(EventosTawApplication.class, args);
    }

}
