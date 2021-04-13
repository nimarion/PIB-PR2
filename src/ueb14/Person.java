package ueb14;

public class Person {

    private final String vorname;
    private final String nachname;

    public Person(final String vorname, final String nachname) {
        this.vorname = vorname;
        this.nachname = nachname;
    }

    public String getNachname() {
        return nachname;
    }

    public String getVorname() {
        return vorname;
    }

    @Override
    public String toString() {
        return String.format("%s %s", vorname, nachname);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;

        }
        if (!(obj instanceof Person)) {
            return false;
        }
        final Person person = (Person) obj;
        return person.getNachname().equals(this.nachname) && person.getVorname().equals(this.vorname);
    }
}
