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
        wechselSzene(0);



    }

    public void zeichneSpielfeldZelle(int i, int j, char symbol) {
        spielFenseter.zeichneSpielfeldZelle(i, j, symbol);
    }

    public void zeichnePacMan(int x, int y) {
        if (spielFenseter != null) {
            spielFenseter.zeichnePacMan(x, y);
        }
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
