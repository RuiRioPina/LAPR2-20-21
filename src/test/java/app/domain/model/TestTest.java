package app.domain.model;

import app.controller.App;
import app.domain.store.ClientList;
import app.domain.store.ParameterStore;
import app.domain.store.TestStore;
import app.domain.store.TestTypeStore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;


public class TestTest {
    Company company = App.getInstance().getCompany();

    @Test
    public void getNhsCode() {
    }

    @Test
    public void getInternalCode() {
    }

    @Test
    public void getClient() {
    }

    @Test
    public void getTestType() {
    }

    @Test
    public void getSampleCollectionMethod() {
    }

    @Test
    public void getParameterCategory() {
    }

    @Test
    public void getParameter() {
    }

    @Test
    public void getSamples() {
    }

    @Test
    public void getRegistrationDate() {
    }

    @Test
    public void getSamplesCollectionDate() {
    }

    @Test
    public void getChemicalAnalysisDate() {
    }

    @Test
    public void getReport() {
    }

    @Test
    public void setReport() {
    }

    @Test
    public void getDiagnosisDate() {
    }

    @Test
    public void getValidationDate() {
    }

    @Test
    public void testToString() {
    }

    @Test
    public void setSamplesCollectionDate() {
    }

    @Test
    public void getBarcode() {
    }

    @Test
    public void testEquals() {
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
    }

    @Test
    public void removeItemFromParameterStore() {
    }

    @Test
    public void setChemicalAnalysisDate() {
    }

    @Test
    public void setDiagnosisDate() {
    }

    @Test
    public void setValidationDate() {
    }

    @Test
    public void sendTestCompletedNotification() {
    }

    @Test
    public void getDate() {
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


        ps.saveParameter(new Parameter("HE011", "HB", "Haemoglobin", cat));
        ps.saveParameter(new Parameter("WEC01", "WBC", "White Cell Count", cat));
        ps.saveParameter(parameter);
        ps.saveParameter(new Parameter("REC01", "RBC", "Red Blood Count", cat));
        ps.saveParameter(new Parameter("MEV01", "MCV", "Mean Cell Volume", cat1));
        ps.saveParameter(new Parameter("MEH01", "MCH", "Mean Cell Heaemoglob", cat1));
        ps.saveParameter(new Parameter("MEHC1", "MCHC", "MCH Concentration", cat1));
        ps.saveParameter(new Parameter("EER01", "ESR", "Erythrocyte Sed Rate", cat1));
        ps.saveParameter(new Parameter("IEGAR", "IgC", "Antibodies", cat2));
        List<Parameter> part = new ArrayList<>();
        part.add(parameter);
        part.add(ps.getParameters().get(0));
        part.add(ps.getParameters().get(1));
        part.add(ps.getParameters().get(2));
        part.add(ps.getParameters().get(3));

        TestTypeStore tts = this.company.getTestTypeStore();
        tts.saveTestType(new TestType("BET10", "Blood Test", "Venipuncture", pc));
        tts.saveTestType(new TestType("CED10", "Covid-19 Test", "Nasopharyngeal", p));

        ClientList cl = this.company.getClientList();
        Client c = new Client(1234567890123456L, 1234567890, "22-01-2002", "jorge@gmail.com", 1111111111L, 22222222222L, "Jorge Ferreira");
        cl.saveClient(c);

        Date data = new Date(System.currentTimeMillis());

        app.domain.model.Test testSemSample1 = new app.domain.model.Test("123454abcdef", "932992999999", c, tts.getTestTypes().get(0), pc, part, data);

        String actual = testSemSample1.getDate();
        Date date = new Date(System.currentTimeMillis());
        String expected = date.toString();
        assertEquals(expected,actual);
    }
}