import java.awt.*;

abstract class Szene extends Frame {


    protected Oberflaeche dieOberflaeche;

    public Szene(String derTitel, TastaturManger derTastaturManager) {
        super(derTitel);
        setBackground(Color.BLACK);
        setResizable(false);
        addKeyListener(derTastaturManager);


    }

}
