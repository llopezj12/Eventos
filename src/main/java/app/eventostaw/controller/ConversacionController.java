package app.eventostaw.controller;

import app.eventostaw.dao.UsuarioRepository;
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

    public UsuarioRepository getUsuarioRepository() {
        return usuarioRepository;
    }

    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository)
    {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/conversaciones/{id}")
    public String conversaciones(@PathVariable("id") Integer id, Model model)
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



        //Usuario uff = (Usuario)session.getAttribute("usuario");
        Usuario user = usuarioRepository.findByIdUsuario(id);
        //session.setAttribute("usuario", user);
        model.addAttribute("usuario", user);
        model.addAttribute("listaTeleop", listaTeleop);
        //request.setAttribute("listaTeleop", listaTeleop);
        //RequestDispatcher rd = request.getRequestDispatcher("conversaciones.jsp");
        //rd.forward(request, response);
        return "conversaciones";
    }

}


