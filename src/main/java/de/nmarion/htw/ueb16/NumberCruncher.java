package de.nmarion.htw.ueb16;

public abstract class NumberCruncher {

    protected float[] list;

    public NumberCruncher(final float[] list) {
        this.list = list;
    }

    public void crunch(String[] operations) {
    }

    public float[] getNumbers() {
        return this.list;
    }

}
