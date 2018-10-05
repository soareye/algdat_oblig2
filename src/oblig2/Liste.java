package oblig2;

import java.util.Iterator;

public interface Liste<T> extends Beholder<T> {

    boolean leggInn(T verdi);
    void leggInn(int indeks, T verdi);
    boolean inneholder(T verdi);
    T hent(int indeks);
    int indeksTil(T verdi);
    T oppdater(int indeks, T verdi);
    boolean fjern(T verdi);
    T fjern(int indeks);
    int antall();
    boolean tom();
    void nullstill();
    Iterator<T> iterator();

    default String melding(int indeks) {
        return "Indeks: " + indeks + ", Antall: " + antall();
    }

    default void indeksKontroll(int indeks, boolean leggInn) {
        if (indeks < 0 || (leggInn ? indeks > antall() : indeks >= antall()))
            throw new IndexOutOfBoundsException(melding(indeks));
    }
}
