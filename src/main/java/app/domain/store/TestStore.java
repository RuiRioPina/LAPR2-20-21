package app.domain.store;

import app.controller.App;
import app.domain.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestStore {
    List<Test> tests;
    Test test = new Test();
    TestParam testParam = new TestParam(test);

    Parameter parameter;
    TestResult testResult;

    public TestStore() {
        tests = new ArrayList<>();
        tests.add(test);
    }

    public void addTests(Test test) {
        tests.add(test);
    }

    public List<Parameter> getParameterSelectedList() {
        return getTest(test.getBarcode()).getParameterStore();
    }

    public TestResult addTestResult(String parameterCode, double result) {


        if (testParam.findParameterInTestParameter(parameterCode) != null) {
            parameter = testParam.findParameterInTestParameter(parameterCode);
            testResult = test.addTestResult(parameter, result);
            return testResult;
        }
        return null;
    }

    public Test createTest(String nhsCode, String internalCode, Client client, TestType testType, String sampleCollectionMethod,
                           List<ParameterCategory> parameterCategory, List<Parameter> parameter, List<Sample> samples, Date registrationDate,
                           Date samplesCollectionDate, Date chemicalAnalysisDate, Date diagnosisDate, Date validationDate) {


        return new Test(nhsCode, internalCode, client, testType, sampleCollectionMethod,
                parameterCategory, parameter, samples, registrationDate, samplesCollectionDate, chemicalAnalysisDate,
                diagnosisDate, validationDate);
    }

    public boolean testExists(long barcode) {
        return tests.stream().anyMatch(o -> o.getBarcode() == barcode);
    }

    private boolean testExists(Test test) {
        return tests.contains(test);
    }

    public boolean hasTestPassedSampleCollection(long barcode) {
        return getTest(barcode).getSamplesCollectionDate() != null;
    }

    public Test getTest(long barcode) {

        for (Test testFound : tests) {
            if (testExists(barcode)) {
                return testFound;
            }
        }
        return null;
    }

    public TestResult getTestResult() {
        return testResult;
    }

    public Test getTest(Test test) {

        for (Test testFound : tests) {
            if (testExists(test)) {
                return testFound;
            }
        }
        return null;
    }

    public void associateToParameter() {
        parameter.setTestResult(testResult);
    }

    public void saveTest(Test t) {
        validateTest(t);
        addTest(t);
    }

    public List<TestResult> getTestsResults(Test test) {
        return App.getInstance().getCompany().getTestStore().getTestsResults(test);
    }

    public boolean findParameter() {
        return test.getParameter().contains(parameter);
    }


    private boolean isTestResultInBetweenReferenceValue(TestResult parameterBeingTested) {
        double maxValue = parameterBeingTested.getReferenceValue().getMaxValue();
        double minValue = parameterBeingTested.getReferenceValue().getMinValue();
        double result = parameterBeingTested.getResult();
        return result > minValue && result < maxValue;

    }

    private void validateTest(Test t) {
        checkNhsCode(t.getNhsCode());
    }

    private void checkNhsCode(String nhsCode) {
        if (!nhsCode.matches("[A-Za-z0-9]+")) {
            throw new IllegalArgumentException("Code must be alphanumeric.");
        }
        if (nhsCode.length() != 12)
            throw new IllegalArgumentException("Code must have 12 chars.");
    }


    private void addTest(Test t) {
        this.tests.add(t);
    }

    List<Parameter> validatedParameterList = new ArrayList<>();

    public List<Parameter> getValidatedTests(String parameterCode) {
        Parameter parameterFromWhichTestResultWillBeExtracted = testParam.findParameterInTestParameter(parameterCode);
        TestResult testResultBeingValidated = parameterFromWhichTestResultWillBeExtracted.getTestResult();

        if (isTestResultInBetweenReferenceValue(testResultBeingValidated)) {
            validatedParameterList.add(parameter);
        }

        return validatedParameterList;

    }

    private boolean workCanBeValidated(Test t) {
        return t.getRegistrationDate() != null && t.getSamplesCollectionDate() != null && t.getChemicalAnalysisDate() != null && t.getDiagnosisDate() != null && t.getValidationDate() == null;
    }

    public List<Test> getUnvalidatedTests() {
        List<Test> unvalidatedTestList = new ArrayList<>();
        for (Test value : tests) {
            if (workCanBeValidated(value)) {
                unvalidatedTestList.add(value);
            }
        }
        return unvalidatedTestList;
    }

    public List<Test> getTests() {
        return new ArrayList<>(this.tests);
    }


}
