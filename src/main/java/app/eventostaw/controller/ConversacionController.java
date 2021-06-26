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

    @GetMapping("/crearConversacion/{id}")
    public String crearConversacion(@PathVariable("id") Integer id, Model model, HttpSession session)
    {
        Usuario uses = (Usuario) session.getAttribute("usuario");
        Usuario usid = usuarioRepository.findByIdUsuario(id);
        Boolean encontrado = false;
        Conversacion found = new Conversacion();

        List<Conversacion> lista = conversacionRepository.findAll();
        for (Conversacion c : lista)
        {
            if ((c.getUsuarioByIdUsuario1().getIdUsuario() == uses.getIdUsuario()
                    && c.getUsuarioByIdUsuario2().getIdUsuario() == usid.getIdUsuario())
                    || (c.getUsuarioByIdUsuario1().getIdUsuario() == usid.getIdUsuario()
                    && c.getUsuarioByIdUsuario2().getIdUsuario() == uses.getIdUsuario()))
            {
                encontrado = true;
                found = c;

            }
        }
        if (!encontrado)
        {
            Conversacion conv = new Conversacion();
            if (uses.getRolesByRol().getIdRol()==4)
            {
                conv.setUsuarioByIdUsuario1(uses);
                conv.setUsuarioByIdUsuario2(usid);
                List<Conversacion> list1 = (List<Conversacion>) uses.getConversacionsByIdUsuario();
                List<Conversacion> list2 = (List<Conversacion>) usid.getConversacionsByIdUsuario_0();
                list1.add(conv);
                list2.add(conv);
                uses.setConversacionsByIdUsuario(list1);
                usid.setConversacionsByIdUsuario_0(list2);
            }
            else {
                conv.setUsuarioByIdUsuario1(usid);
                conv.setUsuarioByIdUsuario2(uses);
                List<Conversacion> list1 = (List<Conversacion>) usid.getConversacionsByIdUsuario();
                List<Conversacion> list2 = (List<Conversacion>) uses.getConversacionsByIdUsuario_0();
                list1.add(conv);
                list2.add(conv);
                usid.setConversacionsByIdUsuario(list1);
                uses.setConversacionsByIdUsuario_0(list2);
            }
            conversacionRepository.save(conv);
            usuarioRepository.save(uses);
            usuarioRepository.save(usid);
            model.addAttribute("conversacion", conv);

            return "conversacion";
            /*conversacionFacade.create(conv);
            usuarioFacade.edit(uses);
            usuarioFacade.edit(usid);
            request.setAttribute("conversacion", conv);

            RequestDispatcher rd = request.getRequestDispatcher("conversacion.jsp");
            rd.forward(request, response);*/
        }
        else {

            model.addAttribute("conversacion", found);
            return "conversacion";

            /*request.setAttribute("conversacion", found);
            RequestDispatcher rd = request.getRequestDispatcher("conversacion.jsp");
            rd.forward(request, response);*/
        }
    }

}


