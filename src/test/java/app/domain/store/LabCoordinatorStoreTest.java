package app.domain.store;

import app.domain.model.Client;
import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;
import app.domain.model.TestType;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class LabCoordinatorStoreTest {

    @Test
    public void maxSubArraySum() {
        int [] list = new int [10];
        list [1] = 1;
        list [2] = 3;
        LabCoordinatorStore lc = new LabCoordinatorStore();
        assertNotEquals(LabCoordinatorStore.maxSubArraySum(list), LabCoordinatorStore.maxSubArraySum(list));
    }

    @Test
    public void maxSubArray() {
        int [] list = new int [10];
        list [1] = 1;
        list [2] = 3;
        LabCoordinatorStore lc = new LabCoordinatorStore();
        assertNotEquals(LabCoordinatorStore.maxSubArray(list), LabCoordinatorStore.maxSubArray(list));
    }

@Test
    public void testGetNumberTestsByYear() {
    Client c = new Client(1234567890123456L, 1234567890, "22-01-2002", "jorge@gmail.com", 1111111111L, 22222222222L, "Jorge Ferreira");
    List<ParameterCategory> pc = new ArrayList<>();
    ParameterCategory p1 = new ParameterCategory("CAT00", "Category00");
    pc.add(p1);
    TestType tt1 = new TestType("BLT00", "Blood Test", "Venipuncture", pc);
    TestStore ts = new TestStore();
    List<Parameter> par = new ArrayList<>();
    Parameter par1= new Parameter("HB000", "HB", "Haemoglobin", pc);
    par.add(par1);
    Calendar data = Calendar.getInstance();
    app.domain.model.Test t = ts.createTest("abcdefghijkl", "900000000000", c, tt1, pc, par, data);
    List <app.domain.model.Test> testList = new ArrayList<>();
    testList.add(t);
    LabCoordinatorStore lc = new LabCoordinatorStore();
    Map <String, Integer> a = lc.getNumberTestsByDay(testList,data,data,"Covid");
    assertEquals(lc.getNumberTestsByDay(testList,data,data,"Covid"),a);
    Map <String, Integer> b = lc.getNumberTestsByWeek(testList,data,data,"Covid");
    assertEquals(b,lc.getNumberTestsByWeek(testList,data,data,"Covid"));
    Map <String, Integer> cc = lc.getNumberTestsByMonth(testList,data,data,"Covid");
    assertEquals(lc.getNumberTestsByMonth(testList,data,data,"Covid"),cc);
    Map <String, Integer> d = lc.getNumberTestsByYear(testList,data,data,"Covid");
    assertEquals(d,lc.getNumberTestsByYear(testList,data,data,"Covid"));
    }

    @Test
    public void testGetNumOfTReadyBy() {
        Client c = new Client(1234567890123456L, 1234567890, "22-01-2002", "jorge@gmail.com", 1111111111L, 22222222222L, "Jorge Ferreira");
        List<ParameterCategory> pc = new ArrayList<>();
        ParameterCategory p1 = new ParameterCategory("CAT00", "Category00");
        pc.add(p1);
        TestType tt1 = new TestType("BLT00", "Blood Test", "Venipuncture", pc);
        TestStore ts = new TestStore();
        List<Parameter> par = new ArrayList<>();
        Parameter par1= new Parameter("HB000", "HB", "Haemoglobin", pc);
        par.add(par1);
        Calendar data = Calendar.getInstance();
        app.domain.model.Test t = ts.createTest("abcdefghijkl", "900000000000", c, tt1, pc, par, data);
        List <app.domain.model.Test> testList = new ArrayList<>();
        testList.add(t);
        LabCoordinatorStore lc = new LabCoordinatorStore();
        assertEquals(lc.getNumOfTReadyByDay(testList,data,"clients"),1);
        assertEquals(lc.getNumOfTReadyByDay(testList,data,"readyTests"),0);
        assertEquals(lc.getNumOfTReadyByDay(testList,data,"missingResultsTests"),0);
        assertEquals(lc.getNumOfTReadyByDay(testList,data,"diagnosisTests"),0);
        assertEquals(lc.getNumOfTReadyByMonth(testList,data,"clients",data,data),1);
        assertEquals(lc.getNumOfTReadyByMonth(testList,data,"diagnosisTests",data,data),0);
        assertEquals(lc.getNumOfTReadyByMonth(testList,data,"missingResultsTests",data,data),0);
        assertEquals(lc.getNumOfTReadyByMonth(testList,data,"readyTests",data,data),0);
        assertEquals(lc.getNumOfTReadyByWeek(testList,data,"clients",data,data),1);
        assertEquals(lc.getNumOfTReadyByWeek(testList,data,"diagnosisTests",data,data),0);
        assertEquals(lc.getNumOfTReadyByWeek(testList,data,"missingResultsTests",data,data),0);
        assertEquals(lc.getNumOfTReadyByYear(testList,data,"clients",data,data),1);
        assertEquals(lc.getNumOfTReadyByYear(testList,data,"readyTests",data,data),0);
        assertEquals(lc.getNumOfTReadyByYear(testList,data,"missingResultsTests",data,data),0);
        assertEquals(lc.getNumOfTReadyByYear(testList,data,"diagnosisTests",data,data),0);
    }

    @Test
    public void others() {
        Client c = new Client(1234567890123456L, 1234567890, "22-01-2002", "jorge@gmail.com", 1111111111L, 22222222222L, "Jorge Ferreira");
        List<ParameterCategory> pc = new ArrayList<>();
        ParameterCategory p1 = new ParameterCategory("CAT00", "Category00");
        pc.add(p1);
        TestType tt1 = new TestType("BLT00", "Blood Test", "Venipuncture", pc);
        TestStore ts = new TestStore();
        List<Parameter> par = new ArrayList<>();
        Parameter par1= new Parameter("HB000", "HB", "Haemoglobin", pc);
        par.add(par1);
        Calendar data = Calendar.getInstance();
        app.domain.model.Test t = ts.createTest("abcdefghijkl", "900000000000", c, tt1, pc, par, data);
        List <app.domain.model.Test> testList = new ArrayList<>();
        testList.add(t);
        LabCoordinatorStore lc = new LabCoordinatorStore();
        LocalDateTime D = LocalDateTime.now();
        assertNotEquals(D, LabCoordinatorStore.tCalendarToLDT(data));
        assertEquals(1,LabCoordinatorStore.countNumber(2));
    }
    }

