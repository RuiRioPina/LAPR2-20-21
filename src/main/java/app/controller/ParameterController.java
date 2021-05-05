package app.controller;

import app.domain.model.Company;
import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;
import app.domain.store.ParameterCategoryStore;
import app.domain.store.ParameterStore;

import java.util.ArrayList;
import java.util.List;

public class ParameterController {
    private Company company;
    private Parameter parameter ;

    public ParameterController() {
        this.company = App.getInstance().getCompany();
    }

    public List<ParameterCategory> getParameterCategories() {
        ParameterCategoryStore cs = this.company.getParameterCategoryStore();
        return cs.getParameterCategories();
    }

    public Parameter createParameter(String code,String shortname, String description,
                                     List<String> parameterCategoryCodes) throws IllegalArgumentException {

        ParameterCategoryStore pcs = this.company.getParameterCategoryStore();

        List<ParameterCategory> parameterCategories = new ArrayList<ParameterCategory>();
        for(String parameterCategoryCode : parameterCategoryCodes) {
            parameterCategories.add(pcs.getParameterCategoryByCode(parameterCategoryCode));
        }

        ParameterStore ps = this.company.getParameterStore();
        Parameter p = ps.createParameter(code, shortname,description, parameterCategories);
        ps.validateParameter(p);
        ps.printCategoryList();

        this.parameter = p;
        return this.parameter;
    }

    public void saveParameter() throws IllegalArgumentException {
        ParameterStore ps = this.company.getParameterStore();
        ps.saveParameter(this.parameter);
    }
}
