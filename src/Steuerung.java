package src;

import java.awt.event.KeyListener;
import java.util.Date;
import java.util.Timer;

public class Steuerung {

    private final Oberflaeche dieOberflaeche;
    private final TastaturManger derTastaturmanager;
    private final VirtuellesSpielfeld dasVirtuelleSpielfeld;
    private final Timer timer;

    private int punkte = 0;
    private char pacManBewegungsRichtung = 'R';
    private int anzAktualisierungen = 0;

    public Steuerung() {
        new TimerAufruf(this);
        timer = new Timer();
        derTastaturmanager = new TastaturManger(this);
        dieOberflaeche = new Oberflaeche(derTastaturmanager, this);
        dasVirtuelleSpielfeld = new VirtuellesSpielfeld();
        dieOberflaeche.wechselSzene(0);
        dasVirtuelleSpielfeld.ladeLevel();
    }

    public void aktualisieren() {
        anzAktualisierungen++;
        bewegePacMan();

        dieOberflaeche.zeichneSpielfeld(dasVirtuelleSpielfeld.gibSpielfeld());
    }

    public void bewegePacMan() {
        int[] posPacManAlt = dasVirtuelleSpielfeld.findePacMan(), posPacManNeu;

        switch (pacManBewegungsRichtung) {
            case 'O':
                if (dasVirtuelleSpielfeld.istKeineWand(posPacManAlt[0], posPacManAlt[1] - 1))
                    posPacManNeu = new int[]{posPacManAlt[0], posPacManAlt[1] - 1};
                else
                    posPacManNeu = posPacManAlt;
                break;
            case 'R':
                if (dasVirtuelleSpielfeld.istKeineWand(posPacManAlt[0] + 1, posPacManAlt[1]))
                    posPacManNeu = new int[]{posPacManAlt[0] + 1, posPacManAlt[1]};
                else
                    posPacManNeu = posPacManAlt;
                break;
            case 'U':
                if (dasVirtuelleSpielfeld.istKeineWand(posPacManAlt[0], posPacManAlt[1] + 1))
                    posPacManNeu = new int[]{posPacManAlt[0], posPacManAlt[1] + 1};
                else
                    posPacManNeu = posPacManAlt;
                break;
            case 'L':
                if (dasVirtuelleSpielfeld.istKeineWand(posPacManAlt[0] - 1, posPacManAlt[1])) {
                    posPacManNeu = new int[]{posPacManAlt[0] - 1, posPacManAlt[1]};
                } else {
                    posPacManNeu = posPacManAlt;
                }
                break;
            default:
                throw new IllegalStateException("unmoegliche Richtung: " + pacManBewegungsRichtung);
        }

        System.out.println(posPacManNeu[1] + " | " + posPacManNeu[0]);
        if (dasVirtuelleSpielfeld.gibZelle(posPacManNeu[0], posPacManNeu[1]) == '*') {
            erhoehePunkte(1);
        }
        dasVirtuelleSpielfeld.veraendereZelle(posPacManAlt[0], posPacManAlt[1], '-');
        dasVirtuelleSpielfeld.veraendereZelle(posPacManNeu[0], posPacManNeu[1], 'C');
    }

    public void erhoehePunkte(int extraPunkte) {
        punkte += extraPunkte;
        dieOberflaeche.setzePunkteAnzeige(punkte);
    }

    public void starteSpiel() {
        int ZEITLUPE = 2000, LANGSAM = 600, SCHNELL = 300;            //Geschwindigkeiten zum testen

        Date datum = new Date();
        TimerAufruf aufgabe = new TimerAufruf(this);
        timer.scheduleAtFixedRate(aufgabe, datum, SCHNELL); //Intervall ist in ms (0.001 sekunde)

        dieOberflaeche.zeichneSpielfeld(dasVirtuelleSpielfeld.gibSpielfeld());
    }

    public void setzePacManBewegunsRichtung(char richtung) {
        pacManBewegungsRichtung = richtung;
    }

    public void beende() {
        dieOberflaeche.beende();
    }

    public KeyListener gibTastaturManager(){
        return derTastaturmanager;
    }

    public static void main(String[] args) {
        new Steuerung();
    }
}
