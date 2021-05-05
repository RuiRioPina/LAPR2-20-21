package app.domain.store;

import java.util.ArrayList;
import java.util.List;
import app.domain.model.ParameterCategory;

public class ParameterCategoryStore {

	private List<ParameterCategory> parameterCategories;
	
	public ParameterCategoryStore() {
		this.parameterCategories = new ArrayList<ParameterCategory>();
	}
	
	public List<ParameterCategory> getParameterCategories() {
		List<ParameterCategory> pc = new ArrayList<ParameterCategory>();
		pc.addAll(this.parameterCategories);
		return pc;
	}

	public ParameterCategory getParameterCategoryByCode(String parameterCategoryCode) {
		for(ParameterCategory pc : this.parameterCategories) {
			if(pc.getCode().equals(parameterCategoryCode)) {
				return pc;
			}
		}
		return null;
	}
	
	public void saveParameterCategory(ParameterCategory pc) {
		this.parameterCategories.add(pc);
	}
}
