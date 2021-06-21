package es.eventos.eventos.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Evento {
    private Integer idEvento;
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
    private Usuario usuarioByIdCreador;
    private Collection<UsuarioInscrito> usuarioInscritosByIdEvento;

    @Id
    @Column(name = "ID_EVENTO", nullable = false)
    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    @Basic
    @Column(name = "TITULO", nullable = false, length = 70)
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Basic
    @Column(name = "DESCRIPCION", nullable = true, length = 1000)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Basic
    @Column(name = "FECHA", nullable = true)
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Basic
    @Column(name = "FECHARES", nullable = true)
    public Date getFechares() {
        return fechares;
    }

    public void setFechares(Date fechares) {
        this.fechares = fechares;
    }

    @Basic
    @Column(name = "COSTE", nullable = true)
    public Integer getCoste() {
        return coste;
    }

    public void setCoste(Integer coste) {
        this.coste = coste;
    }

    @Basic
    @Column(name = "AFORO", nullable = true)
    public Integer getAforo() {
        return aforo;
    }

    public void setAforo(Integer aforo) {
        this.aforo = aforo;
    }

    @Basic
    @Column(name = "ENTRADAS", nullable = true)
    public Integer getEntradas() {
        return entradas;
    }

    public void setEntradas(Integer entradas) {
        this.entradas = entradas;
    }

    @Basic
    @Column(name = "ASIENTOSFIJOS", nullable = false, length = 1)
    public String getAsientosfijos() {
        return asientosfijos;
    }

    public void setAsientosfijos(String asientosfijos) {
        this.asientosfijos = asientosfijos;
    }

    @Basic
    @Column(name = "NUMFILAS", nullable = true)
    public Integer getNumfilas() {
        return numfilas;
    }

    public void setNumfilas(Integer numfilas) {
        this.numfilas = numfilas;
    }

    @Basic
    @Column(name = "NUMASIENTOSPORFILA", nullable = true)
    public Integer getNumasientosporfila() {
        return numasientosporfila;
    }

    public void setNumasientosporfila(Integer numasientosporfila) {
        this.numasientosporfila = numasientosporfila;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evento evento = (Evento) o;
        return Objects.equals(idEvento, evento.idEvento) && Objects.equals(titulo, evento.titulo) && Objects.equals(descripcion, evento.descripcion) && Objects.equals(fecha, evento.fecha) && Objects.equals(fechares, evento.fechares) && Objects.equals(coste, evento.coste) && Objects.equals(aforo, evento.aforo) && Objects.equals(entradas, evento.entradas) && Objects.equals(asientosfijos, evento.asientosfijos) && Objects.equals(numfilas, evento.numfilas) && Objects.equals(numasientosporfila, evento.numasientosporfila);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEvento, titulo, descripcion, fecha, fechares, coste, aforo, entradas, asientosfijos, numfilas, numasientosporfila);
    }

    @ManyToOne
    @JoinColumn(name = "ID_CREADOR", referencedColumnName = "ID_USUARIO")
    public Usuario getUsuarioByIdCreador() {
        return usuarioByIdCreador;
    }

    public void setUsuarioByIdCreador(Usuario usuarioByIdCreador) {
        this.usuarioByIdCreador = usuarioByIdCreador;
    }

    @OneToMany(mappedBy = "eventoByIdEvento")
    public Collection<UsuarioInscrito> getUsuarioInscritosByIdEvento() {
        return usuarioInscritosByIdEvento;
    }

    public void setUsuarioInscritosByIdEvento(Collection<UsuarioInscrito> usuarioInscritosByIdEvento) {
        this.usuarioInscritosByIdEvento = usuarioInscritosByIdEvento;
    }
}
