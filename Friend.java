import java.util.Random;

// Observer. Each friend has a 50% chance of asking Eren to be safe,
// and if Eren's energy is below 20 they also encourage him to rage.
// Friend observes Eren's energy and responds with messages.
public class Friend implements ErenObserver {

    private String name;
    private UI ui;
    private Random rand = new Random();

    public Friend(String name, UI ui, Eren eren) {
        this.name = name;
        this.ui = ui;
        eren.addObserver(this);   // register as an observer of Eren
    }

    @Override
    public void update(int energy) {
        if (rand.nextBoolean()) {
            ui.showMessage(name + ": Please be safe, Eren!");
        }
        if (energy < 20) {
            ui.showMessage(name + ": Rage, rage against the dying of the light!");
        }
    }
}
