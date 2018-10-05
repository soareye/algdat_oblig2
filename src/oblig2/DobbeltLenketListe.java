package oblig2;

import java.util.*;

public class DobbeltLenketListe<T> implements Liste<T> {

    private Node<T> hode, hale;
    private int antall;
    private int endringer;

    public DobbeltLenketListe() {
        hode = hale = null;
        antall = endringer = 0;
    }

    // Oppgave 1 (The constructor part of task 1)
    public DobbeltLenketListe(T list[]) {
        this();
        Objects.requireNonNull(list, "Tabellen 'list' er null!");

        Node<T> node = null;

        // Find the first 'verdi' in the list that's not null
        // and add this 'verdi' to the head-node:
        int hodeIndex = 0;
        for (int i = 0; i < list.length; i++) {

             hodeIndex = i;
             if (list[i] != null) {
                 node = new Node<>(list[i]);
                 antall++;
                 break;
             }
        }

        hode = node;

        // Create the rest of the linked list:
        for (int i = hodeIndex + 1; i < list.length; i++) {

            if (list[i] != null) {
                Node<T> nesteNode = new Node<>(list[i], node, null);
                node.neste = nesteNode;

                antall++;
                node = nesteNode;
            }
        }

        hale = node;
    }

    // Oppgave 2 b)
    @Override
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "Parameter 'verdi' er null!");

        Node<T> node = new Node<>(verdi);

        if (hode != null) {
            hale.neste = node;
            node.forrige = hale;
            hale = node;

        } else {
            hode = node;
            hale = node;
        }

        antall++;
        endringer++;

        return true;
    }

    // Oppgave 5
    @Override
    public void leggInn(int indeks, T verdi) {
        indeksKontroll(indeks, true);

        // The other leggInn already handles the cases where
        // we're adding an element to the end of a list
        // including empty lists (antall == 0)
        if (indeks == antall || antall == 0) {
            leggInn(verdi);
            return;
        }

        // We will place the node between nesteNode and forrigeNode, hence their names.
        Node<T> nesteNode = finnNode(indeks);
        Node<T> forrigeNode = nesteNode.forrige;

        Node<T> node = new Node<>(verdi, forrigeNode, nesteNode);

        if (indeks == 0) {
            hode = node;
        } else {
            // If indeks is not 0, then forrigeNode is not null,
            // so we can assign the 'neste'-attribute to point to 'node'.
            forrigeNode.neste = node;
        }

        // We don't have to check if nesteNode is null because the first if-clause in
        // this method takes care of that case.
        nesteNode.forrige = node;

        antall++;
        endringer++;
    }

    // Oppgave 4
    @Override
    public boolean inneholder(T verdi) {
        return indeksTil(verdi) != -1;
    }

    // Oppgave 3 a)
    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks, false);

        return finnNode(indeks).verdi;
    }

    // Helper method for one of the 'fjern'-methods in oppgave 6:
    private Node<T> finnNode(T verdi) {

        Node<T> node = hode;
        for (int i = 0; i < antall; i++) {

            if (node.verdi.equals(verdi))
                return node;

            node = node.neste;
        }

        return node;
    }

    // Oppgave 3 a)
    private Node<T> finnNode(int index) {
        if (index < antall/2) {
            return findNodeFirstHalf(index);
        } else {
            return findNodeLastHalf(index);
        }
    }

    // Oppgave 3 a)
    private Node<T> findNodeFirstHalf(int index) {
        Node<T> node = hode;

        for (int i = 0; i < index; i++) {
            node = node.neste;
        }

        return node;
    }

    // Oppgave 3 a)
    private Node<T> findNodeLastHalf(int index) {
        Node<T> node = hale;

        for (int i = 0; i < antall - 1 - index; i++) {
            node = node.forrige;
        }

        return node;
    }

    // Oppgave 4
    @Override
    public int indeksTil(T verdi) {
        Node<T> node = hode;

        for (int i = 0; i < antall; i++) {
            if (verdi.equals(node.verdi))
                return i;

            node = node.neste;
        }

        return -1;
    }

    // Oppgave 3 a)
    @Override
    public T oppdater(int indeks, T verdi) {
        Objects.requireNonNull(verdi);
        indeksKontroll(indeks, false);

        Node<T> node = finnNode(indeks);

        T oldverdi = node.verdi;
        node.verdi = verdi;

        endringer++;

        return oldverdi;
    }

    // Oppgave 6
    @Override
    public boolean fjern(T verdi) {

        Node<T> node = finnNode(verdi);
        if (node == null) return false;

        Node<T> forrigeNode = node.forrige;
        Node<T> nesteNode = node.neste;

        if (forrigeNode == null && nesteNode == null) {
            hode = null;
            hale = null;
        }

        if (forrigeNode != null) {
            forrigeNode.neste = nesteNode;
        } else {
            hode = nesteNode;
        }

        if (nesteNode != null) {
            nesteNode.forrige = forrigeNode;
        } else {
            hale = forrigeNode;
        }

        antall--;
        endringer++;

        return true;
    }

    // Oppgave 6
    @Override
    public T fjern(int indeks) {
        indeksKontroll(indeks, false);

        Node<T> node = finnNode(indeks);
        Node<T> forrigeNode = node.forrige;
        Node<T> nesteNode = node.neste;

        if (forrigeNode == null && nesteNode == null) {
            hode = null;
            hale = null;
        }

        if (forrigeNode != null) {
            forrigeNode.neste = nesteNode;
        } else {
            hode = nesteNode;
        }

        if (nesteNode != null) {
            nesteNode.forrige = forrigeNode;
        } else {
            hale = forrigeNode;
        }

        antall--;
        endringer++;

        return node.verdi;
    }

    // Oppgave 1 (The constructor for task 1 is near the top of the class)
    @Override
    public int antall() {
        return antall;
    }

    // Oppgave 1
    @Override
    public boolean tom() {
        return antall < 1;
    }

    // Oppgave 7
    // I measured the time of the methods on 3000 lists with 10 000 elements
    // and I never saw any real difference between the time of the two
    // implementations of 'nullstill'.
    // They both usually took 150 ms to clear 3000 lists with 10 000 elements.
    //
    // Because they seemed equally efficient according to my measurements I chose to
    // keep the one with the fewest lines of code.
    // I left the other one commented out below, just in case you wanted to look at it.
    @Override
    public void nullstill() {

        // Set the number of iterations to 'antall',
        // because antall will change during the loop:
        int iters = antall;
        for (int i = 0; i < iters; i++) {
            fjern(0);
        }
    }

    /*
    @Override
    public void nullstill() {

        Node<T> curNode = hode;
        int iters = antall;
        for (int i = 0; i < iters; i++) {
            curNode.forrige = null;
            curNode.verdi = null;

            Node<T> tmp = curNode.neste;
            curNode.neste = null;

            curNode = tmp;

            antall--;
            endringer++;
        }

        hode = null;
        hale = null;
    }
    */

    // Oppgave 8 b)
    @Override
    public Iterator<T> iterator() {
        return new DobbeltLenketListeIterator();
    }

    // Oppgave 8 d)
    public Iterator<T> iterator(int indeks) {
        indeksKontroll(indeks, false);
        return new DobbeltLenketListeIterator(indeks);
    }

    // Oppgave 3 b)
    public Liste<T> subliste(int fra, int til) {
        fraTilKontroll(antall, fra, til);

        Liste<T> sub = new DobbeltLenketListe<>();

        Node<T> node = finnNode(fra);

        for (int i = fra; i < til; i++) {
            sub.leggInn(node.verdi);
            node = node.neste;
        }

        return sub;
    }

    // Oppgave 3 b)
    private void fraTilKontroll(int antall, int fra, int til) {
        if (antall < 0)
            throw new IllegalArgumentException
                    ("Negativt antall");

        if (fra < 0)
            throw new IndexOutOfBoundsException
                    ("fra(" + fra + ") er negativ!");

        if (til > antall)
            throw new IndexOutOfBoundsException
                    ("til(" + til + ") > antall(" + antall + ")");

        if (fra > til)
            throw new IllegalArgumentException
                    ("fra(" + fra + ") > til(" + til + ") - ulovlig intervall!");
    }

    // Oppgave 2 a)
    @Override
    public String toString() {
        StringBuilder strBuilder = new StringBuilder("[");

        Node<T> node = hode;
        for (int i = 0; i < antall; i++) {

            strBuilder.append(node.verdi);

            if (i < antall - 1)
                strBuilder.append(", ");

            node = node.neste;
        }

        strBuilder.append("]");

        return strBuilder.toString();
    }

    public String omvendtString() {
        StringBuilder strBuilder = new StringBuilder("[");

        Node<T> node = hale;
        for (int i = 0; i < antall; i++) {

            strBuilder.append(node.verdi);

            if (i < antall - 1)
                strBuilder.append(", ");

            node = node.forrige;
        }

        strBuilder.append("]");

        return  strBuilder.toString();
    }

    // Oppgave 10
    // Insertion-sort, comparing each value to earlier elements and placing it when
    // the previous value is smaller:
    // The algorithm sorts a list of 1600 elements in around 1 second and a list of
    // 3200 elements in around 7.7 seconds, so the method seems to be of cubic order.
    // According to my rough analysis of the method, it uses (n^3 - n^2)/2 iterations
    // to sort a list of n elements and O(n^3).
    // (n-1)*((n-1) + (n-2) + (n-2)*(n-1) + n-1) = (n-1)*(3*n-4 + (n-2)(n-1))
    // = (n-2)*(n-1)^2 + (n-1)(3n-4) = (n-2)*(n^2-2*n+1) + (3n^2-7*n+4)
    // = n^3-2*n^2+n - 2*n^2+4*n-2 + 3*n^2 - 7*n + 4 = n^3 - n^2 - 2*n + 2
    public static <T> void insertionSort(Liste<T> list, Comparator<T> c) {

        for (int i = 1; i < list.antall(); i++) {
            T current = list.hent(i);

            int j = i - 1;
            T prev = list.hent(j);

            // Compare current value with previous values and move
            // greater values further back in the list:
            while (j >= 0 && c.compare(current, prev) < 0) {
                list.oppdater(j + 1, prev);
                j--;
            }

            // If the loop went all the way to the end, j will be equal to -1 and so
            // 'verdi' will be placed in the first spot in the array.
            // Else, a[j] will be less than 'verdi' while everything after a[j] will be greater.
            list.oppdater(j + 1, current);
        }
    }

    private static final class Node<T> {

        private T verdi;
        private Node<T> forrige, neste;

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {

        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;
            fjernOK = false;
            iteratorendringer = endringer;
        }

        // Oppgave 8 c)
        private DobbeltLenketListeIterator(int indeks) {
            this();

            // Move 'denne' to the node at 'indeks' with the use of 'next':
            for (int i = 0; i < indeks; i++) {
                next();
            }
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        // Oppgave 8 a)
        @Override
        public T next() {
            if (iteratorendringer != endringer)
                throw new ConcurrentModificationException("Listen har blitt endret!");

            if (!hasNext())
                throw new NoSuchElementException();

            fjernOK = true;

            T gammelVerdi = denne.verdi;
            denne = denne.neste;

            return gammelVerdi;
        }

        // Oppgave 9 a)
        @Override
        public void remove() {
            if (!fjernOK)
                throw new IllegalStateException();

            if (iteratorendringer != endringer)
                throw new ConcurrentModificationException();

            fjernOK = false;

            if (antall == 1) {
                // Remove the references to the only node:
                hode = hale = null;

            } else if (denne == null) {
                // Remove the references to the last node:
                Node<T> forrigeNode = hale.forrige;
                forrigeNode.neste = null;
                hale = forrigeNode;

            } else if (denne.forrige.equals(hode)) {
                // Remove the references to the first node:
                denne.forrige = null;
                hode = denne;

            } else {
                // Remove the references to a node in the middle:
                Node<T> node = denne.forrige;
                Node<T> forrigeNode = node.forrige;

                denne.forrige = forrigeNode;
                forrigeNode.neste = denne;
            }

            antall--;
            iteratorendringer++;
            endringer++;
        }
    }
}
