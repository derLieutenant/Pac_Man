package src;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TastaturManger implements KeyListener {

    private final Steuerung dieSteuerung;

    public TastaturManger(Steuerung dieSteuerung) {
        this.dieSteuerung = dieSteuerung;
    }


    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE -> dieSteuerung.beende();
            case KeyEvent.VK_W -> dieSteuerung.setzePacManBewegunsRichtung('O');
            case KeyEvent.VK_D -> dieSteuerung.setzePacManBewegunsRichtung('R');
            case KeyEvent.VK_S -> dieSteuerung.setzePacManBewegunsRichtung('U');
            case KeyEvent.VK_A -> dieSteuerung.setzePacManBewegunsRichtung('L');
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {} //das funktioniert nicht so gut
}
