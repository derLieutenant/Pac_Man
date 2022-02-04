package src;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TastaturManger implements KeyListener {

    private  Steuerung dieSteuerung;

    public TastaturManger(Steuerung dieSteuerung) {
        this.dieSteuerung = dieSteuerung;
    }


    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE -> dieSteuerung.beende();       //das ist einfach nur eine andere Schreibweise für switch caseses (Lambda Schreibweise)
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {} //das funktioniert nicht so gut
}
