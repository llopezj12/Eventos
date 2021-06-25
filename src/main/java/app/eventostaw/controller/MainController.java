package app.eventostaw.controller;

import app.eventostaw.dao.RolesRepository;
import app.eventostaw.dao.UsuarioRepository;
import app.eventostaw.entity.Roles;
import app.eventostaw.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @GetMapping("/")
    public String doInit (Model model) {
        //Roles rol = new Roles();
        //rol.setIdRol(20);
        //rol.setDescripcion("Refachero");
        //rolesRepository.save(rol);
        model.addAttribute("lista", rolesRepository.findAll().toString());
        return "prueba";
    }
}
