package app.eventostaw.controller;

import app.eventostaw.dao.*;
import app.eventostaw.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {
    private RolesRepository rolesRepository;
    private UsuarioRepository usuarioRepository;
    private EventoRepository eventoRepository;
    private MensajeRepository mensajeRepository;
    private ConversacionRepository conversacionRepository;
    private UsuarioInscritoRepository usuarioInscritoRepository;

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

    @Autowired
    public void setMensajeRepository(MensajeRepository mensajeRepository){
        this.mensajeRepository = mensajeRepository;
    }

    @Autowired
    public void setConversacionRepository(ConversacionRepository conversacionRepository){
        this.conversacionRepository = conversacionRepository;
    }

    @Autowired
    public void setUsuarioInscritoRepository(UsuarioInscritoRepository usuarioInscritoRepository){
        this.usuarioInscritoRepository = usuarioInscritoRepository;
    }

    @GetMapping("/adminlistar")
    public String listar(Model model, HttpSession session){
        List<Evento> listaE = this.eventoRepository.findAllByIDOrdeando();
        List<Usuario> listaU = this.usuarioRepository.findAllByIdOrdenado();
        model.addAttribute("listaE",listaE);
        model.addAttribute("lista",listaU);
        return "AdminListar";
    }

    @GetMapping("/adminEliminarUsuario/{id}")
    public String adminEliminarUsuario(@PathVariable("id")Integer id){
        Optional<Usuario> u = this.usuarioRepository.findById(id);
        if(u.isPresent()){
            Usuario aux = u.get();
            List<Conversacion> listaC = (List<Conversacion>) aux.getConversacionsByIdUsuario();
            listaC.addAll(aux.getConversacionsByIdUsuario_0());
            if(listaC != null && !listaC.isEmpty()){
                List<Mensaje> listaM = new ArrayList<Mensaje>();
                for(Conversacion c : listaC){
                    listaM.addAll(c.getMensajesByIdConversacion());
                    for(Mensaje m : listaM){
                        this.mensajeRepository.delete(m);
                    }
                    this.conversacionRepository.delete(c);
                }
            }
            List<UsuarioInscrito> listaUI = this.usuarioInscritoRepository.findAll();
            if(listaUI != null || !listaUI.isEmpty()){
                for(UsuarioInscrito ui : listaUI){
                    if(ui.getIdUsuario() == aux.getIdUsuario()){
                        this.usuarioInscritoRepository.delete(ui);
                    }
                }
            }
            if(aux.getRolesByRol().getIdRol() == 1){
                List<Evento> listaE = this.eventoRepository.findByIdCreador(aux.getIdUsuario());
                if(listaE != null && !listaE.isEmpty()){
                    List<UsuarioInscrito> listaUI2 = this.usuarioInscritoRepository.findAll();
                    if(listaUI2 != null && !listaUI2.isEmpty()){
                        for(UsuarioInscrito u2 : listaUI2){
                            this.usuarioInscritoRepository.delete(u2);
                        }
                    }
                    for(Evento e2 : listaE){
                        this.eventoRepository.delete(e2);
                    }
                }
            }
            this.usuarioRepository.delete(aux);
        }
        return "redirect:/adminlistar";
    }

    @GetMapping("/adminEliminarEvento/{id}")
    public String adminEliminarEvento(@PathVariable("id") Integer id){
        Optional<Evento> e = this.eventoRepository.findById(id);
        if(e.isPresent()){
            Evento aux = e.get();
            List<UsuarioInscrito> listaUIns = this.usuarioInscritoRepository.findAll();
            if(listaUIns != null && !listaUIns.isEmpty()){
                for(UsuarioInscrito usr : listaUIns){
                    if(usr.getIdEvento() == aux.getIdEvento()){
                        this.usuarioInscritoRepository.delete(usr);
                    }
                }
            }
            this.eventoRepository.delete(aux);
        }
        return "redirect:/adminlistar";
    }
    @GetMapping("/redireccionarAgregarUsuario")
    public String redireccionar(Model model){
        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        return "AdminAgregarUsuario";
    }

    /*@PostMapping("/crear")
    public String crearUsuario(@ModelAttribute("usuario") Usuario usuario, BindingResult result, Model model){
        if(result.hasErrors()){
            String error = "Datos err??neos";
            model.addAttribute("error",error);
            return "AdminAgregarUsuario";
        }
        this.usuarioRepository.save(usuario);
        return "adminlistar";
    }*/

    @PostMapping("/crear")
    public String crearUsuario(@RequestParam("nombre") String nombre,
                               @RequestParam("apellidos") String apellido,
                               @RequestParam("pass")String password,
                               @RequestParam("dom")String domicilio,
                               @RequestParam("ciudad") String ciudad,
                               @RequestParam(value = "genero", required = false) String sexo,
                               @RequestParam("email") String email,
                               @RequestParam("fecha") String nacimiento,
                               @RequestParam(value = "rol", required = false) String rol,
                               Model model){
        Optional<Roles> r;
        Roles rolBueno = new Roles();
        boolean error = false;
        String errorMsg = "";
        if(rol == null){
            error = true;
            errorMsg += " Rol no especificado";
        } else{
            r = this.rolesRepository.findById(Integer.parseInt(rol));
            if(r.isPresent()){
                rolBueno = r.get();
            }
        }

        Date fecha = new Date();

        if (nombre.isEmpty()) {
            error = true;
            errorMsg += " Nombre vac??o";
        }
        if (apellido.isEmpty()) {
            error = true;
            errorMsg += " Apellido vac??o";
        }
        if (ciudad.isEmpty()) {
            error = true;
            errorMsg += " Ciudad vac??a";
        }
        if (domicilio.isEmpty()) {
            error = true;
            errorMsg += " Domicilio vac??o";
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
            errorMsg += " E-mail vac??o";
        }
        if (password.isEmpty()) {
            error = true;
            errorMsg += " Contrase??a vac??a";
        }

        if(sexo == null){
            error = true;
            errorMsg += " Genero no especificado";
        }

        model.addAttribute("error", error);
        model.addAttribute("errorMsg", errorMsg);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            fecha = df.parse(nacimiento);
        } catch (ParseException ex) {
            error = true;
        }

        if(!error){
            Usuario usuario = new Usuario();
            usuario.setNombre(nombre);
            usuario.setApellidos(apellido);
            usuario.setCiudad(ciudad);
            usuario.setEmail(email);
            usuario.setGenero(sexo);
            usuario.setPassword(password);
            usuario.setNacimiento(new java.sql.Date(fecha.getTime()));
            usuario.setDomicilio(domicilio);
            usuario.setRolesByRol(rolBueno);
            this.usuarioRepository.save(usuario);
            return "redirect:/adminlistar";
        } else{
            return "AdminAgregarUsuario";
        }
      }

      @GetMapping("/editarUsuario/{id}")
      public String redireccionarEditarUsuario(@PathVariable("id") Integer id, Model model){
        Optional<Usuario> u = this.usuarioRepository.findById(id);
        Usuario usuario = u.get();
        model.addAttribute("usuario", usuario);
        return "AdminEditar";
      }

      @PostMapping("/guardarUsuario")
      public String editarUsuario(@RequestParam("idUsuario") Integer id,
                                  @RequestParam("nombre") String nombre,
                                  @RequestParam("apellidos") String apellido,
                                  @RequestParam("pass")String password,
                                  @RequestParam("dom")String domicilio,
                                  @RequestParam("ciudad") String ciudad,
                                  @RequestParam("genero") String gen,
                                  @RequestParam("gen") String genDefault,
                                  @RequestParam("email") String email,
                                  @RequestParam("fecha") String nacimiento,
                                  @RequestParam(value = "rol", required = false) String r,
                                  @RequestParam("rolDef") String rolDefault,
                                  Model model){
        Optional<Usuario> usuario = this.usuarioRepository.findById(id);
        Usuario u = usuario.get();
        Optional<Roles> rolD = this.rolesRepository.findById(Integer.parseInt(rolDefault));
        Roles rd = rolD.get();
          if(gen != null){
              u.setGenero(gen);
          } else{
              u.setGenero(genDefault);
          }

          if(r != null){
              Optional<Roles> rol = this.rolesRepository.findById(Integer.parseInt(r));
              u.setRolesByRol(rol.get());
          } else{
              u.setRolesByRol(rd);
          }

          Date fecha = new Date();
          DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
          try {
              fecha = df.parse(nacimiento);
          } catch (ParseException ex) {
              System.out.println(ex.getMessage());
          }

          u.setNombre(nombre);
          u.setApellidos(apellido);
          u.setPassword(password);
          u.setDomicilio(domicilio);
          u.setCiudad(ciudad);
          u.setEmail(email);
          u.setNacimiento(new java.sql.Date(fecha.getTime()));
          this.usuarioRepository.save(u);
          return "redirect:/adminlistar";
      }

    @GetMapping("/adminEditarEvento/{id}")
    public String redireccionarEvento(@PathVariable("id")Integer id, Model model){
        Optional<Evento> evento = this.eventoRepository.findById(id);
        Evento e = evento.get();
        List<Usuario> listaEditores = this.usuarioRepository.findByRol(1);
        model.addAttribute("lista", listaEditores);
        model.addAttribute("evento",e);
        return "AdminEditarEvento";
    }

    @PostMapping("/guardarEvento")
    public String guardarEvento(@RequestParam("idEvento") String id,
                                @RequestParam("titulo") String titulo,
                                @RequestParam("date") String fe,
                                @RequestParam("fechaRes") String feReq,
                                @RequestParam("coste") String coste,
                                @RequestParam("asientos") String asiFijo,
                                @RequestParam("asi") String asiFijoDefault,
                                @RequestParam("aforo") String aforo,
                                @RequestParam("entradas") String entradas,
                                @RequestParam("nfilas") String numfilas,
                                @RequestParam("asifil") String asifil,
                                @RequestParam("idcre") String idcre,
                                @RequestParam("desc") String desc,
                                Model model){
        Optional<Evento> e = this.eventoRepository.findById(Integer.parseInt(id));
        Evento evento = new Evento();
        int aux = 0;
        int aux2 = 0;
        if(e.isPresent()){
            evento = e.get();
        }
        Optional<Usuario> usuario = this.usuarioRepository.findById(Integer.parseInt(idcre));
        Usuario u = new Usuario();
        if(usuario.isPresent()){
            u = usuario.get();
        }
        if(asiFijo != null) {
            if (asiFijo.charAt(0) == 'N') {
                numfilas = null;
                asifil = null;
            } else {
                if (numfilas != null) {
                    aux = Integer.parseInt(numfilas);
                }
                if (asifil != null) {
                    aux2 = Integer.parseInt(asifil);
                }
            }
        }
        Date fecha = new Date();
        Date fecha2 = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            fecha = df.parse(fe);
        } catch (ParseException ex) {

        }

        try {
            fecha2 = df.parse(feReq);
        } catch (ParseException ex) {

        }
        evento.setTitulo(titulo);
        evento.setCoste(Integer.parseInt(coste));
        evento.setNumfilas(aux);
        evento.setNumasientosporfila(aux2);
        evento.setDescripcion(desc);
        evento.setAsientosfijos(asiFijo);
        evento.setIdCreador(u.getIdUsuario());
        evento.setEntradas(Integer.parseInt(entradas));
        evento.setAforo(Integer.parseInt(aforo));
        evento.setFecha(new java.sql.Date(fecha.getTime()));
        evento.setFechares(new java.sql.Date(fecha2.getTime()));
        this.eventoRepository.save(evento);
        return "redirect:/adminlistar";
    }

    @GetMapping("/redireccionarAgregarEvento")
    public String redireccionarAgregarEvento(Model model){
        List<Usuario> listaEditores = this.usuarioRepository.findByRol(1);
        model.addAttribute("lista", listaEditores);
        return "AdminAgregarEvento";
    }

    @PostMapping("/crearEvento")
    public String crearEvento(@RequestParam("titulo") String titulo,
                              @RequestParam("date") String fe,
                              @RequestParam("fechaRes") String feReq,
                              @RequestParam("coste") String coste,
                              @RequestParam(value = "asientos", required = false) String asiFijo,
                              @RequestParam("aforo") String aforo,
                              @RequestParam("entradas") String entradas,
                              @RequestParam("nfilas") String numfilas,
                              @RequestParam("asifil") String asifil,
                              @RequestParam(value = "idcre", required = false) String idcre,
                              @RequestParam("desc") String desc,
                              Model model){
        List<Usuario> listaEditores = this.usuarioRepository.findByRol(1);
        model.addAttribute("lista", listaEditores);
        boolean error = false;
        String errorMsg = "";
        int aux = 0;
        int aux2 = 0;
        if(titulo.isEmpty()){
            error = true;
            errorMsg += "Titulo no especificado ";
        }
        if(fe.isEmpty()){
            error = true;
            errorMsg += "Fecha de evento no especificada ";
        }

        if(feReq.isEmpty()){
            error = true;
            errorMsg += "Fecha limite de venta de entradas no especificada ";
        }

        if(coste.isEmpty()){
            error = true;
            errorMsg += "Coste no especificado ";
        }


        if(entradas.isEmpty()){
            error = true;
            errorMsg += "Entradas no especificadas ";
        }

        if(aforo.isEmpty()){
            error = true;
            errorMsg += "Aforo no especificado ";
        }

        if(idcre.isEmpty()){
            error = true;
            errorMsg += "ID del crador no especificado ";
        }

        if(desc.isEmpty()){
            error = true;
            errorMsg += "Descripcion no especificada ";
        }

        Optional<Usuario> usuario = this.usuarioRepository.findById(Integer.parseInt(idcre));
        Usuario u = new Usuario();
        if(usuario.isPresent()){
            u = usuario.get();
        } else{
            error = true;
            errorMsg += "Usuario especiicado no encontrado ";
        }


        Date fecha = new Date();
        Date fecha2 = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            fecha = df.parse(fe);
        } catch (ParseException ex) {
            error = true;
        }

        try {
            fecha2 = df.parse(feReq);
        } catch (ParseException ex) {
            error = true;
        }

        if(asiFijo != null){
            if(asiFijo.charAt(0) == 'N'){
                numfilas = null;
                asifil = null;
            } else{
                if(numfilas == null){
                    error = true;
                    errorMsg += "Numero de filas no especificado ";
                } else{
                    aux = Integer.parseInt(numfilas);
                }

                if(asifil == null){
                    error = true;
                    errorMsg += "Numero de asientos por fila no especificado ";
                } else {
                    aux2 = Integer.parseInt(asifil);
                }
            }
        } else{
            error = true;
            errorMsg += "Asientos fijos no especificados ";
        }

        model.addAttribute("error", error);
        model.addAttribute("errorMsg", errorMsg);

        if(!error){
            Evento evento = new Evento();
            evento.setTitulo(titulo);
            evento.setCoste(Integer.parseInt(coste));
            evento.setNumfilas(aux);
            evento.setNumasientosporfila(aux2);
            evento.setDescripcion(desc);
            evento.setIdCreador(u.getIdUsuario());
            evento.setAsientosfijos(asiFijo);
            evento.setEntradas(Integer.parseInt(entradas));
            evento.setAforo(Integer.parseInt(aforo));
            evento.setFecha(new java.sql.Date(fecha.getTime()));
            evento.setFechares(new java.sql.Date(fecha2.getTime()));
            this.eventoRepository.save(evento);
            return "redirect:/adminlistar";
        } else{
            return "AdminAgregarEvento";
        }
    }

    @PostMapping("/filtrarUsuarios")
    public String filtrarUsuario(@RequestParam("busqueda") String busqueda, Model model){
        List<Usuario> listaUsuarios;
        List<Evento> listaEventos = this.eventoRepository.findAll();
        if (busqueda == "" ||busqueda== null)
        {
            listaUsuarios = this.usuarioRepository.findAll();
        }
        else
        {
            listaUsuarios = this.usuarioRepository.findByBusqueda(busqueda);
        }
        model.addAttribute("busqueda", busqueda);
        model.addAttribute("lista", listaUsuarios);
        model.addAttribute("listaE",listaEventos);
        return "AdminListar";

    }

    @PostMapping("/filtrarEventos")
    public String filtrarEventos(@RequestParam("busquedaEvento") String busquedaEvento, Model model){
        List<Usuario> listaUsuarios = this.usuarioRepository.findAll();
        List<Evento> listaEventos;
        if (busquedaEvento == "" ||busquedaEvento == null)
        {
            listaEventos = this.eventoRepository.findAll();
        }
        else
        {
            listaEventos = this.eventoRepository.findByBusqueda(busquedaEvento);
        }
        model.addAttribute("busquedaEvento", busquedaEvento);
        model.addAttribute("lista", listaUsuarios);
        model.addAttribute("listaE",listaEventos);
        return "AdminListar";

    }
}