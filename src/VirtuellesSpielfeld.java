package src;

import yanwittmann.file.File;

import java.io.IOException;
import java.util.Arrays;

public class VirtuellesSpielfeld {

    private char[][] spielfeld = new char[23][19];          //# = Wand; - = Blank; C = PacMan

    public void bewegePacMan(char richtung) {

        int[] posPacManAlt = findePacMan(), posPacManNeu;

        switch (richtung) {
            case 'O':
                if (istKeineWand(posPacManAlt[0], posPacManAlt[1] - 1))
                    posPacManNeu = new int[]{posPacManAlt[0], posPacManAlt[1] - 1};
                else
                    posPacManNeu = posPacManAlt;
                break;
            case 'R':
                if (istKeineWand(posPacManAlt[0] + 1, posPacManAlt[1]))
                    posPacManNeu = new int[]{posPacManAlt[0] + 1, posPacManAlt[1]};
                else
                    posPacManNeu = posPacManAlt;
                break;
            case 'U':
                if (istKeineWand(posPacManAlt[0], posPacManAlt[1] + 1))
                    posPacManNeu = new int[]{posPacManAlt[0], posPacManAlt[1] + 1};
                else
                    posPacManNeu = posPacManAlt;
                break;
            case 'L':
                if (istKeineWand(posPacManAlt[0] - 1, posPacManAlt[1])) {
                    posPacManNeu = new int[]{posPacManAlt[0] - 1, posPacManAlt[1]};
                } else {
                    posPacManNeu = posPacManAlt;
                }
                break;

            default:
                throw new IllegalStateException("unmoegliche Richtung: " + richtung);
        }

        System.out.println(posPacManNeu[1] + " | " + posPacManNeu[0]);
        veraendereZelle(posPacManAlt[0], posPacManAlt[1], '-');
        veraendereZelle(posPacManNeu[0], posPacManNeu[1], 'C');

    }

    public boolean istKeineWand(int x, int y) {
        if (spielfeld[y][x] == '#')
            return false;
        return true;
    }

    public void veraendereZelle(int x, int y, char neu) {
        spielfeld[y][x] = neu;
    }

    private int[] findePacMan() {
        for (int i = 0; i < spielfeld.length; i++) {
            for (int j = 0; j < spielfeld[0].length; j++) {
                if (spielfeld[i][j] == 'C')
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

}
