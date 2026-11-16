import java.util.Random;

// Observer. Each titan has a 50% chance of attacking Eren,
// and if Eren's energy is above 40 they complain about the problems.
public class Titan implements ErenObserver {

    private String name;
    private UI ui;
    private Random rand = new Random();

    public Titan(String name, UI ui, Eren eren) {
        this.name = name;
        this.ui = ui;
        eren.addObserver(this);   // register as an observer of Eren
    }

    @Override
    public void update(int energy) {
        if (rand.nextBoolean()) {
            ui.showMessage(name + " attacks Eren!");
        }
        if (energy > 40) {
            ui.showMessage(name + ": Now we got problems, and I don't think we can solve 'em");
        }
    }
}
