package app.domain.model;

import app.domain.model.matcp.LinearRegression;
import app.domain.model.matcp.MultiLinearRegression;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

public class NHSReportTest {

    @Test
    public void getReportString(){
        double[] arrX1 = {3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        double[] arrX2 = {825, 215, 1070, 550, 480, 920, 1350, 325, 670, 1215};
        double[] arrY1 = {3.5, 1, 4, 2, 1, 3, 4.5, 1.5, 3, 5};
        LinearRegression linearRegression = new LinearRegression(arrX2, arrY1);
        MultiLinearRegression multiLinearRegression = new MultiLinearRegression(arrX1, arrX2, arrY1);
        NHSReport nhsReport = new NHSReport(linearRegression, arrX2 ,arrY1);
        NHSReport nhsReportMultilinear = new NHSReport(multiLinearRegression,arrX1, arrX2 ,arrY1);
        Calendar data1 = Calendar.getInstance();
        Calendar data2 = Calendar.getInstance();
        data1.set(2021, Calendar.MAY, 3);
        data2.set(2021, Calendar.MAY, 13);
        int actual = nhsReport.getReportString(data1,data2,0.95,0,0.95,0,0.95,0.95,"days").length();
        int actual1 = nhsReportMultilinear.getReportString(data1,data2,0.95,0,0.95,0,0.95,0.95,"days").length();
        int actual2 = nhsReport.getReportString(data1,data2,0.95,0,0.95,0,0.95,0.95,"weeks").length();
        int actual3 = nhsReportMultilinear.getReportString(data1,data2,0.95,0,0.95,0,0.95,0.95,"weeks").length();
        int expected = 1739;
        int expected1 = 1848;
        int expected2 = 1089;
        int expected3 = 1191;

        assertEquals(expected,actual);
        assertEquals(expected1,actual1);
        assertEquals(expected2,actual2);
        assertEquals(expected3,actual3);
    }
}