package src;

public class Pinky extends Geist {

    PacMan pacman;

    public Pinky(VirtuellesSpielfeld dasVirtuellleSpielfeld, PacMan pacman) {
        super(dasVirtuellleSpielfeld);
        pos = dasVirtuelleSpielfeld.finde('P');
        kuerzel = 'P';
        this.pacman = pacman;
    }


    protected char findeNeueBewegungsrichtung() {
        int[] abstandZielPinky = new int[2];
        boolean[] moeglicheRichtungen = new boolean[]{true, true, true, true};
        char[] richtungsReihenfolge;
        int[] posPacMan = dasVirtuelleSpielfeld.finde('<');
        int[] zielPos = switch (pacman.gibBewegungsrichtung()) {
            case 'O' -> new int[]{posPacMan[0], posPacMan[1] - 2};
            case 'R' -> new int[]{posPacMan[0] + 2, posPacMan[1]};
            case 'U' -> new int[]{posPacMan[0], posPacMan[1] + 2};
            case 'L' -> new int[]{posPacMan[0] - 2, posPacMan[1]};
            default -> throw new IllegalStateException("Unexpected value: " + pacman.gibBewegungsrichtung());
        };

        abstandZielPinky[0] = zielPos[0] - this.pos[0];
        abstandZielPinky[1] = zielPos[1] - this.pos[1];

        moeglicheRichtungen[0] = !dasVirtuelleSpielfeld.istWand(this.pos[0], this.pos[1] - 1);
        moeglicheRichtungen[1] = !dasVirtuelleSpielfeld.istWand(this.pos[0] + 1, this.pos[1]);
        moeglicheRichtungen[2] = !dasVirtuelleSpielfeld.istWand(this.pos[0], this.pos[1] + 1);
        moeglicheRichtungen[3] = !dasVirtuelleSpielfeld.istWand(this.pos[0] - 1, this.pos[1]);

        switch (bewegungsrichtung) {
            case 'O' -> moeglicheRichtungen[2] = false;
            case 'R' -> moeglicheRichtungen[3] = false;
            case 'U' -> moeglicheRichtungen[0] = false;
            case 'L' -> moeglicheRichtungen[1] = false;
        }

        if (Math.abs(abstandZielPinky[0]) > Math.abs(abstandZielPinky[1])) {
            if (abstandZielPinky[0] > 0) {                                                  //Rechts
                if (abstandZielPinky[1] < 0) {
                    richtungsReihenfolge = new char[]{'R', 'O', 'U', 'L'};
                } else {
                    richtungsReihenfolge = new char[]{'R', 'U', 'O', 'L'};
                }
            } else {
                if (abstandZielPinky[1] < 0)
                    richtungsReihenfolge = new char[]{'L', 'O', 'U', 'R'};
                else {
                    richtungsReihenfolge = new char[]{'L', 'U', 'O', 'R'};
                }
            }
        } else {
            if (abstandZielPinky[1] < 0) {                                                    //Oben
                if (abstandZielPinky[0] > 0) {
                    richtungsReihenfolge = new char[]{'O', 'R', 'L', 'U'};
                } else {
                    richtungsReihenfolge = new char[]{'O', 'L', 'R', 'U'};
                }
            } else {                                                                                //unten
                if (abstandZielPinky[0] > 0)
                    richtungsReihenfolge = new char[]{'U', 'R', 'L', 'O'};
                else {
                    richtungsReihenfolge = new char[]{'U', 'L', 'R', 'O'};
                }
            }
        }

        for (int i = 0; i < richtungsReihenfolge.length; i++) {
            switch (richtungsReihenfolge[i]) {
                case 'O' -> {
                    if (moeglicheRichtungen[0]) return richtungsReihenfolge[i];
                }
                case 'R' -> {
                    if (moeglicheRichtungen[1]) return richtungsReihenfolge[i];
                }
                case 'U' -> {
                    if (moeglicheRichtungen[2]) return richtungsReihenfolge[i];
                }
                case 'L' -> {
                    if (moeglicheRichtungen[3]) return richtungsReihenfolge[i];
                }
            }
        }
        return 'O';         //für den Compiler
    }
}