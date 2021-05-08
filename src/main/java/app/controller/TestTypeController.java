package app.controller;

import java.util.ArrayList;
import java.util.List;

import app.domain.model.Company;
import app.domain.model.ParameterCategory;
import app.domain.model.TestType;
import app.domain.store.ParameterCategoryStore;
import app.domain.store.TestTypeStore;

public class TestTypeController {

	private Company company;
	private TestType testType;
	
	public TestTypeController() {
		this.company = App.getInstance().getCompany();
	}
	
	public List<ParameterCategory> getParameterCategories() {
		ParameterCategoryStore cs = this.company.getParameterCategoryStore();
		return cs.getParameterCategories();
	}
	
	public TestType createTestType(String code, String description, String collectingMethod,
			List<String> parameterCategoryCodes) throws IllegalArgumentException {
		ParameterCategoryStore cs = this.company.getParameterCategoryStore();
		
		List<ParameterCategory> parameterCategories = new ArrayList<ParameterCategory>();
		for(String parameterCategoryCode : parameterCategoryCodes) {
			ParameterCategory pc = cs.getParameterCategoryByCode(parameterCategoryCode);
			if(pc==null) {
				throw new IllegalArgumentException("Parameter category code not found.");
			}
			parameterCategories.add(pc);
		}
		
		TestTypeStore ts = this.company.getTestTypeStore();
		TestType tt = ts.createTestType(code, description, collectingMethod, parameterCategories);
		ts.validateTestType(tt);
		
		this.testType = tt;
		return this.testType;
	}
	
	public void saveTestType() throws IllegalArgumentException {
		TestTypeStore ts = this.company.getTestTypeStore();
		ts.saveTestType(this.testType);
	}
}
