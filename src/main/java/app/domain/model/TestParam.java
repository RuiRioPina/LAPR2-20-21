package app.domain.model;

import java.util.List;

public class TestParam {
    List<Parameter> parametersSelected;
    public TestParam(Test test) {
        this.parametersSelected = test.getParameter();
    }

    /**
     * Find the test parameter of a given test in between all the others parameters that were selected for a test
     * @param parameterCode the parameter being seeked
     * @return the parameter found / or not
     */

    public Parameter findParameterInTestParameter(String parameterCode) {
        for (Parameter parameter : parametersSelected) {
            if (parameter.getCode().equals(parameterCode)) {
                return parameter;
            }
        }
        return null; // not found
    }

    /**
     * Find the test parameter of a given test in between all the others parameters from a given list of parameters
     * @param parameterCode the parameter being seeked
     * @return the parameter found / or not
     */

    public Parameter findParameterInTestParameter(String parameterCode,List<Parameter> parameters) {
        for (Parameter parameter : parameters) {
            if (parameter.getCode().equals(parameterCode)) {
                return parameter;
            }
        }
        return null; // not found
    }

}
