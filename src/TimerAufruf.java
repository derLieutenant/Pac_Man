package src;

import java.util.TimerTask;

public class TimerAufruf extends TimerTask {

    private Steuerung dieSteuerung;

    public TimerAufruf(Steuerung steuerung) {
        dieSteuerung = steuerung;
    }

    @Override
    public void run() {
        dieSteuerung.aktualisieren();
    }
}
