package es.eventos.eventos.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USUARIO_INSCRITO", schema = "EVENTOS", catalog = "")
public class UsuarioInscrito {
    private Evento eventoByIdEvento;
    private Usuario usuarioByIdUsuario;

    @ManyToOne
    @JoinColumn(name = "ID_EVENTO", referencedColumnName = "ID_EVENTO", nullable = false)
    public Evento getEventoByIdEvento() {
        return eventoByIdEvento;
    }

    public void setEventoByIdEvento(Evento eventoByIdEvento) {
        this.eventoByIdEvento = eventoByIdEvento;
    }

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO", nullable = false)
    public Usuario getUsuarioByIdUsuario() {
        return usuarioByIdUsuario;
    }

    public void setUsuarioByIdUsuario(Usuario usuarioByIdUsuario) {
        this.usuarioByIdUsuario = usuarioByIdUsuario;
    }
}
