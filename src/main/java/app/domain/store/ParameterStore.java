package app.domain.store;

import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ParameterStore implements Serializable {
    /**
     * Object oriented class used to Store the parameters within the company.
     */
    private final List <Parameter> parameter;

    /**
     * Parameter Store constructor.
     */
    public ParameterStore() {
        this.parameter = new ArrayList<>();
    }

    /**
     * Creates an object of Parameter class.
     * @param code - code of the parameter.
     * @param shortname - short name of the parameter.
     * @param description - description of the parameter.
     * @param parameterCategories - parameter category.
     * @return Object of Parameter class.
     */
    public Parameter createParameter(String code, String shortname, String description, List<ParameterCategory> parameterCategories) {
        return new Parameter(code,shortname,description, parameterCategories);
    }

    public Parameter getParameterByCode(String parameterCode) {
        for(Parameter p : this.parameter) {
            if(p.getCode().equals(parameterCode)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Method to get all Parameters stored.
     * @return all parameters.
     */
    public List<Parameter> getParameters() {
        List<Parameter> p = new ArrayList<>();
        p.addAll(this.parameter);
        return p;
    }

    /**
     * Method to get the Parameters from a specific category.
     * @param cat - (Parameter) Category.
     * @return a list with the Parameters of the specific category.
     */
    public List<Parameter> getParameterByCategory(ParameterCategory cat) {
        List<Parameter> p = new ArrayList<>();
        List<Parameter> p2 = new ArrayList<>();
        p.addAll(this.parameter);
        for (int i = 0; i < p.size(); i++){
            if (p.get(i).getPc().get(0) == cat){
                p2.add(p.get(i));
            }
        }
        return p2;
    }

    /**
     * Validates a Parameter.
     * @param p - parameter.
     */

    public void validateParameter(Parameter p) {
        checkCodeRules(p.getCode());
        checkShortnameRules(p.getShortname());
        checkDescriptionRules(p.getDescription());
        checkParameterCategoryRules(p.getPc());

    }

    /**
     * Saves a Parameter.
     * @param p - parameter.
     */
    public void saveParameter(Parameter p) {
        validateParameter(p);
        addParameter(p);
    }

    /**
     * Adds a Parameter to the list.
     * @param p - parameter.
     */
    private void addParameter(Parameter p) {
        this.parameter.add(p);
    }

    /**
     * Checks parameter code rules.
     * @param code - code of the parameter.
     */
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

    /**
     * Checks parameter short name rules.
     * @param shortname - short name of the parameter.
     */
    void checkShortnameRules(String shortname){
        if ( (shortname.length() < 1) || (shortname.length() > 8))
            throw new IllegalArgumentException("Short Name must have 1 to 8 chars.");
    }

    /**
     * Checks parameter description rules.
     * @param description - description of the parameter.
     */
    void checkDescriptionRules(String description) {
        if ((description.length()) < 1 || description.length() > 20){
            throw new IllegalArgumentException("Description must have 1 to 20 chars.");
        }
    }

    /**
     * Checks parameter category rules.
     * @param parameterCategories - parameter category.
     */
    void checkParameterCategoryRules(List<ParameterCategory> parameterCategories) {
        if (parameterCategories.isEmpty()) {
            throw new IllegalArgumentException("Parameter categories cannot be empty.");
        }
    }

    /**
     * Method to check if a Parameter is listed.
     * @param p - parameter.
     * @return the value of Parameter
     */
    public boolean isParameterInList(Parameter p) {
        return this.parameter.contains(p);
    }

}

