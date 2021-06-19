package app.ui.console;

import app.controller.WriteReportController;
import app.domain.model.Report;
import app.domain.model.Test;
import app.domain.model.TestResult;
import app.ui.console.utils.Utils;

import java.util.List;
import java.util.Scanner;

public class WriteReportUI implements Runnable {

    private final WriteReportController writeReportController;

    public WriteReportUI(){
        this.writeReportController=new WriteReportController();
    }

    @Override
    public void run() {
        Scanner x = new Scanner(System.in);
        List<TestResult>testResults;
        int option = 0;


        do {
            option = 0;
            List<Test> lt = this.writeReportController.getTestsWithoutDiagnosis();
            String report="";
            String testCode;
            Utils.log("Beginning to write a report.\n");

            option = Utils.showAndSelectIndex(lt, "Select test.");

            if ((option >= 0) && (option < lt.size())) {
                Report rep=null;
                testResults = lt.get(option).getTestResult();
                testCode=lt.get(option).getInternalCode();
                boolean validation = false;
                System.out.println(testResults);
                while(!validation) {
                    Utils.log("\nWrite Report:");
                    report = x.nextLine();
                    validation = checkReportRules(report);
                    if(!validation) {
                        Utils.log("The report should have between 1 and 400 words.");
                    }
                }
                validation=false;

                try {
                    rep=this.writeReportController.writeReport(testCode,report);
                } catch (IllegalArgumentException ex) {
                    Utils.log(ex.getMessage());
                }

                Utils.log("Confirmation: ");
                Utils.log(String.format("-Report: %s", report));

                if(!Utils.confirm("Confirm test report creation (y/n)?")){
                    return;
                }

                Test test=writeReportController.getTestByInternalCode(testCode);

                try {
                    this.writeReportController.saveReport(test,rep);
                } catch (IllegalArgumentException ex) {
                    Utils.log(ex.getMessage());
                }
            }
            if (option == -1) {
                return;
            }


        }while(option!=-1 );

    }

    public boolean checkReportRules(String report){
        boolean validation=true;
        if ((numberOfWords(report)<=1 && report.equals("")) || numberOfWords(report)>400){

            validation=false;
        }
        return validation;
    }

    public long numberOfWords(String report){
        long wordsInReport = report.split(" ", -1).length - 1;
        wordsInReport=wordsInReport+1;
        return wordsInReport;
    }
}
