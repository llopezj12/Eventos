package app.eventostaw.controller;

import app.eventostaw.dao.EventoRepository;
import app.eventostaw.dao.UsuarioInscritoRepository;
import app.eventostaw.entity.Evento;
import app.eventostaw.entity.Usuario;
import app.eventostaw.entity.UsuarioInscrito;
import app.eventostaw.entity.UsuarioInscritoPK;
import app.eventostaw.util.Consulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class UsuarioController {
    @Autowired
    private UsuarioInscritoRepository usuarioInscritoRepository;

    @Autowired
    private EventoRepository eventoRepository;


    @GetMapping("/InscribirseEvento")
    public String InscribirseEvento(@RequestParam("idEvento") int idEvento, HttpSession session) {
        InscribirseEnEvento(session, idEvento);
        return "redirect:/";
    }

    @GetMapping("/QuitarEvento")
    public String QuitarEvento(@RequestParam("idEvento") int idEvento, HttpSession session) {
        QuitarseDeEvento(session, idEvento);
        return "redirect:/";
    }

    @GetMapping("/QuitarEventoD")
    public String QuitarEventoD(@RequestParam("idEvento") int idEvento, HttpSession session) {
        QuitarseDeEvento(session, idEvento);
        return "redirect:/datosusuario";
    }

    @GetMapping("/datosusuario")
    public String DatosUsuario(Model model, HttpSession session) {
        Usuario user = (Usuario)session.getAttribute("usuario");
        List<Evento> listaInscritos = new ArrayList<>();
        if (user != null) {
            try {
                List<UsuarioInscrito> listaUsuarioInscritos = usuarioInscritoRepository.findAllUsuarioInscritoByIdUsuario(user.getIdUsuario());
                for (UsuarioInscrito ui : listaUsuarioInscritos) {
                    listaInscritos.add(eventoRepository.findById(ui.getIdEvento()).get());
                }
            } catch (Exception e) {

            }
        }
        model.addAttribute("listaInscritos", listaInscritos);

        return "DatosUsuario";
    }

    private void InscribirseEnEvento(HttpSession session, int idEvento) {
        Usuario user = (Usuario)session.getAttribute("usuario");
        Optional<Evento> optEvento = eventoRepository.findById(idEvento);

        if (user != null && optEvento.isPresent()) {
            Evento evento = optEvento.get();
            UsuarioInscritoPK pk = new UsuarioInscritoPK();
            pk.setIdEvento(idEvento);
            pk.setIdUsuario(user.getIdUsuario());
            boolean permitir = false;
            try {
                Optional<UsuarioInscrito> usuarioInscrito = usuarioInscritoRepository.findById(pk);
                permitir = !usuarioInscrito.isPresent();
            } catch (Exception e) {

            }
            if (Consulta.EventoDisponible(evento) && permitir) {
                UsuarioInscrito usuarioInscrito = new UsuarioInscrito();

                usuarioInscrito.setIdUsuario(user.getIdUsuario());
                usuarioInscrito.setIdEvento(idEvento);

                try {
                    usuarioInscritoRepository.save(usuarioInscrito);
                    evento.setEntradas(evento.getEntradas()-1);
                    eventoRepository.save(evento);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }

    private void QuitarseDeEvento(HttpSession session, int idEvento) {
        Usuario user = (Usuario)session.getAttribute("usuario");
        Optional<Evento> optEvento = eventoRepository.findById(idEvento);

        if (user != null && optEvento.isPresent() && optEvento.get().getFechares().after(new Date())) {
            UsuarioInscritoPK pk = new UsuarioInscritoPK();
            Evento evento = optEvento.get();
            pk.setIdEvento(idEvento);
            pk.setIdUsuario(user.getIdUsuario());
            try {
                usuarioInscritoRepository.delete(usuarioInscritoRepository.findById(pk).get());
                evento.setEntradas(evento.getEntradas()+1);
                eventoRepository.save(evento);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
