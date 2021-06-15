package app.domain.model;

public class NHSReport {
    private static String lineEquation;
    private static String confidencenceLevel = "95";

    public static String nhsReport() {

        return String.format("%s%s%s%s%s%s",regressionModelLine(),otherStatistics(13,13,13),hypothesisTests(13,13,13,13,13,12,12,12,135),decisionF(), linePredictionValues(),predictionValues("12-02-2000",13,13.2,"12-13"));
    }

    private static String regressionModelLine() {
        //lineEquation = getLine();
        return String.format("The regression model fitted using data from the interval %n %s%n", lineEquation);
    }

    private static String otherStatistics(double r2, double r2Adjusted, double r) {
        return String.format("%nOther statistics %n R2 = %s %n R2adjusted = %s %n R = %s %n ", r2, r2Adjusted, r);
    }

    private static String hypothesisTests(double dfRegression, double sSRegression, double mSRegression, double fRegression, double dResidual, double sSResidual, double mSResidual, double dTotal, double sSTotal) {
        return String.format("%nSignificance model with Anova \n" +
                "H0: b=0  H1:b<>0 \n %15s %12s %12s %12s \n" +
                "Regression %8.4f %12.4f %12.4f\t%12.4f \nResidual %10.4f  %11.4f %12.4f \n Total %12.4f %12.4f", "df", "SS", "MS", "F", dfRegression, sSRegression, mSRegression, fRegression, dResidual, sSResidual, mSResidual,dTotal,sSTotal);
    }

    private static String decisionF() {
        return String.format("%n%nDecision: f%n" +
                "0 > f0.05,(2.15)=3.682%n" +
                "Reject H0%nThe regression model is significant");
    }

    private static String predictionValues(String date, int positiveCases, double estimatedCases, String intervals) {
        return String.format("%n%4s %15d  %43.4f %45s %n", date, positiveCases, estimatedCases, intervals);
    }

    private static String linePredictionValues() {
        //confidencenceLevel = getConfidenceLevel();
        return String.format("%n%n%s %40s %40s %30s","Date","Number of OBSERVED positive cases","Number of ESTIMATED positive cases ", confidencenceLevel+"% intervals ");
    }


    public static void main(String[] args) {
        System.out.println(nhsReport());
//        System.out.println(regressionModelLine());
//        System.out.println(hypothesisTests(1221, 1221, 1212, 1115.2, 9999, 2222, 2222));
//        System.out.println(linePredictionValues());
//        System.out.println(predictionValues("13-05-2021",13,25.2,"13.16-23.48"));
    }
}
