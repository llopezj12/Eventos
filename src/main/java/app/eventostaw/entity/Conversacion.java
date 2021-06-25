package app.eventostaw.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Conversacion {
    private int idConversacion;

    @Id
    @Column(name = "ID_CONVERSACION")
    public int getIdConversacion() {
        return idConversacion;
    }

    public void setIdConversacion(int idConversacion) {
        this.idConversacion = idConversacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conversacion that = (Conversacion) o;
        return idConversacion == that.idConversacion;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idConversacion);
    }
}
