package src;

public abstract class Entitaet {

    protected VirtuellesSpielfeld dasVirtuelleSpielfeld;

    protected char kuerzel;

    protected int[] pos;
    protected char bewegungsrichtung = 'R';

    public Entitaet( VirtuellesSpielfeld dasVirtuelleSpielfeld) {
        this.dasVirtuelleSpielfeld = dasVirtuelleSpielfeld;

    }

    public char gibBewegungsrichtung() {
        return bewegungsrichtung;
    }
}
