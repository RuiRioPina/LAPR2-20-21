package app.domain.model.matcp;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MultiLinearRegressionTest {
    double[] x;
    double[] x1;
    double[] y;

    @Before
    public void setUp() throws Exception {
//        x = new double[]{4, 5, 5.5, 7, 6, 8.5, 9};
//        x1 = new double[]{36, 33, 37, 37, 34, 38, 39};
//        y = new double[]{4, 4.5, 5, 6.5, 7, 8, 8.5};
        x = new double[]{80, 93, 100, 82, 90, 99, 81, 96, 94, 93, 97, 95};
        x1 = new double[]{8, 9, 10, 12, 11, 8, 8, 10, 12, 11, 13, 11};
        y = new double[]{2256, 2340, 2426, 2293, 2330, 2368, 2250, 2409, 2364, 2379, 2440, 2364};
    }

    @Test
    public void testSqr() {
        MultiLinearRegression multiLinearRegression = new MultiLinearRegression(x, x1, y);
        double actual = multiLinearRegression.sqr();
        double expected = 38223.5606;
        assertEquals(expected, actual, 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void differentSizeArrays() {
        double[] t = new double[]{80, 93, 100, 82, 90, 99, 81, 96, 94, 93, 97, 95};
        double[] t1 = new double[]{8, 9, 10, 12, 11, 8, 8, 10, 12, 11, 13};
        double[] s = new double[]{2256, 2368, 2250, 2409, 2364, 2379, 2440, 2364};
        MultiLinearRegression multiLinearRegression = new MultiLinearRegression(t, t1, s);
    }

    @Test
    public void testSqe() {
        MultiLinearRegression multiLinearRegression = new MultiLinearRegression(x, x1, y);
        double actual = multiLinearRegression.sqe();
        double expected = 3245.3561;
        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void testSqt() {
        MultiLinearRegression multiLinearRegression = new MultiLinearRegression(x, x1, y);
        double actual = multiLinearRegression.sqt();
        double expected = 41468.9167;
        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void testRegressionDegreesOfFreedom() {
        MultiLinearRegression multiLinearRegression = new MultiLinearRegression(x, x1, y);
        double actual = multiLinearRegression.regressionDegreesOfFreedom();
        double expected = 2;
        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void testErrorDegreesOfFreedom() {
        MultiLinearRegression multiLinearRegression = new MultiLinearRegression(x, x1, y);
        double actual = multiLinearRegression.errorDegreesOfFreedom();
        double expected = 9.0;
        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void testTotalDegreesOfFreedom() {
        MultiLinearRegression multiLinearRegression = new MultiLinearRegression(x, x1, y);
        double actual = multiLinearRegression.totalDegreesOfFreedom();
        double expected = 11.0;
        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void testMqr() {
        MultiLinearRegression multiLinearRegression = new MultiLinearRegression(x, x1, y);
        double actual = multiLinearRegression.mqr();
        double expected = 19111.780276548117;
        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void testMqe() {
        MultiLinearRegression multiLinearRegression = new MultiLinearRegression(x, x1, y);
        double actual = multiLinearRegression.mqe();
        double expected = 360.5951237289442;
        assertEquals(expected, actual, 0.001);

    }

    @Test
    public void testTestStatisticF() {
        MultiLinearRegression multiLinearRegression = new MultiLinearRegression(x, x1, y);
        double actual = multiLinearRegression.testStatisticF();
        double expected = 53.00066201370558;
        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void testGetFSnedcorFromTable() {
        MultiLinearRegression multiLinearRegression = new MultiLinearRegression(x, x1, y);
        double actual = multiLinearRegression.getFSnedcorFromTable(0.05);
        double expected = 4.256494729093686;
        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void testRSquared() {
        MultiLinearRegression multiLinearRegression = new MultiLinearRegression(x, x1, y);
        double actual = multiLinearRegression.r2();
        double expected = 0.9217400314638569;
        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void testRSquaredAdjusted() {
        MultiLinearRegression multiLinearRegression = new MultiLinearRegression(x, x1, y);
        double actual = multiLinearRegression.r2Adjusted();
        double expected = 0.904348927344714;
        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void testCoeficientRegressionTestAuxiliaryCalculus() {
        MultiLinearRegression multiLinearRegression = new MultiLinearRegression(x, x1, y);
        double actual = multiLinearRegression.coeficientRegressionTestAuxiliaryCalculus(0.05);
        double expected = 7.814112634940464;
        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void testGetTStudentFromTable() {
        MultiLinearRegression multiLinearRegression = new MultiLinearRegression(x, x1, y);
        double actual = multiLinearRegression.getTStudentFromTable(0.05);
        double expected = 2.262157162798239;
        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void testPredict() {
        MultiLinearRegression multiLinearRegression = new MultiLinearRegression(x, x1, y);
        double actual = multiLinearRegression.predict(2, 4);
        double expected = 1616.999403972731;
        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void testExpectedValueTestConfidenceIntervalAuxiliaryCalculus() {
        MultiLinearRegression multiLinearRegression = new MultiLinearRegression(x, x1, y);
        double actual = multiLinearRegression.expectedValueTestConfidenceIntervalAuxiliaryCalculus(0.05, 5, 6);
        double expected = 161.40431715459925;
        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void testToString() {
        MultiLinearRegression multiLinearRegression = new MultiLinearRegression(x, x1, y);
        String expected = "^y=1562,7301+ 7,5084x + 9,8131x";
        String actual = multiLinearRegression.toString().replace(".",",");
        assertEquals(expected, actual);
    }

    @Test
    public void hypothesisB0() {
        MultiLinearRegression multiLinearRegression = new MultiLinearRegression(x, x1, y);
        double expected = 20.293548351359536;
        double actual = multiLinearRegression.hypothesisB0();
        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void hypothesisB1() {
        MultiLinearRegression multiLinearRegression = new MultiLinearRegression(x, x1, y);
        double expected = 8.86700839876075;
        double actual = multiLinearRegression.hypothesisB1();
        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void hypothesisB2() {
        MultiLinearRegression multiLinearRegression = new MultiLinearRegression(x, x1, y);
        double expected = 2.840863945203475;
        double actual = multiLinearRegression.hypothesisB2();
        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void r() {
        MultiLinearRegression multiLinearRegression = new MultiLinearRegression(x, x1, y);
        double expected = 0.9600729302838701;
        double actual = multiLinearRegression.r();
        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void predict() {
        MultiLinearRegression multiLinearRegression = new MultiLinearRegression(x, x1, y);
        double expected = 1597.3731541140326;
        double expected1 = 1625.0209611450946;

        double actual1 = multiLinearRegression.predict(1.5,5.2);


        assertEquals(expected1, actual1, 0.001);
    }
}