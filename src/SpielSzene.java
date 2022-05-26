package src;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpielSzene extends Szene {

    private JLabel pacman;
    private TextField punkteAnzeige;

    protected final int KAESTCHENGROESSE = 32;
    protected final int BREITE = 19 * KAESTCHENGROESSE, HOEHE = 23 * KAESTCHENGROESSE + 32;     //extra Platz für Score
    //Spielfeld = 19 * 23 (mit äußerem Rand) ;

    char[][] aktuellesSpielfeld = {
            {'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},{'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},{'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},{'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},{'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},
            {'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},{'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},{'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},{'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},{'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},
            {'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},{'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},{'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},{'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},{'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},
            {'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},{'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},{'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},{'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},{'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},
            {'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},{'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},{'-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'}};

    public SpielSzene(String derTitel, Steuerung dieSteuerung, Oberflaeche dieOberflaeche) {
        super(derTitel, dieSteuerung);
        this.dieOberflaeche = dieOberflaeche;

        setSize(BREITE, HOEHE);
        setBackground(Color.black);
        setLocation(200, -32);
        setLayout(null);

        punkteAnzeige = new TextField("0");
        punkteAnzeige.setBounds(BREITE - 4*KAESTCHENGROESSE, HOEHE - KAESTCHENGROESSE, 3*KAESTCHENGROESSE, KAESTCHENGROESSE);
        punkteAnzeige.setBackground(Color.black);
        punkteAnzeige.setFocusable(false);
        punkteAnzeige.setForeground(Color.blue);
        add(punkteAnzeige);
        punkteAnzeige.setVisible(true);

        /*
        pacman = new JLabel(new ImageIcon("bilder/pacman1.png"));
        pacman.setBounds(0,0,32, 32);
        add(pacman);
        pacman.setVisible(true);
                 */

        setVisible(true);
    }

    public void zeichneSpielfeld(char [][] spielfeld) {
        aktuellesSpielfeld = spielfeld;
        System.out.println("update");

        repaint();
    }

    public void zeichnePacMan(int x, int y) {
        pacman.setLocation(KAESTCHENGROESSE*x, KAESTCHENGROESSE*y);
        ladeBild();
    }

    public void ladeBild() {
        BufferedImage bild = null;
        try {
            bild = ImageIO.read(new File("bilder/pacman1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pacman.setIcon(new ImageIcon(bild));
        revalidate();
    }

    @Override
    public void paint(Graphics g) {
        System.out.println("paint");

        for (int i = 0; i < aktuellesSpielfeld.length; i++) {
            for (int j = 0; j < aktuellesSpielfeld[0].length; j++) {
                if (aktuellesSpielfeld[i][j] == '#') {
                    g.setColor(Color.blue);
                    g.fillRect(j * KAESTCHENGROESSE, (i+1) * KAESTCHENGROESSE, KAESTCHENGROESSE, KAESTCHENGROESSE);
                }
                else if (aktuellesSpielfeld[i][j] == '*') {
                    g.setColor(Color.white);
                    g.fillOval(j * KAESTCHENGROESSE + KAESTCHENGROESSE * 3/8, (i+1) * KAESTCHENGROESSE + KAESTCHENGROESSE * 3/8,
                            KAESTCHENGROESSE/4, KAESTCHENGROESSE/4);
                }
                else if (aktuellesSpielfeld[i][j] == 'C') {
                    g.setColor(Color.yellow);
                    g.fillOval(j * KAESTCHENGROESSE + 1, (i+1) * KAESTCHENGROESSE + 1, KAESTCHENGROESSE -2, KAESTCHENGROESSE - 2);

                    /*
                    pacman.setLocation(KAESTCHENGROESSE*j, KAESTCHENGROESSE*i);
                    BufferedImage bild = null;
                    try {
                        bild = ImageIO.read(new File("bilder/pacman1.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //pacman.setIcon(new ImageIcon(bild));
                    pacman.revalidate();
                    */
                }
            }
        }
    }

    public void setztePunkteAnzeige(int pPunkte) {
        punkteAnzeige.setText(pPunkte + "");
    }
}