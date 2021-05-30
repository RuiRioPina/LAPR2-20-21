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


    public TestResult addTestResult(Parameter parameter, double result, ReferenceValue referenceValue) {
        TestResult testResult = new TestResult(parameter, result, referenceValue);
        resultOfTestsStore.add(testResult);
        return testResult;
    }


    public List<TestResult> getResultOfTest() {
        return new ArrayList<>(this.resultOfTestsStore);
    }

}
