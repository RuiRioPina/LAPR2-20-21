package app.controller;

import app.domain.model.*;
import app.domain.store.ResultOfTestStore;
import app.domain.store.TestStore;

import java.util.Date;
import java.util.List;

public class RecordTestResultsController {
    Company company = App.getInstance().getCompany();
    TestStore tests = App.getInstance().getCompany().getTestStore();

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


    public List<Parameter> getValidatedTests(String parameterCode, String barcode) {
        return tests.getValidatedTests(parameterCode, barcode);
    }

    public boolean hasTestPassedSampleCollection(String barcode) {
        return tests.hasTestPassedSampleCollection(barcode);
    }

    public void setChemicalAnalysisDate(Test test) {
        Date data = new Date(System.currentTimeMillis());
        tests.getTest(test).setChemicalAnalysisDate(data);
    }


    public void getDate(Test testByBarcode) {
        testByBarcode.getDate();
    }
}
