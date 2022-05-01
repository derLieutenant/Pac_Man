package src;


public class Oberflaeche {

    private final TastaturManger derTastaturmanager;
    private final Steuerung dieSteuerung;

    private Szene aktuelleSzene;
    MenuSzene menuFenster;
    SpielSzene spielFenseter;

    public Oberflaeche(TastaturManger derTastaturmanager, Steuerung dieSteuerung) {
        this.dieSteuerung = dieSteuerung;
        this.derTastaturmanager = derTastaturmanager;
    }

    public void zeichneSpielfeld(char[][] spielfeld) {
        spielFenseter.zeichneSpielfeld(spielfeld);
    }

    public void wechselSzene(int neueSzene) {
        switch (neueSzene) {
            case 0:
                menuFenster = new MenuSzene("-MENU-",dieSteuerung, this);
                break;
            case 1:
                spielFenseter = new SpielSzene("-PACMAN-", dieSteuerung, this);
                dieSteuerung.starteSpiel();
                break;
        }

    }

    public void beende() {
        menuFenster.dispose();
        if (spielFenseter != null) {
            spielFenseter.dispose();
        }
        System.exit(0);
    }
}
