package app.ui.console;

import app.controller.RecordTestResultsController;
import app.domain.model.Parameter;
import app.ui.console.utils.Utils;

import java.util.List;
import java.util.Scanner;

public class RecordTestResultsUI implements Runnable {
    public RecordTestResultsUI() {
        // Do nothing because there is no need to construct the UI layer with any value. This is only used to be able to use the UI when selecting in menus.
    }


    @Override
    public void run() {
        List<Parameter> parametersToShow;
        List<Parameter> parametersSelected;
        String barcode;
        double result = 0;
        String parameterCode = "";
        Scanner sc = new Scanner(System.in);
        RecordTestResultsController recordTestResultsController = new RecordTestResultsController();
        System.out.println();
        System.out.println("Welcome to the Record Results of Test Page");
        System.out.println();
        do {
            System.out.println("Please introduce the barcode of the Sample where the results will be recorded.(11 digits)");
            barcode = sc.nextLine();
        } while (!recordTestResultsController.testExists(barcode) || recordTestResultsController.hasTestPassedSampleCollection(barcode));

        parametersToShow = recordTestResultsController.getParameterStoreToShow(recordTestResultsController.getTest(barcode));

        while (!parametersToShow.isEmpty()) {

            int option = 0;

            option = Utils.showAndSelectIndex(parametersToShow, "Select parameter to record the result.");
            if ((option >= 0) && (option < parametersToShow.size())) {
                System.out.println("Please insert the result for the intended parameter: ");
                result = sc.nextDouble();
                parameterCode = parametersToShow.get(option).getCode();
                recordTestResultsController.addTestResult(parameterCode, result,recordTestResultsController.getTest(barcode));
                System.out.println("Confirmation: \n");
                System.out.printf("-Parameter Selected: %s%n", parameterCode);
                System.out.printf("-Result introduced: %s%n", result);

            }

            if (!Utils.confirm("Confirm test result creation (s/n)?")) {
                return;
            }
            recordTestResultsController.associateToParameter();


            parametersSelected = recordTestResultsController.getListOfParametersSelected(recordTestResultsController.getTest(barcode));

            for (Parameter parameter1231 : parametersSelected) {
                System.out.println(parameter1231);
            }
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            parametersToShow.remove(option);
            List<Parameter> yau = recordTestResultsController.getValidatedTests(parameterCode);

            for (Parameter parameter1231 : yau) {
                System.out.println(parameter1231);
            }
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

        }
    }

}

