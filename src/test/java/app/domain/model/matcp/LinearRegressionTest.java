package app.domain.model.matcp;

import app.controller.App;
import app.domain.model.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;


public class LinearRegressionTest {
    double[] x;
    double[] y;
    @Before
    public void setUp() throws Exception {
        x = new double[]{825, 215, 1070, 550, 480, 920, 1350, 325, 670, 1215};
        y = new double[]{3.5, 1, 4, 2, 1, 3, 4.5, 1.5, 3, 5};
    }

    @Test
    public void testIntercept() {
        LinearRegression linearRegression = new LinearRegression(x, y);
        double actual = linearRegression.intercept();
        double expected = 0.11812907401414696;
        Assert.assertEquals(expected, actual,0.001);
    }

    @Test  (expected = IllegalArgumentException.class)
    public void checkWheterItThrowsException() {
        double[] t = new double[]{825, 215, 1070, 550, 480, 920, 1350, 325, 670, 1215};
        double[] p = new double[]{3.5, 1, 4, 2, 1, 3, 4.5, 1.5, 3, };
        LinearRegression linearRegression = new LinearRegression(t, p);

    }

    @Test
    public void testSlope() {
        LinearRegression linearRegression = new LinearRegression(x, y);
        double actual = linearRegression.slope();
        double expected = 0.003585132448800332;
        Assert.assertEquals(expected, actual,0.001);
    }

    @Test
    public void testR2() {
        LinearRegression linearRegression = new LinearRegression(x, y);
        double actual = linearRegression.R2();
        double expected = 0.9004923770185123;
        Assert.assertEquals(expected, actual,0.001);
    }

    @Test
    public void testInterceptStdErr() {
        LinearRegression linearRegression = new LinearRegression(x, y);
        double actual = linearRegression.interceptStdErr();
        double expected = 0.35514770556413716;
        Assert.assertEquals(expected, actual,0.001);
    }

    @Test
    public void testSlopeStdErr() {
        LinearRegression linearRegression = new LinearRegression(x, y);
        double actual = linearRegression.slopeStdErr();
        double expected = 0.000421355208747327;
        Assert.assertEquals(expected, actual,0.001);
    }

    @Test
    public void testPredict() {
        LinearRegression linearRegression = new LinearRegression(x, y);
        double actual = linearRegression.predict(2);
        double expected = 0.12529933891174763;
        Assert.assertEquals(expected, actual,0.001);
    }

    @Test
    public void testTestToString() {
        LinearRegression linearRegression = new LinearRegression(x, y);
        String actual = linearRegression.toString();
        String expected = "^y=0,0036x+0,1181";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testR2Adjusted() {
        LinearRegression linearRegression = new LinearRegression(x, y);
        double actual = linearRegression.R2Adjusted();
        double expected = 0.888053924145827;
        Assert.assertEquals(expected, actual,0.001);

    }

    @Test
    public void testR() {
        LinearRegression linearRegression = new LinearRegression(x, y);
        double actual = linearRegression.R();
        double expected = 0.9489427680416308;
        Assert.assertEquals(expected, actual,0.001);
    }

    @Test
    public void testArrayYthroughRegressionModel() {
        LinearRegression linearRegression = new LinearRegression(x, y);
        double[] actual = linearRegression.arrayYthroughRegressionModel();
        double[] expected = {3.075863344274421, 0.8889325505062183, 3.954220794230502, 2.08995192085433, 1.8389926494383062, 3.416450926910452, 4.958057879894595, 1.2832971198742549, 2.520167814710369, 4.474064999306551};
        Assert.assertEquals(Arrays.toString(expected), Arrays.toString(actual));
    }

    @Test
    public void testCalculateS() {
        LinearRegression linearRegression = new LinearRegression(x, y);
        double actual = linearRegression.calculateS();
        double expected = 0.48002326971356946;
        Assert.assertEquals(expected, actual,0.001);
    }

    @Test
    public void testTestCalculationforAparameter() {
        LinearRegression linearRegression = new LinearRegression(x, y);
        double actual = linearRegression.testCalculationforAparameter(2);
        double expected = 5.298840162845991;
        Assert.assertEquals(expected, actual,0.001);
    }

    @Test
    public void testXxBar() {
        LinearRegression linearRegression = new LinearRegression(x, y);
        double actual = linearRegression.xxBar();
        double expected = 1297860.0;
        Assert.assertEquals(expected, actual,0.001);
    }

    @Test
    public void testTestCalculationforBparameter() {
        LinearRegression linearRegression = new LinearRegression(x, y);
        double actual = linearRegression.testCalculationforBparameter(0.02);
        double expected = 38.95731489827892;
        Assert.assertEquals(expected, actual,0.001);
    }

    @Test
    public void testGetTStudentFromTable() {
        LinearRegression linearRegression = new LinearRegression(x, y);
        double actual = linearRegression.getTStudentFromTable(0.05);
        double actual1 = linearRegression.getTStudentFromTable(1.05);
        double expected = 2.3060041352042036;
        double expected1 = 0.06470050272734236;
        Assert.assertEquals(expected, actual,0.001);
        Assert.assertEquals(expected1, actual1,0.001);
    }

    @Test
    public void testCalculateAnovaSR() {
        LinearRegression linearRegression = new LinearRegression(x, y);
        double actual = linearRegression.calculateAnovaSR();
        double expected = 16.68162128426794;
        Assert.assertEquals(expected, actual,0.001);
    }

    @Test
    public void testCalculateAnovaSE() {
        LinearRegression linearRegression = new LinearRegression(x, y);
        double actual = linearRegression.calculateAnovaSE();
        double expected = 1.84337871573205;
        Assert.assertEquals(expected, actual,0.001);
    }

    @Test
    public void testCalculateAnovaST() {
        LinearRegression linearRegression = new LinearRegression(x, y);
        double actual = linearRegression.calculateAnovaST();
        double expected = 18.52499999999999;
        Assert.assertEquals(expected, actual,0.001);
    }

    @Test
    public void testCalculateAnovaMSR() {
        LinearRegression linearRegression = new LinearRegression(x, y);
        double actual = linearRegression.calculateAnovaMSR();
        double expected = 16.681621284268;
        Assert.assertEquals(expected, actual,0.001);
    }

    @Test
    public void testCalculateAnovaMSE() {
        LinearRegression linearRegression = new LinearRegression(x,y);
        double actual = linearRegression.calculateAnovaMSE();
        double expected = 0.230422339466506;
        Assert.assertEquals(expected, actual,0.001);
    }

    @Test
    public void testCalculateTestF() {
        LinearRegression linearRegression = new LinearRegression(x,y);
        double actual = linearRegression.calculateTestF();
        double expected = 72.39585069264844;
        Assert.assertEquals(expected, actual,0.001);
    }

    @Test
    public void testGetFSnedcorFromTable() {
        LinearRegression linearRegression = new LinearRegression(x,y);
        double actual = linearRegression.getFSnedcorFromTable(0.05);
        double expected = 5.317655071584828;
        Assert.assertEquals(expected, actual,0.001);
    }

    @Test
    public void testGetResidualDF() {
        LinearRegression linearRegression = new LinearRegression(x,y);
        int actual = linearRegression.getResidualDF();
        int expected = 8;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testDelta() {
        LinearRegression linearRegression = new LinearRegression(x,y);
        double actual = linearRegression.delta(0.05,3.02);
        double expected = 0.816320209317185;
        Assert.assertEquals(expected, actual,0.001);
    }

    @Test
    public void testGetRegressionDF() {
        LinearRegression linearRegression = new LinearRegression(x,y);
        int actual = linearRegression.getRegressionDF();
        int expected = 1;
        Assert.assertEquals(expected, actual);
    }
}