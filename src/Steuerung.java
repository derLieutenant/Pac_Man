package src;

import java.awt.event.KeyListener;
import java.util.Date;
import java.util.Timer;

public class Steuerung {

    Blinky blinky;
    Pinky pinky;
    PacMan pacman;

    private final Oberflaeche dieOberflaeche;
    private final TastaturManger derTastaturmanager;
    private final VirtuellesSpielfeld dasVirtuelleSpielfeld;
    private final Timer timer;

    private int punkte = 0;
    private int anzAktualisierungen = 0;

    private boolean jageModus = true;

    public Steuerung() {
        new TimerAufruf(this);
        timer = new Timer();
        derTastaturmanager = new TastaturManger(this);
        dieOberflaeche = new Oberflaeche(derTastaturmanager, this);
        dasVirtuelleSpielfeld = new VirtuellesSpielfeld();
        dasVirtuelleSpielfeld.ladeLevel();

        pacman = new PacMan(dasVirtuelleSpielfeld, this);
        blinky = new Blinky(dasVirtuelleSpielfeld);
        pinky = new Pinky(dasVirtuelleSpielfeld, pacman);

        dieOberflaeche.wechselSzene(0);
    }

    public void aktualisieren() {
        anzAktualisierungen++;

        if (anzAktualisierungen % ((int) Math.floor(9 - ((double) anzAktualisierungen) / 900)) == 0)
            bewegeGeister();
        testeSpielende();                                            //Wir haben uns für diese Updateorder entschieden
        if (anzAktualisierungen % 5 == 0)
            pacman.bewege();


        dieOberflaeche.zeichneSpielfeld(dasVirtuelleSpielfeld.gibSpielfeld());
    }

    private void bewegeGeister() {
        if (jageModus) {
            blinky.jagePacMan();
            pinky.jagePacMan();
        }
    }

    private void testeSpielende() {
        if (dasVirtuelleSpielfeld.finde('<')[0] == -1 && dasVirtuelleSpielfeld.finde('<')[1] == -1) {
            timer.cancel();
            dieOberflaeche.wechselSzene(2);
        }
    }

    public void erhoehePunkte(int extraPunkte) {
        punkte += extraPunkte;
        dieOberflaeche.setzePunkteAnzeige(punkte);
    }

    public void starteSpiel() {

        int ZEITLUPE = 500, LANGSAM = 150, SCHNELL = 70;
        /*
        Geschwindigkeiten zum testen (PacMan bewegt sich jede 5 tiks)

        Wegen einem zu ineffizienten paint kann nur jede 60 ms geupdated werden
        Eventuell abhängig von der PC Performance
        (Häufigeres Updaten würde mehr Geschwindigkeitsabstufungen ermöglichen)

        Mit paint() muss nämlich der ganze Bildschirm neu gezeichnet werden
         */

        Date datum = new Date();
        TimerAufruf aufgabe = new TimerAufruf(this);
        timer.scheduleAtFixedRate(aufgabe, datum, SCHNELL); //Intervall ist in ms (0.001 sekunde)

        dieOberflaeche.zeichneSpielfeld(dasVirtuelleSpielfeld.gibSpielfeld());
    }

    public void setzePacManBewegunsRichtung(char richtung) {
        pacman.setzeBewegungsrichtung(richtung);
    }

    public void beende() {
        dieOberflaeche.beende();
    }

    public KeyListener gibTastaturManager() {
        return derTastaturmanager;
    }

    public static void main(String[] args) {
        new Steuerung();
    }
}
