package app.controller;

import app.domain.model.Company;
import app.domain.model.Parameter;


import app.domain.model.ParameterCategory;
import app.domain.model.TestType;
import app.domain.store.ParameterCategoryStore;
import app.domain.store.ParameterStore;
import app.domain.store.TestTypeStore;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestTypeControllerTest {

	@Test
    public void getParameterCategories() {
		List<ParameterCategory> pc = App.getInstance().getCompany().getParameterCategoryStore().getParameterCategories();
		TestTypeController ttController = new TestTypeController();
		
		List<ParameterCategory> pcList = ttController.getParameterCategories();
		
        assertArrayEquals(pc.toArray(), pcList.toArray());
    }
	
	@Test
    public void createAndSaveTestType() {
		Company cmp = App.getInstance().getCompany();
		TestTypeStore tsStore = cmp.getTestTypeStore();
		ParameterCategoryStore csStore = cmp.getParameterCategoryStore();
		ParameterCategory pc = new ParameterCategory("12345", "Um nome");
		csStore.saveParameterCategory(pc);
		
		String code = "54321";
		String description = "uma descri��o";
		String collectingMethod = "colheita";
		String categoryCode = pc.getCode();
		List<String> parameterCategoryCodes = new ArrayList<String>();
		parameterCategoryCodes.add(categoryCode);
		
		TestTypeController ttController = new TestTypeController();
		ttController.createTestType(code, description, collectingMethod, parameterCategoryCodes);
		ttController.saveTestType();
    	
		List<TestType> testTypes = tsStore.getTestTypes();
		
        assertEquals(1, testTypes.size());
        
        TestType tt = testTypes.get(0);        
        assertEquals(code, tt.getCode());
        assertEquals(description, tt.getDescription());
        assertEquals(collectingMethod, tt.getCollectingMethod());
        
        List<ParameterCategory> pcList = tt.getParameterCategories();
        assertEquals(1, pcList.size());
        assertEquals(categoryCode, pcList.get(0).getCode());
    }
	
	@Test
    public void createAndSaveTestTypeWithMissingCategory() {
		Company cmp = App.getInstance().getCompany();
		TestTypeStore tsStore = cmp.getTestTypeStore();
		
		String code = "54321";
		String description = "uma descri��o";
		String collectingMethod = "colheita";
		String categoryCode = "aaaaa";
		List<String> parameterCategoryCodes = new ArrayList<String>();
		parameterCategoryCodes.add(categoryCode);
		
		TestTypeController ttController = new TestTypeController();
		Exception exception = null;
		try {
			ttController.createTestType(code, description, collectingMethod, parameterCategoryCodes);
		} catch (Exception ex) {
			exception = ex;
		}

		assertNotNull(exception);
		assertEquals("Parameter category code not found.", exception.getMessage());
    }
}