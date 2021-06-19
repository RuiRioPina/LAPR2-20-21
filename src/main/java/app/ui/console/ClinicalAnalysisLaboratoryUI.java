package app.ui.console;

import app.controller.ClinicalAnalysisLaboratoryController;
import app.domain.model.ClinicalAnalysisLaboratory;
import app.domain.model.TestType;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClinicalAnalysisLaboratoryUI implements Runnable {

    private final ClinicalAnalysisLaboratoryController clinicalAnalysisLaboratoryController;

    public ClinicalAnalysisLaboratoryUI() {
        this.clinicalAnalysisLaboratoryController = new ClinicalAnalysisLaboratoryController();
    }

    public void run() {
        Scanner x = new Scanner(System.in);
        Utils.log("Beginning to register a new clinical analysis laboratory.\n");
        Utils.log("Insert the laboratoryID.");
        String laboratoryID=x.nextLine();
        Utils.log("Insert name.");
        String name=x.nextLine();
        Utils.log("Insert adress.");
        String adress=x.nextLine();
        Utils.log("Insert phone number.");
        long phoneNumber=x.nextLong();
        Utils.log("Insert TIN number.");
        long tin=x.nextLong();

        List<String> testTypeCodes = new ArrayList<>();

        List<TestType> list=this.clinicalAnalysisLaboratoryController.getTestTypes();
        int option=0;
        do
        {
            option = Utils.showAndSelectIndex(list, "Select test types.");

            if ( (option >= 0) && (option < list.size()))
            {
                testTypeCodes.add(list.get(option).getCode());
                list.remove(option);
            }
        }
        while (option != -1 && !list.isEmpty());

        ClinicalAnalysisLaboratory cal;
        try {
            cal = this.clinicalAnalysisLaboratoryController.registerClinicalAnalysisLaboratory(laboratoryID, name, adress, phoneNumber, tin, testTypeCodes);
        } catch (IllegalArgumentException ex) {
            Utils.log(ex.getMessage());
            return;
        }

        Utils.log("Confirmation: %n");
        Utils.log(String.format("-LaboratoryID: %s", cal.getLaboratoryID()));
        Utils.log(String.format("-Name: %s", cal.getName()));
        Utils.log(String.format("-Adress: %s", cal.getAdress()));
        Utils.log(String.format("-Phone number: %s", cal.getPhoneNumber()));
        Utils.log(String.format("-TIN: %s", cal.getTin()));
        Utils.log("-Test types: ");

        for(TestType tt : cal.getTestTypes()) {
            Utils.log(String.format("\t%s - %s - %s - %s", tt.getCode(),tt.getDescription(),tt.getCollectingMethod(), tt.getParameterCategories()));
        }

        if(!Utils.confirm("Confirm test type creation (y/n)?")){
            return;
        }

        try {
            this.clinicalAnalysisLaboratoryController.saveClinicalAnalysisLaboratory(cal);
        } catch (IllegalArgumentException ex) {
            Utils.log(ex.getMessage());
        }
    }
}
