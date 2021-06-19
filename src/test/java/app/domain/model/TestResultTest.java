package app.domain.model;

import app.controller.App;
import app.domain.store.TestStore;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestResultTest {
    Company company = App.getInstance().getCompany();

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
    public void getResultForBlood() {
        ParameterCategory parameterCategory = new ParameterCategory("CAT02", "Category02");
        List<ParameterCategory> pcl = new ArrayList<>();
        pcl.add(parameterCategory);
        Parameter parameter = new Parameter("IgGAN", "IgC", "Antibodies", pcl);
        double result = 1;
        ReferenceValue referenceValue = new ReferenceValue("10e9L", 1.4D, 0);
        TestResult testResult = new TestResult(parameter, result, referenceValue);
        double actual = testResult.getResult();
        ReferenceValue actualReferenceValue = testResult.getReferenceValue();
        double expected = 1;
        ReferenceValue expectedReferenceValue = referenceValue;
        assertEquals(expected, actual, 0.01);
        assertEquals(expectedReferenceValue, actualReferenceValue);
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
        String expected = "Result = 170.0 - Metric = 10e9L - MaxValue = 450.0 - MinValue = 150.0";
        assertEquals(expected,actual);
    }
}