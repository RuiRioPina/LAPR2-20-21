package app.controller;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import app.domain.model.Client;
import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;
import app.domain.model.TestType;
import app.domain.store.TestStore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GenerateSampleControllerTest {

	@Test
	public void gestTestsWithoutSamples() {
		List<app.domain.model.Test> lt = App.getInstance().getCompany().getTestStore().getTestsWithoutSamples();
		GenerateSampleController sController = new GenerateSampleController();
		
		List<app.domain.model.Test> tList = sController.getTestsWithoutSamples();
		
        assertArrayEquals(lt.toArray(), tList.toArray());
	}
	
	@Test
	public void createSampleValid() {
		GenerateSampleController sController = new GenerateSampleController();
		String testCode = "932992911345";
		int numberOfSamples = 2;
		
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
        
		app.domain.model.Test t = new app.domain.model.Test("abcdefghi112", testCode, c, tt1, pc, par, data);
		TestStore tStore = App.getInstance().getCompany().getTestStore();
		tStore.saveTest(t);
		
		Exception ex = null;
		try {
			sController.createSamples(testCode, numberOfSamples);
		} catch (Exception e) {
			ex = e;
		}
		
		assertNull("Error creating samples.", ex);
		assertEquals(numberOfSamples, t.getSamples().size());
	}
}