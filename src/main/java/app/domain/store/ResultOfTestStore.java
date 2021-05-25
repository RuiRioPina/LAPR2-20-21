package app.domain.store;

import app.domain.model.*;

import java.util.ArrayList;
import java.util.List;

public class ResultOfTestStore {
    private final List<TestResult> resultOfTestsStore;
    private List<Test> test;




    /**
     * empty constructor of the class ClientList which initializes the arraylist.
     */
    public ResultOfTestStore() {
        this.resultOfTestsStore = new ArrayList<>();
    }




    public List<TestResult> getResultOfTestsStore() {
        return new ArrayList<>(this.resultOfTestsStore);
    }



    public boolean isTestResultInStore(TestResult testResult) {
        return this.resultOfTestsStore.contains(testResult);
    }


    public TestResult addTestResult(Parameter parameter, double result, ReferenceValue referenceValue) {
        TestResult testResult = new TestResult(parameter, result, referenceValue);
        resultOfTestsStore.add(testResult);
        return testResult;
    }


    public List<TestResult> getResultOfTest() {
        return new ArrayList<>(this.resultOfTestsStore);
    }



    public void saveTest(Test t) throws IllegalArgumentException {
        addTest(t);
    }


    private void addTest(Test t) {
        this.test.add(t);
    }

    public List<Test> getTests() {
        return new ArrayList<>(this.test);
    }
}
