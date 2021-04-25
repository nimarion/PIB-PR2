package de.nmarion.htw.ueb14;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mitarbeiter extends Person {

  private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
      Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
  private final String email;
  protected List<Reservierung> buchungen;

  /**
   * Konstruktor für die Mitarbeiter Klasse
   *
   * @param vorname Vorname des Mitarbeiters
   * @param nachname Nachname des Mitarbeiters
   * @param email Email des Mitarbeiters
   * @throws IllegalArgumentException Wenn <code>email</code> null oder leer ist
   * @throws IllegalArgumentException Wenn <code>email</code> keine gültige Email Adresse ist
   */
  public Mitarbeiter(String vorname, String nachname, String email) {
    super(vorname, nachname);
    if (email == null || email.isBlank()) {
      throw new IllegalArgumentException("Email darf nicht null sein");
    }
    final Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
    if (!matcher.matches()) {
      throw new IllegalArgumentException("Keine gültige Email");
    }
    this.email = email;
    this.buchungen = new ArrayList<>();
  }

  public String getEmail() {
    return email;
  }

  /**
   * Erstellt eine Reservierung für den aktuellen Mitarbeiter
   *
   * @param raum der Raum der reserviert werden soll
   * @param beginn der Beginn der Reservierung
   * @param ende das Ende der Reservierung
   * @param bemerkung die Bemerkung für die Reservierung
   */
  public void reserviere(
      final Raum raum, final Uhrzeit beginn, final Uhrzeit ende, final String bemerkung) {
    final Reservierung reservierung = new Reservierung(beginn, ende);
    reservierung.setBemerkung(bemerkung);
    reservierung.setRaum(raum);
    reservierung.setMitarbeiter(this);
    buchungen.add(reservierung);
  }

  @Override
  public String toString() {
    return String.format("%s %s (%s)", getVorname(), getNachname(), this.email);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((buchungen == null) ? 0 : buchungen.hashCode());
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!super.equals(obj)) {
      return false;
    }
    if (!(obj instanceof Mitarbeiter)) {
      return false;
    }
    final Mitarbeiter mitarbeiter = (Mitarbeiter) obj;
    return mitarbeiter.getEmail().equals(this.email)
        && mitarbeiter.getVorname().equals(this.getVorname())
        && mitarbeiter.getNachname().equals(this.getNachname());
  }
}
