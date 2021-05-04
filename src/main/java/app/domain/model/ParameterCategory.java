package app.domain.model;

import org.apache.commons.lang3.StringUtils;
//FROM ESOFT SLIDES
public class ParameterCategory {

	private String name;
    private String code;

    public ParameterCategory(String name, String code) {
        checkCodeRules(code);
        this.name = name;
        this.code = code;
    }

    private void checkCodeRules(String code) {
        if (StringUtils.isBlank(code))
            throw new IllegalArgumentException("Code cannot be blank.");
        if ( (code.length() < 4) || (code.length() > 8))
            throw new IllegalArgumentException("Code must have 4 to 8 chars.");
    }
}