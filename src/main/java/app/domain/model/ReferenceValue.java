package app.domain.model;

public class ReferenceValue {
    private String metric;
    private double maxValue;
    private double minValue;

    private static final String METRIC_DEFAULT = "No metric";
    private static final double MAXVALUE_DEFAULT = 0;
    private static final double MINVALUE_DEFAULT = 0;

    public ReferenceValue() {
        metric = METRIC_DEFAULT;
        maxValue = MAXVALUE_DEFAULT;
        minValue = MINVALUE_DEFAULT;
    }

    public ReferenceValue(String metric, double maxValue, double minValue) {
        this.metric = metric;
        this.maxValue = maxValue;
        this.minValue = minValue;
    }

    @Override
    public String toString() {
        return "ReferenceValue{" +
                "metric='" + metric + '\'' +
                ", maxValue=" + maxValue +
                ", minValue=" + minValue +
                '}';
    }
}
