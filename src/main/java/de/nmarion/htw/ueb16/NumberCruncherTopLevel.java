package de.nmarion.htw.ueb16;

import java.util.Arrays;
import java.util.List;

import de.nmarion.htw.ueb16.toplevel.Average;
import de.nmarion.htw.ueb16.toplevel.Divide;
import de.nmarion.htw.ueb16.toplevel.Subtract;
import de.nmarion.htw.ueb16.toplevel.Sum;
import de.nmarion.htw.ueb16.toplevel.Swirl;

public class NumberCruncherTopLevel {

    private float[] list;
    private final List<CrunchOperation> operations;


    public NumberCruncherTopLevel(final float[] list) {
        this.list = list;
        operations = Arrays.asList(new Sum(), new Swirl(), new Divide(), new Subtract(), new Average());
    }

    public void crunch(String[] operations) {
        for (String s : operations) {
            for (CrunchOperation operator : this.operations) {
                if (operator.getName().equals(s.toLowerCase())) {
                    operator.crunch(list);
                }
            }
        }
    }

    public float[] getNumbers() {
        return this.list;
    }

    public interface CrunchOperation {
        String getName();

        void crunch(float values[]);
    }

}
