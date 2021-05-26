package app.domain.store;

import app.controller.App;
import app.domain.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestStore {

    List<Test> tests;
    TestParam testParam;
    Parameter parameter;
    TestResult testResult;

    public TestStore() {
        tests = new ArrayList<>();
    }

    public void addTests(Test test) {
        tests.add(test);
    }

    public List<Parameter> getParameterSelectedList(Test test) {
        return getTest(test.getBarcode()).getParameterStore();
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

    public Test createTest(String nhsCode, String internalCode, Client client, TestType testType, List<ParameterCategory>
            parameterCategory, List<Parameter> parameter, Date registrationDate) {

        return new Test(nhsCode, internalCode, client, testType,
                parameterCategory, parameter, registrationDate);
    }

    public void saveTest(Test t) {
        validateTest(t);
        addTest(t);
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

    public List<Test> getTests() {
        List<Test> tests = new ArrayList<>();
        tests.addAll(this.tests);
        return tests;
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

    private boolean testExists(Test test) {
        return tests.contains(test);
    }

    public boolean hasTestPassedSampleCollection(String barcode) {
        return getTest(barcode).getSamplesCollectionDate() != null;
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

    public boolean findParameter(Test test) {
        return test.getParameter().contains(parameter);
    }


    private boolean isTestResultInBetweenReferenceValue(TestResult parameterBeingTested) {
        double maxValue = parameterBeingTested.getReferenceValue().getMaxValue();
        double minValue = parameterBeingTested.getReferenceValue().getMinValue();
        double result = parameterBeingTested.getResult();
        return result > minValue && result < maxValue;

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

    private boolean isUnvalidatedTest(Test t) {
        return t.getRegistrationDate() != null && t.getSamplesCollectionDate() != null && t.getChemicalAnalysisDate() != null && t.getDiagnosisDate() != null && t.getValidationDate() == null;
    }

    public List<Test> getUnvalidatedTests() {
        List<Test> lUnvalidatedTests = new ArrayList<>();
        for (int i = 0; i < tests.size(); i++) {
            if (isUnvalidatedTest(tests.get(i))) {
                lUnvalidatedTests.add(tests.get(i));
            }
        }
        return lUnvalidatedTests;
    }

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
            if (t.getSamplesCollectionDate() != null && t.getChemicalAnalysisDate() != null && t.getDiagnosisDate() == null) {
                complete.add(t);
            }
        }
        return complete;
    }
}
