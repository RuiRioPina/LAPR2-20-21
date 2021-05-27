package app.ui.console;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class LaboratoryCoordinatorUI implements Runnable {
    public LaboratoryCoordinatorUI() {
        // Do nothing because there is no need to construct the UI layer with anything. This is only used to be able to use the UI when selecting in menus.
    }

    public void run() {
        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Option 1 - Validate Work.", new ValidateWorkUI()));

        int option;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nLaboratory Coordinator Menu:");
            if (option >= 0 && option < options.size()) {
                ((MenuItem) options.get(option)).run();
            }
        } while (option != -1);

    }
}
