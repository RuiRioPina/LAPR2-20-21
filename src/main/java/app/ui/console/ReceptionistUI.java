
package app.ui.console;

import app.domain.store.ClientList;
import app.ui.console.utils.Utils;
import java.util.ArrayList;
import java.util.List;

public class ReceptionistUI implements Runnable {
    public ReceptionistUI() {
        // Do nothing because there is no need to construct the UI layer with any value. This is only used to be able to use the UI when selecting in menus.
    }

    public void run() {
        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Option 1 - Register New Client", new RegisterClientUI()));
        options.add(new MenuItem("Option 2 - Register a test to be performed to a registered client", new TestUI()));

        int option;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nReceptionist Menu:");
            if (option >= 0 && option < options.size()) {
                ((MenuItem)options.get(option)).run();
            }
        } while(option != -1);

    }
}
