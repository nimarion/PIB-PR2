package de.nmarion.htw.ueb18;

import java.util.List;

/**
 * Diese Klasse ist eine Fassade, hinter der Sie Ihre Loesung verstecken. Der Test ruft nur die hier
 * definierten Schnittstellenmethoden auf. Loeschen Sie bitte keine Methoden. Wenn Sie eine Methode
 * nicht implementieren moechten, lassen Sie bitte die leere Implementierung stehen. Innerhalb der
 * Methoden und in allen anderen Klassen, die Sie noch benoetigen, haben Sie alle Freiheiten.
 *
 * <p>Wenn Sie Ihre Loesung mit JUnit testen moechten, testen Sie diese Schnittstellenmethoden.
 *
 * @author christopher
 */
public class Ueb18Fassade {
  /**
   * Loest die Aufgabe (c) i. <br>
   * Sortierung nach den folgenden Kriterien:
   *
   * <ol>
   *   <li>Unterkategorie (alphabetisch)
   *   <li>Bestand
   *   <li>Preis
   * </ol>
   *
   * @param lager Das Lager mit der unsortierten Artikelliste.
   * @return Die sortierte Artikelliste.
   */
  public Artikel[] aufgabe_c_i(Lager lager) {}

  /**
   * Loest die Aufgabe (c) ii. <br>
   * Der Preis aller Artikel wird um 10% reduziert.
   *
   * @param lager Das Lager mit den Artikeln, deren Preise geaendert werden sollen.
   */
  public void aufgabe_c_ii(Lager lager) {
    lager.applyToArticles(a -> a.aenderePreis(-10));
  }

  /**
   * Loest die Aufgabe (c) iii. <br>
   * An alle Artikelbezeichnungen wird das Suffix (Sonderangebot) angehaengt.
   *
   * @param lager Das Lager mit den Artikeln, deren Bezeichnungen geaendert werden sollen.
   */
  public void aufgabe_c_iii(Lager lager) {
    lager.applyToArticles(a -> a.setArt("Sonderangebot" + a.getArt()));
  }

  /**
   * Loest die Aufgabe (c) iv. <br>
   * Die beiden Operatoren aus den Aufgabenteilen ii und iii werden konkateniert, d.h. alle Preise
   * werden um 10 % reduziert und alle Bezeichnungen werden um das Suffix (Sonderangebot) erweitert.
   *
   * @param lager Das Lager mit den Artikeln, deren Preise und Bezeichnungen geaendert werden
   *     sollen.
   */
  public void aufgabe_c_iv(Lager lager) {
    lager.applyToArticles(
        a -> {
          a.setArt("Sonderangebot" + a.getArt());
          a.aenderePreis(-10);
        });
  }

  /**
   * Loest die Aufgabe (h) i. <br>
   * Der Preis aller CDs wird um 10 % erhoeht.
   *
   * @param lager Das Lager mit den Artikeln. Die Aenderungen werden direkt in diesem Objekt
   *     vorgenommen.
   */
  public void aufgabe_h_i(Lager lager) {
    lager.applyToSomeArticles(a -> a instanceof CD, a -> a.aenderePreis(10));
  }

  /**
   * Loest die Aufgabe (h) ii. <br>
   * Der Preis aller Artikel, von denen der Bestand hoechstes zwei ist, wird um 5 % reduziert.
   *
   * @param lager Das Lager mit den Artikeln. Die Aenderungen werden direkt in diesem Objekt
   *     vorgenommen.
   */
  public void aufgabe_h_ii(Lager lager) {
    lager.applyToSomeArticles(a -> a.getBestand() <= 2, a -> a.aenderePreis(-5));
  }

  /**
   * Loest die Aufgabe (h) iii. <br>
   * Der Preis der Buecher eines bestimmten Autors wird um 5 % reduziert.
   *
   * @param lager Das Lager mit den Artikeln. Die Aenderungen werden direkt in diesem Objekt
   *     vorgenommen.
   * @param gesuchterAutor Der Autor, dessen Buecher guenstiger werden sollen.
   */
  public void aufgabe_h_iii(Lager lager, String gesuchterAutor) {
    lager.applyToSomeArticles(
        a -> a instanceof Buch && ((Buch) a).getAutor().equals(gesuchterAutor),
        a -> a.aenderePreis(-5));
  }

  /**
   * Loest die Aufgabe (h) iv. <br>
   * Der Preis aller CDs wird um 10 % erhoeht und der Preis aller Artikel, von denen der Bestand
   * hoechstes zwei ist, wird um 5 % reduziert.
   *
   * @param lager Das Lager mit den Artikeln. Die Aenderungen werden direkt in diesem Objekt
   *     vorgenommen.
   */
  public void aufgabe_h_iv(Lager lager) {
    lager.applyToArticles(
        artikel -> {
          if (artikel instanceof CD) {
            artikel.aenderePreis(10);
          }
          if (artikel.getBestand() <= 2) {
            artikel.aenderePreis(-5);
          }
        });
  }

  /**
   * Loest die Aufgabe (h) v. <br>
   *
   * @param lager Das Lager mit den Artikeln.
   * @return Eine Liste mit allen Buechern, sortiert nach den Namen der Autoren.
   */
  public Artikel[] aufgabe_h_v(Lager lager) {
    List<Artikel> sorted =
        lager.getArticles(
            artikel -> artikel instanceof Buch,
            (a1, a2) -> {
              final Buch a = (Buch) a1;
              final Buch b = (Buch) a2;
              for (int i = 0; i < a.getAutor().length(); i++) {
                if (b.getAutor().length() <= i) {
                  return true;
                }
                int a_char = a.getAutor().charAt(i);
                int b_char = b.getAutor().charAt(i);
                if (b_char > a_char) {
                  return false;
                } else if (a_char > b_char) {
                  return true;
                }
              }
              return false;
            });
    Artikel[] array = new Artikel[sorted.size()];
    array = sorted.toArray(array);
    return array;
  }

  /**
   * Loest die Aufgabe (h) vi. <br>
   *
   * @param lager Das Lager, dessen Artikel gefiltert werden sollen.
   * @param gesuchterAutor Der Autor, nach dem gefiltert werden soll.
   * @param minPreis Der kleinste Preis, den die zu filternden Buecher haben sollen.
   * @param maxPreis Der hoechste Preis, den die zu filternden Buecher haben sollen.
   * @return Alle Buecher vom Autor autor und mit einem Preis, der zwischen minPreis und maxPreis
   *     liegt.
   */
  public Artikel[] aufgabe_h_vi(
      Lager lager, String gesuchterAutor, double minPreis, double maxPreis) {
    List<Artikel> list =
        lager.filterAll(
            artikel -> artikel.getPrice() >= minPreis, artikel -> artikel.getPrice() <= maxPreis);
    Artikel[] array = new Artikel[list.size()];
    array = list.toArray(array);
    return array;
  }
}
