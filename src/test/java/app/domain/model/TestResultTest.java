package app.domain.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestResultTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void getResult() {
        ParameterCategory parameterCategory = new ParameterCategory("PLT00", "plalets");
        List<ParameterCategory> pcl = new ArrayList<>();
        pcl.add(parameterCategory);
        Parameter parameter = new Parameter("PLT00", "plalets", "Plalets", pcl);
        double result = 170;
        ReferenceValue referenceValue = new ReferenceValue("10e9L", 450, 150);
        TestResult testResult = new TestResult(parameter, result, referenceValue);
        double actual = testResult.getResult();
        double expected = 170;
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void getReferenceValue() {
        ParameterCategory parameterCategory = new ParameterCategory("PLT00", "plalets");
        List<ParameterCategory> pcl = new ArrayList<>();
        pcl.add(parameterCategory);
        Parameter parameter = new Parameter("PLT00", "plalets", "Plalets", pcl);
        double result = 170;
        ReferenceValue referenceValue = new ReferenceValue("10e9L", 450, 150);
        TestResult testResult = new TestResult(parameter, result, referenceValue);
        ReferenceValue actual = testResult.getReferenceValue();
        ReferenceValue expected = referenceValue;
        assertEquals(expected, actual);
    }

    @Test
    public void testToString() {
        ParameterCategory parameterCategory = new ParameterCategory("PLT00", "plalets");
        List<ParameterCategory> pcl = new ArrayList<>();
        pcl.add(parameterCategory);
        Parameter parameter = new Parameter("PLT00", "plalets", "Plalets", pcl);
        double result = 170;
        ReferenceValue referenceValue = new ReferenceValue("10e9L", 450, 150);
        TestResult testResult = new TestResult(parameter, result, referenceValue);
        String actual = testResult.toString();
        String expected = "ResultOfTest{" + "result=" + result + ", refValue=" + referenceValue + '}';
        assertEquals(expected,actual);
    }
}