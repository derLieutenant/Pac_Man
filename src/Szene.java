package src;


import java.awt.*;

abstract class Szene extends Frame {


    protected Oberflaeche dieOberflaeche;

    public Szene(String derTitel, Steuerung dieSteuerung) {
        super(derTitel);
        setBackground(Color.BLACK);
        setResizable(false);
        addKeyListener(dieSteuerung.gibTastaturManager());


    }

}
