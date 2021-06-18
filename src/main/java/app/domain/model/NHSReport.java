package app.domain.model;

import app.domain.model.matcp.LinearRegression;
import app.domain.model.matcp.MultiLinearRegression;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NHSReport {
    private static String lineEquation;
    private static String confidencenceLevel = "95";
    private LinearRegression linearRegression = null;
    private MultiLinearRegression multiLinearRegression = null;
    private double[] receivedYData;
    private double[] receivedX1Data;

    public NHSReport(LinearRegression linearRegression, double[] receivedYData, double[] receivedXData) {
        this.receivedYData = receivedYData;
        this.linearRegression = linearRegression;
        this.receivedX1Data = receivedXData;
    }

    public NHSReport(MultiLinearRegression multiLinearRegression) {
        this.multiLinearRegression = multiLinearRegression;
    }


    private String regressionModelLine() {
        //lineEquation = getLine();
        if (this.multiLinearRegression == null && this.linearRegression != null) {
            return String.format("The regression model fitted using data from the interval %n %s%n", linearRegression);
        } else if (this.multiLinearRegression != null && this.linearRegression == null) {
            return String.format("The regression model fitted using data from the interval %n %s%n", multiLinearRegression);
        }
        return String.format("You have must introduce either Multilinear / Simple Linear Regression");
    }

    private String otherStatistics() {
        if (this.multiLinearRegression == null && this.linearRegression != null) {
            return String.format("%nOther statistics %n R2 = %s %n R2adjusted = %s %n R = %s %n ", this.linearRegression.R2(), this.linearRegression.R2Adjusted(), this.linearRegression.R());
        } else if (this.multiLinearRegression != null && this.linearRegression == null) {
            return String.format("%nOther statistics %n R2 = %s %n R2adjusted = %s %n R = %s %n ", this.multiLinearRegression.rSquared(), this.multiLinearRegression.rSquaredAdjusted(), this.multiLinearRegression.mqr());
        }
        return "123123123";
    }


    private String hypothesisTests(double aParemeterSignificance, double aTestParameter, double bParameterSignificance, double bTestParameter) {
        if (this.multiLinearRegression == null && this.linearRegression != null) {
            return String.format("%nHypothesis tests for regression coefficients \n" +
                    "H0: a=%s (b=%s) H1: a<>%s (b<>%s) %n " +
                    "tobs(a)=%.4f tobs(b)=%.4f %n" +
                    "Decison:%n" +
                    "Regarding the test for the a parameter h0 is %s %n" +
                    "Regarding the test for the b parameter h0 is %s %n", aTestParameter, bTestParameter, aTestParameter, bTestParameter, this.linearRegression.testCalculationforAparameter(aTestParameter), this.linearRegression.testCalculationforBparameter(bTestParameter), isARejectedOrNot(aTestParameter, aParemeterSignificance), isBRejectedOrNot(bTestParameter, bParameterSignificance));

        } else if (this.multiLinearRegression != null && this.linearRegression == null) {
            return String.format("%nHypothesis tests for regression coefficients \n" +
                            "H0: b2=%s (b2=%s) H1: b2<>%s (b2<>%s) %n " +
                            "tobs(a)=%.4f tobs(b)=%.4f %n" +
                            "Decison:%n" +
                            "Regarding the test for the a parameter h0 is %s %n" +
                            "Regarding the test for the b parameter h0 is %s %n", aTestParameter, bTestParameter, aTestParameter, bTestParameter, this.multiLinearRegression
                    , this.multiLinearRegression.coeficientRegressionTestAuxiliaryCalculus(bTestParameter), isARejectedOrNot(aTestParameter, aParemeterSignificance), isBRejectedOrNot(bTestParameter, bParameterSignificance))
                    ;
        }
        return "1231321312";
    }


    private String Anova() {
        if (this.multiLinearRegression == null && this.linearRegression != null) {
            return String.format("%nSignificance model with Anova \n" +
                    "H0: b=0  H1:b<>0 \n %15s %12s %12s %12s \n" +
                    "Regression %8.4f %12.4f %12.4f\t%12.4f \nResidual %10.4f  %11.4f %12.4f \n Total %12.4f %12.4f", "df", "SS", "MS", "F", (double) linearRegression.getRegressionDF(), linearRegression.calculateAnovaSR(), linearRegression.calculateAnovaMSR(), linearRegression.calculateTestF(), (double) linearRegression.getResidualDF(), linearRegression.calculateAnovaSE(), linearRegression.calculateAnovaMSE(), linearRegression.calculateAnovaSE() + 1, linearRegression.calculateAnovaST());
        } else if (this.multiLinearRegression != null && this.linearRegression == null) {
            return String.format("%nSignificance model with Anova \n" +
                    "H0: b=0  H1:b<>0 \n %15s %12s %12s %12s \n" +
                    "Regression %8.4f %12.4f %12.4f\t%12.4f \nResidual %10.4f  %11.4f %12.4f \n Total %12.4f %12.4f", "df", "SS", "MS", "F", multiLinearRegression.regressionDegreesOfFreedom(), multiLinearRegression.sqr(), multiLinearRegression.sqt(), multiLinearRegression.regressionDegreesOfFreedom(), multiLinearRegression.errorDegreesOfFreedom(),multiLinearRegression.mqe() + 1 , multiLinearRegression.mqr(), multiLinearRegression.totalDegreesOfFreedom(), multiLinearRegression.testStatisticF());
        }
        return "1231t";
    }


    private String decisionF(double fTestDecisionSignificance) {
        if (this.multiLinearRegression == null && this.linearRegression != null) {
            return String.format("Decision: f %n" +
                    "0 > f%.2f,(%d.%d)=%.4f%n" +
                    "%s", fTestDecisionSignificance, linearRegression.getRegressionDF(), linearRegression.getResidualDF(), linearRegression.getFSnedcorFromTable(fTestDecisionSignificance), isFRejectedOrNot(fTestDecisionSignificance));
        }
        return String.format("Decision: f %n" +
                "0 > f%.2f,(%d.%d)=%.4f%n" +
                "%s", fTestDecisionSignificance, linearRegression.getRegressionDF(), linearRegression.getResidualDF(), linearRegression.getFSnedcorFromTable(fTestDecisionSignificance), isFRejectedOrNot(fTestDecisionSignificance));
    }

    private static String predictionValues(String date, int positiveCases, double estimatedCases, String intervals) {
        return String.format("%n%4s %15d  %43.4f %45s %n", date, positiveCases, estimatedCases, intervals);
    }

    private static String linePredictionValues() {
        //confidencenceLevel = getConfidenceLevel();
        return String.format("%n%n%s %40s %40s %30s", "Date", "Number of OBSERVED positive cases", "Number of ESTIMATED positive cases ", confidencenceLevel + "% intervals \n");
    }

    private String isARejectedOrNot(double TestParameter, double TestSignificance) {
        if (linearRegression != null && multiLinearRegression == null) {
            if (this.linearRegression.testCalculationforAparameter(TestParameter) > this.linearRegression.getTStudentFromTable(TestSignificance)) {
                return "rejected";
            } else return "not rejected";
        } else return "multilinear,";
    }

    private String isBRejectedOrNot(double TestParameter, double TestSignificance) {
        if (linearRegression != null && multiLinearRegression == null) {
            if (this.linearRegression.testCalculationforBparameter(TestParameter) > this.linearRegression.getTStudentFromTable(TestSignificance)) {
                return "rejected";
            } else return "not rejected";
        } else return "multilinear";
    }

    private String isFRejectedOrNot(double FTestSignificance) {
        if (linearRegression != null && multiLinearRegression == null) {
            if (linearRegression.calculateTestF() > linearRegression.getFSnedcorFromTable(FTestSignificance)) {
                return String.format("Reject H0 %n The regression model is significant %n");
            } else return String.format("Not Reject H0 %n The regression model is not significant %n\"");
        } else if (linearRegression == null && multiLinearRegression != null) {
            if (linearRegression.calculateTestF() > linearRegression.getFSnedcorFromTable(FTestSignificance)) {
                return String.format("Reject H0 %n The regression model is significant %n");
            } else return String.format("Not Reject H0 %n The regression model is not significant %n\"");
        }
        return "1t31t";
    }

    public String printFinalTable(Calendar olderDate, Calendar newerDate, double confidenceIntervalSignificance) {
        int i = 1;
        StringBuilder resultString = new StringBuilder();
        Calendar olderDateUsed = (Calendar) olderDate.clone();
        Calendar newerDateUsed = (Calendar) newerDate.clone();

        do {
            if (olderDateUsed.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                if (i != 1) {
                    resultString.append(String.format("%d/%d/%d %30.2f %30.2f %30.2f-%.2f %n", olderDateUsed.get(Calendar.DAY_OF_MONTH), olderDateUsed.get(Calendar.MONTH), olderDateUsed.get(Calendar.YEAR), receivedYData[i - 1], linearRegression.predict(receivedX1Data[i - 1]), linearRegression.predict(receivedX1Data[i - 1]) - linearRegression.delta(confidenceIntervalSignificance, receivedX1Data[i - 1]), linearRegression.predict(receivedX1Data[i - 1]) + linearRegression.delta(confidenceIntervalSignificance, receivedX1Data[i - 1])));
                }
                i++;
            }
            olderDateUsed.add(Calendar.DAY_OF_MONTH, 1);

        } while (newerDateUsed.after(olderDateUsed));
        return resultString.toString();
    }

    private LocalDate getLocalDate(Calendar calendar) {
        return LocalDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId()).toLocalDate();
    }

    public String getReportString(Calendar olderDate, Calendar newerDate, double aParameterSignificance,
                                  double aTestParameter, double bTestSignificance, double bTestParameter, double fTestSignificance,
                                  double confidenceIntervalSignificance) {

        return regressionModelLine() + otherStatistics() + hypothesisTests(aParameterSignificance, aTestParameter, bTestSignificance, bTestParameter) + Anova() + decisionF(fTestSignificance) + linePredictionValues() + printFinalTable(olderDate, newerDate, confidenceIntervalSignificance);
    }


    public static void main(String[] args) {
        double[] arrX1 = {825, 215, 1070, 550, 480, 920, 1350, 325, 670, 1215};
        double[] arrX2 = {1000, 215, 1070, 550, 480, 920, 1350, 325, 670, 1215};
        double[] arrY1 = {3.5, 1, 4, 2, 1, 3, 4.5, 1.5, 3, 5};
        MultiLinearRegression multiLinearRegression = new MultiLinearRegression(arrX1, arrX2, arrY1);
        NHSReport nhsReport = new NHSReport(multiLinearRegression);
        Calendar data1 = Calendar.getInstance();
        Calendar data2 = Calendar.getInstance();
        data1.set(2021, 5, 3);
        data2.set(2021, 5, 12);
//       System.out.println(nhsReport.regressionModelLine());
//         System.out.println(nhsReport.otherStatistics());
//       System.out.println(nhsReport.hypothesisTests(0.05,0,0.05,0));
//        System.out.println(nhsReport.Anova());
//        System.out.println(nhsReport.decisionF(0.05));
//        System.out.println(nhsReport.printFinalTable(data1, data2, 0.95));
//        System.out.println(hypothesisTests(1221, 1221, 1212, 1115.2, 9999, 2222, 2222));
//        System.out.println(linePredictionValues());
//        System.out.println(predictionValues("13-05-2021",13,25.2,"13.16-23.48"));
    }
}


