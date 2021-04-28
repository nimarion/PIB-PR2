package de.nmarion.htw.ueb15;

public class MathFunctions {

  private MathFunctions() {}

  /**
   * Berechnet den GGT von zwei Zahlen rekursiv
   *
   * @param a
   * @param b
   * @return den berechneten GGT
   */
  public static long berechneGgt(long a, long b) {
    if (b == 0) {
      return a;
    }
    return berechneGgt(b, a % b);
  }
}
