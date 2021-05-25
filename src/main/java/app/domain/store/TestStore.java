package app.domain.store;

import app.domain.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestStore {

    private List<Test> test;

    public TestStore() {
        this.test = new ArrayList<>();
    }

    public Test createTest(String nhsCode, String internalCode, Client client, TestType testType, String sampleCollectionMethod,
                           List<ParameterCategory> parameterCategory, List<Parameter> parameter, Date registrationDate,
                           Date samplesCollectionDate, Date chemicalAnalysisDate, Date diagnosisDate, Date validationDate) {

        return new Test(nhsCode, internalCode, client, testType, sampleCollectionMethod,
                parameterCategory, parameter, registrationDate, samplesCollectionDate, chemicalAnalysisDate,
                diagnosisDate, validationDate);
    }

    public void saveTest(Test t) throws IllegalArgumentException {
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
        this.test.add(t);
    }

    public List<Test> getTests() {
        List<Test> tests = new ArrayList<>();
        tests.addAll(this.test);
        return tests;
    }

}
