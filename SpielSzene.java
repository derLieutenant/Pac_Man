
public class SpielSzene extends Szene{

    protected final int KAESTCHENGROESSE = 32;
    protected final int BREITE = 28 * KAESTCHENGROESSE, HOEHE = 31 * KAESTCHENGROESSE + 120;
                            //Spielfeld = 17*19 (mit Rand 28*31) ; 100px oben und 20 unten frei für Score etc.

    public SpielSzene(String derTitel, TastaturManger derTastaturManager, Oberflaeche dieOberflaeche) {
        super(derTitel, derTastaturManager);
        this.dieOberflaeche = dieOberflaeche;

        setSize(BREITE, HOEHE);
        setLocationRelativeTo(null);
        setLayout(null);


        this.setVisible(true);
    }



}
