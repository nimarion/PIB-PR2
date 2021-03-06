package de.nmarion.htw.ueb16.toplevel;

import de.nmarion.htw.ueb16.NumberCruncherTopLevel.CrunchOperation;

public class Subtract implements CrunchOperation {

  @Override
  public String getName() {
    return "subtract";
  }

  /**
   * Das Array wird stellenweise subtrahiert. a[0] = a[1] - a[0]; a[1] = a[2] - a[1]
   *
   * @param values Übergebenes Array
   */
  @Override
  public void crunch(float[] values) {
    for (int i = 0; i < values.length - 1; i++) {
      values[i + 1] -= values[i];
    }
  }
}
