package app.ui.console;

import app.controller.ClinicalAnalysisLaboratoryController;
import app.controller.TestTypeController;

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
    }
}
