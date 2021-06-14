package app.domain.store;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import app.controller.App;
import app.controller.TestTypeController;
import app.domain.model.Company;
import app.domain.model.ParameterCategory;

public class TestTypeStoreTest {

	@Test
    public void getParameterCategories() {
    	ParameterCategoryStore categoryStore = new ParameterCategoryStore();
    	ParameterCategory pc = new ParameterCategory("54321","HEMOGRAM");
    	categoryStore.saveParameterCategory(pc);
    	
    	List<ParameterCategory> pcList = categoryStore.getParameterCategories();
    	
        assertEquals(pcList.size(), 1);
        assertEquals(pcList.get(0), pc);
    }
	
	@Test
    public void invalidCodeLength() {
		Company cmp = App.getInstance().getCompany();
		cmp.getTestTypeStore();
		ParameterCategoryStore csStore = cmp.getParameterCategoryStore();
		ParameterCategory pc = new ParameterCategory("99999", "Um nome");
		csStore.saveParameterCategory(pc);
		
		String code = "5431";
		String description = "uma descri��o";
		String collectingMethod = "colheita";
		String categoryCode = pc.getCode();
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
		assertEquals("Code must have 5 chars.", exception.getMessage());
    }
	
	@Test
    public void invalidCodeChars() {
		Company cmp = App.getInstance().getCompany();
		cmp.getTestTypeStore();
		ParameterCategoryStore csStore = cmp.getParameterCategoryStore();
		ParameterCategory pc = new ParameterCategory("88888", "Um nome");
		csStore.saveParameterCategory(pc);
		
		String code = "5431$";
		String description = "uma descri��o";
		String collectingMethod = "colheita";
		String categoryCode = pc.getCode();
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
		assertEquals("Code must be an alphanumeric.", exception.getMessage());
    }
	
	@Test
    public void invalidCodeAlreadyExists() {
		Company cmp = App.getInstance().getCompany();
		cmp.getTestTypeStore();
		ParameterCategoryStore csStore = cmp.getParameterCategoryStore();
		ParameterCategory pc = new ParameterCategory("77777", "Um nome");
		csStore.saveParameterCategory(pc);
		
		String code = "94321";
		String description = "uma descri��o";
		String collectingMethod = "colheita";
		String categoryCode = pc.getCode();
		List<String> parameterCategoryCodes = new ArrayList<String>();
		parameterCategoryCodes.add(categoryCode);
		
		TestTypeController ttController = new TestTypeController();
		ttController.createTestType(code, description, collectingMethod, parameterCategoryCodes);
		ttController.saveTestType();
		
		Exception exception = null;
		try {
			ttController.createTestType(code, description, collectingMethod, parameterCategoryCodes);
		} catch (Exception ex) {
			exception = ex;
		}

		assertNotNull(exception);
		assertEquals("Code already exist.", exception.getMessage());
    }
	
	@Test
    public void invalidDescriptionEmpty() {
		Company cmp = App.getInstance().getCompany();
		cmp.getTestTypeStore();
		ParameterCategoryStore csStore = cmp.getParameterCategoryStore();
		ParameterCategory pc = new ParameterCategory("22222", "Um nome");
		csStore.saveParameterCategory(pc);
		
		String code = "34321";
		String description = "";
		String collectingMethod = "colheita";
		String categoryCode = pc.getCode();
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
		assertEquals("Description must have 1 to 15 chars.", exception.getMessage());
    }
	
	@Test
    public void invalidDescriptionBig() {
		Company cmp = App.getInstance().getCompany();
		cmp.getTestTypeStore();
		ParameterCategoryStore csStore = cmp.getParameterCategoryStore();
		ParameterCategory pc = new ParameterCategory("AB123", "Um nome");
		csStore.saveParameterCategory(pc);
		
		String code = "123AB";
		String description = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		String collectingMethod = "colheita";
		String categoryCode = pc.getCode();
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
		assertEquals("Description must have 1 to 15 chars.", exception.getMessage());
    }
	
	@Test
    public void invalidCollectingMethodEmpty() {
		Company cmp = App.getInstance().getCompany();
		ParameterCategoryStore csStore = cmp.getParameterCategoryStore();
		ParameterCategory pc = new ParameterCategory("33333", "Um nome");
		csStore.saveParameterCategory(pc);
		
		String code = "14321";
		String description = "uma descri��o";
		String collectingMethod = "";
		String categoryCode = pc.getCode();
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
		assertEquals("Collecting Method must have 1 to 20 chars.", exception.getMessage());
    }
	
	@Test
    public void invalidCollectingMethodBig() {
		Company cmp = App.getInstance().getCompany();
		ParameterCategoryStore csStore = cmp.getParameterCategoryStore();
		ParameterCategory pc = new ParameterCategory("ABCD1", "Um nome");
		csStore.saveParameterCategory(pc);
		
		String code = "AB2D1";
		String description = "uma descri��o";
		String collectingMethod = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		String categoryCode = pc.getCode();
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
		assertEquals("Collecting Method must have 1 to 20 chars.", exception.getMessage());
    }
	
	@Test
    public void invalidParameterCategories() {
		Company cmp = App.getInstance().getCompany();
		cmp.getTestTypeStore();
		
		String code = "81321";
		String description = "uma descri��o";
		String collectingMethod = "colheita";
		List<String> parameterCategoryCodes = new ArrayList<String>();
		
		TestTypeController ttController = new TestTypeController();
		
		Exception exception = null;
		try {
			ttController.createTestType(code, description, collectingMethod, parameterCategoryCodes);
		} catch (Exception ex) {
			exception = ex;
		}

		assertNotNull(exception);
		assertEquals("Parameter categories cannot be empty.", exception.getMessage());
    }
}
