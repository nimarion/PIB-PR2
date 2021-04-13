package ueb14;

import java.util.ArrayList;
import java.util.List;

public class Raum {

    protected final List<Reservierung> reservierung;
    private final int geb;
    private final int etage;
    private final int raum;

    public Raum(final int geb, final int etage, final int raum) {
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

    public void addReservierung(final Reservierung reservierung){
        this.reservierung.add(reservierung);
    }

    public Reservierung getReservierung(final int index){
        return this.reservierung.get(index);
    }

    public int getAnzahlReservierung(){
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
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;

        }
        if (!(obj instanceof Raum)) {
            return false;
        }
        final Raum raum = (Raum) obj;
        return raum.getEtage() == this.etage && raum.getGeb() == this.geb && raum.getRaum() == this.raum;
    }
}
