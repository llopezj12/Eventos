package es.eventos.eventos.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Roles {
    private Integer idRol;
    private String descripcion;
    private Collection<Usuario> usuariosByIdRol;

    @Id
    @Column(name = "ID_ROL", nullable = false)
    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    @Basic
    @Column(name = "DESCRIPCION", nullable = true, length = 50)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Roles roles = (Roles) o;
        return Objects.equals(idRol, roles.idRol) && Objects.equals(descripcion, roles.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRol, descripcion);
    }

    @OneToMany(mappedBy = "rolesByRol")
    public Collection<Usuario> getUsuariosByIdRol() {
        return usuariosByIdRol;
    }

    public void setUsuariosByIdRol(Collection<Usuario> usuariosByIdRol) {
        this.usuariosByIdRol = usuariosByIdRol;
    }
}
