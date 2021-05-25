package de.nmarion.htw.ueb16.toplevel;

import de.nmarion.htw.ueb16.NumberCruncherTopLevel.CrunchOperation;
import java.util.Random;

public class Swirl implements CrunchOperation {

  @Override
  public String getName() {
    return "swirl";
  }

  /**
   * Das Array wird zufällig durchgemischt
   *
   * @param values Übergebenes Array
   */
  @Override
  public void crunch(float[] values) {
    final Random r = new Random();
    for (int i = 0; i < values.length; i++) {
      values[r.nextInt(values.length - 1)] = values[r.nextInt(values.length - 1)];
    }
  }
}
