package app.controller;

import app.domain.model.Company;
import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;
import app.domain.store.ParameterCategoryStore;
import app.domain.store.ParameterStore;

import java.util.ArrayList;
import java.util.List;

public class ParameterController {

    /**
     * Controller Class for the Specify a new Parameter and categorize it function [US-10 of the integrative project of 1st year ISEP DEI students].
     */

    private Company company;
    private Parameter parameter ;

    /**
     * Constructor of the Controller Class. Gets the company that is using the software.
     */

    public ParameterController() {
        this.company = App.getInstance().getCompany();
    }

    /**
     * Gets the Parameter Category list of the company that is using the software.
     * @return List with parameter categories.
     */

    public List<ParameterCategory> getParameterCategories() {
        ParameterCategoryStore cs = this.company.getParameterCategoryStore();
        return cs.getParameterCategories();
    }

    /**
     * Creates an object of Parameter class.
     * @param code - code of the parameter.
     * @param shortname - shortname of the parameter.
     * @param description - description of the parameter.
     * @param parameterCategoryCodes - parameter category.
     * @return Object of class Parameter.
     */

    public Parameter createParameter(String code,String shortname, String description,
                                     List<String> parameterCategoryCodes) throws IllegalArgumentException {

        ParameterCategoryStore pcs = this.company.getParameterCategoryStore();

        List<ParameterCategory> parameterCategories = new ArrayList<>();
        for(String parameterCategoryCode : parameterCategoryCodes) {
            parameterCategories.add(pcs.getParameterCategoryByCode(parameterCategoryCode));
        }

        ParameterStore ps = this.company.getParameterStore();
        Parameter p = ps.createParameter(code, shortname,description, parameterCategories);
        ps.validateParameter(p);

        this.parameter = p;
        return this.parameter;
    }

    /**
     * Method used to direct the saving of an parameter to the parameter store.
     * @param p  = parameter.
     */

    public void saveParameter(Parameter p) throws IllegalArgumentException {
        ParameterStore ps = this.company.getParameterStore();
        ps.saveParameter(p);
    }
}
