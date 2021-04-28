package de.nmarion.htw.ueb15;

public class PalindromRekursiv implements Palindrom {

    @Override
    public boolean istPalindrom(String wort) {
        if (wort.length() == 0 || wort.length() == 1) {
            return true;
        }
        if (wort.charAt(0) == wort.charAt(wort.length() - 1)) {
            return istPalindrom(wort.substring(1, wort.length() - 1));
        }
        return false;
    }

}
