package app.eventostaw.service;

import app.eventostaw.dao.EventoRepository;
import app.eventostaw.entity.Evento;
import app.eventostaw.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    private EventoRepository eventoRepository;

    @Autowired
    public void setEventoRepository(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public List<Evento> filtroEventos(Usuario user, String filtroTitulo, String filtroDescripcion,
                                      String filtroPrecioMin, String filtroPrecioMax){
        List<Evento> lista = buscarEventoIdCreador(user.getIdUsuario());
        List<Evento> listaAux;
        if(filtroTitulo!=null && filtroTitulo.length()>0){
            listaAux = buscarEventoTituloSimilar(filtroTitulo);
            lista.retainAll(listaAux);
        }
        if(filtroDescripcion!=null && filtroDescripcion.length()>0){
            listaAux = buscarEventoDescripcionSimilar(filtroDescripcion);
            lista.retainAll(listaAux);
        }
        if(filtroPrecioMin!=null && filtroPrecioMin.length()>0){
            listaAux = buscarEventoRangoMinPrecio(filtroPrecioMin);
            lista.retainAll(listaAux);
        }
        if(filtroPrecioMax!=null && filtroPrecioMax.length()>0){
            listaAux = buscarEventoMaxPrecio(filtroPrecioMax);
            lista.retainAll(listaAux);
        }
        return lista;
    }

    private List<Evento> buscarEventoMaxPrecio(String filtroPrecioMax) {
        List<Evento> listaEventos = this.eventoRepository.findByRangoMaxPrecio(new Integer(filtroPrecioMax));
        return  listaEventos;
    }

    private List<Evento> buscarEventoRangoMinPrecio(String filtroPrecioMin) {
        List<Evento> listaEventos = this.eventoRepository.findByRangoMinPrecio(new Integer(filtroPrecioMin));
        return  listaEventos;
    }

    private List<Evento> buscarEventoDescripcionSimilar(String filtroDescripcion) {
        List<Evento> listaEventos = this.eventoRepository.findBySimilarDescripcion(filtroDescripcion);
        return  listaEventos;
    }

    private List<Evento> buscarEventoTituloSimilar(String filtroTitulo) {
        List<Evento> listaEventos = this.eventoRepository.findBySimilarTitulo(filtroTitulo);
        return  listaEventos;
    }

    public List<Evento> buscarEventoIdCreador(Integer id){
        List<Evento> listaEventos = this.eventoRepository.findByIdCreador(id);
        return  listaEventos;
    }

    public Optional<Evento> buscarEventoId(Integer id){
        Optional<Evento> e = this.eventoRepository.findById(id);
        return e;
    }

    public void borrarEvento(Evento evento){
        this.eventoRepository.delete(evento);
    }

    public void guardarEvento(Evento evento){
        this.eventoRepository.save(evento);
    }
}
