package app.domain.store;

import app.domain.model.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class ResultOfTestStoreTest {

    @Test
    public void addTestResult() {
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
        ResultOfTestStore resultOfTestStore = new ResultOfTestStore();

        TestResult actual = resultOfTestStore.addTestResult(par1,170,new ReferenceValue("cm3",180,130));
        List<app.domain.model.TestResult> expected = resultOfTestStore.getResultOfTest();
        TestResult testResultexpected = expected.get(0);

        assertEquals(testResultexpected,actual);
    }
}