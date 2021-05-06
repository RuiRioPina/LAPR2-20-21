
package app.ui.console;

import app.domain.store.ClientList;
import app.ui.console.utils.Utils;
import java.util.ArrayList;
import java.util.List;

public class ReceptionistUI implements Runnable {
    public ReceptionistUI() {
    }

    public void run() {
        ClientList clientList = new ClientList();
        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Option A - Register New Client", new RegisterClientUI()));
        boolean var3 = false;

        int option;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nReceptionist Menu:");
            if (option >= 0 && option < options.size()) {
                ((MenuItem)options.get(option)).run();
            }
        } while(option != -1);

    }
}
