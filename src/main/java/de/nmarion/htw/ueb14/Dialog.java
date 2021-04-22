package de.nmarion.htw.ueb14;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dialog {

  private List<Mitarbeiter> mitarbeiterList = new ArrayList<>();
  private List<Raum> raumList = new ArrayList<>();

  /** Methode zum interaktiven Testen der Artikel Klasse */
  public void readInput() {
    try (final Scanner scanner = new Scanner(System.in)) {
      while (true) {
        final String input = readString(scanner, "Command: ").toLowerCase().trim();
        if ((mitarbeiterList.isEmpty() || raumList.isEmpty())
            && input.equalsIgnoreCase("reservierung")) {
          System.out.println(
              "Es wurde noch keine Räume oder Mitarbeiter mit *mitarbeiter* oder *raum* erstellt");
          continue;
        }
        executeCommand(scanner, input);
      }
    }
  }

  /**
   * Führt den eingebenen Befehl aus
   *
   * @param scanner das genutzte Scanner Objekt
   * @param input der eingebene Command
   */
  private void executeCommand(final Scanner scanner, String input) {
    try {
      switch (input) {
        case "mitarbeiter":
          mitarbeiterList.add(createMitarbeiter(scanner));
          break;
        case "raum":
          raumList.add(createRaum(scanner));
          break;
        case "reservierung":
          createReservierung(scanner);
          break;
        case "ausgeben":
          raumList.forEach(System.out::println);
          break;
        case "stop":
          System.exit(0);
        default:
          System.out.printf("Der Befehl %s existiert nicht\n", input);
      }
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  /**
   * Erstellt ein neues Mitarbeiter Objekt
   *
   * @param scanner das genutzte Scanner Objekt
   * @return das erstellte Mitarbeiter Objekt
   */
  private Mitarbeiter createMitarbeiter(final Scanner scanner) {
    final String vorname = readString(scanner, "Vorname: ");
    final String nachname = readString(scanner, "Nachname: ");
    final String email = readString(scanner, "Mail: ");
    return new Mitarbeiter(vorname, nachname, email);
  }

  /**
   * Erstellt ein neues Raum Objekt
   *
   * @param scanner das genutzte Scanner Objekt
   * @return das erstellte Raum Objekt
   */
  private Raum createRaum(final Scanner scanner) {
    final int geb = readInt(scanner, "Gebäude: ");
    final int etage = readInt(scanner, "Etage: ");
    final int raum = readInt(scanner, "Raumnummer: ");
    return new Raum(geb, etage, raum);
  }

  /**
   * Erstellt eine neue Reservierung bei einem Mitarbeiter
   *
   * @param scanner das genutzte Scanner Objekt
   */
  private void createReservierung(final Scanner scanner) {
    final Raum raum = (Raum) selectFromList(scanner, "Wähle einen Raum aus: ", this.raumList);
    final Mitarbeiter mitarbeiter =
        (Mitarbeiter)
            selectFromList(scanner, "Wähle einen Mitarbeiter aus: ", this.mitarbeiterList);
    final String bemerkung = readString(scanner, "Bemerkung: ");
    final Uhrzeit start =
        new Uhrzeit(readInt(scanner, "Startstunde: "), readInt(scanner, "Startminute: "));
    final Uhrzeit end =
        new Uhrzeit(readInt(scanner, "Endstunde: "), readInt(scanner, "Endminute: "));
    mitarbeiter.reserviere(raum, start, end, bemerkung);
    System.out.println("Reservierung wurde erstellt");
  }

  /**
   * Erlaubt das Auswählen eines Objektes aus einer Liste
   *
   * @param scanner das genutzte Scanner Objekt
   * @param promtMessage
   * @param list die zu nutzende Liste
   * @return das ausgewählte Objekt
   */
  private Object selectFromList(
      final Scanner scanner, final String promtMessage, final List<?> list) {
    System.out.println(promtMessage);
    for (int i = 0; i < list.size(); i++) {
      System.out.println(String.format("%d: %s", i, list.get(i)));
    }
    int selectedIndex = -1;
    while (selectedIndex < 0 || selectedIndex > list.size()) {
      selectedIndex = readInt(scanner, "Index: ");
    }
    return list.get(selectedIndex);
  }

  /**
   * @param scanner das genutzte Scanner Objekt
   * @param promtMessage
   * @return der eingegebene String
   */
  private String readString(final Scanner scanner, final String promtMessage) {
    System.out.print(promtMessage);
    return scanner.nextLine();
  }

  /**
   * Wartet bis der Nutzer eine Nummer eingegeben hat
   *
   * @param scanner
   * @param promtMessage
   * @return die eingegebene Nummer
   */
  private int readInt(final Scanner scanner, final String promtMessage) {
    System.out.print(promtMessage);
    while (!scanner.hasNextInt()) {
      scanner.nextLine();
      System.out.print(promtMessage);
    }
    final int number = scanner.nextInt();
    scanner.nextLine();
    return number;
  }

  public static void main(String... args) {
    new Dialog().readInput();
  }
}
