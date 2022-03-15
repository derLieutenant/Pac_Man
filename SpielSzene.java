package src;

public class SpielSzene extends Szene {

  protected final int KAESTCHENGROESSE = 32;
  protected final int BREITE = 28 * KAESTCHENGROESSE, HOEHE = 31 * KAESTCHENGROESSE + 120;
                            //Spielfeld = 26*29 (mit Rand 28*31) ; 100px oben und 20 unten frei für Score etc.

  public SpielSzene(String derTitel, Steuerung dieSteuerung, Oberflaeche dieOberflaeche) {
    super(derTitel, dieSteuerung);
    this.dieOberflaeche = dieOberflaeche;
    
    setSize(BREITE, HOEHE);
    setLocationRelativeTo(null);
    setLayout(null);
    
    dieSteuerung.starteSpiel();
    
    this.setVisible(true);
  }

  public void zeichnePacMan(int x, int y) {
    
  }
  public ladeBild(int x, int y){
   BufferedImage img = null;
  try 
  {
    img = ImageIO.read(new File(bilder/pacman1.png) )
    } 
catch (IOException e) 
      {
    e.printStackTrace();
    } 
  }  

}
