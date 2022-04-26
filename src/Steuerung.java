package src;

import yanwittmann.file.*;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Timer;

public class Steuerung {

    private Oberflaeche dieOberflaeche;
    private TastaturManger derTastaturmanager;
    private Timer timer;
    private TimerAufruf aufgabe;
    private char[][] spielfeld = new char[23][19];          //# = Wand; - = Blank; C = PacMan
    private char pacManBewegungsRichtung = 'R';
    private int anzAktualisierungen = 0;

    public Steuerung() {
        new TimerAufruf(this);
        timer = new Timer();
        derTastaturmanager = new TastaturManger(this);
        dieOberflaeche = new Oberflaeche(derTastaturmanager, this);
        ladeLevel();
    }

    public void bewegePacMan(char richtung) {
        pacManBewegungsRichtung = richtung;
    }

    public void zeichneSpielfeld() {
        for (int i = 0; i < spielfeld.length; i++) {
            for (int j = 0; j < spielfeld[0].length; j++) {
                dieOberflaeche.zeichneSpielfeldZelle(i, j, spielfeld[i][j]);
            }
        }
    }

    public void starteSpiel() {
        int ZEITLUPE = 2000, LANGSAM = 600, SCHNELL = 250;            //Geschwindigkeiten zum testen

        Date datum = new Date();
        aufgabe = new TimerAufruf(this);
        timer.scheduleAtFixedRate(aufgabe, datum, LANGSAM); //Intervall ist in ms (0.001 sekunde)

        //zeichneSpielfeld();
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
        }catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Eingelesene Datei passt nicht!");
        }
    }

    public static void main(String[] args) {
        new Steuerung();
    }

    public void aktualisieren() {
        anzAktualisierungen++;
        //System.out.println("-----\ntick" + anzAktualisierungen);
        int[] posPacManAlt = findePacMan(), posPacManNeu;

        posPacManNeu = switch (pacManBewegungsRichtung) {
            case 'O' -> new int[]{posPacManAlt[0], posPacManAlt[1] - 1};        //hier ist posPacManAlt[0] = x
            case 'R' -> new int[]{posPacManAlt[0] + 1, posPacManAlt[1]};        // und posPacManAlt[1] = y
            case 'U' -> new int[]{posPacManAlt[0], posPacManAlt[1] + 1};
            case 'L' -> new int[]{posPacManAlt[0] - 1, posPacManAlt[1]};
            default -> throw new IllegalStateException("unmoeliche Richtung: " + pacManBewegungsRichtung);
        };
        System.out.println(posPacManNeu[1]+" | "+posPacManNeu[0]);
        spielfeld[posPacManAlt[1]][posPacManAlt[0]] = '-';                  //wieder verwirrendes "Feldverhalten"
        spielfeld[posPacManNeu[1]][posPacManNeu[0]] = 'C';
        dieOberflaeche.zeichnePacMan(posPacManNeu[0], posPacManNeu[1]);
    }

    private int[] findePacMan() {
        for (int i = 0; i < spielfeld.length; i++) {
            for (int j = 0; j < spielfeld[0].length; j++) {
                if (spielfeld[i][j] == 'C')
                    return new int[]{j, i};
            }
        }
        return new int[]{-1,-1};
    }

    public void beende() {
        dieOberflaeche.beende();
    }

    public KeyListener gibTastaturManager(){
        return derTastaturmanager;
    }

}
