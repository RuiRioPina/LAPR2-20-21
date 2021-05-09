package app.domain.store;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import app.domain.model.ParameterCategory;
import app.domain.model.TestType;

public class TestTypeStore {
	/**
     * Object oriented class used to Store the test types within the company.
     */
	private List<TestType> testTypes;

	/**
     * TestTypeStore constructor.
     */
	public TestTypeStore() {
		this.testTypes = new ArrayList<TestType>();
	}
	
	/**
     * Creates an object of TestType class.
     * @param code - code of the test type.
     * @param description - description of the test type.
     * @param collectingMethod - the collecting method of the test type.
     * @param parameterCategories - list of parameter category.
     * @return Object of TestType class.
     */
	public TestType createTestType(String code, String description, String collectingMethod, 
			List<ParameterCategory> parameterCategories) {
		return new TestType(code, description, collectingMethod, parameterCategories);
	}

	/**
     * Gets the list of the objects of TestType class.
     * @return list of object of TestType class.
     */
	public List<TestType> getTestTypes() {
		List<TestType> ts = new ArrayList<TestType>();
		ts.addAll(this.testTypes);
		return ts;
	}
	
	/**
     * Validates a test type.
     * @param tt - test type.
     */
	public void validateTestType(TestType tt) throws IllegalArgumentException {
		checkCodeRules(tt.getCode());
		checkDescriptionRules(tt.getDescription());
		checkCollectingMethodRules(tt.getCollectingMethod());
		checkParameterCategoryRules(tt.getParameterCategories());
	}
	
	/**
     * Saves a test type.
     * @param tt - test type.
     */
	public void saveTestType(TestType tt) throws IllegalArgumentException {
		validateTestType(tt);
		addTestType(tt);
	}
	
	/**
     * Adds a test type.
     * @param tt - test type.
     */
	private void addTestType(TestType tt) {
		this.testTypes.add(tt);
	}

	/**
     * Checks test type code rules.
     * @param code - code of the test type.
     */
	private void checkCodeRules(String code) throws IllegalArgumentException {
        if (code.length() != 5) {
            throw new IllegalArgumentException("Code must have 5 chars.");
        }
        
        if (!code.matches("^[a-zA-Z0-9]*$")) {
        	throw new IllegalArgumentException("Code must be an alphanumeric.");
        }
        
        if (this.getTestTypeByCode(code) != null) {
        	throw new IllegalArgumentException("Code already exist.");
        }
    }
	
	/**
     * Checks test type description rules.
     * @param description - description of the parameter.
     */
	private void checkDescriptionRules(String description) throws IllegalArgumentException {
	    if ((description.length()) < 1 || description.length() > 15) {
	        throw new IllegalArgumentException("Description must have 1 to 15 chars.");
	    }
	}
	
	/**
     * Checks test type collecting method rules.
     * @param collectingMethod - collecting method of the test type.
     */
	private void checkCollectingMethodRules(String collectingMethod) throws IllegalArgumentException {
		if ((collectingMethod.length()) < 1 || collectingMethod.length() > 20) {
			throw new IllegalArgumentException("Collecting Method must have 1 to 20 chars.");
		}
	}
	
	/**
     * Checks test type parameter categories rules.
     * @param parameterCategories - parameter categories of the test type.
     */
	private void checkParameterCategoryRules(List<ParameterCategory> parameterCategories) throws IllegalArgumentException {
		if (parameterCategories.isEmpty()) {
			throw new IllegalArgumentException("Parameter categories cannot be empty.");
		}
	}
	
	/**
     * Gets the list of a test type by its code.
     * @param testTypeCode - code of a test type
     * @return list of object of TestType class.
     */
	public TestType getTestTypeByCode(String testTypeCode) {
		for(TestType tt : this.testTypes) {
			if(tt.getCode().equals(testTypeCode)) {
				return tt;
			}
		}
		return null;
	}
}
