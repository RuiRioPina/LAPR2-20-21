package app.controller;

import java.util.ArrayList;
import java.util.List;

import app.domain.model.Company;
import app.domain.model.ParameterCategory;
import app.domain.model.TestType;
import app.domain.store.ParameterCategoryStore;
import app.domain.store.TestTypeStore;

public class TestTypeController {

	/**
     * Controller Class for the Specify a new Test Type [US-9 of the integrative project of 1st year ISEP DEI students].
     */
	
	private Company company;
	private TestType testType;
	
	/**
     * Constructor of the Controller Class. Gets the company that is using the software.
     */
	public TestTypeController() {
		this.company = App.getInstance().getCompany();
	}
	
	/**
     * Gets the Parameter Category list of the company that is using the software.
     * @return List with parameter categories.
     */
	public List<ParameterCategory> getParameterCategories() {
		ParameterCategoryStore cs = this.company.getParameterCategoryStore();
		return cs.getParameterCategories();
	}
	
	/**
     * Creates an object of test type class.
     * @param code - code of the test type.
     * @param description - description of the test type.
     * @param collectingMethod - collecting method of the test type.
     * @param parameterCategoryCodes - parameter category.
     * @return Object of class test type.
     */
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
	
	/**
     * Method used to direct the saving of an test type to the TestTypeStore.
     */
	public void saveTestType() throws IllegalArgumentException {
		TestTypeStore ts = this.company.getTestTypeStore();
		ts.saveTestType(this.testType);
	}
}
