package app.domain.store;

import app.controller.App;
import app.controller.GenerateSampleController;
import app.domain.model.*;
import org.junit.Test;

import java.util.*;

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
            Calendar data = Calendar.getInstance();

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
        Calendar data = Calendar.getInstance();

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
        Calendar data = Calendar.getInstance();

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
    public void getTestsWithoutSamplesValid() {
		TestStore tStore= new TestStore();
		List<ParameterCategory> pc = new ArrayList<>();
        ParameterCategory p1 = new ParameterCategory("CAT00", "Category00");
        ParameterCategory P2 = new ParameterCategory("CAT01", "Category01");
        ParameterCategory P3 = new ParameterCategory("CAT02", "Category02");
        pc.add(p1);
        pc.add(P2);

        List<ParameterCategory> p = new ArrayList<>();
        p.add(P3);
        Calendar data = Calendar.getInstance();

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
        
        app.domain.model.Test t = new app.domain.model.Test("1324efghi112", "908412481721", c, tt1, pc, par, data);
        tStore.saveTest(t);
        
        List<app.domain.model.Test> lt = tStore.getTestsWithoutSamples();
		
        assertEquals(1, lt.size());
        assertEquals(lt.get(0), t);
    }
    
    @Test
    public void getTestsWithoutSamplesInvalid() {
		TestStore tStore= new TestStore();
		List<ParameterCategory> pc = new ArrayList<>();
        ParameterCategory p1 = new ParameterCategory("CAT00", "Category00");
        ParameterCategory P2 = new ParameterCategory("CAT01", "Category01");
        ParameterCategory P3 = new ParameterCategory("CAT02", "Category02");
        pc.add(p1);
        pc.add(P2);

        List<ParameterCategory> p = new ArrayList<>();
        p.add(P3);
        Calendar data = Calendar.getInstance();

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
        
        app.domain.model.Test t = new app.domain.model.Test("1324efghi112", "908412481721", c, tt1, pc, par, data);
        tStore.saveTest(t);
        
        Sample s = new Sample("86315489123");
        tStore.saveSample(t, s);
        
        List<app.domain.model.Test> lt = tStore.getTestsWithoutSamples();
		
        assertEquals(0, lt.size());
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
        Calendar data = Calendar.getInstance();

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
    	String code = "518912581123";
    	TestStore tStore = new TestStore();
    	
    	app.domain.model.Test t = tStore.getTestByInternalCode(code);
    	
    	assertNull("Test should not be found", t);
    }

    @Test
    public void createSample() {
    	String code = "98765432191";
    	TestStore tStore = new TestStore();
    	
    	Sample s = tStore.createSample(code);
    	
    	assertNotNull(s);
    	assertEquals(code, s.getBarcode());
    }

    @Test
    public void saveSampleValid() {
    	String code = "98765432191";
    	TestStore tStore = new TestStore();
    	
    	List<ParameterCategory> pc = new ArrayList<>();
        ParameterCategory p1 = new ParameterCategory("CAT00", "Category00");
        ParameterCategory P2 = new ParameterCategory("CAT01", "Category01");
        ParameterCategory P3 = new ParameterCategory("CAT02", "Category02");
        pc.add(p1);
        pc.add(P2);

        List<ParameterCategory> p = new ArrayList<>();
        p.add(P3);
        Calendar data = Calendar.getInstance();

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
        
        app.domain.model.Test t = new app.domain.model.Test("1324efghi112", "908412481721", c, tt1, pc, par, data);
        tStore.saveTest(t);
    	
    	Sample s = tStore.createSample(code);
    	
    	Exception ex = null;
    	try {
    		tStore.saveSample(t, s);
    	} catch (Exception e) {
    		ex = e;
    	}
    	
    	assertNull(ex);
    	assertEquals(1, t.getSamples().size());
    	assertEquals(s, t.getSamples().get(0));
    }
    
    @Test
    public void saveSampleInvalidDigits() {
    	String code = "98$65432191";
    	TestStore tStore = new TestStore();
    	
    	List<ParameterCategory> pc = new ArrayList<>();
        ParameterCategory p1 = new ParameterCategory("CAT00", "Category00");
        ParameterCategory P2 = new ParameterCategory("CAT01", "Category01");
        ParameterCategory P3 = new ParameterCategory("CAT02", "Category02");
        pc.add(p1);
        pc.add(P2);

        List<ParameterCategory> p = new ArrayList<>();
        p.add(P3);
        Calendar data = Calendar.getInstance();

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
        
        app.domain.model.Test t = new app.domain.model.Test("1324efghi112", "908412481721", c, tt1, pc, par, data);
        tStore.saveTest(t);
    	
    	Sample s = tStore.createSample(code);
    	
    	Exception ex = null;
    	try {
    		tStore.saveSample(t, s);
    	} catch (Exception e) {
    		ex = e;
    	}
    	
    	assertNotNull(ex);
    	assertEquals("Barcode must be numeric.", ex.getMessage());
    }

    @Test
    public void saveSampleInvalidSize() {
    	String code = "9865432191";
    	TestStore tStore = new TestStore();
    	
    	List<ParameterCategory> pc = new ArrayList<>();
        ParameterCategory p1 = new ParameterCategory("CAT00", "Category00");
        ParameterCategory P2 = new ParameterCategory("CAT01", "Category01");
        ParameterCategory P3 = new ParameterCategory("CAT02", "Category02");
        pc.add(p1);
        pc.add(P2);

        List<ParameterCategory> p = new ArrayList<>();
        p.add(P3);
        Calendar data = Calendar.getInstance();

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
        
        app.domain.model.Test t = new app.domain.model.Test("1324efghi112", "908412481721", c, tt1, pc, par, data);
        tStore.saveTest(t);
    	
    	Sample s = tStore.createSample(code);
    	
    	Exception ex = null;
    	try {
    		tStore.saveSample(t, s);
    	} catch (Exception e) {
    		ex = e;
    	}
    	
    	assertNotNull(ex);
    	assertEquals("Barcode must have 11 chars.", ex.getMessage());
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
        Calendar data = Calendar.getInstance();

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
        Calendar data = Calendar.getInstance();

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
        Calendar data = Calendar.getInstance();

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
        List<ParameterCategory> pc = new ArrayList<>();
        ParameterCategory p1 = new ParameterCategory("CAT00", "Category00");
        ParameterCategory P2 = new ParameterCategory("CAT01", "Category01");
        ParameterCategory P3 = new ParameterCategory("CAT02", "Category02");
        pc.add(p1);
        pc.add(P2);

        List<ParameterCategory> p = new ArrayList<>();
        p.add(P3);
        Calendar data = Calendar.getInstance();

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
        app.domain.model.Test t = ts.createTest("abcdefghis21", "900000000000", c, tt1, pc, par, data);
        app.domain.model.Test t2 = ts.createTest("abcdefghi212", "900000000000", c, tt1, pc, par, data);
        ts.saveTest(t2);
        ts.saveTest(t);
        t.setChemicalAnalysisDate(data);
        t.setSamplesCollectionDate(data);
        t.setDiagnosisDate(data);
        List<app.domain.model.Test> testList1= new ArrayList<>();
        List<app.domain.model.Test> testList2= new ArrayList<>();
        testList1.add(t);
        testList2.add(t2);
        testList2.add(t);
        assertEquals(testList1,ts.getUnvalidatedTests());
        assertNotEquals(testList2,ts.getUnvalidatedTests());
    }

    @Test
    public void validateTests() {
        List<ParameterCategory> pc = new ArrayList<>();
        ParameterCategory p1 = new ParameterCategory("CAT00", "Category00");
        ParameterCategory P2 = new ParameterCategory("CAT01", "Category01");
        ParameterCategory P3 = new ParameterCategory("CAT02", "Category02");
        pc.add(p1);
        pc.add(P2);

        List<ParameterCategory> p = new ArrayList<>();
        p.add(P3);
        Calendar data = Calendar.getInstance();

        List<TestType> tt = new ArrayList<>();

        TestType tt1 = new TestType("BLT00", "Blood Test", "Venipuncture", pc);
        TestType tt2 = new TestType("CVD00", "Covid-19 Test", "Nasopharyngeal", p);

        tt.add(tt2);
        tt.add(tt1);

        List<Parameter> par = new ArrayList<>();
        List<ParameterCategory> cat = new ArrayList<>();
        cat.add(pc.get(0));
        Client c = new Client(1234567890123456L, 1234567890, "22-01-2002", "jorge@gmail.com", 1111111111L, 22222222222L, "Teste");
        Parameter par1= new Parameter("HB000", "HB", "Haemoglobin", cat);
        Parameter par2 = new Parameter("WBC00", "WBC", "White Cell Count", cat);
        par.add(par1);
        par.add(par2);

        TestStore ts = new TestStore();
        app.domain.model.Test t = ts.createTest("abcdefghis21", "900000000000", c, tt1, pc, par, data);
        app.domain.model.Test t2 = ts.createTest("abcdefghi212", "900000000000", c, tt1, pc, par, data);
        ts.saveTest(t2);
        ts.saveTest(t);
        t.setChemicalAnalysisDate(data);
        t.setSamplesCollectionDate(data);
        t.setDiagnosisDate(data);
        t2.setChemicalAnalysisDate(data);
        t2.setSamplesCollectionDate(data);
        t2.setDiagnosisDate(data);
        List<app.domain.model.Test> testList1= new ArrayList<>();
        testList1.add(t);
        testList1.add(t2);
        ts.validateTests(testList1,data);
        assertEquals(t.getValidationDate(),data);
        assertEquals(t2.getValidationDate(),data);
    }

    @Test
    public void testGetTestsWithoutDiagnosis() {
    }
//    @Test
//    public void getTestsPerformedPerDay(){
//        List<ParameterCategory> pc = new ArrayList<>();
//        ParameterCategory p1 = new ParameterCategory("CAT00", "Category00");
//        ParameterCategory P2 = new ParameterCategory("CAT01", "Category01");
//        ParameterCategory P3 = new ParameterCategory("CAT02", "Category02");
//        pc.add(p1);
//        pc.add(P2);
//
//        List<ParameterCategory> p = new ArrayList<>();
//        p.add(P3);
//        Calendar data = Calendar.getInstance();
//
//        List<TestType> tt = new ArrayList<>();
//
//        TestType tt1 = new TestType("BLT00", "Blood Test", "Venipuncture", pc);
//        TestType tt2 = new TestType("CVD00", "Covid-19 Test", "Nasopharyngeal", p);
//
//        tt.add(tt2);
//        tt.add(tt1);
//
//        List<Parameter> par = new ArrayList<>();
//        List<ParameterCategory> cat = new ArrayList<>();
//        cat.add(pc.get(0));
//        Client c = new Client(1234567890123456L, 1234567890, "22-01-2002", "jorge@gmail.com", 1111111111L, 22222222222L, "Teste");
//        Parameter par1= new Parameter("HB000", "HB", "Haemoglobin", cat);
//        Parameter par2 = new Parameter("WBC00", "WBC", "White Cell Count", cat);
//        par.add(par1);
//        par.add(par2);
//
//        TestStore ts = new TestStore();
//        app.domain.model.Test t = ts.createTest("abcdefghis21", "900000000000", c, tt1, pc, par, data);
//        app.domain.model.Test t2 = ts.createTest("abcdefghi212", "900000000000", c, tt2, pc, par, data);
//        app.domain.model.Test t3 = ts.createTest("abcdefghid21", "900000000000", c, tt1, pc, par, data);
//        app.domain.model.Test t4 = ts.createTest("abcdefghig21", "900000000000", c, tt1, pc, par, data);
//        app.domain.model.Test t5 = ts.createTest("abcdefghss21", "900000000000", c, tt1, pc, par, data);
//        app.domain.model.Test t6 = ts.createTest("abcde3ghss21", "900000000000", c, tt1, pc, par, data);
//t2.getParameter().get(0).setTestResult(new TestResult(t2.getParameter().get(0),1.5,new ReferenceValue("xfsfsdf",1.4,0))); ;
//        ts.saveTest(t2);
//        ts.saveTest(t);
//        Calendar data1=Calendar.getInstance();
//        data1.set(2021,5,23);
//        Calendar data2=Calendar.getInstance();
//        Calendar data3=Calendar.getInstance();
//        Calendar data4=Calendar.getInstance();
//        Calendar data5=Calendar.getInstance();
//        data5.set(2021,5,27);
//        data2.set(2021,5,21);
//        t.setValidationDate(data);
//        t2.setValidationDate(data1);
//        t3.setValidationDate(data2);
//        data3.set(2021,5,22);
//        t4.setValidationDate(data3);
//        data4.set(2021,5,26);
//        t5.setValidationDate(data4);
//        Calendar data6=Calendar.getInstance();
//        data6.set(2021,5,27);
//        t6.setValidationDate(data6);
//        ts.saveTest(t6);
//        Calendar data7= Calendar.getInstance();
//        data7.set(2021,5,28);
//        List<app.domain.model.Test> testList1= new ArrayList<>();
//        testList1.add(t);
//        testList1.add(t2);
//        testList1.add(t3);
//        ts.saveTest(t3);
//        ts.saveTest(t4);
//        ts.saveTest(t5);
//        testList1.add(t4);
//        testList1.add(t5);
//        double[] expected={1,1,0,0,1,0};
//        for (int i=0;i<expected.length;i++){
//            System.out.println(expected[i]);
//        }
//        System.out.println("----------------------------");
//        for (int i=0;i<ts.getTestsPerformedPerDay(data3,data5).length;i++){
//            System.out.println(ts.getTestsPerformedPerDay(data3,data5)[i]);
//        }
//        System.out.println("---------------------------");
//        for (int i=0;i<ts.getTestsPerformedPerWeek(data2,data7).length;i++){
//            System.out.println(ts.getTestsPerformedPerWeek(data2,data7)[i]);
//        }
//
//    }
}