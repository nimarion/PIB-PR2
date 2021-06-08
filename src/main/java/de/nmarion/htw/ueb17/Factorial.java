package de.nmarion.htw.ueb17;

public class Factorial {

  public static int fak(int i) {
    int result = 1;
    for (int a = 2; a <= i; a++) {
      result *= a;
    }
    return result;
  }

  static class Nested {
    public static int fakNested(int i) {
      int result = 1;
      for (int a = 2; a <= i; a++) {
        result *= a;
      }
      return result;
    }
  }
}
