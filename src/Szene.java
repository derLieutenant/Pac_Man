package src;


import javax.swing.*;
import java.awt.*;

abstract class Szene extends Frame {


    protected Oberflaeche dieOberflaeche;

    public Szene(String derTitel, Steuerung dieSteuerung) {
        super(derTitel);

        setBackground(Color.black);
        setResizable(false);
        addKeyListener(dieSteuerung.gibTastaturManager());
    }

    public abstract void paint(Graphics g);
}
