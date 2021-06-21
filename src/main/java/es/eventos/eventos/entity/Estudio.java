package es.eventos.eventos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Estudio {
    private Integer idEstudio;

    @Id
    @Column(name = "ID_ESTUDIO", nullable = false)
    public Integer getIdEstudio() {
        return idEstudio;
    }

    public void setIdEstudio(Integer idEstudio) {
        this.idEstudio = idEstudio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estudio estudio = (Estudio) o;
        return Objects.equals(idEstudio, estudio.idEstudio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEstudio);
    }
}
