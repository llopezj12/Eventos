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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @GetMapping("/registro")
    public String registro() {
        return "registro";
    }
    @GetMapping("/tryregistro")
    public String tryregistro(@RequestParam("nombre") String nombre, @RequestParam("apellido") String apellido, @RequestParam("ciudad") String ciudad,
    @RequestParam("domicilio") String domicilio, @RequestParam("sexo") String sexo, @RequestParam("nacimiento") String nacimiento, @RequestParam("email") String email,
    @RequestParam("password") String password, Model model, HttpSession session) {
        String res = "redirect:/login";
        Date fecha = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        boolean error = false;
        String errorMsg = "";

        model.addAttribute("nombre", nombre);
        model.addAttribute("apellido", apellido);
        model.addAttribute("ciudad", ciudad);
        model.addAttribute("domicilio", domicilio);
        model.addAttribute("sexo", sexo);
        model.addAttribute("fecha", nacimiento);
        model.addAttribute("email", email);

        if (nombre.isEmpty()) {
            error = true;
            errorMsg += " Nombre vacío";
        }
        if (apellido.isEmpty()) {
            error = true;
            errorMsg += " Apellido vacío";
        }
        if (ciudad.isEmpty()) {
            error = true;
            errorMsg += " Ciudad vacía";
        }
        if (domicilio.isEmpty()) {
            error = true;
            errorMsg += " Domicilio vacío";
        }
        if (sexo == null) {
            error = true;
            errorMsg += " Sexo no especificado";
        }
        if (nacimiento.isEmpty()) {
            error = true;
            errorMsg += " Fecha de nacimiento no especificada";
        }
        if (email.isEmpty()) {
            error = true;
            errorMsg += " E-mail vacío";
        }
        if (password.isEmpty()) {
            error = true;
            errorMsg += " Contraseña vacía";
        }

        try {
            fecha = df.parse(nacimiento);
        } catch (Exception e) {
            error = true;
        }
        model.addAttribute("error", error);
        model.addAttribute("errorMsg", errorMsg);

        if (!error) {
            Usuario usuario = new Usuario();
            usuario.setNombre(nombre);
            usuario.setApellidos(apellido);
            usuario.setCiudad(ciudad);
            usuario.setEmail(email);
            String genero;
            if (sexo.equals("male")) {
                genero = "M";
            } else {
                genero = "F";
            }
            usuario.setGenero(genero);
            usuario.setPassword(password);
            usuario.setNacimiento(new java.sql.Date(fecha.getTime()));
            usuario.setDomicilio(domicilio);
            usuario.setRolesByRol(rolesRepository.findById(3).get());

            usuarioRepository.save(usuario);

        } else {
            res = "registro";
        }

        return res;
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
