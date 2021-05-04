package app.domain.model;

import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class TestType {

	private String code;
	private String description;
	private String collectingMethod;
	private List<ParameterCategory> parameterCategories;
	
	private TestType(String code, String description, String collectingMethod, 
			List<ParameterCategory> parameterCategories) {
		checkCodeRules(code);
		checkDescriptionRules(description);
		checkCollectingMethodRules(collectingMethod);
		
		this.code = code;
		this.description = description;
		this.collectingMethod = collectingMethod;
		this.parameterCategories = parameterCategories;
	}
	
	private void checkCodeRules(String code) {
        if (StringUtils.isBlank(code)) {
            throw new IllegalArgumentException("Code cannot be blank.");
        }
        
        if (code.length() != 5 && code.length() > 0) {
            throw new IllegalArgumentException("Code must have 1 to 5 chars.");
        }
    }
	
	private void checkDescriptionRules(String description) {
	    if ((description.length()) < 1 || description.length() > 15) {
	        throw new IllegalArgumentException("Description must have 1 to 15 chars.");
	    }
	}
	
	private void checkCollectingMethodRules(String collectingMethod) {
		if ((collectingMethod.length()) < 1 || collectingMethod.length() > 20) {
			throw new IllegalArgumentException("Collecting Method must have 1 to 20 chars");
		}
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
