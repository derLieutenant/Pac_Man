package src;

public abstract class Geist extends Entitaet{



    public Geist(VirtuellesSpielfeld dasVirtuelleSpielfeld) {
        super(dasVirtuelleSpielfeld);
    }

    protected void jagePacMan() {

        dasVirtuelleSpielfeld.veraendereZelle(pos[0], pos[1], '-');
        char neueBewegungsrichtung = findeNeueBewegungsrichtung();
        switch (neueBewegungsrichtung) {
            case 'O' -> pos[1]--;
            case 'R' -> pos[0]++;
            case 'U' -> pos[1]++;
            case 'L' -> pos[0]--;
        }
        bewegungsrichtung = neueBewegungsrichtung;
        dasVirtuelleSpielfeld.veraendereZelle(pos[0], pos[1], kuerzel);
    }

    protected abstract char findeNeueBewegungsrichtung();
}
