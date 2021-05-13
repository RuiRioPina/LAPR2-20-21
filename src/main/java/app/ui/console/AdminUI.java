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
        options.add(new MenuItem("Option 1 - Register New Employee", new RegisterEmployeeUI()));
        options.add(new MenuItem("Option 2 - Register a new Clinical Analysis Laboratory", new ClinicalAnalysisLaboratoryUI()));
        options.add(new MenuItem("Option 3 - Specify a new type of Test ", new TestTypeUI()));
        options.add(new MenuItem("Option 4 - Specify a new Parameter and Categorize it", new ParameterUI()));
        options.add(new MenuItem("Option 5 - Specify a new Parameter Category ", new ParameterCategoryUI()));

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
