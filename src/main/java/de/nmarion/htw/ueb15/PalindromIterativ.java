package de.nmarion.htw.ueb15;

public class PalindromIterativ implements Palindrom {

    @Override
    public boolean istPalindrom(String wort) {
        int a = 0;
        int b = wort.length() - 1;
        char[] chars = wort.toCharArray();
        while (b > a) {
            if (chars[a] != chars[b]) {
                return false;
            }
            a++;
            b--;
        }
        return true;
    }

}
