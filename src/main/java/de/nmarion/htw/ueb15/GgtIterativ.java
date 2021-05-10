package de.nmarion.htw.ueb15;

public class GgtIterativ implements GgT {

    @Override
    public long berechneGgt(long a, long b) {
        if (a == 0) {
            return b;
        }
        while (b != 0) {
            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
        }
        return a;
    }

}
