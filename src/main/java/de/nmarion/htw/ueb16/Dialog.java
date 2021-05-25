package de.nmarion.htw.ueb16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Dialog {

  private NumberCruncher numberCruncher;

  /** Methode zum interaktiven Testen der Artikel Klasse */
  public void readInput() {
    try (final Scanner scanner = new Scanner(System.in, "UTF-8")) {
      while (true) {
        System.out.println("Auswählbare Kommandos: Operation, Füllen, Ausgeben, Stop\n");
        final String input = readString(scanner, "Command: ").toLowerCase().trim();
        if (numberCruncher == null
            && (!input.equalsIgnoreCase("füllen") && !input.equalsIgnoreCase("stop"))) {
          System.out.println("Es wurde noch keine Zahlen mit *füllen* festgelegt!");
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
        case "operation":
          final List<String> operations = new ArrayList<>();
          System.out.println(
              "Auswählbare Operationen: Sum, Swirl, Divide, Subtract, Average, *Ende*\n");
          while (true) {
            final String operation = readString(scanner, "Operation: ").toLowerCase().trim();
            if (operation.equals("ende")) {
              break;
            }
            operations.add(operation);
          }
          System.out.println(
              "Folgende Operationen werden auf dem Array ausgeführt: \n" + operations.toString());
          numberCruncher.crunch(operations.toArray(new String[0]));
          break;
        case "füllen":
          final String operation = readString(scanner, "Zufällig/Manuell?: ").toLowerCase().trim();
          float[] floatArray;
          if (operation.equals("zufällig")) {
            Random random = new Random();
            floatArray = new float[10];
            for (int i = 0; i < 10; i++) {
              floatArray[i] = -100 + random.nextFloat() * 200;
            }
          } else {
            int size = readInt(scanner, "Geben Sie die Größe des Arrays an: ");
            floatArray = new float[size];
            for (int i = 0; i < size; i++) {
              floatArray[i] = readFloat(scanner, String.format("Zahl eingeben (%d): ", i));
            }
          }
          final String mode = readString(scanner, "Toplevel/Anonym?: ").toLowerCase().trim();
          if (mode.equals("anonym")) {
            numberCruncher = new NumberCruncherAnonym(floatArray);
          } else {
            numberCruncher = new NumberCruncherTopLevel(floatArray);
          }

          break;
        case "ausgeben":
          System.out.println(Arrays.toString(numberCruncher.getNumbers()));
          break;
        case "stop":
          System.exit(0);
        default:
          System.out.printf("Der Befehl %s existiert nicht%n", input);
      }
    } catch (Exception exception) {
      exception.printStackTrace();
    }
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

  /**
   * Wartet bis der Nutzer eine Nummer eingegeben hat
   *
   * @param scanner
   * @param promtMessage
   * @return die eingegebene Nummer
   */
  private float readFloat(final Scanner scanner, final String promtMessage) {
    System.out.print(promtMessage);
    while (!scanner.hasNextFloat()) {
      scanner.nextLine();
      System.out.print(promtMessage);
    }
    final float number = scanner.nextFloat();
    scanner.nextLine();
    return number;
  }

  public static void main(String... args) {
    new Dialog().readInput();
  }
}
