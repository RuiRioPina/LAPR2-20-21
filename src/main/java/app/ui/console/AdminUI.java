package app.ui.console;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */

public class AdminUI implements Runnable{
    public AdminUI()
    {
    }

    public void run()
    {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Option A ", new ShowTextUI("You have chosen Option A.")));
        options.add(new MenuItem("Option B ", new ShowTextUI("You have chosen Option B.")));
        options.add(new MenuItem("Option C ", new ShowTextUI("You have chosen Option C.")));
        options.add(new MenuItem("Option D - Register New Employee", new ShowTextUI("Beginning to register a new employee")));
        options.add(new MenuItem("Option E - Specify a new Parameter and Categorize it", new ShowTextUI("You have chosen Option E.")));

        int option = 0;
        do
        {
            option = Utils.showAndSelectIndex(options, "\n\nAdmin Menu:");

            if ( (option >= 0) && (option < options.size()))
            {
                options.get(option).run();
            }
        }
        while (option != -1 );
    }
}
