package app.domain.store;

import app.domain.model.Client;
import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;
import app.domain.model.TestType;
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
    public void getTests() {
    }
}