package de.nmarion.htw.ueb14;

public class Uhrzeit {

  private final int stunde;
  private final int minute;

  /**
   * Konstruktor der Uhrzeit Klasse
   *
   * @param stunde
   * @param minute
   */
  public Uhrzeit(final int stunde, final int minute) {
    if (stunde < 0 || stunde > 23) {
      throw new IllegalArgumentException("Stunde muss zwischen 0 und 23 liegen");
    }
    if (minute < 0 || minute > 59) {
      throw new IllegalArgumentException("Minute muss zwischen 0 und 59 liegen");
    }
    this.stunde = stunde;
    this.minute = minute;
  }

  public int getStunde() {
    return stunde;
  }

  public int getMinute() {
    return minute;
  }

  @Override
  public String toString() {
    return String.format("%d:%d Uhr", stunde, minute);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + minute;
    result = prime * result + stunde;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof Uhrzeit)) {
      return false;
    }
    final Uhrzeit uhrzeit = (Uhrzeit) obj;
    return uhrzeit.getMinute() == this.minute && uhrzeit.getStunde() == this.stunde;
  }
}
