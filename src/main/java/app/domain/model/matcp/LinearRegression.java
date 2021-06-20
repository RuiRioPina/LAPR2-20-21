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
    private final double intercept;



    private final double slope;
    private final double r2;
    private final double svar0, svar1;
    private final double n;
    private final double[] arrayX;
    private final double[] arrayY;
    private final static double k = 1;
    private final static double SR_DEGREES_OF_FREEDOM=1;
    private final double sEDegreesOfFreedom;

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
        double xxbar = 0.0;
        double yybar = 0.0;
        double xybar = 0.0;
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
        this.sEDegreesOfFreedom =arrayX.length-2;
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
        s.append(String.format("^y=%.4fx+%.4f", slope(), intercept()));
        return s.toString();
    }

    /**
     * Returns the r2 adjusted value of the linear regression model.
     * @return r2 adjusted value
     */
    public double R2Adjusted() {
        double topRow = (1 - this.R2()) * (this.n - 1);
        double bottomRow = this.n - k - 1;
        return 1 - (topRow / bottomRow);
    }

    /**
     * Returns the r value of the linear regression model.
     * @return r value
     */
    public double R(){
        return Math.sqrt(this.R2());
    }

    /**
     * Gives a double array with the y values recalculated through the linear regression model.
     * @return double with the y values recalculated.
     */
    private double[] arrayYthroughRegressionModel() {
        double[] yThroughRegressionModel = new double[this.arrayY.length];
        for (int i = 0; i < yThroughRegressionModel.length; i++) {
            yThroughRegressionModel[i] = this.slope * this.arrayX[i] + this.intercept;
        }
        return yThroughRegressionModel;
    }

    /**
     * Calculates the S value of the linear regression model.
     * @return the s value.
     */
    private double calculateS() {
        double total = 0;
        double[] yThroughRegressionModelMinusGivenYarray = new double[this.arrayY.length];
        for (int i = 0; i < yThroughRegressionModelMinusGivenYarray.length; i++) {
            yThroughRegressionModelMinusGivenYarray[i] = this.arrayYthroughRegressionModel()[i] - this.arrayY[i];
        }
        for (int i = 0; i < yThroughRegressionModelMinusGivenYarray.length; i++) {
            yThroughRegressionModelMinusGivenYarray[i] = Math.pow(yThroughRegressionModelMinusGivenYarray[i], 2);
            total += yThroughRegressionModelMinusGivenYarray[i];
        }
        return Math.sqrt((double) 1 / (this.arrayX.length - 2) * (total));
    }

    /**
     * Calculates a hypothesis test for a certain A parameter .
     * @param aParameter - a Parametr to be used.
     * @return the result of the t observated for that parameter
     */
    public double testCalculationforAparameter(double aParameter) {
        double xxbar = xxBar();
        return Math.abs((this.intercept - aParameter) / (this.calculateS() * (Math.sqrt((double) 1 / this.arrayX.length + (Math.pow(arrayAverage(arrayX), 2) / xxbar)))));
    }

    /**
     * Calculates the xxBar  of the linear regression model.
     * @return xxBar value
     */
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

    /**
     * Calculates a hypothesis test for a certain b parameter .
     * @param bParameter - a Parametr to be used.
     * @return the result of the t observated for that parameter
     */
    public double testCalculationforBparameter(double bParameter) {
        double xxbar = xxBar();
        return Math.abs((this.slope - bParameter) / this.calculateS() * (Math.sqrt(xxbar)));
    }


    /**
     * Gets a t student value from the table using a significance.
     * @param significance desired significance
     * @return the t student value using that significance and the values of the degrees of freedom.
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

    /**
     * Calculates the average for an array of doubles
     * @param arr- array to be used.
     * @return average of an array.
     */
    private double arrayAverage(double[] arr) {
        double total = 0;
        for (int i = 0; i < arr.length; i++) {
            total += arr[i];
        }
        return total / arr.length;
    }

    /**
     * Calculates the Anova SR Value of the linear regression model.
     * @return Anova SR Value.
     */
    public double calculateAnovaSR() {
        double SRValue = 0;
        double[] yThroughRegressionModel = this.arrayYthroughRegressionModel();
        for (int i = 0; i < arrayY.length; i++) {
            SRValue += Math.pow(yThroughRegressionModel[i] - arrayAverage(arrayY), 2);
        }
        return SRValue;
    }

    /**
     * Calculates the Anova SE Value of the linear regression model.
     * @return Anova SE Value.
     */
    public double calculateAnovaSE() {
        double SEValue = 0;
        double[] yThroughRegressionModel = this.arrayYthroughRegressionModel();
        for (int i = 0; i < arrayY.length; i++) {
            SEValue += Math.pow(arrayY[i] - yThroughRegressionModel[i], 2);
        }
        return SEValue;
    }

    /**
     * Calculates the Anova ST Value of the linear regression model.
     * @return Anova ST Value.
     */
    public double calculateAnovaST(){
        return  calculateAnovaSR()+calculateAnovaSE();
    }

    /**
     * Calculates the Anova MSR Value of the linear regression model.
     * @return Anova MSR Value.
     */
    public double calculateAnovaMSR(){
        return  calculateAnovaSR()/1;
    }

    /**
     * Calculates the Anova MSE Value of the linear regression model.
     * @return Anova MSE Value.
     */
    public double calculateAnovaMSE(){
        return calculateAnovaSE()/(arrayX.length-2);
    }

    /**
     * Calculates the Anova F Value of the linear regression model.
     * @return Anova F Value.
     */
    public double calculateTestF(){
        return calculateAnovaMSR()/calculateAnovaMSE();
    }

    /**
     * Gives the FSnedcor from the table using the SR degrees of freedom and the SE degrees of freedom and significance.
     * @param significance the desired significance.
     * @return the FSnedcor from the table.
     */
    public double getFSnedcorFromTable(double significance){
        FDistribution fd= new FDistribution(SR_DEGREES_OF_FREEDOM, sEDegreesOfFreedom);
        double alphaFD= significance;
        double critFD= fd.inverseCumulativeProbability(1- alphaFD);
        return critFD;

    }

    /**
     * Calculates the Anova residual DF Value of the linear regression model.
     * @return residual DFValue.
     */
    public int getResidualDF(){
        return arrayX.length-2;
    }

    /**
     * Calculates a delta value for a certain X value with a significance value
     * @param significance the desired significance
     * @param x the x value to be predicted.
     * @return
     */
    public double delta(double significance,double x){
        return getTStudentFromTable(significance)*calculateS()*Math.sqrt(((double) 1/arrayX.length)+(Math.pow(x-arrayAverage(arrayX),2)/xxBar()));
    }

    /**
     * Calculates the Regression DF Value of the linear regression model.
     * @return Regression DF Value.
     */
    public int getRegressionDF(){
        return  1;
    }

}