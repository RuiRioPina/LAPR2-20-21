package app.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import app.domain.model.Company;
import app.domain.model.ParameterCategory;
import app.domain.model.TestType;
import app.domain.store.ParameterCategoryStore;
import app.domain.store.TestTypeStore;

public class ParameterCategoryControllerTest {

	@Test
    public void createAndParameterCategory() {
		Company cmp = App.getInstance().getCompany();
		ParameterCategoryStore csStore = cmp.getParameterCategoryStore();
		
		String code = "ABD19";
		String name = "alguma";
		
		int count = csStore.getParameterCategories().size();
		
		ParameterCategoryController pcController = new ParameterCategoryController();
		pcController.createParameterCategory(code, name);
		pcController.saveParameterCategory();
    	
		List<ParameterCategory> parameterCategories = csStore.getParameterCategories();
		
        assertEquals(count + 1, parameterCategories.size());
        
        ParameterCategory pc = parameterCategories.get(count);        
        assertEquals(code, pc.getCode());
        assertEquals(name, pc.getName());
    }
}
