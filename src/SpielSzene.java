package src;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpielSzene extends Szene {

    private JLabel pacman;

    protected final int KAESTCHENGROESSE = 32;
    protected final int BREITE = 19 * KAESTCHENGROESSE, HOEHE = 23 * KAESTCHENGROESSE + 32;     //extra Platz für Score
    //Spielfeld = 19 * 23 (mit äußerem Rand) ;

    public SpielSzene(String derTitel, Steuerung dieSteuerung, Oberflaeche dieOberflaeche) {
        super(derTitel, dieSteuerung);
        this.dieOberflaeche = dieOberflaeche;

        setSize(BREITE, HOEHE);
        setLocation(200, -31);
        setLayout(null);

        pacman = new JLabel(new ImageIcon("bilder/pacman1.png"));
        pacman.setBounds(0,0,32, 32);
        add(pacman);
        pacman.setVisible(true);
        revalidate();
        this.setVisible(true);

        dieSteuerung.starteSpiel();
    }

    public void zeichneSpielfeldZelle(int i, int j, char symbol) {
        int x = j * KAESTCHENGROESSE;       //wegen unlogischem Feldsystem entspricht i = y und j = x
        int y = i * KAESTCHENGROESSE;

        switch (symbol) {           //TODO Spielfeld zeichnen
            //case '#' ->;
            //case '-' ->;
            //default -> ;
        }
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

}