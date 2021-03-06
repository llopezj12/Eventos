package app.eventostaw.controller;

import app.eventostaw.dao.ConversacionRepository;
import app.eventostaw.dao.MensajeRepository;
import app.eventostaw.dao.UsuarioRepository;
import app.eventostaw.entity.Conversacion;
import app.eventostaw.entity.Mensaje;
import app.eventostaw.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ConversacionController {

    private UsuarioRepository usuarioRepository;
    private ConversacionRepository conversacionRepository;
    private MensajeRepository mensajeRepository;

    public MensajeRepository getMensajeRepository() { return mensajeRepository; }

    public ConversacionRepository getConversacionRepository() { return conversacionRepository; }

    public UsuarioRepository getUsuarioRepository() {
        return usuarioRepository;
    }

    @Autowired
    public void setMensajeRepository(MensajeRepository mensajeRepository)
    {
        this.mensajeRepository = mensajeRepository;
    }
    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository)
    {
        this.usuarioRepository = usuarioRepository;
    }

    @Autowired
    public void setConversacionRepository(ConversacionRepository conversacionRepository)
    {
        this.conversacionRepository = conversacionRepository;
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

        List<Conversacion> lista = new ArrayList<Conversacion>();

        try {
            lista = (List<Conversacion>)conversacionRepository.findAll();
        } catch (NullPointerException e)
        {

        }

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

            try {
                if (conv.getMensajesByIdConversacion().isEmpty() )
                {
                    List<Mensaje> m = new ArrayList<Mensaje>();
                    conv.setMensajesByIdConversacion(m);
                }
            } catch (Exception e)
            {
                List<Mensaje> m = new ArrayList<Mensaje>();
                conv.setMensajesByIdConversacion(m);
            }if (conv.getMensajesByIdConversacion().isEmpty() )

            model.addAttribute("conversacion", conv);

            return "conversacion";

        }
        else {

            model.addAttribute("conversacion", found);
            return "conversacion";


        }
    }
    @GetMapping("/mensaje")
    public String mensaje(@RequestParam("conver") int conver, @RequestParam("msg") String msg, Model model, HttpSession session)
    {
        Usuario user = (Usuario) session.getAttribute("usuario");
        Conversacion conversacion = conversacionRepository.findByIdConversacion(conver);

        if (msg != "")
        {
            Mensaje mensaje = new Mensaje();
            Date date = new Date();
            String hora = new SimpleDateFormat("HH").format(date);
            String minuto = new SimpleDateFormat("mm").format(date);
            mensaje.setFecha(new java.sql.Date(date.getTime()));
            mensaje.setHora(new Integer(hora));
            mensaje.setMinuto(new Integer(minuto));
            mensaje.setConversacionByIdConversacion(conversacion);
            mensaje.setUsuarioByIdUsuario(user);
            mensaje.setMensaje(msg);

            mensajeRepository.save(mensaje);
            List<Mensaje> lista = (List<Mensaje>) conversacion.getMensajesByIdConversacion();
            conversacion.setMensajesByIdConversacion(lista);
            Usuario user1 = conversacion.getUsuarioByIdUsuario1();
            Usuario user2 = conversacion.getUsuarioByIdUsuario2();
            List<Conversacion> lista1 = (List<Conversacion>) user1.getConversacionsByIdUsuario();
            List<Conversacion> lista2 = (List<Conversacion>) user2.getConversacionsByIdUsuario_0();

            for (int i = 0; i <lista1.size(); i++)
            {
                if (conversacion.equals(lista1.get(i)))
                {
                    lista1.set(i, conversacion);
                }
            }

            for (int j = 0; j <lista2.size(); j++)
            {
                if (conversacion.equals(lista2.get(j)))
                {
                    lista2.set(j, conversacion);
                }
            }
            user1.setConversacionsByIdUsuario(lista1);
            user2.setConversacionsByIdUsuario_0(lista2);

            usuarioRepository.save(user1);
            usuarioRepository.save(user2);
            conversacionRepository.save(conversacion);
        }


        Usuario uff = (Usuario)session.getAttribute("usuario");
        Usuario usuariosesion = usuarioRepository.findByIdUsuario(uff.getIdUsuario());
        session.setAttribute("usuario", usuariosesion);

        model.addAttribute("conversacion", conversacion);
        return "conversacion";

    }

    @GetMapping("/menuConversaciones")
    public String menuConversaciones(@RequestParam("busqueda") String busqueda, Model model)
    {
        List<Conversacion> listaconv;
        if (busqueda == "" ||busqueda== null)
        {
            listaconv = conversacionRepository.findAll();
        }
        else
        {
            listaconv = conversacionRepository.findByBusqueda(busqueda);
        }
        List<Usuario> listausuario = usuarioRepository.findAll();
        model.addAttribute("busqueda", busqueda);
        model.addAttribute("listaUsuario", listausuario);
        model.addAttribute("listacontiene", listaconv);
        return "menuConversaciones";

    }

    @GetMapping("/menuConversaciones2")
    public String menuConversaciones(Model model)
    {
        List<Conversacion> listaconv;
        String busqueda = "";
            listaconv = conversacionRepository.findAll();

        List<Usuario> listausuario = usuarioRepository.findAll();
        model.addAttribute("busqueda", busqueda);
        model.addAttribute("listaUsuario", listausuario);
        model.addAttribute("listacontiene", listaconv);
        return "menuConversaciones";

    }


    @GetMapping("/borrarConversacion/{id}")
    public String borrarConversacion(@PathVariable("id") Integer id, Model model, HttpSession session)
    {
        Conversacion c = conversacionRepository.findByIdConversacion(id);

        List<Mensaje> listaMensajes = (List<Mensaje>) c.getMensajesByIdConversacion();
        for(Mensaje m : listaMensajes){
            this.mensajeRepository.delete(m);
        }
        Usuario user1 = c.getUsuarioByIdUsuario1();
        Usuario user2 = c.getUsuarioByIdUsuario2();

        List<Conversacion> list1 = (List<Conversacion>) user1.getConversacionsByIdUsuario();
        List<Conversacion> list2 = (List<Conversacion>) user2.getConversacionsByIdUsuario_0();

        list1.remove(c);
        list2.remove(c);

        user1.setConversacionsByIdUsuario(list1);
        user2.setConversacionsByIdUsuario_0(list2);

        usuarioRepository.save(user1);
        usuarioRepository.save(user2);

        conversacionRepository.delete(c);

        List<Usuario> listausuario = usuarioRepository.findAll();
        List<Conversacion> listaconv = conversacionRepository.findAll();

        Usuario uff = (Usuario)session.getAttribute("usuario");
        Usuario user = usuarioRepository.findByIdUsuario(uff.getIdUsuario());
        session.setAttribute("usuario", user);



        return "redirect:/menuConversaciones2";
    }

    @GetMapping("teleopConversacion/{id}")
    public String TeleopConversacion(@PathVariable("id") Integer id, Model model)
    {
        Conversacion c = this.conversacionRepository.findByIdConversacion(id);
        model.addAttribute("conversacion", c);
        return "teleopConversacion";
    }

}


