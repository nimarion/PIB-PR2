package de.nmarion.htw.ueb16;

import de.nmarion.htw.ueb16.toplevel.Average;
import de.nmarion.htw.ueb16.toplevel.Divide;
import de.nmarion.htw.ueb16.toplevel.Subtract;
import de.nmarion.htw.ueb16.toplevel.Sum;
import de.nmarion.htw.ueb16.toplevel.Swirl;
import java.util.Arrays;
import java.util.List;

public class NumberCruncherTopLevel extends NumberCruncher {

  private final List<CrunchOperation> operations;

  public NumberCruncherTopLevel(float[] list) {
    super(list);
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

  public interface CrunchOperation {
    String getName();

    void crunch(float values[]);
  }
}
