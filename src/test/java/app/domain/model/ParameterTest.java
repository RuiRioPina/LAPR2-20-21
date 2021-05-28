package app.domain.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ParameterTest {

    @Test
    public void getCode() {
        String code = "12345";

        List<ParameterCategory> parameterCategories = new ArrayList<>();
        ParameterCategory p1 = new ParameterCategory("54321","HEMOGRAM");
        parameterCategories.add(p1);

        Parameter p = new Parameter("12345","RBC", "RED BLOOD CELLS",parameterCategories);

        assertEquals(code,p.getCode());
    }

    @Test
    public void getShortname() {
        String shortname = "RBC";

        List<ParameterCategory> parameterCategories = new ArrayList<>();
        ParameterCategory p1 = new ParameterCategory("54321","HEMOGRAM");
        parameterCategories.add(p1);

        Parameter p = new Parameter("12345","RBC", "RED BLOOD CELLS", parameterCategories);

        assertEquals(shortname,p.getShortname());
    }

    @Test
    public void getDescription() {
        String description = "RED BLOOD CELLS";

        List<ParameterCategory> parameterCategories = new ArrayList<>();
        ParameterCategory p1 = new ParameterCategory("54321","HEMOGRAM");
        parameterCategories.add(p1);

        Parameter p = new Parameter("12345","RBC", "RED BLOOD CELLS", parameterCategories);

        assertEquals(description,p.getDescription());
    }

    @Test
    public void getPc() {
        List<ParameterCategory> parameterCategories = new ArrayList<>();
        ParameterCategory p2 = new ParameterCategory("54321","HEMOGRAM");
        parameterCategories.add(p2);

        Parameter p = new Parameter("12345","RBC", "RED BLOOD CELLS", parameterCategories);

        assertEquals(parameterCategories,p.getPc());
    }

    @Test
    public void getTestResult() {
        ParameterCategory parameterCategory = new ParameterCategory("PLT00", "plalets");
        List<ParameterCategory> pcl = new ArrayList<>();
        pcl.add(parameterCategory);
        Parameter parameter = new Parameter("PLT00", "plalets", "Plalets", pcl);
        ReferenceValue referenceValue = new ReferenceValue();
        ParameterCategory p1 = new ParameterCategory("CAT00", "Category00");
        List<ParameterCategory> pc = new ArrayList<>();
        pc.add(p1);
        double result = 5;
        TestType testType = new TestType("BLT00", "Blood Test", "Venipuncture", pc);
        ReferenceValue referenceValue1 =testType.checkExternalModuleBasedOnTestType(parameter);

        TestResult testResult = new TestResult(parameter,155,referenceValue1);
        parameter.setTestResult(testResult);
        assertEquals(parameter.getTestResult(),testResult);
    }


    @Test
    public void getTestResult1() {
        ParameterCategory parameterCategory = new ParameterCategory("PLT00", "plalets");
        List<ParameterCategory> pcl = new ArrayList<>();
        pcl.add(parameterCategory);
        Parameter parameter = new Parameter("PLT00", "plalets", "Plalets", pcl);
        ReferenceValue referenceValue = new ReferenceValue();
        ParameterCategory p1 = new ParameterCategory("CAT00", "Category00");
        List<ParameterCategory> pc = new ArrayList<>();
        pc.add(p1);
        double result = 5;
        TestType testType = new TestType("BLT00", "Blood Test", "Venipuncture", pc);
        ReferenceValue referenceValue1 =testType.checkExternalModuleBasedOnTestType(parameter);
        Parameter parameter1 = new Parameter(parameter);
        TestResult testResult = new TestResult(parameter,155,referenceValue1);
        parameter1.setTestResult(testResult);
        assertEquals(parameter1.getTestResult(),testResult);
    }

    @Test
    public void setTestResult() {
        ParameterCategory parameterCategory = new ParameterCategory("PLT00", "plalets");
        List<ParameterCategory> pcl = new ArrayList<>();
        pcl.add(parameterCategory);
        Parameter parameter = new Parameter("PLT00", "plalets", "Plalets", pcl);
        ReferenceValue referenceValue = new ReferenceValue();
        ParameterCategory p1 = new ParameterCategory("CAT00", "Category00");
        List<ParameterCategory> pc = new ArrayList<>();
        pc.add(p1);
        double result = 5;
        TestType testType = new TestType("BLT00", "Blood Test", "Venipuncture", pc);
        ReferenceValue referenceValue1 =testType.checkExternalModuleBasedOnTestType(parameter);

        TestResult testResult = new TestResult(parameter,155,referenceValue1);
        parameter.setTestResult(testResult);
        assertEquals(parameter.getTestResult(),testResult);
    }

    @Test
    public void testToString() {
        ParameterCategory parameterCategory = new ParameterCategory("PLT00", "plalets");
        List<ParameterCategory> pcl = new ArrayList<>();
        pcl.add(parameterCategory);
        Parameter parameter = new Parameter("PLT00", "plalets", "Plalets", pcl);
        assertEquals("Parameter{code='PLT00', shortname='plalets', description='Plalets', Parameter Category=[PLT00 | plalets]}Test Result =null",parameter.toString());
    }
}