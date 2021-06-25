package app.eventostaw.controller;

import app.eventostaw.dao.EventoRepository;
import app.eventostaw.dao.RolesRepository;
import app.eventostaw.dao.UsuarioRepository;
import app.eventostaw.entity.Evento;
import app.eventostaw.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {
    private RolesRepository rolesRepository;
    private UsuarioRepository usuarioRepository;
    private EventoRepository eventoRepository;

    @Autowired
    public void setRolesRepository(RolesRepository rolesRepository){
        this.rolesRepository = rolesRepository;
    }

    @Autowired
    public void setEventoRepository(EventoRepository eventoRepository){
        this.eventoRepository = eventoRepository;
    }

    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/adminlistar")
    public String listar(Model model, HttpSession session){
        List<Evento> listaE = this.eventoRepository.findAll();
        List<Usuario> listaU = this.usuarioRepository.findAll();
        model.addAttribute("listaE",listaE);
        model.addAttribute("lista",listaU);
        return "AdminListar";
    }

    @GetMapping("/adminEliminarUsuario/{id}")
    public String adminEliminarUsuario(@PathVariable("id")Integer id){
        Optional<Usuario> u = this.usuarioRepository.findById(id);
        if(u.isPresent()){
            Usuario aux = u.get();
            this.usuarioRepository.delete(aux);
        }
        return "redirect:/adminlistar";
    }

    @GetMapping("/adminEliminarEvento/{id}")
    public String adminEliminarEvento(@PathVariable("id") Integer id){
        Optional<Evento> e = this.eventoRepository.findById(id);
        if(e.isPresent()){
            Evento aux = e.get();
            this.eventoRepository.delete(aux);
        }
        return "redirect:/adminlistar";
    }
}