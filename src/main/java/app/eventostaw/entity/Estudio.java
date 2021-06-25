package app.eventostaw.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Estudio {
    private int idEstudio;

    @Id
    @Column(name = "ID_ESTUDIO")
    public int getIdEstudio() {
        return idEstudio;
    }

    public void setIdEstudio(int idEstudio) {
        this.idEstudio = idEstudio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estudio estudio = (Estudio) o;
        return idEstudio == estudio.idEstudio;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEstudio);
    }
}
