package app.eventostaw.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Conversacion {
    private int idConversacion;
    private int idUsuario1;
    private int idUsuario2;

    @Id
    @Column(name = "ID_CONVERSACION")
    public int getIdConversacion() {
        return idConversacion;
    }

    public void setIdConversacion(int idConversacion) {
        this.idConversacion = idConversacion;
    }

    @Basic
    @Column(name = "ID_USUARIO1")
    public int getIdUsuario1() {
        return idUsuario1;
    }

    public void setIdUsuario1(int idUsuario1) {
        this.idUsuario1 = idUsuario1;
    }

    @Basic
    @Column(name = "ID_USUARIO2")
    public int getIdUsuario2() {
        return idUsuario2;
    }

    public void setIdUsuario2(int idUsuario2) {
        this.idUsuario2 = idUsuario2;
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
}
