package de.nmarion.htw.ueb16.toplevel;

import de.nmarion.htw.ueb16.NumberCruncherTopLevel.CrunchOperation;

public class Subtract implements CrunchOperation {

    @Override
    public String getName() {
        return "subtract";
    }

    @Override
    public void crunch(float[] values) {
        for (int i = 0; i < values.length - 1; i++) {
            values[i + 1] -= values[i];
        }

    }
}
