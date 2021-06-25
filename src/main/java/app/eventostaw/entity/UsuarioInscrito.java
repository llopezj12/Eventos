package app.eventostaw.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "USUARIO_INSCRITO", schema = "EVENTO", catalog = "")
@IdClass(UsuarioInscritoPK.class)
public class UsuarioInscrito {
    private int idEvento;
    private int idUsuario;

    @Id
    @Column(name = "ID_EVENTO")
    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    @Id
    @Column(name = "ID_USUARIO")
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioInscrito that = (UsuarioInscrito) o;
        return idEvento == that.idEvento && idUsuario == that.idUsuario;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEvento, idUsuario);
    }
}
