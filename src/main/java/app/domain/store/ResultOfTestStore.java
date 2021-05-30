package app.domain.store;

import app.domain.model.*;

import java.util.ArrayList;
import java.util.List;

public class ResultOfTestStore {
    private final List<TestResult> resultOfTestsStore;


    /**
     * empty constructor of the class ClientList which initializes the arraylist.
     */
    public ResultOfTestStore() {
        this.resultOfTestsStore = new ArrayList<>();
    }

    /**
     * Instanciate an object from the TestResult class
     * @param parameter the parameter to be assigned to the test result
     * @param result the result, that was inputted by the user
     * @param referenceValue the reference value containing max/min values and metrics for the given parameter
     * @return the test result instanciated
     */
    public TestResult addTestResult(Parameter parameter, double result, ReferenceValue referenceValue) {
        TestResult testResult = new TestResult(parameter, result, referenceValue);
        resultOfTestsStore.add(testResult);
        return testResult;
    }

    /**
     * Get all the test results that were recorded
     * @return a list containing all test results recorded
     */

    public List<TestResult> getResultOfTest() {
        return new ArrayList<>(this.resultOfTestsStore);
    }

}
