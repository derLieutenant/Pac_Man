package src;

import java.awt.event.KeyListener;

public class Steuerung {

    private Oberflaeche dieOberflaeche;
    private TastaturManger derTastaturmanager;

    private int anzAktualisierungen = 0;

    public Steuerung() {
        new TimerAufruf(this);
        derTastaturmanager = new TastaturManger(this);
        dieOberflaeche = new Oberflaeche(derTastaturmanager, this);
    }

    public static void main(String[] args) {
        new Steuerung();
    }

    public void aktualisieren() {
        anzAktualisierungen++;
    }

    public void beende() {

    }

}
