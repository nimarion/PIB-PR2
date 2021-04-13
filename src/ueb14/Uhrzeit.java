package ueb14;

public class Uhrzeit {

    private final int stunde;
    private final int minute;

    public Uhrzeit(final int stunde, final int minute) {
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