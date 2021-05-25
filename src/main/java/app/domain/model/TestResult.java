package app.domain.model;


public class TestResult {
    ReferenceValue referenceValue;
    double result;
    Parameter parameter;

    public TestResult(Parameter parameter, double result, ReferenceValue referenceValue) {
        this.parameter = parameter;
        this.result = result;
        this.referenceValue = referenceValue;
    }

    public TestResult(double result, ReferenceValue refValue) {
        this.result = result;
        this.referenceValue = refValue;
    }

    public double getResult() {
        return result;
    }

    public ReferenceValue getReferenceValue() {
        return referenceValue;
    }

    @Override
    public String toString() {
        return "ResultOfTest{" +
                "result=" + result +
                ", refValue=" + referenceValue +
                '}';
    }


}
