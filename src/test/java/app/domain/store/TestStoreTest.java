package app.domain.store;

import app.controller.App;
import app.domain.model.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class TestStoreTest {


        @Test (expected = IllegalArgumentException.class)
        public void saveTestTest() {
            List<ParameterCategory> pc = new ArrayList<>();
            ParameterCategory p1 = new ParameterCategory("CAT00", "Category00");
            ParameterCategory P2 = new ParameterCategory("CAT01", "Category01");
            ParameterCategory P3 = new ParameterCategory("CAT02", "Category02");
            pc.add(p1);
            pc.add(P2);

            List<ParameterCategory> p = new ArrayList<>();
            p.add(P3);
            Date data = new Date(System.currentTimeMillis());

            List<TestType> tt = new ArrayList<>();

            TestType tt1 = new TestType("BLT00", "Blood Test", "Venipuncture", pc);
            TestType tt2 = new TestType("CVD00", "Covid-19 Test", "Nasopharyngeal", p);

            tt.add(tt2);
            tt.add(tt1);

            List<Parameter> par = new ArrayList<>();
            List<ParameterCategory> cat = new ArrayList<>();
            cat.add(pc.get(0));
            Client c = new Client(1234567890123456L, 1234567890, "22-01-2002", "jorge@gmail.com", 1111111111L, 22222222222L, "Jorge Ferreira");
            Parameter par1= new Parameter("HB000", "HB", "Haemoglobin", cat);
            Parameter par2 = new Parameter("WBC00", "WBC", "White Cell Count", cat);
            par.add(par1);
            par.add(par2);

            TestStore ts = new TestStore();
            app.domain.model.Test t = ts.createTest("abcdefghi#", "900000000000", c, tt1, pc, par, data);
            app.domain.model.Test t2 = ts.createTest("abcdefghi", "900000000000", c, tt1, pc, par, data);
            ts.saveTest(t2);
            ts.saveTest(t);

        }
@Test
    public void saveTestTest2andGetTests() {
        List<ParameterCategory> pc = new ArrayList<>();
        ParameterCategory p1 = new ParameterCategory("CAT00", "Category00");
        ParameterCategory P2 = new ParameterCategory("CAT01", "Category01");
        ParameterCategory P3 = new ParameterCategory("CAT02", "Category02");
        pc.add(p1);
        pc.add(P2);

        List<ParameterCategory> p = new ArrayList<>();
        p.add(P3);
        Date data = new Date(System.currentTimeMillis());

        List<TestType> tt = new ArrayList<>();

        TestType tt1 = new TestType("BLT00", "Blood Test", "Venipuncture", pc);
        TestType tt2 = new TestType("CVD00", "Covid-19 Test", "Nasopharyngeal", p);

        tt.add(tt2);
        tt.add(tt1);

        List<Parameter> par = new ArrayList<>();
        List<ParameterCategory> cat = new ArrayList<>();
        cat.add(pc.get(0));
        Client c = new Client(1234567890123456L, 1234567890, "22-01-2002", "jorge@gmail.com", 1111111111L, 22222222222L, "Jorge Ferreira");
        Parameter par1= new Parameter("HB000", "HB", "Haemoglobin", cat);
        Parameter par2 = new Parameter("WBC00", "WBC", "White Cell Count", cat);
        par.add(par1);
        par.add(par2);

        TestStore ts = new TestStore();
        app.domain.model.Test t2 = ts.createTest("abcdefghijkl", "900000000000", c, tt1, pc, par, data);
        ts.saveTest(t2);
        List<app.domain.model.Test> test2 = new ArrayList<>();
        test2.add(t2);
        assertEquals(t2,ts.getTestByInternalCode("900000000000"));
        assertEquals(ts.getTests(),test2);
    }

    @Test
    public void getTestsWithoutDiagnosis(){
        List<app.domain.model.Test> tt = App.getInstance().getCompany().getTestStore().getTestsWithoutDiagnosis();
        TestStore ts=new TestStore();

        List<app.domain.model.Test> t2=ts.getTestsWithoutDiagnosis();
        assertArrayEquals(tt.toArray(), t2.toArray());
    }

    @Test
    public void setTests() {
    }

    @Test
    public void initializeValidationList() {
    }

    @Test
    public void addTestResult() {
    }

    @Test
    public void createTest() {
    }

    @Test
    public void saveTest() {
    }

    @Test
    public void getTests() {
        List<ParameterCategory> pc = new ArrayList<>();
        ParameterCategory p1 = new ParameterCategory("CAT00", "Category00");
        ParameterCategory P2 = new ParameterCategory("CAT01", "Category01");
        ParameterCategory P3 = new ParameterCategory("CAT02", "Category02");
        pc.add(p1);
        pc.add(P2);

        List<ParameterCategory> p = new ArrayList<>();
        p.add(P3);
        Date data = new Date(System.currentTimeMillis());

        List<TestType> tt = new ArrayList<>();

        TestType tt1 = new TestType("BLT00", "Blood Test", "Venipuncture", pc);
        TestType tt2 = new TestType("CVD00", "Covid-19 Test", "Nasopharyngeal", p);

        tt.add(tt2);
        tt.add(tt1);

        List<Parameter> par = new ArrayList<>();
        List<ParameterCategory> cat = new ArrayList<>();
        cat.add(pc.get(0));
        Client c = new Client(1234567890123456L, 1234567890, "22-01-2002", "jorge@gmail.com", 1111111111L, 22222222222L, "Jorge Ferreira");
        Parameter par1= new Parameter("HB000", "HB", "Haemoglobin", cat);
        Parameter par2 = new Parameter("WBC00", "WBC", "White Cell Count", cat);
        par.add(par1);
        par.add(par2);

        TestStore ts = new TestStore();
        app.domain.model.Test t = ts.createTest("abcdefghijkl", "900000000000", c, tt1, pc, par, data);
        ts.saveTest(t);
        TestType testType = new TestType("aaaaa","aaaaa","aab",cat);
        Sample sample = new Sample("11111117112");
        List<Sample> sampleList = new ArrayList<>();
        sampleList.add(sample);
        t.setSamples(sampleList);
        app.domain.model.Test actual = ts.getTest("11111117112");
        app.domain.model.Test actualNull = ts.getTest("11131117112");
        app.domain.model.Test expected = t;
        assertEquals(expected,actual);
        assertNull(actualNull);

    }

    @Test
    public void getTestsWithoutSamples() {
    }

    @Test
    public void getTestsWithSamples() {
        List<ParameterCategory> pc = new ArrayList<>();
        ParameterCategory p1 = new ParameterCategory("CAT00", "Category00");
        ParameterCategory P2 = new ParameterCategory("CAT01", "Category01");
        ParameterCategory P3 = new ParameterCategory("CAT02", "Category02");
        pc.add(p1);
        pc.add(P2);

        List<ParameterCategory> p = new ArrayList<>();
        p.add(P3);
        Date data = new Date(System.currentTimeMillis());

        List<TestType> tt = new ArrayList<>();

        TestType tt1 = new TestType("BLT00", "Blood Test", "Venipuncture", pc);
        TestType tt2 = new TestType("CVD00", "Covid-19 Test", "Nasopharyngeal", p);

        tt.add(tt2);
        tt.add(tt1);

        List<Parameter> par = new ArrayList<>();
        List<ParameterCategory> cat = new ArrayList<>();
        cat.add(pc.get(0));
        Client c = new Client(1234567890123456L, 1234567890, "22-01-2002", "jorge@gmail.com", 1111111111L, 22222222222L, "Jorge Ferreira");
        Parameter par1= new Parameter("HB000", "HB", "Haemoglobin", cat);
        Parameter par2 = new Parameter("WBC00", "WBC", "White Cell Count", cat);
        par.add(par1);
        par.add(par2);

        TestStore ts = new TestStore();
        app.domain.model.Test t = ts.createTest("abcdefghijkl", "900000000000", c, tt1, pc, par, data);
        ts.saveTest(t);
        TestType testType = new TestType("aaaaa","aaaaa","aab",cat);
        Sample sample = new Sample("11111117112");
        List<Sample> sampleList = new ArrayList<>();
        sampleList.add(sample);
        t.setSamplesCollectionDate(data);
        t.setSamples(sampleList);
        List<app.domain.model.Test> actual = ts.getTestsWithSamples();
        List<app.domain.model.Test> expected = ts.getTests();
        assertEquals(expected,actual);

    }

    @Test
    public void getTestByInternalCode() {
    }

    @Test
    public void createSample() {
    }

    @Test
    public void saveSample() {
    }

    @Test
    public void testExists() {
    }

    @Test
    public void getTestByBarcode() {
        List<ParameterCategory> pc = new ArrayList<>();
        ParameterCategory p1 = new ParameterCategory("CAT00", "Category00");
        ParameterCategory P2 = new ParameterCategory("CAT01", "Category01");
        ParameterCategory P3 = new ParameterCategory("CAT02", "Category02");
        pc.add(p1);
        pc.add(P2);

        List<ParameterCategory> p = new ArrayList<>();
        p.add(P3);
        Date data = new Date(System.currentTimeMillis());

        List<TestType> tt = new ArrayList<>();

        TestType tt1 = new TestType("BLT00", "Blood Test", "Venipuncture", pc);
        TestType tt2 = new TestType("CVD00", "Covid-19 Test", "Nasopharyngeal", p);

        tt.add(tt2);
        tt.add(tt1);

        List<Parameter> par = new ArrayList<>();
        List<ParameterCategory> cat = new ArrayList<>();
        cat.add(pc.get(0));
        Client c = new Client(1234567890123456L, 1234567890, "22-01-2002", "jorge@gmail.com", 1111111111L, 22222222222L, "Jorge Ferreira");
        Parameter par1= new Parameter("HB000", "HB", "Haemoglobin", cat);
        Parameter par2 = new Parameter("WBC00", "WBC", "White Cell Count", cat);
        par.add(par1);
        par.add(par2);

        TestStore ts = new TestStore();
        app.domain.model.Test t = ts.createTest("abcdefghijkl", "900000000000", c, tt1, pc, par, data);
        ts.saveTest(t);
        TestType testType = new TestType("aaaaa","aaaaa","aab",cat);
        Sample sample = new Sample("11111117112");
        List<Sample> sampleList = new ArrayList<>();
        sampleList.add(sample);
        t.setSamples(sampleList);
        app.domain.model.Test actual = ts.getTestByBarcode("11111117112");
        app.domain.model.Test actualNull = ts.getTestByBarcode("11131117112");
        app.domain.model.Test expected = t;
        assertEquals(expected,actual);
        assertNull(actualNull);
    }

    @Test
    public void hasTestPassedSampleCollection() {
        List<ParameterCategory> pc = new ArrayList<>();
        ParameterCategory p1 = new ParameterCategory("CAT00", "Category00");
        ParameterCategory P2 = new ParameterCategory("CAT01", "Category01");
        ParameterCategory P3 = new ParameterCategory("CAT02", "Category02");
        pc.add(p1);
        pc.add(P2);

        List<ParameterCategory> p = new ArrayList<>();
        p.add(P3);
        Date data = new Date(System.currentTimeMillis());

        List<TestType> tt = new ArrayList<>();

        TestType tt1 = new TestType("BLT00", "Blood Test", "Venipuncture", pc);
        TestType tt2 = new TestType("CVD00", "Covid-19 Test", "Nasopharyngeal", p);

        tt.add(tt2);
        tt.add(tt1);

        List<Parameter> par = new ArrayList<>();
        List<ParameterCategory> cat = new ArrayList<>();
        cat.add(pc.get(0));
        Client c = new Client(1234567890123456L, 1234567890, "22-01-2002", "jorge@gmail.com", 1111111111L, 22222222222L, "Jorge Ferreira");
        Parameter par1= new Parameter("HB000", "HB", "Haemoglobin", cat);
        Parameter par2 = new Parameter("WBC00", "WBC", "White Cell Count", cat);
        par.add(par1);
        par.add(par2);

        TestStore ts = new TestStore();
        app.domain.model.Test t = ts.createTest("abcdefghijkl", "900000000000", c, tt1, pc, par, data);
        ts.saveTest(t);
        TestType testType = new TestType("aaaaa","aaaaa","aab",cat);
        Sample sample = new Sample("11111117112");
        List<Sample> sampleList = new ArrayList<>();
        sampleList.add(sample);
        t.setSamplesCollectionDate(data);
        t.setSamples(sampleList);
        boolean actual = ts.hasTestPassedSampleCollection("11111117112");
        boolean expected = true;
        assertEquals(expected,actual);
    }

    @Test
    public void getParameterTestToShow() {
    }

    @Test
    public void getTest() {
    }

    @Test
    public void getValidatedParameters() {
        List<ParameterCategory> pc = new ArrayList<>();
        ParameterCategory p1 = new ParameterCategory("CAT00", "Category00");
        ParameterCategory P2 = new ParameterCategory("CAT01", "Category01");
        ParameterCategory P3 = new ParameterCategory("CAT02", "Category02");
        pc.add(p1);
        pc.add(P2);

        List<ParameterCategory> p = new ArrayList<>();
        p.add(P3);
        Date data = new Date(System.currentTimeMillis());

        List<TestType> tt = new ArrayList<>();

        TestType tt1 = new TestType("BLT00", "Blood Test", "Venipuncture", pc);
        TestType tt2 = new TestType("CVD00", "Covid-19 Test", "Nasopharyngeal", p);

        tt.add(tt2);
        tt.add(tt1);

        List<Parameter> par = new ArrayList<>();
        List<Parameter> parValidated = new ArrayList<>();
        List<ParameterCategory> cat = new ArrayList<>();
        cat.add(pc.get(0));
        Client c = new Client(1234567890123456L, 1234567890, "22-01-2002", "jorge@gmail.com", 1111111111L, 22222222222L, "Jorge Ferreira");
        Parameter par1= new Parameter("HB000", "HB", "Haemoglobin", cat);
        Parameter par2 = new Parameter("WBC00", "WBC", "White Cell Count", cat);
        par.add(par1);
        par.add(par2);
        parValidated.add(par1);

        TestStore ts = new TestStore();
        app.domain.model.Test t = ts.createTest("abcdefghijkl", "900000000000", c, tt1, pc, par, data);
        ts.saveTest(t);
        TestType testType = new TestType("aaaaa","aaaaa","aab",cat);
        Sample sample = new Sample("11111117112");
        List<Sample> sampleList = new ArrayList<>();
        sampleList.add(sample);
        t.setSamplesCollectionDate(data);
        t.setSamples(sampleList);
        ts.setTestParam(new TestParam(t));
        TestResult testResult = new TestResult(par1,170,new ReferenceValue("metric",180,150));
        t.getParameter().get(0).setTestResult(testResult);
        System.out.println(par1.getTestResult());
        ts.saveTest(t);
        ts.saveTestResult(par1.getCode(),sample.getBarcode());
        boolean actual1 = ts.isTestResultInBetweenReferenceValue(testResult);


        assertEquals(true,actual1);
    }

    @Test
    public void saveTestResult() {
    }

    @Test
    public void getUnvalidatedTests() {
    }

    @Test
    public void validateTests() {
    }

    @Test
    public void testGetTestsWithoutDiagnosis() {
    }
}