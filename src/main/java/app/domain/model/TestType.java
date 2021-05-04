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
		return code;
	}

	public String getDescription() {
		return description;
	}

	public String getCollectingMethod() {
		return collectingMethod;
	}

	public List<ParameterCategory> getParameterCategories() {
		return parameterCategories;
	}
	
}
