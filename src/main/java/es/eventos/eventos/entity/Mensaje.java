package es.eventos.eventos.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Mensaje {
    private Integer idMensaje;
    private Integer hora;
    private Integer minuto;
    private String mensaje;
    private Date fecha;
    private Conversacion conversacionByIdConversacion;
    private Usuario usuarioByIdUsuario;

    @Id
    @Column(name = "ID_MENSAJE", nullable = false)
    public Integer getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(Integer idMensaje) {
        this.idMensaje = idMensaje;
    }

    @Basic
    @Column(name = "HORA", nullable = true)
    public Integer getHora() {
        return hora;
    }

    public void setHora(Integer hora) {
        this.hora = hora;
    }

    @Basic
    @Column(name = "MINUTO", nullable = true)
    public Integer getMinuto() {
        return minuto;
    }

    public void setMinuto(Integer minuto) {
        this.minuto = minuto;
    }

    @Basic
    @Column(name = "MENSAJE", nullable = true, length = 1000)
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Basic
    @Column(name = "FECHA", nullable = true)
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mensaje mensaje1 = (Mensaje) o;
        return Objects.equals(idMensaje, mensaje1.idMensaje) && Objects.equals(hora, mensaje1.hora) && Objects.equals(minuto, mensaje1.minuto) && Objects.equals(mensaje, mensaje1.mensaje) && Objects.equals(fecha, mensaje1.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMensaje, hora, minuto, mensaje, fecha);
    }

    @ManyToOne
    @JoinColumn(name = "ID_CONVERSACION", referencedColumnName = "ID_CONVERSACION")
    public Conversacion getConversacionByIdConversacion() {
        return conversacionByIdConversacion;
    }

    public void setConversacionByIdConversacion(Conversacion conversacionByIdConversacion) {
        this.conversacionByIdConversacion = conversacionByIdConversacion;
    }

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO", nullable = false)
    public Usuario getUsuarioByIdUsuario() {
        return usuarioByIdUsuario;
    }

    public void setUsuarioByIdUsuario(Usuario usuarioByIdUsuario) {
        this.usuarioByIdUsuario = usuarioByIdUsuario;
    }
}
