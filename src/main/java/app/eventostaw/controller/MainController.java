package app.eventostaw.controller;

import app.eventostaw.dao.EventoRepository;
import app.eventostaw.dao.RolesRepository;
import app.eventostaw.dao.UsuarioRepository;
import app.eventostaw.entity.Evento;
import app.eventostaw.entity.Roles;
import app.eventostaw.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @GetMapping("/")
    public String doInit (Model model, HttpSession session) {
        List<Evento> eventosList = null;
        try {
            eventosList = eventoRepository.findAll();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        model.addAttribute("eventosList", eventosList);
        return "home";
    }
}
