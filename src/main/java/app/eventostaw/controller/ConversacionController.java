package app.eventostaw.controller;

import app.eventostaw.dao.ConversacionRepository;
import app.eventostaw.dao.UsuarioRepository;
import app.eventostaw.entity.Conversacion;
import app.eventostaw.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ConversacionController {

    private UsuarioRepository usuarioRepository;
    private ConversacionRepository conversacionRepository;

    public ConversacionRepository getConversacionRepository() { return conversacionRepository; }

    public UsuarioRepository getUsuarioRepository() {
        return usuarioRepository;
    }

    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository)
    {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/conversaciones")
    public String conversaciones(HttpSession session, Model model)
    {
        List<Usuario> listaUsuarios = usuarioRepository.findAll();

        List<Usuario> listaTeleop = new ArrayList<Usuario>();
        for (Usuario u : listaUsuarios)
        {
            if (u.getRolesByRol().getIdRol()==4)
            {
                listaTeleop.add(u);
            }
        }

        Usuario uff = (Usuario)session.getAttribute("usuario");
        Usuario user = usuarioRepository.findByIdUsuario(uff.getIdUsuario());
        session.setAttribute("usuario", user);
        model.addAttribute("usuario", user);
        model.addAttribute("listaTeleop", listaTeleop);

        return "conversaciones";
    }

    @GetMapping("/conversacion/{id}")
    public String conversacion(@PathVariable("id") Integer id, Model model, HttpSession session)
    {
        Usuario user = (Usuario) session.getAttribute("usuario");
        Conversacion conversacion;
        conversacion = conversacionRepository.findByIdConversacion(id);
        model.addAttribute("conversacion", conversacion);
        return "conversacion";
    }

}


