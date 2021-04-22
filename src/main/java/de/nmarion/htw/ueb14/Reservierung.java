package de.nmarion.htw.ueb14;

public class Reservierung {

  protected Raum raum;
  protected Mitarbeiter von;
  private String bemerkung;
  private final Uhrzeit beginn;
  private final Uhrzeit ende;

  /**
   * Konstruktor der Reservierung Klasse
   *
   * @param beginn Beginn der Reservierung
   * @param ende Ende der Reservierung
   */
  public Reservierung(final Uhrzeit beginn, final Uhrzeit ende) {
    this.beginn = beginn;
    this.ende = ende;
  }

  public void setMitarbeiter(Mitarbeiter mitarbeiter) {
    this.von = mitarbeiter;
  }

  public void setRaum(Raum raum) {
    this.raum = raum;
    raum.addReservierung(this);
  }

  /**
   * Ändert die Bemerkung für die Reservierung
   *
   * @param bemerkung die neue Bemerkung
   * @throws IllegalArgumentException wenn <code>bemerkung</code> null oder leer ist
   */
  public void setBemerkung(String bemerkung) {
    if (bemerkung == null || bemerkung.isBlank()) {
      throw new IllegalArgumentException("Bemerkung darf nicht null sein");
    }
    this.bemerkung = bemerkung;
  }

  public String getBemerkung() {
    return bemerkung;
  }

  public Uhrzeit getBeginn() {
    return beginn;
  }

  public Uhrzeit getEnde() {
    return ende;
  }

  @Override
  public String toString() {
    return String.format("%s von %s bis %s für %s", von, beginn, ende, this.bemerkung);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof Reservierung)) {
      return false;
    }
    final Reservierung reservierung = (Reservierung) obj;
    return reservierung.getBemerkung() == this.bemerkung
        && reservierung.getBeginn().equals(this.beginn)
        && reservierung.getEnde().equals(this.ende);
  }
}
