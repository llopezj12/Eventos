package es.eventos.eventos.controller;

import es.eventos.eventos.dao.EventoRepository;
import es.eventos.eventos.dao.UsuarioRepository;
import es.eventos.eventos.entity.Evento;
import es.eventos.eventos.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminController {
    private UsuarioRepository usuarioRepository;
    private EventoRepository eventoRepository;

    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Autowired
    public void setEventoRepository(EventoRepository eventoRepository){
        this.eventoRepository = eventoRepository;
    }

    @GetMapping("/AdminListar")
    public String adminListar(Model model, HttpSession session){
        List<Usuario> listaU = this.usuarioRepository.findAll();
        List<Evento> listaE = this.eventoRepository.findAll();
        model.addAttribute("lista",listaU);
        model.addAttribute("listaEvento",listaE);
        return "AdminListar";
    }

}
