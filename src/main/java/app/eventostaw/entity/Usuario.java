package app.eventostaw.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Usuario {
    private int idUsuario;
    private String nombre;
    private String apellidos;
    private String password;
    private String domicilio;
    private String ciudad;
    private Date nacimiento;
    private String genero;
    private String email;
    private Roles rolesByRol;
    private Collection<Conversacion> conversacionsByIdUsuario;
    private Collection<Conversacion> conversacionsByIdUsuario_0;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_USUARIO", nullable = false)
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
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
        return idUsuario == usuario.idUsuario && Objects.equals(nombre, usuario.nombre) && Objects.equals(apellidos, usuario.apellidos) && Objects.equals(password, usuario.password) && Objects.equals(domicilio, usuario.domicilio) && Objects.equals(ciudad, usuario.ciudad) && Objects.equals(nacimiento, usuario.nacimiento) && Objects.equals(genero, usuario.genero) && Objects.equals(email, usuario.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, nombre, apellidos, password, domicilio, ciudad, nacimiento, genero, email);
    }

    @ManyToOne
    @JoinColumn(name = "ROL", referencedColumnName = "ID_ROL")
    public Roles getRolesByRol() {
        return rolesByRol;
    }

    public void setRolesByRol(Roles rolesByRol) {
        this.rolesByRol = rolesByRol;
    }

    @OneToMany(mappedBy = "usuarioByIdUsuario1")
    public Collection<Conversacion> getConversacionsByIdUsuario() {
        return conversacionsByIdUsuario;
    }

    public void setConversacionsByIdUsuario(Collection<Conversacion> conversacionsByIdUsuario) {
        this.conversacionsByIdUsuario = conversacionsByIdUsuario;
    }

    @OneToMany(mappedBy = "usuarioByIdUsuario2", fetch = FetchType.EAGER)
    public Collection<Conversacion> getConversacionsByIdUsuario_0() {
        return conversacionsByIdUsuario_0;
    }

    public void setConversacionsByIdUsuario_0(Collection<Conversacion> conversacionsByIdUsuario_0) {
        this.conversacionsByIdUsuario_0 = conversacionsByIdUsuario_0;
    }
}
