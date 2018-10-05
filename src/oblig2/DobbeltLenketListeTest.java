package oblig2;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class DobbeltLenketListeTest {

    @org.junit.jupiter.api.Test
    void leggInn() {
        Integer i[] = {1, 2, 3, 4, 5};
        DobbeltLenketListe<Integer> list = new DobbeltLenketListe<>(i);

        list.leggInn(6);

        int expectedAntall = 6;
        int antall = list.antall();

        int expectedLastValue = 6;
        int lastValue = list.hent(list.antall() - 1);

        assertEquals(expectedAntall, antall);
        assertEquals(expectedLastValue, lastValue);
    }

    @org.junit.jupiter.api.Test
    void leggInn1() {
        Integer i[] = {2, 4};
        DobbeltLenketListe<Integer> list = new DobbeltLenketListe<>(i);

        // Add value to start:
        list.leggInn(0, 1);

        // Add value to middle:
        list.leggInn(2, 3);

        // Add value to end:
        list.leggInn(4, 5);

        // The original list has 2 elements and we add 3 so the resulting list should have 5.
        int expectedAntall = 5;
        int antall = list.antall();

        // We put the value 5 at the end.
        int expectedLastValue = 5;
        int lastValue = list.hent(list.antall() - 1);

        assertEquals(expectedAntall, antall);
        assertEquals(expectedLastValue, lastValue);
    }

    @org.junit.jupiter.api.Test
    void inneholder() {
        Integer i[] = {1, 2, 3, 4, 5};
        DobbeltLenketListe<Integer> list = new DobbeltLenketListe<>(i);

        boolean inneholderFirst = list.inneholder(1);
        boolean inneholderMid = list.inneholder(3);
        boolean inneholderLast = list.inneholder(5);
        boolean inneholderIkke = list.inneholder(7);

        assertTrue(inneholderFirst);
        assertTrue(inneholderMid);
        assertTrue(inneholderLast);

        assertFalse(inneholderIkke);
    }

    @org.junit.jupiter.api.Test
    void hent() {
        Integer i[] = {1, 2, 3, 4, 5};
        DobbeltLenketListe<Integer> list = new DobbeltLenketListe<>(i);

        // Test that it works for the first value:
        int expectedFirst = 1;
        int hentFirst = list.hent(0);

        // Test that it works for a value in the middle:
        int expectedMid = 3;
        int hentMid = list.hent(2);

        // Test that it works for the last value:
        int expectedLast = 5;
        int hentLast = list.hent(4);

        assertEquals(expectedFirst, hentFirst);
        assertEquals(expectedMid, hentMid);
        assertEquals(expectedLast, hentLast);
    }

    @org.junit.jupiter.api.Test
    void indeksTil() {
        Integer i[] = {1, 2, 3, 4, 5};
        DobbeltLenketListe<Integer> list = new DobbeltLenketListe<>(i);

        int expectedFirst = 0;
        int firstIndex = list.indeksTil(i[0]);

        int expectedMid = 2;
        int midIndex = list.indeksTil(i[2]);

        int expectedLast = 4;
        int lastIndex = list.indeksTil(i[4]);

        int expectedOut = -1;
        int outIndex = list.indeksTil(6);

        assertEquals(expectedFirst, firstIndex);
        assertEquals(expectedMid, midIndex);
        assertEquals(expectedLast, lastIndex);
        assertEquals(expectedOut, outIndex);
    }

    @org.junit.jupiter.api.Test
    void oppdater() {
        Integer i[] = {1, 2, 3, 4, 5};
        DobbeltLenketListe<Integer> list = new DobbeltLenketListe<>(i);

        // Update first:
        int firstIndex = 0;
        list.oppdater(firstIndex, 10);
        int expectedFirst = 10;
        int firstValue = list.hent(firstIndex);

        // Update mid:
        int midIndex = 2;
        list.oppdater(midIndex, 30);
        int expectedMid = 30;
        int midValue = list.hent(midIndex);

        // Update last:
        int lastIndex = list.antall() - 1;
        list.oppdater(lastIndex, 50);
        int expectedLast = 50;
        int lastValue = list.hent(lastIndex);

        assertEquals(expectedFirst, firstValue);
        assertEquals(expectedMid, midValue);
        assertEquals(expectedLast, lastValue);
    }

    @org.junit.jupiter.api.Test
    void fjern() {
        Integer i[] = {1, 2, 3, 4, 5};
        DobbeltLenketListe<Integer> list = new DobbeltLenketListe<>(i);

        // Remove first:
        int firstIndex = 0;
        list.fjern(firstIndex);
        int expectedFirst = 2;
        int firstValue = list.hent(firstIndex);

        // Remove mid:
        int midIndex = 1;
        list.fjern(midIndex);
        int expectedMid = 4;
        int midValue = list.hent(midIndex);

        // Remove last:
        int lastIndex = list.antall() - 1;
        list.fjern(lastIndex);
        int expectedLast = 4;
        lastIndex = list.antall() - 1;
        int lastValue = list.hent(lastIndex);

        assertEquals(expectedFirst, firstValue);
        assertEquals(expectedMid, midValue);
        assertEquals(expectedLast, lastValue);
    }

    @org.junit.jupiter.api.Test
    void fjern1() {
        String s[] = {"H", "E", "L", "L", "O"};
        DobbeltLenketListe<String> list = new DobbeltLenketListe<>(s);

        // Remove first:
        list.fjern("H");
        String expectedFirst = "E";
        String firstValue = list.hent(0);

        // Remove mid:
        list.fjern("L");
        String expectedMid = "L";
        String midValue = list.hent(1);

        // Remove last:
        list.fjern("O");
        String expectedLast = "L";
        String lastValue = list.hent(list.antall() - 1);

        assertEquals(expectedFirst, firstValue);
        assertEquals(expectedMid, midValue);
        assertEquals(expectedLast, lastValue);
    }

    @org.junit.jupiter.api.Test
    void antall() {
        DobbeltLenketListe<Integer> emptyList = new DobbeltLenketListe<>();
        int expectedEmpty = 0;
        int amountEmpty = emptyList.antall();

        Integer intArray[] = {1, 2, 3, 4, 5};
        DobbeltLenketListe<Integer> list = new DobbeltLenketListe<>(intArray);
        int expected = 5;
        int amount = list.antall();

        Integer nullIntArray[] = {null, 1, 2, 3, null, 4, 5};
        DobbeltLenketListe<Integer> randomNullList = new DobbeltLenketListe<>(nullIntArray);
        int expectedRNull = 5;
        int amountRNull = randomNullList.antall();

        // Amount should be 0, because 0 elements have been added to the list.
        assertEquals(expectedEmpty, amountEmpty);
        // Amount should be five, because the list contains 5 elements:
        assertEquals(expected, amount);
        // Amount should be five, because the list contains 5 elements that's not null:
        assertEquals(expectedRNull, amountRNull);
    }

    @org.junit.jupiter.api.Test
    void tom() {
        DobbeltLenketListe<Integer> list = new DobbeltLenketListe<>();

        Integer i[] = {1, 2, 3, 4, 5};
        DobbeltLenketListe<Integer> list2 = new DobbeltLenketListe<>(i);

        // Should be empty because nothing was added to the list:
        assertTrue(list.tom());
        // Should not be empty because the list contains values:
        assertFalse(list2.tom());
    }

    @org.junit.jupiter.api.Test
    void nullstill() {
        Integer i[] = {1, 2, 3, 4, 5};
        DobbeltLenketListe<Integer> list = new DobbeltLenketListe<>(i);
        list.nullstill();

        int expectedAntall = 0;
        int antall = list.antall();

        assertEquals(expectedAntall, antall);
    }

    @org.junit.jupiter.api.Test
    void iterator() {
        // Iterator for empty list:
        DobbeltLenketListe<Integer> emptyList = new DobbeltLenketListe<>();
        Iterator<Integer> emptyIter = emptyList.iterator();

        assertFalse(emptyIter.hasNext());
        assertThrows(NoSuchElementException.class, emptyIter::next);

        // Iterator for non-empty list:
        String s[] = {"H", "E", "I"};
        DobbeltLenketListe<String> list = new DobbeltLenketListe<>(s);
        Iterator<String> iter = list.iterator();

        assertTrue(iter.hasNext());

        String expectedFirst = "H";
        String firstValue = iter.next();

        assertEquals(expectedFirst, firstValue);
    }

    @org.junit.jupiter.api.Test
    void toStr() {
        String s[] = {"Hei", "på", null, "deg", "!", null};
        DobbeltLenketListe<String> list = new DobbeltLenketListe<>(s);

        String str = list.toString();
        String expectedStr = "[Hei, på, deg, !]";

        assertEquals(expectedStr, str);
    }

    @org.junit.jupiter.api.Test
    void omvendtString() {
        String s[] = {"Hei", "på", null, "deg", "!", null};
        DobbeltLenketListe<String> list = new DobbeltLenketListe<>(s);

        String omvendtStr = list.omvendtString();
        String expectedOmvendt = "[!, deg, på, Hei]";

        assertEquals(expectedOmvendt, omvendtStr);
    }
}