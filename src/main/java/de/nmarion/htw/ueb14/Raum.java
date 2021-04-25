package de.nmarion.htw.ueb14;

import java.util.ArrayList;
import java.util.List;

public class Raum {

  protected final List<Reservierung> reservierung;
  private final int geb;
  private final int etage;
  private final int raum;

  /**
   * Konstruktor für die Raum Klasse
   *
   * @param geb die Gebäudenummer für den Raum
   * @param etage die Etagen des Raums
   * @param raum die Raumnummer
   * @throws IllegalArgumentException wenn <code>geb</code> negativ ist
   * @throws IllegalArgumentException wenn <code>raum</code> negativ ist
   */
  public Raum(final int geb, final int etage, final int raum) {
    if (geb < 0) {
      throw new IllegalArgumentException("Gebäudenummer darf nicht negativ sein");
    }
    if (raum < 0) {
      throw new IllegalArgumentException("Raumnummer darf nicht negativ sein");
    }
    this.geb = geb;
    this.etage = etage;
    this.raum = raum;
    this.reservierung = new ArrayList<>();
  }

  @Override
  public String toString() {
    final StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(String.format("Raum %d-%d.%d", geb, etage, raum));
    reservierung.forEach(reservierung -> stringBuilder.append("\ngebucht von " + reservierung));
    return stringBuilder.toString();
  }

  public void addReservierung(final Reservierung reservierung) {
    this.reservierung.add(reservierung);
  }

  public Reservierung getReservierung(final int index) {
    return this.reservierung.get(index);
  }

  public int getAnzahlReservierung() {
    return this.reservierung.size();
  }

  public int getGeb() {
    return geb;
  }

  public int getEtage() {
    return etage;
  }

  public int getRaum() {
    return raum;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + etage;
    result = prime * result + geb;
    result = prime * result + raum;
    result = prime * result + ((reservierung == null) ? 0 : reservierung.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof Raum)) {
      return false;
    }
    final Raum raum = (Raum) obj;
    return raum.getEtage() == this.etage
        && raum.getGeb() == this.geb
        && raum.getRaum() == this.raum;
  }
  
}
