package src;

import java.awt.*;

abstract class Szene extends Frame {


    protected Oberflaeche dieOberflaeche;

    public Szene(String derTitel, Steuerung dieSteuerung, Oberflaeche dieOberflaeche) {
        super(derTitel);
        this.dieOberflaeche = dieOberflaeche;

        setBackground(Color.black);
        setResizable(false);
        addKeyListener(dieSteuerung.gibTastaturManager());
    }

    public abstract void paint(Graphics g);
}
