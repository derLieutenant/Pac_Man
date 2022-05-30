package src;

import java.awt.*;

public class SpielSzene extends Szene {

    private final TextField punkteAnzeige;

    protected final int KAESTCHENGROESSE = 32, X = 200, Y = -32;
    protected final int BREITE = 19 * KAESTCHENGROESSE, HOEHE = 23 * KAESTCHENGROESSE + 32;     //extra Platz für Score
                                                                             //Spielfeld = 19 * 23 (mit äußerem Rand) ;


    char[][] aktuellesSpielfeld;

    public SpielSzene(String derTitel, Steuerung dieSteuerung, Oberflaeche dieOberflaeche) {
        super(derTitel, dieSteuerung, dieOberflaeche);

        setBounds(X, Y, BREITE, HOEHE);
        setBackground(Color.black);
        setLayout(null);

        punkteAnzeige = new TextField("0");
        punkteAnzeige.setBounds(BREITE - 4 * KAESTCHENGROESSE, HOEHE - KAESTCHENGROESSE,
                3 * KAESTCHENGROESSE, KAESTCHENGROESSE);
        punkteAnzeige.setBackground(Color.black);
        punkteAnzeige.setFocusable(false);
        punkteAnzeige.setForeground(Color.blue);
        punkteAnzeige.setFont(new Font("Arial", Font.BOLD, 16));
        add(punkteAnzeige);
        punkteAnzeige.setVisible(true);
        setVisible(true);
    }

    public void zeichneSpielfeld(char[][] spielfeld) {
        aktuellesSpielfeld = spielfeld;
        repaint();
    }

    public void paint(Graphics g) {                    //Man muss das ganze spielfeld zeichnen, da es paint ist und sonst nicht geht
        for (int i = 0; i < aktuellesSpielfeld.length; i++) {
            for (int j = 0; j < aktuellesSpielfeld[0].length; j++) {
                switch (aktuellesSpielfeld[i][j]) {
                    case '#' -> {
                        g.setColor(Color.blue);
                        g.fillRect(j * KAESTCHENGROESSE, (i + 1) * KAESTCHENGROESSE, KAESTCHENGROESSE, KAESTCHENGROESSE);
                    }
                    case '*' -> {
                        g.setColor(Color.white);
                        g.fillOval(j * KAESTCHENGROESSE + KAESTCHENGROESSE * 3 / 8, (i + 1) * KAESTCHENGROESSE + KAESTCHENGROESSE * 3 / 8,
                                KAESTCHENGROESSE / 4, KAESTCHENGROESSE / 4);
                    }
                    case '<' -> {
                        g.setColor(Color.yellow);
                        g.fillOval(j * KAESTCHENGROESSE + 1, (i + 1) * KAESTCHENGROESSE + 1,
                                KAESTCHENGROESSE - 2, KAESTCHENGROESSE - 2);
                    }
                    case 'P' -> {
                        g.setColor(Color.pink);
                        g.fillOval(j * KAESTCHENGROESSE + 1, (i + 1) * KAESTCHENGROESSE + 1,
                                KAESTCHENGROESSE - 2, KAESTCHENGROESSE - 2);
                        g.fillRect(j * KAESTCHENGROESSE + 1, (i + 1) * KAESTCHENGROESSE + KAESTCHENGROESSE / 2,
                                KAESTCHENGROESSE - 2, KAESTCHENGROESSE / 2 - 2);
                    }
                    case 'B' -> {
                        g.setColor(Color.red);
                        g.fillOval(j * KAESTCHENGROESSE + 1, (i + 1) * KAESTCHENGROESSE + 1,
                                KAESTCHENGROESSE - 2, KAESTCHENGROESSE - 2);
                        g.fillRect(j * KAESTCHENGROESSE + 1, (i + 1) * KAESTCHENGROESSE + KAESTCHENGROESSE / 2,
                                KAESTCHENGROESSE - 2, KAESTCHENGROESSE / 2 - 2);
                    }
                }
            }
        }
    }

    public void setztePunkteAnzeige(int pPunkte) {
        punkteAnzeige.setText(pPunkte + "");
    }
}