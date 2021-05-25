package de.nmarion.htw.ueb16.toplevel;

import de.nmarion.htw.ueb16.NumberCruncherTopLevel.CrunchOperation;
import java.util.Arrays;

public class Divide implements CrunchOperation {

  @Override
  public String getName() {
    return "divide";
  }

  /**
   * Der größte Wert im Array wird durch den kleinsten Wert im Array geteilt, das Ergebnis wird an die Stelle
   * des größten Wertes geschrieben. Das geht so weiter für den zweitgrößten/-kleinsten, drittgrößten/-kleinsten, usw.
   * 
   * @param values Übergebenes Array
   */
  @Override
  public void crunch(float[] values) {
    Arrays.sort(values);
    for (int i = 0; i < values.length; i++) {
      values[values.length - 1 - i] /= values[i];
    }
  }
}
