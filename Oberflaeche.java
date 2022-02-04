import java.awt.*;


public class Oberflaeche {

    private final TastaturManger derTastaturmanager;
    private final Steuerung dieSteuerung;

    private Szene aktuelleSzene;
    Szene menuFenster, spielFenseter;

    public Oberflaeche(TastaturManger derTastaturmanager, Steuerung dieSteuerung) {
        this.dieSteuerung = dieSteuerung;
        this.derTastaturmanager = derTastaturmanager;
        wechselSzene(0);



    }

    public void wechselSzene(int neueSzene) {
        switch (neueSzene) {
            case 0:
                menuFenster = new MenuSzene("-MENU-", derTastaturmanager, this);
                break;
            case 1:
                spielFenseter = new SpielSzene("-PACMAN-", derTastaturmanager, this);
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
