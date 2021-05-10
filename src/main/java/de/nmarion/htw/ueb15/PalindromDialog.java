package de.nmarion.htw.ueb15;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class PalindromDialog {

  public void scanInput(final Palindrom palindrom) {
    try (final Scanner scanner = new Scanner(System.in)) {
      while (true) {
        final String input = readString(scanner, "Eingabe: ").toLowerCase().trim();
        if (input.equals("stop")) {
          return;
        }
        System.out.println(
            input + (palindrom.istPalindrom(input) ? " ist ein Palindrom" : " ist kein Palindrom"));
      }
    }
  }

  public void scanFile(final Palindrom palindrom, final String filename) {
    final File file = new File(filename);
    if (!file.canRead()) {
      System.out.println("Datei existiert nicht oder kann nicht gelesen werden");
      return;
    }
    try (Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()))) {
      stream.forEach(
          line -> {
            System.out.println(
                line
                    + (palindrom.istPalindrom(line)
                        ? " ist ein Palindrom"
                        : " ist kein Palindrom"));
          });
    } catch (IOException ex) {
      ex.printStackTrace();
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

  public static void main(String... args) {
    if (args.length < 1) {
      System.out.println("java PalindromDialog rekursiv/iterativ [datei]");
      return;
    }
    final PalindromDialog dialog = new PalindromDialog();
    final Palindrom palindrom =
        args[0].equalsIgnoreCase("iterativ") ? new PalindromIterativ() : new PalindromRekursiv();
    if (args.length == 1) {
      dialog.scanInput(palindrom);
    } else {
      dialog.scanFile(palindrom, args[1]);
    }
  }
}
