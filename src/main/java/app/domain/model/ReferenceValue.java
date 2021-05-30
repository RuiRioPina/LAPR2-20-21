package app.domain.model;

public class ReferenceValue {
    /**
     * Class assigned to the creating of a object where the values brought by the API will be stored
     */

    private final String metric;
    private final double maxValue;
    private final double minValue;

    private static final String METRIC_DEFAULT = "No metric";
    private static final double MAXVALUE_DEFAULT = 0;
    private static final double MINVALUE_DEFAULT = 0;


    /**
     * Parameterless constructor containing the default values of the attributes defined above
     */
    public ReferenceValue() {
        metric = METRIC_DEFAULT;
        maxValue = MAXVALUE_DEFAULT;
        minValue = MINVALUE_DEFAULT;
    }

    /**
     * Constructor containing all the attributes, where they are assigned to certain values
     */

    public ReferenceValue(String metric, double maxValue, double minValue) {
        this.metric = metric;
        this.maxValue = maxValue;
        this.minValue = minValue;
    }

    /**
     * Getter for the maxValue of a given test result
     * @return the max value of the test result
     */
    public double getMaxValue() {
        return maxValue;
    }

    /**
     * Getter for the minValue of a given test result
     * @return the min value of the test result
     */

    public double getMinValue() {
        return minValue;
    }

    /**
     * Method toString overwritten in order to be able to view the relevant information in regards to the reference value
     * @return String containing the information about the reference value.
     */
    @Override
    public String toString() {
        return "ReferenceValue{" +
                "metric='" + metric + '\'' +
                ", maxValue=" + maxValue +
                ", minValue=" + minValue +
                '}';
    }
}
