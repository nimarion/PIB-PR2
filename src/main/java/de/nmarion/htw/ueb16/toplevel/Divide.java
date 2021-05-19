package de.nmarion.htw.ueb16.toplevel;

import de.nmarion.htw.ueb16.NumberCruncherTopLevel.CrunchOperation;
import java.util.Arrays;

public class Divide implements CrunchOperation {

  @Override
  public String getName() {
    return "divide";
  }

  @Override
  public void crunch(float[] values) {
    Arrays.sort(values);
    for (int i = 0; i < values.length; i++) {
      values[values.length - 1 - i] /= values[i];
    }
  }
}
