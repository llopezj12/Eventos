package app.eventostaw.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Conversacion {
    private int idConversacion;
    private int idUsuario1;
    private int idUsuario2;
    private Usuario usuarioByIdUsuario1;
    private Usuario usuarioByIdUsuario2;
    private Collection<Mensaje> mensajesByIdConversacion;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_CONVERSACION", nullable = false)
    public int getIdConversacion() {
        return idConversacion;
    }

    public void setIdConversacion(Integer idConversacion) {
        this.idConversacion = idConversacion;
    }

    public void setIdConversacion(int idConversacion) {
        this.idConversacion = idConversacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conversacion that = (Conversacion) o;
        return idConversacion == that.idConversacion && idUsuario1 == that.idUsuario1 && idUsuario2 == that.idUsuario2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idConversacion, idUsuario1, idUsuario2);
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
