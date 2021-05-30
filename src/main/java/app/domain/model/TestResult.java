package app.domain.model;


public class TestResult {
    ReferenceValue referenceValue;
    double result;
    Parameter parameter;

    /**
     * Constructor that creates the test result, later to be associated with a test
     * @param parameter parameter of the test where the Test Result will be associated to.
     * @param result result of the parameter that was introduced by the user
     * @param referenceValue reference that comes from the automatic validation API containing its metric and max and min value
     */
    public TestResult(Parameter parameter, double result, ReferenceValue referenceValue) {
        this.parameter = parameter;
        this.result = result;
        this.referenceValue = referenceValue;
    }

    /**
     * Getter for the test result
     * @return the test result
     */

    public double getResult() {
        return result;
    }

    /**
     * Getter for the reference value object
     * @return the reference value of the test result
     */

    public ReferenceValue getReferenceValue() {
        return referenceValue;
    }

    /**
     * Method toString overwritten to expose information about the test result to the user.
     * @return String containing the information to be exposed
     */

    @Override
    public String toString() {
        return "ResultOfTest{" +
                "result=" + result +
                ", refValue=" + referenceValue +
                '}';
    }


}
