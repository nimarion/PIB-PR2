package de.nmarion.htw.ueb16.toplevel;

import de.nmarion.htw.ueb16.NumberCruncherTopLevel.CrunchOperation;

public class Average implements CrunchOperation {

  @Override
  public String getName() {
    return "average";
  }

  /**
   * Durchschnittswert der Werte im Array wird ermittelt und das Ergebnis
   * an die Stelle des größten Wertes im Array platziert
   * 
   * @param values Übergebenes Array
   */
  @Override
  public void crunch(float[] values) {
    float sum = 0;
    int maxIndex = 0;
    float maxValue = 0;
    for (int i = 0; i < values.length; i++) {
      sum += values[i];
      if (values[i] > maxValue) {
        maxValue = values[i];
        maxIndex = i;
      }
    }
    float avg = sum / values.length;
    values[maxIndex] = avg;
  }
}
