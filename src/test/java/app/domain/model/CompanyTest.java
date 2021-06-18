package app.domain.model;

import app.controller.App;
import app.domain.store.*;
import auth.AuthFacade;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class CompanyTest {

    @Test
    public void getImportedTests() {
        List<app.domain.model.Test> importedTests = new ArrayList<>();
        assertEquals(App.getInstance().getCompany().getImportedTests(),importedTests);
    }

    @Test
    public void setImportedTests() {
        List<app.domain.model.Test> importedTests = new ArrayList<>();
        App.getInstance().getCompany().setImportedTests(importedTests);
        assertEquals(App.getInstance().getCompany().getImportedTests(),importedTests);
        assertNotEquals(null,importedTests);
    }

    @Test
    public void getCLA() {
        List <TestType> tt = new ArrayList<>();
        ClinicalAnalysisLaboratory cal = new ClinicalAnalysisLaboratory("00000","Lab","London",11111111111L,1010101010L,tt);
        App.getInstance().getCompany().setCLA(cal);
        assertEquals(App.getInstance().getCompany().getCLA(),cal);
    }

    @Test
    public void getTestCode() {
        int testCode = 0;
        //assertEquals(App.getInstance().getCompany().getTestCode(),testCode);
    }

    @Test
    public void getNumberOfEmployees() {
        int employees = 0;
        assertEquals(App.getInstance().getCompany().getNumberOfEmployees(),employees);
    }

    @Test
    public void getDesignation() {
        Company manyLabs= new Company("Many Labs");
        assertEquals(manyLabs.getDesignation(), "Many Labs");
    }

    @Test
    public void getAuthFacade() {
        Company manyLabs= new Company("Many Labs");
        AuthFacade af = new AuthFacade();
        assertNotEquals(manyLabs.getAuthFacade(),af);
    }

    @Test
    public void getParameterCategoryStore() {
        ParameterCategoryStore pcStore = new ParameterCategoryStore();
        assertNotEquals(pcStore,App.getInstance().getCompany().getParameterCategoryStore());
    }

    @Test
    public void getTestTypeStore() {
        TestTypeStore ttStore = new TestTypeStore();
        assertNotEquals(ttStore,App.getInstance().getCompany().getTestTypeStore());
    }

    @Test
    public void getParameterStore() {
        ParameterStore pStore = new ParameterStore();
        assertNotEquals(pStore,App.getInstance().getCompany().getParameterStore());
    }

    @Test
    public void getEmployeeStore() {
        EmployeeStore eStore = new EmployeeStore();
        assertNotEquals(eStore, App.getInstance().getCompany().getEmployeeStore());
    }

    @Test
    public void getClientList() {
        ClientList cList = new ClientList();
        assertNotEquals(cList,App.getInstance().getCompany().getClientList());
    }

    @Test
    public void getTestStore() {
        TestStore tStore = new TestStore();
        assertNotEquals(tStore,App.getInstance().getCompany().getTestStore());
    }

    @Test
    public void getLabCoorStore() {
        LabCoordinatorStore lcStore = new LabCoordinatorStore();
        assertNotEquals(lcStore,App.getInstance().getCompany().getLabCoorStore());
    }

    @Test
    public void getReportStore() {
        ReportStore rStore = new ReportStore();
        assertNotEquals(rStore, App.getInstance().getCompany().getReportStore());
    }

    @Test
    public void getRoleStore() {
        RoleStore roleStore = new RoleStore();
        assertNotEquals(roleStore,App.getInstance().getCompany().getRoleStore());
    }

    @Test
    public void getNextBarcode() {

        String i = "99999999999";
        assertNotEquals(i,App.getInstance().getCompany().getNextBarcode());
    }

    @Test
    public void getAllTest() {
        List <app.domain.model.Test> tests = new ArrayList<>();
        assertNotEquals(tests, App.getInstance().getCompany().getAllTest());
    }

    @Test
    public void getAllTestCompleted() {
        List <app.domain.model.Test> tests = new ArrayList<>();
        assertNotEquals(tests, App.getInstance().getCompany().getAllTestCompleted());
    }

    @Test
    public void tStringToCalendar() throws ParseException {
        String txt = "12/04/2001";
        App.getInstance().getCompany().tStringToCalendar(txt);
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date dt = df.parse(txt);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        assertEquals(App.getInstance().getCompany().tStringToCalendar(txt),calendar);
        Calendar calendar1 = null;
        assertNotEquals(App.getInstance().getCompany().tStringToCalendar(txt),calendar1);
    }

    @Test
    public void getClinicalAnalysisLaboratoryStore() {
        ClinicalAnalysisLaboratoryStore calStore = new ClinicalAnalysisLaboratoryStore();
        assertNotEquals(calStore,App.getInstance().getCompany().getClinicalAnalysisLaboratoryStore());
    }

    @Test
    public void setTestCode() {
        Company cmp = new Company("ML");
         int tCode = 1;
        cmp.setTestCode(tCode);
        assertEquals(tCode,cmp.getTestCode());
    }

    @Test
    public void setCLA() {
        List <TestType> tt = new ArrayList<>();
        ClinicalAnalysisLaboratory cal = new ClinicalAnalysisLaboratory("00000","Lab","London",11111111111L,1010101010L,tt);
        Company cmp = new Company("ML");
        cmp.setCLA(cal);
        assertEquals(cal,cmp.getCLA());
    }

    @Test
    public void setNumberOfEmployees() {
        Company cmp = new Company("ML");
        cmp.setNumberOfEmployees(1);
        assertEquals(cmp.getNumberOfEmployees(),1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void company() throws Exception {
        new Company("");
    }
    @Test
    public void Calendar()  {
        String txt = "a";
        assertNull(App.getInstance().getCompany().tStringToCalendar(txt));
    }
}