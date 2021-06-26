package app.eventostaw.util;

import app.eventostaw.entity.Evento;

import java.util.Date;
import java.util.Locale;

public class Consulta {
    public static boolean EventoDisponible(Evento evento) {
        return (evento.getEntradas() > 0) && (evento.getFechares().after(new Date()));

    }

    public static boolean EventoAlgunValorNull(Evento evento) {
        return evento.getEntradas() == null ||
                evento.getFecha() == null ||
                evento.getFechares() == null ||
                evento.getAsientosfijos() == null ||
                evento.getDescripcion() == null ||
                evento.getIdCreador() == null ||
                evento.getAforo() == null;
    }

    public static boolean buscarClave(Evento evento, String clave) {
        String texto = evento.getDescripcion() + evento.getTitulo();
        texto = texto.toUpperCase();
        return texto.contains(clave.toUpperCase());
    }
}
