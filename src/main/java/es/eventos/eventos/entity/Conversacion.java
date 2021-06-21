package es.eventos.eventos.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Conversacion {
    private Integer idConversacion;
    private Usuario usuarioByIdUsuario1;
    private Usuario usuarioByIdUsuario2;
    private Collection<Mensaje> mensajesByIdConversacion;

    @Id
    @Column(name = "ID_CONVERSACION", nullable = false)
    public Integer getIdConversacion() {
        return idConversacion;
    }

    public void setIdConversacion(Integer idConversacion) {
        this.idConversacion = idConversacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conversacion that = (Conversacion) o;
        return Objects.equals(idConversacion, that.idConversacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idConversacion);
    }

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO1", referencedColumnName = "ID_USUARIO", nullable = false)
    public Usuario getUsuarioByIdUsuario1() {
        return usuarioByIdUsuario1;
    }

    public void setUsuarioByIdUsuario1(Usuario usuarioByIdUsuario1) {
        this.usuarioByIdUsuario1 = usuarioByIdUsuario1;
    }

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO2", referencedColumnName = "ID_USUARIO", nullable = false)
    public Usuario getUsuarioByIdUsuario2() {
        return usuarioByIdUsuario2;
    }

    public void setUsuarioByIdUsuario2(Usuario usuarioByIdUsuario2) {
        this.usuarioByIdUsuario2 = usuarioByIdUsuario2;
    }

    @OneToMany(mappedBy = "conversacionByIdConversacion")
    public Collection<Mensaje> getMensajesByIdConversacion() {
        return mensajesByIdConversacion;
    }

    public void setMensajesByIdConversacion(Collection<Mensaje> mensajesByIdConversacion) {
        this.mensajesByIdConversacion = mensajesByIdConversacion;
    }
}
