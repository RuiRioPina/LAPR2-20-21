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

    public List<Parameter> getListOfParametersSelected(Test test) {
        return tests.getTest(test).getParameterStore();
    }

    public void addTestResult(String parameterCode, double result, Test test) {
        tests.addTestResult(parameterCode, result, test);
    }


    public void associateToParameter() {
        tests.associateToParameter();
    }


    public List<Parameter> getParameterStoreToShow(Test test) {
        return tests.getTest(test).getParameterStoreToShow();
    }


    public List<Parameter> getValidatedTests(String parameterCode) {
        return tests.getValidatedTests(parameterCode);
    }

    public boolean hasTestPassedSampleCollection(String barcode) {
        return tests.hasTestPassedSampleCollection(barcode);
    }

    public void setAnalysisDate(String barcode) {
        Date data = new Date(System.currentTimeMillis());

        tests.getTest(barcode).setChemicalAnalysisDate(data);
    }


}
