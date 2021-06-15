package app.domain.model.matcp;

import org.apache.commons.math3.distribution.FDistribution;
import org.apache.commons.math3.distribution.TDistribution;

/**
 * The code LinearRegression class performs a simple linear regression
 * on an set of n data points (y_i, x_i).
 * That is, it fits a straight line y = alpha + beta * x,
 * (where y is the response variable, x is the predictor variable,
 * alpha is the y-intercept, and beta is the slope)
 * that minimizes the sum of squared residuals of the linear regression model.
 * It also computes associated statistics, including the coefficient of
 * determination R^2 and the standard deviation of the
 * estimates for the slope and y-intercept.
 */

public class LinearRegression {
    private final double intercept, slope;
    private final double r2;
    private final double svar0, svar1;
    private final double n;
    private final double[] arrayX;
    private final double[] arrayY;
    private final static double k = 1;
    private final static double SR_DEGREES_OF_FREEDOM=1;
    private final double SEDegreesOfFreedom;

    /**
     * Performs a linear regression on the data points (y[i], x[i]).
     *
     * @param x the values of the predictor variable
     * @param y the corresponding values of the response variable
     * @throws IllegalArgumentException if the lengths of the two arrays are not equal
     */
    public LinearRegression(double[] x, double[] y) {
        this.arrayX = x;
        this.arrayY = y;
        if (x.length != y.length) {
            throw new IllegalArgumentException("array lengths are not equal");
        }
        int n = x.length;
        this.n = n;
        // first pass
        double sumx = 0.0, sumy = 0.0, sumx2 = 0.0;
        for (int i = 0; i < n; i++) {
            sumx += x[i];
            sumx2 += x[i] * x[i];
            sumy += y[i];
        }
        double xbar = sumx / n;
        double ybar = sumy / n;

        // second pass: compute summary statistics
        double xxbar = 0.0, yybar = 0.0, xybar = 0.0;
        for (int i = 0; i < n; i++) {
            xxbar += (x[i] - xbar) * (x[i] - xbar);
            yybar += (y[i] - ybar) * (y[i] - ybar);
            xybar += (x[i] - xbar) * (y[i] - ybar);
        }
        slope = xybar / xxbar;
        intercept = ybar - slope * xbar;

        // more statistical analysis
        double rss = 0.0;      // residual sum of squares
        double ssr = 0.0;      // regression sum of squares
        for (int i = 0; i < n; i++) {
            double fit = slope * x[i] + intercept;
            rss += (fit - y[i]) * (fit - y[i]);
            ssr += (fit - ybar) * (fit - ybar);
        }

        int degreesOfFreedom = n - 2;
        r2 = ssr / yybar;
        double svar = rss / degreesOfFreedom;
        svar1 = svar / xxbar;
        svar0 = svar / n + xbar * xbar * svar1;
        this.SEDegreesOfFreedom=arrayX.length-2;
    }

    /**
     * Returns the y-intercept alpha of the best of the best-fit line y = alpha + beta * x.
     *
     * @return the y-intercept alpha of the best-fit line y = alpha + beta * x
     */
    public double intercept() {
        return intercept;
    }

    /**
     * Returns the slope beta of the best of the best-fit line y = alpha + beta * x.
     *
     * @return the slope beta of the best-fit line y = alpha + beta * x
     */
    public double slope() {
        return slope;
    }

    /**
     * Returns the coefficient of determination R^2.
     *
     * @return the coefficient of determination R^2,
     * which is a real number between 0 and 1
     */
    public double R2() {
        return r2;
    }

    /**
     * Returns the standard error of the estimate for the intercept.
     *
     * @return the standard error of the estimate for the intercept
     */
    public double interceptStdErr() {
        return Math.sqrt(svar0);
    }

    /**
     * Returns the standard error of the estimate for the slope.
     *
     * @return the standard error of the estimate for the slope
     */
    public double slopeStdErr() {
        return Math.sqrt(svar1);
    }

    /**
     * Returns the expected response y given the value of the predictor
     * variable x.
     *
     * @param x the value of the predictor variable
     * @return the expected response y given the value of the predictor
     * variable x
     */
    public double predict(double x) {
        return slope * x + intercept;
    }

    /**
     * Returns a string representation of the simple linear regression model.
     *
     * @return a string representation of the simple linear regression model,
     * including the best-fit line and the coefficient of determination
     * R^2
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(String.format("%.2f n + %.2f", slope(), intercept()));
        s.append("  (R^2 = " + String.format("%.3f", R2()) + ")");
        return s.toString();
    }

    public double r2Adjusted() {
        double topRow = (1 - this.R2()) * (this.n - 1);
        double bottomRow = this.n - k - 1;
        return 1 - (topRow / bottomRow);
    }

    public double[] arrayYthroughRegressionModel() {
        double[] yThroughRegressionModel = new double[this.arrayY.length];
        for (int i = 0; i < yThroughRegressionModel.length; i++) {
            yThroughRegressionModel[i] = this.slope * this.arrayX[i] + this.intercept;
        }
        return yThroughRegressionModel;
    }

    public double calculateS() {
        double total = 0;
        double[] yThroughRegressionModelMinusGivenYarray = new double[this.arrayY.length];
        for (int i = 0; i < yThroughRegressionModelMinusGivenYarray.length; i++) {
            yThroughRegressionModelMinusGivenYarray[i] = this.arrayYthroughRegressionModel()[i] - this.arrayY[i];
        }
        for (int i = 0; i < yThroughRegressionModelMinusGivenYarray.length; i++) {
            yThroughRegressionModelMinusGivenYarray[i] = Math.pow(yThroughRegressionModelMinusGivenYarray[i], 2);
            total += yThroughRegressionModelMinusGivenYarray[i];
        }
        double resultadoFinal = Math.sqrt((double) 1 / (this.arrayX.length - 2) * (total));
        return resultadoFinal;
    }

    public double testCalculationforAparameter(double aParameter) {
        double xxbar = xxBar();
        return ((this.intercept - aParameter) / (this.calculateS() * (Math.sqrt((double) 1 / this.arrayX.length + (Math.pow(arrayAverage(arrayX), 2) / xxbar)))));
    }

    public double xxBar() {
        double xTotal = 0;
        double xSquaredTotal = 0;
        for (double x : this.arrayX) {
            xTotal += x;
        }
        double xAverage = xTotal / this.arrayX.length;
        double[] arrXsquared = new double[this.arrayX.length];
        for (int i = 0; i < this.arrayX.length; i++) {
            arrXsquared[i] = Math.pow(arrayX[i], 2);
            xSquaredTotal += arrXsquared[i];
        }
        double xxbar = xSquaredTotal - (this.arrayX.length * Math.pow(xAverage, 2));
        return xxbar;
    }

    public double testCalculationforBparameter(double bParameter) {
        double xxbar = xxBar();
        return (this.slope - bParameter) / this.calculateS() * (Math.sqrt(xxbar));
    }
/*
significance vem em decimal(ie:intervalo de confiança 95%=significance=0.05
 */
    public double getTStudentFromTable(double significance) {
        TDistribution td = new TDistribution(arrayX.length - 2);
        double alphaTD = significance / 2;
        double critTD = 0;
        if (alphaTD > 0.5) {
            critTD = td.inverseCumulativeProbability(alphaTD);
        } else {
            critTD = td.inverseCumulativeProbability(1 - alphaTD);

        }
        return critTD;
    }

    public boolean isAParameterHypothesisRejected(double significance, double aParameter) {
        TDistribution td = new TDistribution(arrayX.length - 2);
        return true;
    }

    private double arrayAverage(double[] arr) {
        double Total = 0;
        for (int i = 0; i < arr.length; i++) {
            Total += arr[i];
        }
        double Average = Total / arr.length;
        return Average;
    }

    public double calculateAnovaSR() {
        double SRValue = 0;
        double[] yThroughRegressionModel = this.arrayYthroughRegressionModel();
        for (int i = 0; i < arrayY.length; i++) {
            SRValue += Math.pow(yThroughRegressionModel[i] - arrayAverage(arrayY), 2);
        }
        return SRValue;
    }
    public double calculateAnovaSE() {
        double SEValue = 0;
        double[] yThroughRegressionModel = this.arrayYthroughRegressionModel();
        for (int i = 0; i < arrayY.length; i++) {
            SEValue += Math.pow(arrayY[i] - yThroughRegressionModel[i], 2);
        }
        return SEValue;
    }
    public double calculateAnovaST(){
        return  calculateAnovaSR()+calculateAnovaSE();
    }
    public double calculateAnovaMSR(){
        return  calculateAnovaSR()/1;
    }
    public double calculateAnovaMSE(){
        return calculateAnovaSE()/(arrayX.length-2);
    }
    public double calculateTestF(){
        return calculateAnovaMSR()/calculateAnovaMSE();
    }
    public double getFSnedcorFromTable(double significance){
        FDistribution fd= new FDistribution(SR_DEGREES_OF_FREEDOM,SEDegreesOfFreedom);
        double alphaFD= significance;
        double critFD= fd.inverseCumulativeProbability(1- alphaFD);
        return critFD;

    }
    public double delta(double significance,double x){
        return getTStudentFromTable(significance)*calculateS()*Math.sqrt(((double) 1/arrayX.length)+(Math.pow(x-arrayAverage(arrayX),2)/xxBar()));
    }
}