package app.controller;

import app.domain.model.*;
import app.domain.store.ResultOfTestStore;
import app.domain.store.TestStore;

import java.util.Date;
import java.util.List;

public class RecordTestResultsController {
    Company company = App.getInstance().getCompany();
    TestStore tests = App.getInstance().getCompany().getAllTestStore();

    ResultOfTestStore resultOfTestStore = this.company.getResultOfTestStore();


    public RecordTestResultsController() {
        //Constructor
    }

    public boolean testExists(String barcode) {
        return tests.testExists(barcode);
    }

    public Test getTest(String barcode) {
        return tests.getTest(barcode);
    }

    public Test getTestByBarcode(String barcode) {
        return tests.getTestByBarcode(barcode);
    }


    public List<Parameter> getListOfParametersSelected(Test test) {
        return tests.getTest(test).getParameterStore();
    }

    public void addTestResult(String parameterCode, double result, Test test) {
        tests.addTestResult(parameterCode, result, test);
    }


    public void saveTestResult(String parameterCode, String barcode) {
        tests.saveTestResult(parameterCode, barcode);
    }


    public List<Parameter> getParameterStoreToShow(String barcode) {
        return tests.getParameterTestToShow(barcode);
    }


    public List<Parameter> getValidatedParameters(String parameterCode, String barcode) {
        return tests.getValidatedParameters(parameterCode, barcode);
    }

    public boolean hasTestPassedSampleCollection(String barcode) {
        return tests.hasTestPassedSampleCollection(barcode);
    }




    public void getDate(Test testByBarcode) {
        testByBarcode.getDate();
    }

    public void saveTest(Test testByBarcode) {
        tests.saveTest(testByBarcode);
    }

    public void setChemicalAnalysis(Test test) {
        test.setChemicalAnalysisDate(new Date(System.currentTimeMillis()));
    }

    public void initializeValidationList() {
        tests.initializeValidationList();
    }
}
