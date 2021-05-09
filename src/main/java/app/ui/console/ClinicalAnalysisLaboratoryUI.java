package app.ui.console;

import app.controller.ClinicalAnalysisLaboratoryController;
import app.domain.model.ClinicalAnalysisLaboratory;
import app.domain.model.TestType;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClinicalAnalysisLaboratoryUI implements Runnable {

    private ClinicalAnalysisLaboratoryController clinicalAnalysisLaboratoryController;

    public ClinicalAnalysisLaboratoryUI() {
        this.clinicalAnalysisLaboratoryController = new ClinicalAnalysisLaboratoryController();
    }

    public void run() {
        Scanner x = new Scanner(System.in);
        System.out.println("Beginning to register a new clinical analysis laboratory.\n");
        System.out.println("Insert the laboratoryID.");
        String laboratoryID=x.nextLine();
        System.out.println("Insert name.");
        String name=x.nextLine();
        System.out.println("Insert adress.");
        String adress=x.nextLine();
        System.out.println("Insert phone number.");
        long phoneNumber=x.nextLong();
        System.out.println("Insert TIN number.");
        long tin=x.nextLong();

        List<String> testTypeCodes = new ArrayList<String>();

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
            System.out.println(ex.getMessage());
            return;
        }

        System.out.println("Confirmation: \n");
        System.out.printf("-LaboratoryID: %s\n", cal.getLaboratoryID());
        System.out.printf("-Name: %s\n", cal.getName());
        System.out.printf("-Adress: %s\n", cal.getAdress());
        System.out.printf("-Phone number: %s\n", cal.getPhoneNumber());
        System.out.printf("-TIN: %s\n", cal.getTin());
        System.out.println("-Test types: ");

        for(TestType tt : cal.getTestTypes()) {
            System.out.printf("\t%s - %s - %s - %s\n", tt.getCode(),tt.getDescription(),tt.getCollectingMethod(), tt.getParameterCategories());
        }

        if(!Utils.confirm("Confirm test type creation (s/n)?")){
            return;
        }

        try {
            this.clinicalAnalysisLaboratoryController.saveClinicalAnalysisLaboratory(cal);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
