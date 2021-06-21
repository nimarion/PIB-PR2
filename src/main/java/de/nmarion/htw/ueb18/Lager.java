package de.nmarion.htw.ueb18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Lager {

    private Artikel[] artikelArray;

    public Lager(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Lager muss größer als 0 sein");
        }
        artikelArray = new Artikel[size];
    }

    public Lager() {
        this(10);
    }

    public void legeAnArtikel(Artikel artikel) {
        if (isFull()) {
            throw new IllegalArgumentException("Lager ist voll");
        }
        if (getArtikelByNr(artikel.getArtikelNr()) != null) {
            throw new IllegalArgumentException(String
                    .format("Der Artikel mit der Nummer %d existiert bereits in dem Lager", artikel.getArtikelNr()));
        }
        artikelArray[getArtikelAnzahl()] = artikel;
    }

    public void entferneArtikel(int artikelNr) {
        for (int i = 0; i < artikelArray.length; i++) {
            if (artikelArray[i] != null && artikelArray[i].getArtikelNr() == artikelNr) {
                artikelArray[i] = artikelArray[getArtikelAnzahl() - 1];
                artikelArray[getArtikelAnzahl() - 1] = null;
                return;
            }
        }
        throw new NoSuchElementException(String.format("Der Artikel mit der Nummer %d existiert nicht", artikelNr));
    }

    public void bucheZugang(int artikelNr, int menge) {
        Artikel artikel = getArtikelByNr(artikelNr);
        if (artikel == null) {
            throw new NoSuchElementException(String.format("Der Artikel mit der Nummer %d existiert nicht", artikelNr));
        }
        artikel.bucheZugang(menge);
    }

    public void bucheAbgang(int artikelNr, int menge) {
        Artikel artikel = getArtikelByNr(artikelNr);
        if (artikel == null) {
            throw new NoSuchElementException(String.format("Der Artikel mit der Nummer %d existiert nicht", artikelNr));
        }
        artikel.bucheAbgang(menge);
    }

    public void aenderePreisAllerArtikel(double prozent) {
        for (Artikel artikel : artikelArray) {
            if (artikel != null) {
                artikel.aenderePreis(prozent);
            }
        }
    }

    public Artikel getArtikel(int index) {
        Artikel artikel = artikelArray[index];
        return artikel == null ? null : artikel;
    }

    public int getArtikelAnzahl() {
        int count = 0;
        for (Artikel artikel : artikelArray) {
            if (artikel != null) {
                count++;
            }
        }
        return count;
    }

    public int getLagerGroesse() {
        return artikelArray.length;
    }

    public Artikel getArtikelByNr(int artikelNr) {
        for (Artikel artikel : artikelArray) {
            if (artikel != null && artikel.getArtikelNr() == artikelNr) {
                return artikel;
            }
        }
        return null;
    }

    public boolean isFull() {
        return !((getArtikelAnzahl()) < getLagerGroesse());
    }

    public void ausgebenBestandsListe() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Artnr Beschreibung Preis Bestand Gesamt\n");
        double sumPrice = 0;
        for (Artikel artikel : artikelArray) {
            if (artikel != null) {
                sumPrice += artikel.getBestand() * artikel.getPrice();
                stringBuilder
                        .append(String.format("%d %s %f %d %f\n", artikel.getArtikelNr(), artikel.getBeschreibung(),
                                artikel.getPrice(), artikel.getBestand(), artikel.getBestand() * artikel.getPrice()));
            }
        }
        stringBuilder.append("Gesamtwert: " + sumPrice);
        System.out.println(stringBuilder.toString());
    }

    public Artikel[] getSorted(final BiPredicate<Artikel, Artikel> predicate) {
        Artikel[] sortedArray = artikelArray.clone();
        Arrays.sort(sortedArray, new Comparator<Artikel>() {
            public int compare(Artikel a1, Artikel a2) {
                if (a1 == null) {
                    return 1;
                }
                if (predicate.test(a1, a2) == false && predicate.test(a2, a1) == true) {
                    return -1;
                }
                if (predicate.test(a1, a2) == true && predicate.test(a2, a1) == true) {
                    return 0;
                }
                if (predicate.test(a1, a2) == false && predicate.test(a2, a1) == false) {
                    return 0;
                }
                if (predicate.test(a1, a2) == true && predicate.test(a2, a1) == false) {
                    return 1;
                }
                return 0;
            };
        });
        return sortedArray;

    }

    public void applyToArticles(Consumer<Artikel> consumer) {
        for (Artikel artikel : artikelArray) {
            if (artikel != null) {
                consumer.accept(artikel);
            }
        }
    }

    public void applyToSomeArticles(Predicate<Artikel> predicate, Consumer<Artikel> consumer) {
        for (Artikel artikel : artikelArray) {
            if (artikel != null && predicate.test(artikel)) {
                consumer.accept(artikel);
            }
        }
    }

    public List<Artikel> filter(Predicate<Artikel> predicate) {
        final List<Artikel> filteredList = new ArrayList<>();
        for (Artikel artikel : artikelArray) {
            if (artikel != null && predicate.test(artikel)) {
                filteredList.add(artikel);
            }
        }
        return filteredList;
    }

    public List<Artikel> filterAll(Predicate<Artikel>... predicates) {
        final List<Artikel> filteredList = new ArrayList<>();
        for (Artikel artikel : artikelArray) {
            boolean passed = true;
            for (Predicate<Artikel> predicate : predicates) {
                if (!predicate.test(artikel)) {
                    passed = false;
                    break;
                }
            }
            if (passed) {
                filteredList.add(artikel);
            }
        }
        return filteredList;
    }

    public List<Artikel> getArticles(final Predicate<Artikel> searchPredicate,
            final BiPredicate<Artikel, Artikel> sortPredicate) {
        return Arrays.asList(getSorted(sortPredicate)).stream().filter(artikel -> searchPredicate.test(artikel))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        Arrays.asList(artikelArray).stream().filter(artikel -> artikel != null)
                .forEach(artikel -> stringBuilder.append(artikel + "\n"));
        return stringBuilder.toString();
    }
}
