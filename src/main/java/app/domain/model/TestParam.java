package app.domain.model;

import java.util.List;

public class TestParam {
    List<Parameter> parametersSelected;
    public TestParam(Test test) {
        this.parametersSelected = test.getParameter();
    }



    public Parameter findParameterInTestParameter(String parameterCode) {
        for (Parameter parameter : parametersSelected) {
            if (parameter.getCode().equals(parameterCode)) {
                return parameter;
            }
        }
        return null; // not found
    }
    public Parameter findParameterInTestParameter(String parameterCode,List<Parameter> parameters) {
        for (Parameter parameter : parameters) {
            if (parameter.getCode().equals(parameterCode)) {
                return parameter;
            }
        }
        return null; // not found
    }

}
