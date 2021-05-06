package app.controller;

import app.domain.model.Company;
import app.domain.model.ParameterCategory;
import app.domain.store.ParameterCategoryStore;

public class ParameterCategoryController {

	private Company company;
	private ParameterCategory parameterCategory;
	
	public ParameterCategoryController() {
		this.company = App.getInstance().getCompany();
	}
	
	public ParameterCategory createParameterCategory(String code, String name) throws IllegalArgumentException {
		ParameterCategoryStore cs = this.company.getParameterCategoryStore();
		ParameterCategory pc = cs.createParameterCategory(code, name);
		cs.validateParameterCategory(pc);
		
		this.parameterCategory = pc;
		return this.parameterCategory;
	}
	
	public void saveParameterCategory() throws IllegalArgumentException {
		ParameterCategoryStore cs = this.company.getParameterCategoryStore();
		cs.saveParameterCategory(this.parameterCategory);
	}
}
