package app.ui.console;

import app.controller.WriteReportController;
import app.domain.model.Report;
import app.domain.model.Test;
import app.domain.model.TestResult;
import app.domain.store.TestStore;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WriteReportUI implements Runnable {

    private WriteReportController writeReportController;

    public WriteReportUI(){
        this.writeReportController=new WriteReportController();
    }

    @Override
    public void run() {
        Scanner x = new Scanner(System.in);
        List<TestResult>testResults=new ArrayList<>();
        int option = 0;


        do {
            option = 0;
            List<Test> lt = this.writeReportController.getTestsWithoutDiagnosis();
            String report="";
            String testCode="";
            System.out.println("Beginning to write a report.\n");

            option = Utils.showAndSelectIndex(lt, "Select test.");

            if ((option >= 0) && (option < lt.size())) {
                Report rep=null;
                testResults = lt.get(option).getTestResult();
                testCode=lt.get(option).getInternalCode();
                boolean validation = false;
                System.out.println(testResults);
                while(!validation) {
                    System.out.println();
                    System.out.println("Write Report");
                    report = x.nextLine();
                    validation = checkReportRules(report);
                    if(!validation) {
                        System.out.println("Report must have a maximum of 400 words");
                    }
                }
                validation=false;

                try {
                    rep=this.writeReportController.writeReport(testCode,report);
                } catch (IllegalArgumentException ex) {
                    System.out.println(ex.getMessage());
                }

                System.out.println("Confirmation: ");
                System.out.printf("-Report: %s%n", report);

                if(!Utils.confirm("Confirm test type creation (y/n)?")){
                    return;
                }

                Test test=writeReportController.getTestByInternalCode(testCode);

                try {
                    this.writeReportController.saveReport(test,rep);
                } catch (IllegalArgumentException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            if (option == -1) {
                return;
            }


        }while(option!=-1 );

    }

    public boolean checkReportRules(String report){
        boolean validation=true;
        if (numberOfWords(report)<1 || numberOfWords(report)>400){

            validation=false;
        }
        return validation;
    }

    public long numberOfWords(String report){
        long var = report.split(" ", -1).length - 1;
        var=var+1;
        return var;
    }
}
