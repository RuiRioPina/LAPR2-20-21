package app.domain.store;

import app.controller.App;
import app.domain.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestStore {
    /**
     * Object Oriented class used to store the Tests.
     */
    List<Test> tests;
    TestParam testParam;
    Parameter parameter;
    TestResult testResult;

    /**
     * Test Store constructor.
     */
    public TestStore() {
        tests = new ArrayList<>();
    }
    public TestStore(List<Test> tests) {
        this.tests = tests;
    }


    List<Parameter> validatedParameterList;

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    public void initializeValidationList() {
        validatedParameterList = new ArrayList<>();
    }

    public TestResult addTestResult(String parameterCode, double result, Test test) {
        testParam = new TestParam(test);

        if (testParam.findParameterInTestParameter(parameterCode) != null) {
            parameter = testParam.findParameterInTestParameter(parameterCode);
            testResult = test.addTestResult(parameter, result);
            return testResult;
        }
        return null;
    }

    /**
     * Creates an object of Test class.
     * @param nhsCode           - NHS code of the test.
     * @param internalCode      - Internal code of the test.
     * @param client            - Client that performs the test.
     * @param testType          - Test Type of the test.
     * @param parameterCategory - Category/Categories of the test.
     * @param parameter         - Parameter/Parameters of the test.
     * @param registrationDate  - Test registration date.
     * @return Object of Test class.
     */
    public Test createTest(String nhsCode, String internalCode, Client client, TestType testType, List<ParameterCategory>
            parameterCategory, List<Parameter> parameter, Date registrationDate) {

        return new Test(nhsCode, internalCode, client, testType,
                parameterCategory, parameter, registrationDate);
    }

    /**
     * Saves a test.
     * @param t - Test.
     */
    public void saveTest(Test t) {
        validateTest(t);
        addTest(t);
    }

    /**
     * Validates a test.
     * @param t - Test.
     */
    private void validateTest(Test t) {
        checkNhsCode(t.getNhsCode());
    }

    /**
     * Checks Test NHS Code rules.
     * @param nhsCode - NHS Code of the test.
     */
    private void checkNhsCode(String nhsCode) {
        if (!nhsCode.matches("[A-Za-z0-9]+")) {
            throw new IllegalArgumentException("Code must be alphanumeric.");
        }
        if (nhsCode.length() != 12)
            throw new IllegalArgumentException("Code must have 12 chars.");
    }

    /**
     * Adds a test to the list.
     * @param t - Test.
     */
    private void addTest(Test t) {
        this.tests.add(t);
    }

    /**
     * Method to get all tests.
     * @return - all test stored in list.
     */
    public List<Test> getTests() {
        List<Test> testList = new ArrayList<>();
        testList.addAll(this.tests);
        return testList;
    }

    public List<Test> getTestsWithoutSamples() {
        List<Test> result = new ArrayList<>();
        for (Test t : this.tests) {
            if (t.getSamplesCollectionDate() == null) {
                result.add(t);
            }
        }
        return result;
    }

    public List<Test> getTestsWithSamples() {
        List<Test> result = new ArrayList<>();
        for (Test t : this.tests) {
            if (t.getSamplesCollectionDate() != null) {
                result.addAll(this.tests);
            }
        }
        return tests;
    }

    public Test getTestByInternalCode(String testCode) {
        for (Test t : this.tests) {
            if (t.getInternalCode().equals(testCode)) {
                return t;
            }
        }
        return null;
    }

    public Sample createSample(String barcode) {
        return new Sample(barcode);
    }

    public void saveSample(Test t, Sample s) {
        validateSample(s);
        addSample(t, s);
    }

    private void validateSample(Sample s) {
        if (!s.getBarcode().matches("[0-9]+")) {
            throw new IllegalArgumentException("Barcode must be numeric.");
        }
        if (s.getBarcode().length() != 11) {
            throw new IllegalArgumentException("Barcode must have 11 chars.");
        }
        List<Test> tests = App.getInstance().getCompany().getAllTest();
        for(Test test : tests) {
        	for(Sample sample : test.getSamples()) {
        		if(s.getBarcode().equals(sample.getBarcode())) {
        			throw new IllegalArgumentException("Barcode must be unique");
        		}
        	}
        }
    }

    private void addSample(Test t, Sample s) {
        t.getSamples().add(s);
        t.setSamplesCollectionDate(new Date(System.currentTimeMillis()));
    }


    public boolean testExists(String barcode) {
        for (Test t : this.tests) {
            for (Sample samplesOfATest : t.getSamples()) {
                if (samplesOfATest.getBarcode().equals(barcode)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Test getTestByBarcode(String barcode) {
        for (Test t : this.tests) {
            for (Sample samplesOfATest : t.getSamples()) {
                if (samplesOfATest.getBarcode().equals(barcode)) {
                    return t;
                }
            }
        }
        return null;
    }

    private boolean testExists(Test test) {
        return tests.contains(test);
    }

    public boolean hasTestPassedSampleCollection(String barcode) {
        return getTest(barcode).getSamplesCollectionDate() != null;
    }

    public List<Parameter> getParameterTestToShow(String barcode) {
        Test test = getTestByBarcode(barcode);
        return test.getParameterStoreToShow();
    }

    public Test getTest(String barcode) {

        for (Test testFound : tests) {
            if (testExists(barcode)) {
                return testFound;
            }
        }
        return null;
    }

    public List<TestResult> getTestsResults(Test test) {
        return App.getInstance().getCompany().getTestStore().getTestsResults(test);
    }


    private boolean isTestResultInBetweenReferenceValue(TestResult parameterBeingTested) {
        double maxValue = parameterBeingTested.getReferenceValue().getMaxValue();
        double minValue = parameterBeingTested.getReferenceValue().getMinValue();
        double result = parameterBeingTested.getResult();
        return result > minValue && result < maxValue;

    }


    public List<Parameter> getValidatedParameters(String parameterCode,String barcode) {

        Test test = getTestByBarcode(barcode);
        List<Parameter> parameters =test.getParameter();
        Parameter parameterFromWhichTestResultWillBeExtracted = testParam.findParameterInTestParameter(parameterCode,parameters);
        TestResult testResultBeingValidated = parameterFromWhichTestResultWillBeExtracted.getTestResult();

        if (isTestResultInBetweenReferenceValue(testResultBeingValidated)) {
            validatedParameterList.add(parameterFromWhichTestResultWillBeExtracted);
        }

        return validatedParameterList;

    }

    public Test getTest(Test test) {

        for (Test testFound : tests) {
            if (testExists(test)) {
                return testFound;
            }
        }
        return null;
    }

    public void saveTestResult(String parameterCode,String barcode) {
        Test test = getTestByBarcode(barcode);
        List<Parameter> parameters = test.getParameter();
        parameter = testParam.findParameterInTestParameter(parameterCode,parameters);
        parameter.setTestResult(testResult);
    }


    /**
     * Method used to check if a test is ready to be used by the Laboratory Coordinator. Checks if the
     * registrationDate, the sampleCollectionDate, the chemicalAnalysisDate and the diagnosisDate aren't null,while the validationDate is.
     * @param t- Test Object to be checked
     * @return - boolean value saying if the Object is ready to be used or not.
     */
    private boolean isUnvalidatedTest(Test t) {
        return t.getRegistrationDate() != null && t.getSamplesCollectionDate() != null && t.getChemicalAnalysisDate() != null && t.getDiagnosisDate() != null && t.getValidationDate() == null;
    }

    /**
     * Checks the TestStore's Test List to get all the UnvalidatedTests.
     * @return- List with all of the Unvalidated tests.
     */
    public List<Test> getUnvalidatedTests() {
        List<Test> lUnvalidatedTests = new ArrayList<>();
        for (int i = 0; i < tests.size(); i++) {
            if (isUnvalidatedTest(tests.get(i))) {
                lUnvalidatedTests.add(tests.get(i));
            }
        }
        return lUnvalidatedTests;
    }

    /**
     * Method used to change the validationDate of a List of Test Objects.
     * @param lTests- List to be changed.
     * @param newDate- Date used for the validationDate.
     */
    public void validateTests(List<Test> lTests, Date newDate) {
        for (int i = 0; i < lTests.size(); i++) {
            lTests.get(i).setValidationDate(newDate);
            try {
                lTests.get(i).sendTestCompletedNotification();
            } catch (Exception e) {

            }

        }
    }

    public List<Test> getTestsWithoutDiagnosis() {
        List<Test> complete = new ArrayList<>();
        for (Test t : this.tests) {
            if (t.getSamplesCollectionDate() != null && t.getChemicalAnalysisDate()!=null && t.getDiagnosisDate() == null) {
                complete.add(t);
            }
        }
        return complete;
    }


}
