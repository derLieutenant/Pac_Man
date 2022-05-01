package src;

import java.awt.event.KeyListener;
import java.util.Date;
import java.util.Timer;

public class Steuerung {

    private final Oberflaeche dieOberflaeche;
    private final TastaturManger derTastaturmanager;
    private final VirtuellesSpielfeld dasVirtuelleSpielfeld;
    private final Timer timer;


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
        //System.out.println("-----\ntick" + anzAktualisierungen);
        dasVirtuelleSpielfeld.bewegePacMan(pacManBewegungsRichtung);

        dieOberflaeche.zeichneSpielfeld(dasVirtuelleSpielfeld.gibSpielfeld());
    }

    public void starteSpiel() {
        int ZEITLUPE = 2000, LANGSAM = 600, SCHNELL = 250;            //Geschwindigkeiten zum testen

        Date datum = new Date();
        TimerAufruf aufgabe = new TimerAufruf(this);
        timer.scheduleAtFixedRate(aufgabe, datum, LANGSAM); //Intervall ist in ms (0.001 sekunde)

        dieOberflaeche.zeichneSpielfeld(dasVirtuelleSpielfeld.gibSpielfeld());
    }


    public static void main(String[] args) {
        new Steuerung();
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

}
