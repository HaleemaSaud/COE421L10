import java.util.Random;

// Observer. Levi loses 5 patience every time Eren transforms.
public class TeamLeader implements ErenObserver {

    private String name;
    private int patience;
    private UI ui;

    public TeamLeader(String name, int patience, UI ui, Eren eren) {
        this.name = name;
        this.patience = patience;
        this.ui = ui;
        eren.addObserver(this);   // register as an observer of Eren
    }

    @Override
    public void update(int energy) {
        if (patience <= 0) {
            return;   // Levi has said his final words; he prints nothing further
        }
        patience -= 5;
        if (patience == 10) {
            ui.showMessage(name + ": My patience is waning!");
        } else if (patience == 5) {
            ui.showMessage(name + ": Is this entertaining?");
        } else if (patience == 0) {
            ui.showMessage(name + ": Omae wa mou shindeiru");
        } else {
            ui.showMessage(name + ": Eren transformed again... (patience = " + patience + ")");
        }
    }
}
