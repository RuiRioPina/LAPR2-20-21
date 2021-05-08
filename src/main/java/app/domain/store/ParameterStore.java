package app.domain.store;

import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;

import java.util.ArrayList;
import java.util.List;

public class ParameterStore {

    private List <Parameter> parameter;

    public ParameterStore() {
        this.parameter = new ArrayList<>();
    }

    public Parameter createParameter(String code, String shortname, String description, List<ParameterCategory> parameterCategories) {
        return new Parameter(code,shortname,description, parameterCategories);
    }

    public void validateParameter(Parameter p) throws IllegalArgumentException {
        checkCodeRules(p.getCode());
        checkShortnameRules(p.getShortname());
        checkDescriptionRules(p.getDescription());
        checkParameterCategoryRules(p.getPc());

    }
    public void saveParameter(Parameter p) throws IllegalArgumentException {
        validateParameter(p);
        addParameter(p);
    }

    private void addParameter(Parameter p) {
        this.parameter.add(p);
    }

    void checkCodeRules(String code) {
        if (!code.matches("[A-Za-z0-9]+")) {
            throw new IllegalArgumentException("Code must be alphanumeric.");
        }
        if (code.length() != 5)
            throw new IllegalArgumentException("Code must have 5 chars.");
        if (this.getParameterByCode(code) != null) {
            throw new IllegalArgumentException("Code already exist.");
        }
    }

    void checkShortnameRules(String shortname){
        if ( (shortname.length() < 1) || (shortname.length() > 8))
            throw new IllegalArgumentException("Short Name must have 1 to 8 chars.");
    }

    void checkDescriptionRules(String description) {
        if ((description.length()) < 1 || description.length() > 20){
            throw new IllegalArgumentException("Description must have 1 to 20 chars.");
        }
    }

    void checkParameterCategoryRules(List<ParameterCategory> parameterCategories) throws IllegalArgumentException {
        if (parameterCategories.isEmpty()) {
            throw new IllegalArgumentException("Parameter categories cannot be empty.");
        }
    }

    public boolean isParameterInList(Parameter p) {
        return this.parameter.contains(p);
    }

    public Parameter getParameterByCode(String parameterCode) {
        for(Parameter p : this.parameter) {
            if(p.getCode().equals(parameterCode)) {
                return p;
            }
        }
        return null;
    }

}

