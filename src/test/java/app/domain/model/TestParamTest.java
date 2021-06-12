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

public class TestParamTest {
    Company company = App.getInstance().getCompany();
    @Test
    public void findParameterInTestParameter() {

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



        ps.saveParameter(new Parameter("HT011", "HB", "Haemoglobin", cat));
        ps.saveParameter(new Parameter("WCC01", "WBC", "White Cell Count", cat));
        ps.saveParameter(new Parameter("PTT01", "PLT", "Platelet Count", cat));
        ps.saveParameter(new Parameter("RTC01", "RBC", "Red Blood Count", cat));
        ps.saveParameter(new Parameter("MTV01", "MCV", "Mean Cell Volume", cat1));
        ps.saveParameter(new Parameter("MTH01", "MCH", "Mean Cell Heaemoglob", cat1));
        ps.saveParameter(new Parameter("MTHC1", "MCHC", "MCH Concentration", cat1));
        ps.saveParameter(new Parameter("ETR01", "ESR", "Erythrocyte Sed Rate", cat1));
        ps.saveParameter(new Parameter("ITGAR", "IgC", "Antibodies", cat2));
        List<Parameter> par = new ArrayList<>();
        par.add(ps.getParameters().get(0));
        par.add(ps.getParameters().get(1));
        par.add(ps.getParameters().get(2));
        par.add(ps.getParameters().get(3));

        TestTypeStore tts = this.company.getTestTypeStore();
        tts.saveTestType(new TestType("BLL17", "Blood Test", "Venipuncture", pc));
        tts.saveTestType(new TestType("CTD10", "Covid-19 Test", "Nasopharyngeal", p));

        ClientList cl = this.company.getClientList();
        Client c = new Client(1234567890123456L, 1234567890, "22-01-2002", "jorge@gmail.com", 1111111111L, 22222222222L, "Jorge Ferreira");
        cl.saveClient(c);

        TestStore ts = this.company.getTestStore();
        Calendar data = Calendar.getInstance();
        app.domain.model.Test testSemSample1 = new app.domain.model.Test("123454abcdef", "932992999999", c, tts.getTestTypes().get(0), pc, par, data);
        TestParam testParam = new TestParam(testSemSample1);
        Parameter actual1 = testParam.findParameterInTestParameter("PLT00");
        Parameter actual2 = testParam.findParameterInTestParameter("TTT00");
        Parameter expected1 = ps.getParameterByCode("PLT00");
        Parameter expected2 = testParam.findParameterInTestParameter("PLT00");

        assertEquals(null,actual2);
        assertEquals(expected2,actual1);
    }

    @Test
    public void testFindParameterInTestParameter() {
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
        Parameter parameter = new Parameter("PLT01", "PLT", "Platelet Count", cat);


        ps.saveParameter(new Parameter("HB011", "HB", "Haemoglobin", cat));
        ps.saveParameter(new Parameter("WBC01", "WBC", "White Cell Count", cat));
        ps.saveParameter(parameter);
        ps.saveParameter(new Parameter("RBC01", "RBC", "Red Blood Count", cat));
        ps.saveParameter(new Parameter("MCV01", "MCV", "Mean Cell Volume", cat1));
        ps.saveParameter(new Parameter("MCH01", "MCH", "Mean Cell Heaemoglob", cat1));
        ps.saveParameter(new Parameter("MCHC1", "MCHC", "MCH Concentration", cat1));
        ps.saveParameter(new Parameter("ESR01", "ESR", "Erythrocyte Sed Rate", cat1));
        ps.saveParameter(new Parameter("IgGAR", "IgC", "Antibodies", cat2));
        List<Parameter> part = new ArrayList<>();
        part.add(parameter);
        part.add(ps.getParameters().get(0));
        part.add(ps.getParameters().get(1));
        part.add(ps.getParameters().get(2));
        part.add(ps.getParameters().get(3));

        TestTypeStore tts = this.company.getTestTypeStore();
        tts.saveTestType(new TestType("BLT10", "Blood Test", "Venipuncture", pc));
        tts.saveTestType(new TestType("CVD10", "Covid-19 Test", "Nasopharyngeal", p));

        ClientList cl = this.company.getClientList();
        Client c = new Client(1234567890123456L, 1234567890, "22-01-2002", "jorge@gmail.com", 1111111111L, 22222222222L, "Jorge Ferreira");
        cl.saveClient(c);

        TestStore ts = this.company.getTestStore();
        Calendar data = Calendar.getInstance();

        app.domain.model.Test testSemSample1 = new app.domain.model.Test("123454abcdef", "932992999999", c, tts.getTestTypes().get(0), pc, part, data);
        TestParam testParam = new TestParam(testSemSample1);

        Parameter actual1 = testParam.findParameterInTestParameter("PLT01",part);
        Parameter actual2 = testParam.findParameterInTestParameter("PPP01",part);
        Parameter expected1 = parameter;
        assertEquals(expected1,actual1);
        assertNull(actual2);

    }
}