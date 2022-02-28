package src;

import jdk.swing.interop.SwingInterOpUtils;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.awt.event.KeyListener;
import java.util.Date;
import java.util.Timer;

public class Steuerung {

    private Oberflaeche dieOberflaeche;
    private TastaturManger derTastaturmanager;
    private Timer timer;
    private TimerAufruf aufgabe;
    private char[][] spielfeld = new char[28][31];
    private char pacManBewegungsRichtung = 'R';
    private int anzAktualisierungen = 0;

    public Steuerung() {
        new TimerAufruf(this);
        timer = new Timer();
        derTastaturmanager = new TastaturManger(this);
        dieOberflaeche = new Oberflaeche(derTastaturmanager, this);

        for (int i = 0; i < spielfeld.length; i++) {
            for (int j = 0; j < spielfeld[0].length; j++) {
                spielfeld[i][j] = '_';
            }
        }
        spielfeld[10][20] = 'C';
    }

    public void bewegePacMan(char richtung) {
        pacManBewegungsRichtung = richtung;
    }

    public void zeichneSpielfeld() {    //TODO Spielfeld zeichnen

    }

    public void starteSpiel() {

        Date datum = new Date();
        aufgabe = new TimerAufruf(this);
        timer.scheduleAtFixedRate(aufgabe, datum, 5000); //Intervall ist in ms (0.001 sekunde)
    }

    public static void main(String[] args) {
        new Steuerung();
    }

    public void aktualisieren() {
        anzAktualisierungen++;
        System.out.println("-----\ntick" + anzAktualisierungen);


        int[] posPacManAlt = findePacMan(), posPacManNeu;
        System.out.println(pacManBewegungsRichtung);
        switch (pacManBewegungsRichtung) {
            case 'O':
                posPacManNeu = new int[] {posPacManAlt[0],posPacManAlt[1]-1};
                break;
            case 'R':
                posPacManNeu = new int[] {posPacManAlt[0]+1,posPacManAlt[1]};
                break;
            case 'U':
                posPacManNeu = new int[] {posPacManAlt[0],posPacManAlt[1]+1};
                break;
            case 'L':
                posPacManNeu = new int[] {posPacManAlt[0]-1,posPacManAlt[1]};
                break;
            default:
                throw new IllegalStateException("unmoeliche Richtung: " + pacManBewegungsRichtung);
        }
        System.out.println(posPacManNeu[0]+" | "+posPacManNeu[1]);
        spielfeld[posPacManAlt[0]][posPacManAlt[1]] = '_';
        spielfeld[posPacManNeu[0]][posPacManNeu[1]] = 'C';
        dieOberflaeche.zeichnePacMan(posPacManNeu[0], posPacManNeu[1]);
    }

    private int[] findePacMan() {
        for (int i = 0; i < spielfeld.length; i++) {
            for (int j = 0; j < spielfeld[0].length; j++) {
                if (spielfeld[i][j] == 'C')
                    return new int[]{i, j};
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
