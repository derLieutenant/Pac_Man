package src;

public class PacMan extends Entitaet {

    Steuerung dieSteuerung;
    public PacMan(VirtuellesSpielfeld dasVirtuelleSpielfeld, Steuerung dieSteuerung) {
        super(dasVirtuelleSpielfeld);
        this.dieSteuerung = dieSteuerung;
        pos = dasVirtuelleSpielfeld.finde('<');
    }

    public void bewege() {

        dasVirtuelleSpielfeld.veraendereZelle(pos[0], pos[1], '-');

        switch (bewegungsrichtung) {
            case 'O':
                if (! dasVirtuelleSpielfeld.istWand(pos[0], pos[1] - 1))
                    pos[1]--;
                break;
            case 'R':
                if (!dasVirtuelleSpielfeld.istWand(pos[0] + 1, pos[1]))
                    pos[0]++;
                break;
            case 'U':
                if (!dasVirtuelleSpielfeld.istWand(pos[0], pos[1] + 1))
                    pos[1]++;
                break;
            case 'L':
                if (!dasVirtuelleSpielfeld.istWand(pos[0] - 1, pos[1]))
                    pos[0]--;
                break;
            default:
                throw new IllegalStateException("unmoegliche Richtung: " + bewegungsrichtung);
        }

        if (dasVirtuelleSpielfeld.gibZelle(pos[0], pos[1]) == '*')
            dieSteuerung.erhoehePunkte(1);
        dasVirtuelleSpielfeld.veraendereZelle(pos[0], pos[1], '<');
    }

    public void setzeBewegungsrichtung(char richtung) {
        bewegungsrichtung = richtung;
    }

}
