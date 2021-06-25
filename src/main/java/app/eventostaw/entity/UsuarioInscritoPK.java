package app.eventostaw.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class UsuarioInscritoPK implements Serializable {
    private int idEvento;
    private int idUsuario;

    @Column(name = "ID_EVENTO")
    @Id
    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    @Column(name = "ID_USUARIO")
    @Id
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
        UsuarioInscritoPK that = (UsuarioInscritoPK) o;
        return idEvento == that.idEvento && idUsuario == that.idUsuario;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEvento, idUsuario);
    }
}
