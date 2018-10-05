package oblig2;

import java.util.*;
import java.util.function.Predicate;

public interface Beholder<T> extends Iterable<T> {

    boolean leggInn(T verdi);
    boolean inneholder(T verdi);
    boolean fjern(T verdi);
    int antall();
    boolean tom();
    void nullstill();
    Iterator<T> iterator();

    default boolean fjernHvis(Predicate<? super T> p) {

        Objects.requireNonNull(p);

        boolean fjernet = false;
        for (Iterator<T> i = iterator(); i.hasNext(); ) {

            if (p.test(i.next())) {
                i.remove();
                fjernet = true;
            }
        }

        return fjernet;
    }
}
