package src;

import java.awt.*;

public class GameOverSzene extends Szene{

    protected final int BREITE = 400, HOEHE = 200, X = 304, Y = 252;
    protected Button[] dieButtons = new Button[3];
    protected TextField textFeld;

    public GameOverSzene(String derTitel, Steuerung dieSteuerung, Oberflaeche dieOberflaeche) {
        super(derTitel, dieSteuerung, dieOberflaeche);
        setBackground(Color.red);
        this.dieOberflaeche = dieOberflaeche;

        setBounds(X, Y, BREITE, HOEHE);
        setLayout(null);
        initialisiereComponenten();

        setVisible(true);
    }

    private void initialisiereComponenten() {
        textFeld = new TextField("Die geister haben dich gefangen :(");
        textFeld.setBounds(50, 80, 300, 70);
        textFeld.setBackground(Color.black);
        textFeld.setForeground(Color.red);
        textFeld.setFont(new Font("Arial", Font.BOLD, 16));
        textFeld.setEditable(false);
        add(textFeld);
        textFeld.setVisible(true);

    }

    public void paint(Graphics g) {

    }
}
