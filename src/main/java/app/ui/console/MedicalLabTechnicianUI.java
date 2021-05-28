package app.ui.console;

import app.controller.App;
import app.domain.model.ClinicalAnalysisLaboratory;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */

public class MedicalLabTechnicianUI implements Runnable{
	
    public MedicalLabTechnicianUI()
    {
        // Do nothing because there is no need to construct the UI layer with any value. This is only used to be able to use the UI when selecting in menus.
    }

    public void run()
    {
    	List <ClinicalAnalysisLaboratory> claList = App.getInstance().getCompany().getClinicalAnalysisLaboratoryStore().getCLA();

        ClinicalAnalysisLaboratory cla = null;

        int opt = 0;
        opt = Utils.showAndSelectIndex(claList, "Select the Lab.");

        if ( (opt >= 0) && (opt < claList.size()))
        {
            cla = claList.get(opt);
        }

        App.getInstance().getCompany().setCLA(cla);
        
        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Option 1 - Generate Samples", new GenerateSampleUI()));

        int option = 0;
        do
        {
            option = Utils.showAndSelectIndex(options, "\n\nMedicalLabTechnician UI Menu:");

            if ( (option >= 0) && (option < options.size()))
            {
                options.get(option).run();
            }
        }
        while (option != -1 );
    }
}