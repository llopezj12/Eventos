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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    @GetMapping("/redireccionarAgregarUsuario")
    public String redireccionar(Model model){
        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        return "AdminAgregarUsuario";
    }

    /*@PostMapping("/crear")
    public String crearUsuario(@ModelAttribute("usuario") Usuario usuario, BindingResult result, Model model){
        if(result.hasErrors()){
            String error = "Datos erróneos";
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
                               @RequestParam("genero") String sexo,
                               @RequestParam("email") String email,
                               @RequestParam("fecha") String nacimiento,
                               @RequestParam("rol") String rol,
                               Model model){
        Optional<Roles> r;
        Roles rolBueno = new Roles();
        boolean error = false;
        String errorMsg = "";
        if(rol == null){
            error = true;
            errorMsg += "Rol no especificado";
        } else{
            r = this.rolesRepository.findById(Integer.parseInt(rol));
            if(r.isPresent()){
                rolBueno = r.get();
            }
        }

        Date fecha = new Date();

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
            usuario.setRol(rolBueno.getIdRol());
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
              u.setRol(rol.get().getIdRol());
          } else{
              u.setRol(rd.getIdRol());
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
        if(e.isPresent()){
            evento = e.get();
        }
        Optional<Usuario> usuario = this.usuarioRepository.findById(Integer.parseInt(idcre));
        Usuario u = new Usuario();
        if(usuario.isPresent()){
            u = usuario.get();
        }
        if(asiFijo != null){
            evento.setAsientosfijos(asiFijo);
        } else{
            evento.setAsientosfijos(asiFijoDefault);
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
        evento.setNumfilas(Integer.parseInt(numfilas));
        evento.setNumasientosporfila(Integer.parseInt(asifil));
        evento.setDescripcion(desc);
        evento.setIdCreador(u.getIdUsuario());
        evento.setEntradas(Integer.parseInt(entradas));
        evento.setAforo(Integer.parseInt(aforo));
        evento.setFecha(new java.sql.Date(fecha.getTime()));
        evento.setFechares(new java.sql.Date(fecha2.getTime()));
        this.eventoRepository.save(evento);
        return "redirect:/adminlistar";
    }
}