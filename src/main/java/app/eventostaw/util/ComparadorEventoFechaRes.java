package app.eventostaw.util;

import app.eventostaw.entity.Evento;

import java.util.Comparator;

public class ComparadorEventoFechaRes implements Comparator<Evento> {

    @Override
    public int compare(Evento o1, Evento o2) {
        return o1.getFechares().compareTo(o2.getFechares());
    }
}
