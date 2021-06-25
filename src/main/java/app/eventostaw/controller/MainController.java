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
import org.springframework.web.bind.annotation.RequestParam;

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
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/cerrarsesion")
    public String cerrarSesion(HttpSession session) {
        session.setAttribute("usuario", null);
        return "redirect:/";
    }
    @GetMapping("/trylogin")
    public String tryLogin (@RequestParam("correo") String stremail, @RequestParam("contrasena") String strpwd, Model model, HttpSession session) {
        Usuario user = null;
        String res = "redirect:/";
        try {
            user = usuarioRepository.findByEmail(stremail);
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }

        Boolean error = false;
        String errorMsg = "";

        if (stremail == null || stremail.isEmpty())
        {
            error = true;
            errorMsg = "Inserte Email";

        }
        if (strpwd == null || strpwd.isEmpty())
        {
            if (error){
                errorMsg += " y Contraseña";
            } else {
                error = true;
                errorMsg = "Inserte Contraseña";
            }
        }
        if (!error && user != null && user.getPassword().equals(strpwd)) {
            session.setAttribute("usuario", user);
        } else if (!error) {
            error = true;
            errorMsg = "Email o Contraseña invalido";
            model.addAttribute("error", error);
            model.addAttribute("errorMsg", errorMsg);
            res = "login";
        } else {
            model.addAttribute("error", error);
            model.addAttribute("errorMsg", errorMsg);
            res = "login";
        }
        return res;
    }
}
