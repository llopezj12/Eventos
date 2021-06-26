package app.eventostaw.controller;

import app.eventostaw.dao.EventoRepository;
import app.eventostaw.entity.Evento;
import app.eventostaw.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class CreadorController {

    @Autowired
    private EventoRepository eventoRepository;



    @GetMapping("/creador")
    public String doInit (Model model, HttpSession session) {
        Usuario user = (Usuario)session.getAttribute("usuario");
        List<Evento> listaEventos = this.eventoRepository.findByIdCreador(user.getIdUsuario());
        model.addAttribute("listaEventos", listaEventos);
        return "redirect:/inicio";
    }

    @GetMapping("/nuevoEvento")
    public String doNuevo (Model model) {
        //Evento evento = new Evento();
        //model.addAttribute("evento", evento);
        return "crearEditarEvento";
    }

    @PostMapping("/guardarEventoCreador")
    public String doGuardar (@RequestParam("id") String id,
            @RequestParam("titulo") String titulo,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("fecha") String fecha,
            @RequestParam("fechaReserva") String fechaReserva ,
            @RequestParam("precio") String precio,
            @RequestParam("aforo") String aforo,
            @RequestParam("limiteEntradas") String limiteEntradas,
            @RequestParam(value = "asientosFijos", required = false) String asientosFijos,
            @RequestParam("numFilas") String numFilas,
            @RequestParam("limiteEntradas") String asientosFila,
            Model model, HttpSession session) {

        Usuario usuario = (Usuario)session.getAttribute("usuario");
        Date fechaActual = new Date();
        Date fecha1 = new Date();
        Date fecha2 = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        boolean error = false;
        String errorMsg = "";

        if(titulo.isEmpty()){
            error = true;
            errorMsg += " Título vacío";
        }
        if(descripcion.isEmpty()){
            error = true;
            errorMsg += " Descripción vacío";
        }
        try {
            fecha1 = df.parse(fecha);
            if(fecha1.before(fechaActual)){
                error = true;
                errorMsg += "La fecha del evento indicada es anterior a la fecha actual";
            }
        } catch (Exception e) {
            error = true;
        }

        try{
            fecha2 = df.parse(fechaReserva);
            if(fecha2.before(fechaActual)){
                error = true;
                errorMsg += "La fecha de reserva del evento indicada es anterior a la fecha actual";
            }else{
                if(fecha1.before(fecha2)){
                    error = true;
                    errorMsg += "La fecha del evento indicada es anterior a la fecha límite de reserva";
                }
            }
        } catch (Exception e) {
            error = true;
        }
        if(precio.isEmpty()){
            error = true;
            errorMsg += " Precio vacío";
        }
        if(aforo.isEmpty()){
            error = true;
            errorMsg += " Aforo vacío";
        }
        if(limiteEntradas.isEmpty()){
            error = true;
            errorMsg += " Límite de entradas vacío";
        }

        if(asientosFijos != null){ //Evento de asientos fijos asignados
            if(numFilas.isEmpty()){
                error = true;
                errorMsg += " Número de filas vacío";
            }
            if(asientosFila.isEmpty()){
                error = true;
                errorMsg += " Número de asientos por fila vacío";
            }
        }

        model.addAttribute("error", error);
        model.addAttribute("errorMsg", errorMsg);

        if(!error){
            Evento evento = new Evento();
            if(id.length()>0){//Editar
                Optional<Evento> e = this.eventoRepository.findById(Integer.parseInt(id));
                if(e.isPresent()){
                    evento = e.get();
                }
            }
            evento.setTitulo(titulo);
            evento.setDescripcion(descripcion);
            evento.setFecha(new java.sql.Date(fecha1.getTime()));
            evento.setFechares(new java.sql.Date(fecha2.getTime()));
            evento.setCoste(Integer.parseInt(precio));
            evento.setAforo(Integer.parseInt(aforo));
            evento.setEntradas(Integer.parseInt(limiteEntradas));
            String fijos;
            if(asientosFijos==null){
                fijos = "N";
                evento.setNumfilas(null);
                evento.setNumasientosporfila(null);
            }else{
                fijos = "S";
                evento.setNumfilas(Integer.parseInt(numFilas));
                evento.setNumasientosporfila(Integer.parseInt(asientosFila));
            }
            evento.setAsientosfijos(fijos);
            if (id.isEmpty()  || id.length()<=0) { // Crear nuevo evento
                evento.setIdCreador(usuario.getIdUsuario());
            }
            this.eventoRepository.save(evento);
            List<Evento> listaEventos = this.eventoRepository.findByIdCreador(usuario.getIdUsuario());
            model.addAttribute("listaEventos", listaEventos);
            return "inicio";
        }else{
            return "crearEditarEvento";
        }
    }

    @GetMapping("/eliminarEvento/{id}")
    public String doEliminarEvento(@PathVariable("id") Integer id, Model model, HttpSession session){
        Optional<Evento> e = this.eventoRepository.findById(id);
        if(e.isPresent()){
            Evento aux = e.get();
            this.eventoRepository.delete(aux);
        }
        Usuario usuario = (Usuario)session.getAttribute("usuario");
        List<Evento> listaEventos = this.eventoRepository.findByIdCreador(usuario.getIdUsuario());
        model.addAttribute("listaEventos", listaEventos);
        return "inicio";
    }

    @GetMapping("/editarEvento/{id}")
    public String doEditarEvento(@PathVariable("id")Integer id, Model model){
        Optional<Evento> evento = this.eventoRepository.findById(id);
        Evento eventoAux = evento.get();
        model.addAttribute("evento",eventoAux);
        return "crearEditarEvento";
    }
}
