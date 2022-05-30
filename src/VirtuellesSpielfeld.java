package src;

import yanwittmann.file.File;

import java.io.IOException;
import java.util.Arrays;

public class VirtuellesSpielfeld {

    private char[][] spielfeld = new char[22][19];          //# = Wand; - = Blank; C = PacMan


    public boolean istWand(int x, int y) {
        if (spielfeld[y][x] == '#')
            return true;
        return false;
    }

    public void veraendereZelle(int x, int y, char neu) {
        spielfeld[y][x] = neu;
    }

    public int[] finde(char zeichen) {
        for (int i = 0; i < spielfeld.length; i++) {
            for (int j = 0; j < spielfeld[0].length; j++) {
                if (spielfeld[i][j] == zeichen)
                    return new int[]{j, i};
            }
        }
        return new int[]{-1, -1};
    }

    public void ladeLevel() {
        String[] textLevel = new String[23];
        Arrays.fill(textLevel, ";");
        File level = new File("levels/normaleslevel.txt");

        try {
            textLevel = level.readToArray();
        } catch (IOException e) {
            System.out.println("Fehler beim Lesen der Datei!");
        }

        try {
            for (int i = 0; i < spielfeld.length; i++) {
                String zeile = textLevel[i];
                System.out.println(zeile);
                for (int j = 0; j < spielfeld[0].length; j++) {
                    spielfeld[i][j] = zeile.charAt(j);
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Eingelesene Datei passt nicht!");
        }
    }

    public char[][] gibSpielfeld() {
        return spielfeld;
    }

    public char gibZelle(int x, int y) {
        return spielfeld[y][x];
    }
}
