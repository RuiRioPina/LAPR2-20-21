package app.domain.model.matcp;

import org.apache.commons.math3.distribution.FDistribution;
import org.apache.commons.math3.distribution.TDistribution;




public class MultiLinearRegression {
    private final double[] arrayX1;
    private final double[] arrayX2;
    private final double[] arrayY;
    private final double b0;
    private final double b1;
    private final double b2;
    private static final int NUMBER_OF_VARIABLES = 3;
    private final double[][] bColumn = new double[3][1];
    private final double[][] xMatrix;
    private final double[][] yVector;
    private double[][] xMatrixInverse;
    double[][] xMatrixTransposed;
    double[][] xMatrixTimesXMatrixTransposed;
    double[][] xMatrixTimesXMatrixTransposedInverse;

    /**
     *  toString method of the MultiLinear Regresion
     * @return A string of the regression model.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(String.format("^y=%.4f+ %.4fx + %.4fx", b0, b1, b2));
        return s.toString();
    }


    /**
     * Constructor of the MultiLinearRegression
     * @param arrayX1 array with the first x variable values
     * @param arrayX2 array with the second x variable values
     * @param arrayY array with the y variable values
     */
    public MultiLinearRegression(double[] arrayX1, double[] arrayX2, double[] arrayY) {
        this.arrayX1 = arrayX1;
        this.arrayX2 = arrayX2;
        this.arrayY = arrayY;
        if (arrayX1.length != arrayX2.length && arrayX1.length != arrayY.length) {
            throw new IllegalArgumentException("array lengths are not equal");
        }
        double[][] xMatrix = new double[arrayX1.length][3];
        this.xMatrix = xMatrix;
        for (int i = 0; i < xMatrix.length; i++) {
            xMatrix[i][0] = 1;
            xMatrix[i][1] = arrayX1[i];
            xMatrix[i][2] = arrayX2[i];
        }
        xMatrixTransposed = transposeMatrix(xMatrix);
        xMatrixTimesXMatrixTransposed = multiplyMatrices(xMatrixTransposed, xMatrix);
        xMatrixTimesXMatrixTransposedInverse = invert(xMatrixTimesXMatrixTransposed);
        this.xMatrixInverse = xMatrixTimesXMatrixTransposedInverse;
        double[][] yVector = new double[arrayY.length][1];
        for (int i = 0; i < arrayY.length; i++) {
            yVector[i][0] = arrayY[i];
        }
        this.yVector = yVector;
        double[][] xMatrixTransposedTimesYVector = multiplyMatrices(xMatrixTransposed, yVector);
        double[][] finalRegressionModelMatrix = multiplyMatrices(xMatrixTimesXMatrixTransposedInverse, xMatrixTransposedTimesYVector);
        b0 = finalRegressionModelMatrix[0][0];
        b1 = finalRegressionModelMatrix[1][0];
        b2 = finalRegressionModelMatrix[2][0];
        bColumn[0][0] = b0;
        bColumn[1][0] = b1;
        bColumn[2][0] = b2;
    }




    /**
     * taken from https://stackoverflow.com/questions/15449711/transpose-double-matrix-with-a-java-function/15497288
     * Gives the transposed version of a matrix
     * @param m matrix to be transposed
     * @return the original matrix but transposed
     */
    private double[][] transposeMatrix(double[][] m) {
        double[][] temp = new double[m[0].length][m.length];
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[0].length; j++)
                temp[j][i] = m[i][j];
        return temp;
    }

    /**
     * from https://www.sanfoundry.com/java-program-find-inverse-matrix/
     * returns the inverse matrix of a matrix
     * @param a the matrix that will be inverted
     * @return the inverted matrix
     */
    private double[][] invert(double[][] a) {
        int n = a.length;
        double[][] x = new double[n][n];
        double[][] b = new double[n][n];
        int[] index = new int[n];
        for (int i = 0; i < n; ++i)
            b[i][i] = 1;

        // Transform the matrix into an upper triangle
        gaussian(a, index);

        // Update the matrix b[i][j] with the ratios stored
        for (int i = 0; i < n - 1; ++i)
            for (int j = i + 1; j < n; ++j)
                for (int k = 0; k < n; ++k)
                    b[index[j]][k]
                            -= a[index[j]][i] * b[index[i]][k];

        // Perform backward substitutions
        for (int i = 0; i < n; ++i) {
            x[n - 1][i] = b[index[n - 1]][i] / a[index[n - 1]][n - 1];
            for (int j = n - 2; j >= 0; --j) {
                x[j][i] = b[index[j]][i];
                for (int k = j + 1; k < n; ++k) {
                    x[j][i] -= a[index[j]][k] * x[k][i];
                }
                if (a[index[j]][j] != 0) {
                    x[j][i] /= a[index[j]][j];
                }
            }
        }
        return x;
    }


    private void gaussian(double[][] a, int[] index) {
        int n = index.length;
        double c[] = new double[n];

        // Initialize the index
        for (int i = 0; i < n; ++i)
            index[i] = i;

        // Find the rescaling factors, one from each row
        for (int i = 0; i < n; ++i) {
            double c1 = 0;
            for (int j = 0; j < n; ++j) {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1) c1 = c0;
            }
            c[i] = c1;
        }

        // Search the pivoting element from each column
        int k = 0;
        for (int j = 0; j < n - 1; ++j) {
            double pi1 = 0;
            for (int i = j; i < n; ++i) {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1) {
                    pi1 = pi0;
                    k = i;
                }
            }

            // Interchange rows according to the pivoting order
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i = j + 1; i < n; ++i) {
                double pj = a[index[i]][j] / a[index[j]][j];

                // Record pivoting ratios below the diagonal
                a[index[i]][j] = pj;

                // Modify other elements accordingly
                for (int l = j + 1; l < n; ++l)
                    a[index[i]][l] -= pj * a[index[j]][l];
            }
        }
    }

    private double multiplyMatricesCell(double[][] firstMatrix, double[][] secondMatrix, int row, int col) {
        double cell = 0;
        for (int i = 0; i < secondMatrix.length; i++) {
            cell += firstMatrix[row][i] * secondMatrix[i][col];
        }
        return cell;
    }

    /**
     * from https://www.baeldung.com/java-matrix-multiplication
     * Gives the matrix result of a matrix multiplication
     * @param firstMatrix the first matrix on the multiplication
     * @param secondMatrix the second matrix on the multiplication
     * @return the result of the matrix multiplication
     */
    private double[][] multiplyMatrices(double[][] firstMatrix, double[][] secondMatrix) {
        double[][] result = new double[firstMatrix.length][secondMatrix[0].length];

        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                result[row][col] = multiplyMatricesCell(firstMatrix, secondMatrix, row, col);
            }
        }

        return result;
    }

    /**
     * Calculates the average for an array of doubles
     * @param arr- array to be used.
     * @return average of an array.
     */
    private double arrayAverage(double[] arr) {
        double total = 0;
        for (double v : arr) {
            total += v;
        }
        return total / arr.length;
    }
    /**
     * Calculates the Anova SQR Value of the linear regression model.
     * @return Anova SQR Value.
     */
    public double sqr() {
        double[][] bTransposed = transposeMatrix(bColumn);
        double[][] xMatrixTransposed = transposeMatrix(xMatrix);
        double[][] finalMatrix = multiplyMatrices(multiplyMatrices(bTransposed, xMatrixTransposed), yVector);
        double number = finalMatrix[0][0];
        return number - (arrayX1.length * Math.pow(arrayAverage(arrayY), 2));

    }
    /**
     * Calculates the Anova SQE Value of the linear regression model.
     * @return Anova SQE Value.
     */
    public double sqe() {
        double[][] yVectorTransposed = transposeMatrix(yVector);
        double[][] bTransposed = transposeMatrix(bColumn);
        double[][] xMatrixTransposed = transposeMatrix(xMatrix);
        double firstNumber = multiplyMatrices(yVectorTransposed, yVector)[0][0];
        double secondNumber = multiplyMatrices(multiplyMatrices(bTransposed, xMatrixTransposed), yVector)[0][0];
        return firstNumber - secondNumber;
    }
    /**
     * Calculates the Anova SQT Value of the linear regression model.
     * @return Anova SQT Value.
     */
    public double sqt() {
        return sqe() + sqr();
    }
    /**
     * Calculates the Anova regression degrees of freedom Value of the linear regression model.
     * @return regression degrees of freedom Value.
     */
    public double regressionDegreesOfFreedom() {
        return (double) NUMBER_OF_VARIABLES - 1;
    }

    /**
     * Calculates the Anova error degrees of freedom Value of the linear regression model.
     * @return error degrees of freedom Value.
     */
    public double errorDegreesOfFreedom() {
        return arrayY.length - regressionDegreesOfFreedom() - 1;
    }
    /**
     * Calculates the Anova total degrees of freedom Value of the linear regression model.
     * @return total degrees of freedom Value.
     */
    public double totalDegreesOfFreedom() {
        return regressionDegreesOfFreedom() + errorDegreesOfFreedom();
    }
    /**
     * Calculates the Anova MQR Value of the linear regression model.
     * @return Anova MQR Value.
     */
    public double mqr() {
        return sqr() / regressionDegreesOfFreedom();
    }
    /**
     * Calculates the Anova MQE Value of the linear regression model.
     * @return Anova MQE Value.
     */
    public double mqe() {
        return sqe() / errorDegreesOfFreedom();
    }
    /**
     * Calculates the Anova F test Value of the linear regression model.
     * @return Anova F test Value.
     */
    public double testStatisticF() {
        return mqr() / mqe();
    }

    /**
     * Gives the FSnedcor from the table using the SR degrees of freedom and the SE degrees of freedom and significance.
     * @param significance the desired significance.
     * @return the FSnedcor from the table.
     */
    public double getFSnedcorFromTable(double significance) {
        FDistribution fd = new FDistribution(regressionDegreesOfFreedom(), errorDegreesOfFreedom());
        return fd.inverseCumulativeProbability(1 - significance);

    }

    /**
     *  gets the r2 value of the linear regression model.
     * @return r2 value.
     */
    public double r2() {
        return sqr() / sqt();
    }

    /**
     * Calculates the r2 adjusted of the linear regression model.
     * @return r2adjusted value
     */
    public double r2Adjusted() {
        return 1 - ((double) (arrayY.length - 1) / errorDegreesOfFreedom()) * (1 - r2());
    }

    /**
     * Calculates for the coefficient Regression Auxiliary equation using a significance
     * @param significance significance to be used
     * @return coefficient Regression Auxiliary value.
     */
    public double coeficientRegressionTestAuxiliaryCalculus(double significance) {
        return getTStudentFromTable(significance) * Math.sqrt(mqe() * xMatrixInverse[xMatrixInverse.length - 1][xMatrixInverse.length - 1]);
    }

    /**
     * Gets a t student value from the table using a significance.
     * @param significance desired significance
     * @return the t student value using that significance and the values of the degrees of freedom.
     */
    public double getTStudentFromTable(double significance) {
        TDistribution td = new TDistribution(errorDegreesOfFreedom());
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
     * Calculates a delta value for  certain X1 and X2 value with a significance value
     * @param x1 the x1 value to be predicted.
     * @param x2 the x2 value to be predicted.
     * @return the predicted value.
     */
    public double predict(double x1, double x2) {
        double[][] auxiliaryLine = new double[1][NUMBER_OF_VARIABLES];
        auxiliaryLine[0][0] = 1;
        auxiliaryLine[0][1] = x1;
        auxiliaryLine[0][2] = x2;
        return multiplyMatrices(auxiliaryLine, bColumn)[0][0];
    }

    /**
     * Calculates the confidence interval value using a predicted value and a significance
     * @param significance desired significance
     * @param x1 the x1 value used to make the prediction
     * @param x2 the x2 value used to make the prediction
     * @return the  confidence interval value for a predicted value
     */
    public double expectedValueTestConfidenceIntervalAuxiliaryCalculus(double significance, double x1, double x2) {
        double[][] auxiliaryLine = new double[1][NUMBER_OF_VARIABLES];
        auxiliaryLine[0][0] = 1;
        auxiliaryLine[0][1] = x1;
        auxiliaryLine[0][2] = x2;
        double[][] auxiliaryLineTransposed = transposeMatrix(auxiliaryLine);
        double t = getTStudentFromTable(significance);
        double resultOfMatrixMultiplication = multiplyMatrices(multiplyMatrices(auxiliaryLine, xMatrixInverse), auxiliaryLineTransposed)[0][0];
        return t * Math.sqrt(mqe() * resultOfMatrixMultiplication);
    }

    /**
     * Calculates the hypothesis test for the B0 coeffiecient parameter
     * @return test for the B0 coefficient parameter
     */
    public double hypothesisB0() {
        return b0 / Math.sqrt(mqe() * xMatrixTimesXMatrixTransposedInverse[0][0]);
    }
    /**
     * Calculates the hypothesis test for the B1 coeffiecient parameter
     * @return test for the B1 coefficient parameter
     */
    public double hypothesisB1() {
        return b1 / Math.sqrt(mqe() * xMatrixTimesXMatrixTransposedInverse[1][1]);
    }
    /**
     * Calculates the hypothesis test for the B2 coeffiecient parameter
     * @return test for the B2 coefficient parameter
     */
    public double hypothesisB2() {
        return b2 / Math.sqrt(mqe() * xMatrixTimesXMatrixTransposedInverse[2][2]);
    }

    /**
     * Calculates the r value of the linear regression model
     * @return the r value of the linear regression model.
     */

    public double r() {
        return Math.sqrt(this.r2());
    }




}


