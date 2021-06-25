package app.eventostaw.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Mensaje {
    private int idMensaje;
    private Integer hora;
    private Integer minuto;
    private String mensaje;
    private Date fecha;

    @Id
    @Column(name = "ID_MENSAJE")
    public int getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(int idMensaje) {
        this.idMensaje = idMensaje;
    }

    @Basic
    @Column(name = "HORA")
    public Integer getHora() {
        return hora;
    }

    public void setHora(Integer hora) {
        this.hora = hora;
    }

    @Basic
    @Column(name = "MINUTO")
    public Integer getMinuto() {
        return minuto;
    }

    public void setMinuto(Integer minuto) {
        this.minuto = minuto;
    }

    @Basic
    @Column(name = "MENSAJE")
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Basic
    @Column(name = "FECHA")
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
        return idMensaje == mensaje1.idMensaje && Objects.equals(hora, mensaje1.hora) && Objects.equals(minuto, mensaje1.minuto) && Objects.equals(mensaje, mensaje1.mensaje) && Objects.equals(fecha, mensaje1.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMensaje, hora, minuto, mensaje, fecha);
    }
}
