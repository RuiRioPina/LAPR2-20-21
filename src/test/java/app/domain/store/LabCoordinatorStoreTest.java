package app.domain.store;

import app.domain.model.Client;
import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;
import app.domain.model.TestType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

public class LabCoordinatorStoreTest {
/*
    @Test
    public void getNnr() {
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
        assertEquals(lc.getNumberOfTNR(testList),0);
        List<app.domain.model.Test> test = new ArrayList<>();
        t.setSamplesCollectionDate(data);
        test.add(t);
        assertEquals(lc.getNumberOfTNR(test),1);
        assertEquals(lc.getNumberOfTR(testList),0);
        List<app.domain.model.Test> test1 = new ArrayList<>();
        t.setValidationDate(data);
        test1.add(t);
        assertEquals(lc.getNumberOfTR(test1),1);
    }


 */



    @Test
    public void setInterval() {

    }

    @Test
    public void maxSubArraySum() {
        int [] list = new int [10];
        list [1] = 1;
        list [2] = 3;
        LabCoordinatorStore lc = new LabCoordinatorStore();
        assertNotEquals(lc.maxSubArraySum(list),lc.maxSubArraySum(list));
    }


    @Test
    public void isTestInInterval() {
    }

    @Test
    public void tStringToCalendar() {
    }

    @Test
    public void getMax() {
    }

    @Test
    public void maxPlaces() {
    }

    @Test
    public void countNumber() {
    }
}