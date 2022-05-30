package src;


public class Oberflaeche {

    private final TastaturManger derTastaturmanager;
    private final Steuerung dieSteuerung;

    private MenuSzene menuFenster;
    private SpielSzene spielFenseter;
    private GameOverSzene gameOverFenster;

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
            case 2:
                gameOverFenster = new GameOverSzene("-GAME OVER-", dieSteuerung, this);
        }

    }

    public void setzePunkteAnzeige(int pPunkte) {
        spielFenseter.setztePunkteAnzeige(pPunkte);
    }

    public void beende() {
        menuFenster.dispose();
        if (spielFenseter != null) {
            spielFenseter.dispose();
        }
        System.exit(0);
    }
}
