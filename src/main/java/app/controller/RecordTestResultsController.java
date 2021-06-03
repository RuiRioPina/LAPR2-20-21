package app.controller;

import app.domain.model.*;
import app.domain.store.TestStore;

import java.util.Date;
import java.util.List;

public class RecordTestResultsController {
    TestStore tests = App.getInstance().getCompany().getAllTestStore();
    TestType testType = new TestType();

    /**
     * Constructor of the Class RecordTestResultsController
     */
    public RecordTestResultsController() {
        //Constructor
    }

    /**
     * Checks if the test exists on the database
     * @param barcode barcode of one of the samples associated to the test
     * @return boolean containing the result if it finds or not
     */

    public boolean testExists(String barcode) {
        return tests.testExists(barcode);
    }

    /**
     * Bring the test that has the barcode associated to it
     * @param barcode barcode of one of the samples associated to the test
     * @return the test containing a sample with that barcode
     */

    public Test getTestByBarcode(String barcode) {
        return tests.getTestByBarcode(barcode);
    }

    /**
     * Adds the Test Result to the parameter of the test
     * @param parameterCode parameter of the test where the result will be recorded
     * @param result the value of the result inputted by the user
     * @param test test where the parameter is and therefore where the result will be recorded
     */

    public void addTestResult(String parameterCode, double result, Test test) {
        tests.addTestResult(parameterCode, result, test);
    }

    /**
     * Save the Test Result created before when the information introduced is confirmed
     * @param parameterCode parameter of the test where the result will be saved
     * @param barcode barcode of one of the samples associated to the test
     */

    public void saveTestResult(String parameterCode, String barcode) {
        tests.saveTestResult(parameterCode, barcode);
    }

    /**
     * Bring the list of parameters of a given test
     * @param barcode barcode of one of the samples associated to the test
     * @return list containing the parameters that a test contain
     */


    public List<Parameter> getParameterStoreToShow(String barcode) {
        return tests.getParameterTestToShow(barcode);
    }

    /**
     * Get the list of parameters that passed in the automatic validation API
     * @param parameterCode parameter of the test where the result will be saved
     * @param barcode barcode of one of the samples associated to the test
     * @return list of parameters that passed in the automatic validation API
     */

    public List<Parameter> getValidatedParameters(String parameterCode, String barcode) {
        return tests.getValidatedParameters(parameterCode, barcode);
    }

    /**
     * Checks if the test to be manipulated with has already passed through the medical lab technician
     * @param barcode barcode of one of the samples associated to the test
     * @return boolean containing the result of the assertion
     */

    public boolean hasTestPassedSampleCollection(String barcode) {
        return tests.hasTestPassedSampleCollection(barcode);
    }

    /**
     * Get the clinical chemistry date that was associated with the test after the clinical chemistry technologist finished all his work
     * @param testByBarcode the test from where the date will be extracted
     */

    public void getDate(Test testByBarcode) {
        testByBarcode.getDate();
    }

    /**
     * Set the clinical chemistry date that was associated with the test after the clinical chemistry technologist finished all his work
     * @param test the test from where the date will be set.
     */

    public void setChemicalAnalysis(Test test) {
        test.setChemicalAnalysisDate(new Date(System.currentTimeMillis()));
    }

    /**
     * Initializes a new list everytime a new test has its results recorded
     */
    public void initializeValidationList() {
        tests.initializeValidationList();
    }

    /**
     * Initializes a new list everytime a new test has its results recorded
     */
    public String getMetricsFor(String parameter) {
        return testType.getMetricsBasedOnTestType(parameter);
    }
}
