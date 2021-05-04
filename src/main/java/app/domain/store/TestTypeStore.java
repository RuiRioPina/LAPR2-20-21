package app.domain.store;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import app.domain.model.ParameterCategory;
import app.domain.model.TestType;

public class TestTypeStore {
	
	private List<TestType> testTypes;

	public TestTypeStore() {
		this.testTypes = new ArrayList<TestType>();
	}
	
	public TestType createTestType(String code, String description, String collectingMethod, 
			List<ParameterCategory> parameterCategories) {
		return new TestType(code, description, collectingMethod, parameterCategories);
	}
	
	public void validateTestType(TestType tt) throws IllegalArgumentException {
		checkCodeRules(tt.getCode());
		checkDescriptionRules(tt.getDescription());
		checkCollectingMethodRules(tt.getCollectingMethod());
		checkParameterCategoryRules(tt.getParameterCategories());
	}
	
	public void saveTestType(TestType tt) throws IllegalArgumentException {
		validateTestType(tt);
		addTestType(tt);
	}
	
	private void addTestType(TestType tt) {
		this.testTypes.add(tt);
	}
	
	private void checkCodeRules(String code) throws IllegalArgumentException {
        if (code.length() != 5) {
            throw new IllegalArgumentException("Code must have 5 chars.");
        }
        
        if (!code.matches("^[a-zA-Z0-9]*$")) {
        	throw new IllegalArgumentException("Code must be an alphanumeric.");
        }
    }
	
	private void checkDescriptionRules(String description) throws IllegalArgumentException {
	    if ((description.length()) < 1 || description.length() > 15) {
	        throw new IllegalArgumentException("Description must have 1 to 15 chars.");
	    }
	}
	
	private void checkCollectingMethodRules(String collectingMethod) throws IllegalArgumentException {
		if ((collectingMethod.length()) < 1 || collectingMethod.length() > 20) {
			throw new IllegalArgumentException("Collecting Method must have 1 to 20 chars.");
		}
	}
	
	private void checkParameterCategoryRules(List<ParameterCategory> parameterCategories) throws IllegalArgumentException {
		if (parameterCategories.isEmpty()) {
			throw new IllegalArgumentException("Parameter categories cannot be empty.");
		}
	}
}
