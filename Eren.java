import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Subject of the Observer pattern. Eren runs on his own thread:
// every 5-10 seconds he transforms into a titan for 10 seconds,
// notifies all observers with his current energy, and loses 5 energy.
public class Eren implements Runnable {

    private int energy;
    private UI ui;
    private List<ErenObserver> observers = new ArrayList<ErenObserver>();
    private boolean transformed = false;
    private Thread thread;

    public Eren(int energy, UI ui) {
        this.energy = energy;
        this.ui = ui;
        thread = new Thread(this);
        thread.start();
    }

    public void addObserver(ErenObserver o) {
        observers.add(o);
    }

    public int getEnergy() {
        return energy;
    }

    public boolean isTransformed() {
        return transformed;
    }

    @Override
    public void run() {
        Random rand = new Random();
        ui.showMessage("Eren: I start with " + energy + " energy. Everyone is watching me...");
        while (energy > 0) {
            try {
                // wait a random number of seconds between 5 and 10
                int waitSeconds = 5 + rand.nextInt(6);
                ui.showMessage("Eren is calm for " + waitSeconds + " seconds...");
                Thread.sleep(waitSeconds * 1000L);

                // transform into a titan
                transformed = true;
                energy -= 5;
                ui.showMessage("*** EREN HAS TRANSFORMED INTO A TITAN! (energy = " + energy + ") ***");

                // notify all observers together with his current energy
                notifyObservers();

                // stay a titan for 10 seconds
                Thread.sleep(10000);
                transformed = false;
                ui.showMessage("Eren has reverted to human form. (energy = " + energy + ")");
            } catch (InterruptedException e) {
                ui.showMessage("Eren's thread was interrupted.");
            }
        }
        ui.showMessage("Eren has no energy left. Goodbye...");
    }

    private void notifyObservers() {
        for (ErenObserver o : observers) {
            o.update(energy);
        }
    }
}
