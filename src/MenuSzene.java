package src;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuSzene extends Szene implements ActionListener {

    protected final int BREITE = 500, HOEHE = 400, X = 200, Y = 200;
    protected Button[] dieButtons = new Button[3];

    public MenuSzene(String derTitel, Steuerung dieSteuerung, Oberflaeche dieOberflaeche) {
        super(derTitel, dieSteuerung, dieOberflaeche);

        setBounds(X, Y, BREITE, HOEHE);
        setLayout(null);
        initialisiereButtons();

        setVisible(true);
    }

    private void initialisiereButtons(){
        dieButtons[0] = new Button("Weiter");
        dieButtons[0].setBounds(50, 55, 400, 90);
        dieButtons[0].setBackground(Color.blue);
        dieButtons[0].addActionListener(this);
        add(dieButtons[0]);
        dieButtons[0].setVisible(true);
        dieButtons[1] = new Button("Beende");
        dieButtons[1].setBounds(50, 155, 400, 90);
        dieButtons[1].setBackground(Color.blue);
        dieButtons[1].addActionListener(this);
        add(dieButtons[1]);
        dieButtons[1].setVisible(true);

        /*
        dieButtons[2] = new Button();
        dieButtons[2].setBounds(50, 255, 400, 90);
        dieButtons[2].setBackground(Color.CYAN);
        dieButtons[2].addActionListener(this);
        add(dieButtons[2]);
        dieButtons[2].setVisible(true);

         */
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(dieButtons[0])) {
            dieOberflaeche.wechselSzene(1);
        }
        if(e.getSource().equals(dieButtons[1])) {
            dieOberflaeche.beende();
        }
    }

    @Override
    public void paint(Graphics g) {

    }
}
