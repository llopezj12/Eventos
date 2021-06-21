package es.eventos.eventos.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Usuario {
    private Integer idUsuario;
    private String nombre;
    private String apellidos;
    private String password;
    private String domicilio;
    private String ciudad;
    private Date nacimiento;
    private String genero;
    private String email;
    private Collection<Conversacion> conversacionsByIdUsuario;
    private Collection<Conversacion> conversacionsByIdUsuario_0;
    private Collection<Evento> eventosByIdUsuario;
    private Collection<Mensaje> mensajesByIdUsuario;
    private Roles rolesByRol;
    private Collection<UsuarioInscrito> usuarioInscritosByIdUsuario;

    @Id
    @Column(name = "ID_USUARIO", nullable = false)
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Basic
    @Column(name = "NOMBRE", nullable = false, length = 70)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "APELLIDOS", nullable = true, length = 120)
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Basic
    @Column(name = "PASSWORD", nullable = true, length = 150)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "DOMICILIO", nullable = true, length = 150)
    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    @Basic
    @Column(name = "CIUDAD", nullable = true, length = 120)
    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Basic
    @Column(name = "NACIMIENTO", nullable = true)
    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    @Basic
    @Column(name = "GENERO", nullable = false, length = 1)
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Basic
    @Column(name = "EMAIL", nullable = false, length = 120)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(idUsuario, usuario.idUsuario) && Objects.equals(nombre, usuario.nombre) && Objects.equals(apellidos, usuario.apellidos) && Objects.equals(password, usuario.password) && Objects.equals(domicilio, usuario.domicilio) && Objects.equals(ciudad, usuario.ciudad) && Objects.equals(nacimiento, usuario.nacimiento) && Objects.equals(genero, usuario.genero) && Objects.equals(email, usuario.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, nombre, apellidos, password, domicilio, ciudad, nacimiento, genero, email);
    }

    @OneToMany(mappedBy = "usuarioByIdUsuario1")
    public Collection<Conversacion> getConversacionsByIdUsuario() {
        return conversacionsByIdUsuario;
    }

    public void setConversacionsByIdUsuario(Collection<Conversacion> conversacionsByIdUsuario) {
        this.conversacionsByIdUsuario = conversacionsByIdUsuario;
    }

    @OneToMany(mappedBy = "usuarioByIdUsuario2")
    public Collection<Conversacion> getConversacionsByIdUsuario_0() {
        return conversacionsByIdUsuario_0;
    }

    public void setConversacionsByIdUsuario_0(Collection<Conversacion> conversacionsByIdUsuario_0) {
        this.conversacionsByIdUsuario_0 = conversacionsByIdUsuario_0;
    }

    @OneToMany(mappedBy = "usuarioByIdCreador")
    public Collection<Evento> getEventosByIdUsuario() {
        return eventosByIdUsuario;
    }

    public void setEventosByIdUsuario(Collection<Evento> eventosByIdUsuario) {
        this.eventosByIdUsuario = eventosByIdUsuario;
    }

    @OneToMany(mappedBy = "usuarioByIdUsuario")
    public Collection<Mensaje> getMensajesByIdUsuario() {
        return mensajesByIdUsuario;
    }

    public void setMensajesByIdUsuario(Collection<Mensaje> mensajesByIdUsuario) {
        this.mensajesByIdUsuario = mensajesByIdUsuario;
    }

    @ManyToOne
    @JoinColumn(name = "ROL", referencedColumnName = "ID_ROL")
    public Roles getRolesByRol() {
        return rolesByRol;
    }

    public void setRolesByRol(Roles rolesByRol) {
        this.rolesByRol = rolesByRol;
    }

    @OneToMany(mappedBy = "usuarioByIdUsuario")
    public Collection<UsuarioInscrito> getUsuarioInscritosByIdUsuario() {
        return usuarioInscritosByIdUsuario;
    }

    public void setUsuarioInscritosByIdUsuario(Collection<UsuarioInscrito> usuarioInscritosByIdUsuario) {
        this.usuarioInscritosByIdUsuario = usuarioInscritosByIdUsuario;
    }
}
