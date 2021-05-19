package de.nmarion.htw.ueb16.toplevel;

import java.util.Random;

import de.nmarion.htw.ueb16.NumberCruncherTopLevel.CrunchOperation;

public class Swirl implements CrunchOperation {

    @Override
    public String getName() {
        return "swirl";
    }

    @Override
    public void crunch(float[] values) {
        final Random r = new Random();
        for (int i = 0; i < values.length; i++) {
            values[r.nextInt(values.length - 1)] = values[r.nextInt(values.length - 1)];
        }

    }

}
