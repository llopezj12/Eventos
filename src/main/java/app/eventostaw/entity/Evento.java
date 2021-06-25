package app.eventostaw.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Evento {
    private int idEvento;
    private String titulo;
    private String descripcion;
    private Date fecha;
    private Date fechares;
    private Integer coste;
    private Integer aforo;
    private Integer entradas;
    private String asientosfijos;
    private Integer numfilas;
    private Integer numasientosporfila;
    private Integer idCreador;

    @Id
    @Column(name = "ID_EVENTO")
    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    @Basic
    @Column(name = "TITULO")
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Basic
    @Column(name = "DESCRIPCION")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Basic
    @Column(name = "FECHA")
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Basic
    @Column(name = "FECHARES")
    public Date getFechares() {
        return fechares;
    }

    public void setFechares(Date fechares) {
        this.fechares = fechares;
    }

    @Basic
    @Column(name = "COSTE")
    public Integer getCoste() {
        return coste;
    }

    public void setCoste(Integer coste) {
        this.coste = coste;
    }

    @Basic
    @Column(name = "AFORO")
    public Integer getAforo() {
        return aforo;
    }

    public void setAforo(Integer aforo) {
        this.aforo = aforo;
    }

    @Basic
    @Column(name = "ENTRADAS")
    public Integer getEntradas() {
        return entradas;
    }

    public void setEntradas(Integer entradas) {
        this.entradas = entradas;
    }

    @Basic
    @Column(name = "ASIENTOSFIJOS")
    public String getAsientosfijos() {
        return asientosfijos;
    }

    public void setAsientosfijos(String asientosfijos) {
        this.asientosfijos = asientosfijos;
    }

    @Basic
    @Column(name = "NUMFILAS")
    public Integer getNumfilas() {
        return numfilas;
    }

    public void setNumfilas(Integer numfilas) {
        this.numfilas = numfilas;
    }

    @Basic
    @Column(name = "NUMASIENTOSPORFILA")
    public Integer getNumasientosporfila() {
        return numasientosporfila;
    }

    public void setNumasientosporfila(Integer numasientosporfila) {
        this.numasientosporfila = numasientosporfila;
    }

    @Basic
    @Column(name = "ID_CREADOR")
    public Integer getIdCreador() {
        return idCreador;
    }

    public void setIdCreador(Integer idCreador) {
        this.idCreador = idCreador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evento evento = (Evento) o;
        return idEvento == evento.idEvento && Objects.equals(titulo, evento.titulo) && Objects.equals(descripcion, evento.descripcion) && Objects.equals(fecha, evento.fecha) && Objects.equals(fechares, evento.fechares) && Objects.equals(coste, evento.coste) && Objects.equals(aforo, evento.aforo) && Objects.equals(entradas, evento.entradas) && Objects.equals(asientosfijos, evento.asientosfijos) && Objects.equals(numfilas, evento.numfilas) && Objects.equals(numasientosporfila, evento.numasientosporfila) && Objects.equals(idCreador, evento.idCreador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEvento, titulo, descripcion, fecha, fechares, coste, aforo, entradas, asientosfijos, numfilas, numasientosporfila, idCreador);
    }
}
