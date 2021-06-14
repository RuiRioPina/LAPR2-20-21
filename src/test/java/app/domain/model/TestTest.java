package app.domain.model;

import app.controller.App;
import app.domain.store.ClientList;
import app.domain.store.ParameterStore;
import app.domain.store.TestStore;
import app.domain.store.TestTypeStore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;


public class TestTest {
    Company company = App.getInstance().getCompany();

    @Test
    public void getNhsCode() {
        Client c = new Client(1234567897123456L, 1234567690, "21-01-2002", "jorgepaulo@gmail.com", 1111231111L, 22262222122L, "Jorge Paulo");
        TestTypeStore tts = this.company.getTestTypeStore();
        List<ParameterCategory> pc = new ArrayList<>();
        ParameterCategory p1 = new ParameterCategory("CAT00", "Category00");
        ParameterCategory P2 = new ParameterCategory("CAT01", "Category01");
        ParameterCategory P3 = new ParameterCategory("CAT02", "Category02");
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new Parameter("HE011", "HB", "Haemoglobin", pc));
        pc.add(p1);
        pc.add(P2);
        Calendar data = Calendar.getInstance();
        tts.saveTestType(new TestType("BTT10", "Blood Test", "Venipuncture", pc));
        app.domain.model.Test test = new app.domain.model.Test("123454abcdef", "932992999999", c, tts.getTestTypes().get(0), pc, parameters, data);
        String actual = test.getNhsCode();
        String expected = "123454abcdef";
        assertEquals(expected,actual);
    }

    @Test
    public void getInternalCode() {
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
        String expected ="900000000000";
        assertEquals(expected,t.getInternalCode());
    }

    @Test
    public void getClient() {
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
        assertEquals(c,t.getClient());
    }

    @Test
    public void getTestType() {
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
        //System.out.println(t);
        assertEquals(tt1,t.getTestType());
    }

    @Test
    public void getSampleCollectionMethod() {
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
        assertEquals(tt1.getCollectingMethod(),t.getSampleCollectionMethod());
    }

    @Test
    public void getParameterCategory() {
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
        assertEquals(pc,t.getParameterCategory());
    }

    @Test
    public void getParameter() {
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
        assertEquals(par,t.getParameter());
    }

    @Test
    public void getSamples() {
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
        assertEquals(new ArrayList<>(),t.getSamples());
    }



    @Test
    public void getSamplesCollectionDate() {
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
        assertNull(t.getSamplesCollectionDate());
        Calendar d1 = new Calendar() {
            @Override
            protected void computeTime() {

            }

            @Override
            protected void computeFields() {

            }

            @Override
            public void add(int field, int amount) {

            }

            @Override
            public void roll(int field, boolean up) {

            }

            @Override
            public int getMinimum(int field) {
                return 0;
            }

            @Override
            public int getMaximum(int field) {
                return 0;
            }

            @Override
            public int getGreatestMinimum(int field) {
                return 0;
            }

            @Override
            public int getLeastMaximum(int field) {
                return 0;
            }
        };
        t.setSamplesCollectionDate(d1);
        assertEquals(d1,t.getSamplesCollectionDate());
    }
    @Test
    public void getDiagnosisDate() {
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
        assertNull(t.getDiagnosisDate());
        Calendar d1 = new Calendar() {
            @Override
            protected void computeTime() {

            }

            @Override
            protected void computeFields() {

            }

            @Override
            public void add(int field, int amount) {

            }

            @Override
            public void roll(int field, boolean up) {

            }

            @Override
            public int getMinimum(int field) {
                return 0;
            }

            @Override
            public int getMaximum(int field) {
                return 0;
            }

            @Override
            public int getGreatestMinimum(int field) {
                return 0;
            }

            @Override
            public int getLeastMaximum(int field) {
                return 0;
            }
        };
        t.setDiagnosisDate(d1);
        assertEquals(d1,t.getDiagnosisDate());
    }

    @Test
    public void getValidationDate() {
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
        assertNull(t.getValidationDate());
        Calendar d1 = new Calendar() {
            @Override
            protected void computeTime() {

            }

            @Override
            protected void computeFields() {

            }

            @Override
            public void add(int field, int amount) {

            }

            @Override
            public void roll(int field, boolean up) {

            }

            @Override
            public int getMinimum(int field) {
                return 0;
            }

            @Override
            public int getMaximum(int field) {
                return 0;
            }

            @Override
            public int getGreatestMinimum(int field) {
                return 0;
            }

            @Override
            public int getLeastMaximum(int field) {
                return 0;
            }
        };
        t.setValidationDate(d1);
        assertEquals(d1,t.getValidationDate());
    }

    @Test
    public void getReport(){
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
        Report report=new Report("Report test");
        t.setReport(report);

        assertEquals(report,t.getReport());

    }

    @Test
    public void testEquals() {
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
        TestType testType = new TestType("aaaaa","aaaaa","aab",cat);
        boolean actual =t.equals(null);
        boolean actual2 =t.equals(testType);
        boolean expected = false;
        Sample sample = new Sample("11111117112");
        List<Sample> sampleList = new ArrayList<>();
        sampleList.add(sample);
        t.setSamples(sampleList);
        app.domain.model.Test t2 = ts.createTest("abcdefghijkl", "900001000000", c, tt1, pc, par, data);
        t2.setSamples(sampleList);
        boolean actual3 =t.equals(t2);
        assertEquals(expected,actual);
        assertEquals(expected,actual2);
        assertEquals(true,actual3);

    }

    @Test
    public void addTestResult() {
    }

    @Test
    public void getTestResult() {
    }

    @Test
    public void getParameterStore() {
    }

    @Test
    public void getParameterStoreToShow() {
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
        Sample sample = new Sample("11113117112");
        List<Sample> sampleList = new ArrayList<>();
        sampleList.add(sample);
        t.setSamples(sampleList);
        List<Parameter> actual =t.getParameterStoreToShow();
        List<Parameter> expected = par;
        assertEquals(expected,actual);


    }


    @Test
    public void getDate() {

    }
    @Test
    public void toStringTest(){
        ParameterStore ps = this.company.getParameterStore();

        List<ParameterCategory> pc = new ArrayList<>();
        ParameterCategory p1 = new ParameterCategory("CAT00", "Category00");
        ParameterCategory P2 = new ParameterCategory("CAT01", "Category01");
        ParameterCategory P3 = new ParameterCategory("CAT02", "Category02");
        pc.add(p1);
        pc.add(P2);
        List<ParameterCategory> p = new ArrayList<>();
        List<ParameterCategory> cat = new ArrayList<>();
        cat.add(pc.get(0));
        List<ParameterCategory> cat1 = new ArrayList<>();
        cat1.add(P2);

        List<ParameterCategory> cat2 = new ArrayList<>();
        cat2.add(P3);
        p.add(P3);
        pc.add(p1);
        pc.add(P2);
        Parameter parameter = new Parameter("PET01", "PLT", "Platelet Count", cat);



        List<Parameter> part = new ArrayList<>();
        part.add(parameter);
        part.add(ps.getParameters().get(0));
        part.add(ps.getParameters().get(1));
        part.add(ps.getParameters().get(2));
        part.add(ps.getParameters().get(3));

        TestTypeStore tts = this.company.getTestTypeStore();

        ClientList cl = this.company.getClientList();
        Client c = new Client(1234567890123456L, 1234567890, "22-01-2002", "jorge@gmail.com", 1111111111L, 22222222222L, "Jorge Ferreira");
        Calendar data = Calendar.getInstance();
        Date rDate = data.getTime();
        app.domain.model.Test testSemSample1 = new app.domain.model.Test("123454abcdeg", "932992999998", c, tts.getTestTypes().get(0), pc, part, data);
        String string1 ="Internal code: 932992999998 Registration Date:"+rDate+" Chemical Analysis Date:"+null+" Diagnosis Date:"+null;
        assertNotEquals(testSemSample1.toString(),string1);
        testSemSample1.setSamplesCollectionDate(data);
        assertNotEquals(testSemSample1.toString(),string1);
        testSemSample1.setChemicalAnalysisDate(data);
        assertNotEquals(testSemSample1.toString(),string1);
        testSemSample1.setDiagnosisDate(data);
        assertNotEquals(testSemSample1.toString(),string1);


    }

    @Test
    public void getChemicalAnalysisDate() {
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
        Calendar date = Calendar.getInstance();
        t.setChemicalAnalysisDate(date);
        Calendar actual = t.getChemicalAnalysisDate();
        Calendar expected = t.getChemicalAnalysisDate();
        assertEquals(expected,actual);
    }

    @Test
    public void getLabID() {
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
        String expected = App.getInstance().getCompany().getCLA().getLaboratoryID();
        assertEquals(expected,t.getLabID());
    }
    @Test
    public void getChemicalDate() {
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
        //assertNotEquals(null,t.getChemicalAnalysisDate());
        t.setChemicalAnalysisDate(null);
        assertNull(t.getChemicalAnalysisDate());
    }
}