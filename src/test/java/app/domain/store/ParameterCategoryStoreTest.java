package app.domain.store;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import app.controller.App;
import app.controller.ParameterCategoryController;
import app.controller.TestTypeController;
import app.domain.model.Company;
import app.domain.model.ParameterCategory;

public class ParameterCategoryStoreTest {

	
	@Test
    public void invalidCodeLength() {
		Company cmp = App.getInstance().getCompany();
		ParameterCategoryStore csStore = cmp.getParameterCategoryStore();
		
		String code = "AB19";
		String name = "alguma";
		
		ParameterCategoryController pcController = new ParameterCategoryController();
		Exception exception = null;
		try {
			pcController.createParameterCategory(code, name);
		} catch (Exception ex) {
			exception = ex;
		}

		assertNotNull(exception);
		assertEquals("Code must have 5 chars.", exception.getMessage());
    }
	
	@Test
    public void invalidCodeChars() {
		Company cmp = App.getInstance().getCompany();
		ParameterCategoryStore csStore = cmp.getParameterCategoryStore();
		
		String code = "A$$19";
		String name = "alguma";
		
		ParameterCategoryController pcController = new ParameterCategoryController();
		Exception exception = null;
		try {
			pcController.createParameterCategory(code, name);
		} catch (Exception ex) {
			exception = ex;
		}

		assertNotNull(exception);
		assertEquals("Code must be an alphanumeric.", exception.getMessage());
    }
	
	@Test
    public void invalidCodeAlreadyExists() {
		Company cmp = App.getInstance().getCompany();
		ParameterCategoryStore csStore = cmp.getParameterCategoryStore();
		
		String code = "BCA19";
		String name = "alguma";
		
		ParameterCategoryController pcController = new ParameterCategoryController();
		pcController.createParameterCategory(code, name);
		pcController.saveParameterCategory();
		
		Exception exception = null;
		try {
			pcController.createParameterCategory(code, name);
		} catch (Exception ex) {
			exception = ex;
		}

		assertNotNull(exception);
		assertEquals("Code already exist.", exception.getMessage());
    }
	
	@Test
    public void invalidDescriptionEmpty() {
		Company cmp = App.getInstance().getCompany();
		ParameterCategoryStore csStore = cmp.getParameterCategoryStore();
		
		String code = "ACD19";
		String name = "";
		
		ParameterCategoryController pcController = new ParameterCategoryController();
		Exception exception = null;
		try {
			pcController.createParameterCategory(code, name);
		} catch (Exception ex) {
			exception = ex;
		}

		assertNotNull(exception);
		assertEquals("Name must have 1 to 10 chars.", exception.getMessage());
    }
	
	@Test
    public void invalidDescriptionBig() {
		Company cmp = App.getInstance().getCompany();
		ParameterCategoryStore csStore = cmp.getParameterCategoryStore();
		
		String code = "CBD19";
		String name = "algumaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		
		ParameterCategoryController pcController = new ParameterCategoryController();
		Exception exception = null;
		try {
			pcController.createParameterCategory(code, name);
		} catch (Exception ex) {
			exception = ex;
		}

		assertNotNull(exception);
		assertEquals("Name must have 1 to 10 chars.", exception.getMessage());
    }
}
