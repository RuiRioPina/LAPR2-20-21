package app.domain.model;

import org.apache.commons.lang3.StringUtils;

public class Parameter {

    private String code;
    private String shortname;
    private String description;

    public Parameter(String code, String shortname, String description, ParameterCategory pc) {
        checkcoderules(code);
        checkshortnamerules(shortname);
        checkdescriptionrules(description);
        this.code = code;
        this.shortname = shortname;
        this.description = description;
    }

    private void checkdescriptionrules(String description) {
    if ((description.length()) < 1 || description.length() > 20){
        throw new IllegalArgumentException("Description must have 1 to 20 chars.");
    }
    }

    private void checkshortnamerules(String shortname){
        if ( (shortname.length() < 1) || (shortname.length() > 8))
            throw new IllegalArgumentException("Short Name must have 1 to 8 chars.");
    }

    private void checkcoderules(String code) {
        if (StringUtils.isBlank(code))
            throw new IllegalArgumentException("Code cannot be blank.");
        if (code.length() != 5 && code.length() > 0)
            throw new IllegalArgumentException("Code must have 1 to 5 chars.");
    }

}
