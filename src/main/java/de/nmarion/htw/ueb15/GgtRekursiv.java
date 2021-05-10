package de.nmarion.htw.ueb15;

public class GgtRekursiv implements GgT {

    @Override
    public long berechneGgt(long a, long b) {
        if (b == 0) {
            return a;
        }
        return berechneGgt(b, a % b);
    }

}
