package app.domain.model;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class TestType {

	private String code;
	private String description;
	private String collectingMethod;
	private List<ParameterCategory> parameterCategories;
	
	public TestType(String code, String description, String collectingMethod, 
			List<ParameterCategory> parameterCategories) {		
		this.code = code;
		this.description = description;
		this.collectingMethod = collectingMethod;
		this.parameterCategories = parameterCategories;
	}

	public String getCode() {
		return this.code;
	}

	public String getDescription() {
		return this.description;
	}

	public String getCollectingMethod() {
		return this.collectingMethod;
	}

	public List<ParameterCategory> getParameterCategories() {
		return this.parameterCategories;
	}

	public String toString() {
		String result = String.format("%s - %s - %s", this.code, this.description, this.collectingMethod);
		for(ParameterCategory pc : this.parameterCategories) {
			result += String.format(" - %s", pc);
		}
		return result;
	}
	
}
