package ueb14;

import java.util.ArrayList;
import java.util.List;

public class Mitarbeiter extends Person {

    private final String email;
    protected List<Reservierung> buchungen;

    /**
     * @param vorname
     * @param nachname
     * @param email
     */
    public Mitarbeiter(String vorname, String nachname, String email) {
        super(vorname, nachname);
        this.email = email;
        this.buchungen = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public void reserviere(final Raum raum, final Uhrzeit beginn, final Uhrzeit ende, final String bemerkung) {
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
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;

        }
        if (!(obj instanceof Mitarbeiter)) {
            return false;
        }
        final Mitarbeiter mitarbeiter = (Mitarbeiter) obj;
        return mitarbeiter.getEmail().equals(this.email) && mitarbeiter.getVorname().equals(this.getVorname())
                && mitarbeiter.getNachname().equals(this.getNachname());
    }

}
