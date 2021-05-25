package app.domain.model;

import java.util.List;

public class Parameter {

    /**
     * Object oriented Class to the specification of a parameter in a company context.
     */
    private final String code;
    private final String shortname;
    private final String description;
    private TestResult testResult;
    private List<ParameterCategory> pc;

    public TestResult getTestResult() {
        return testResult;
    }

    /**
     * Constructor for the parameter.
     *
     * @param code        - code of parameter.
     * @param shortname   - shortname of parameter.
     * @param description - description of parameter.
     * @param pc          - parameter category.
     */
    public Parameter(String code, String shortname, String description, List<ParameterCategory> pc) {
        this.code = code;
        this.shortname = shortname;
        this.description = description;
        this.pc = pc;
        this.testResult = null;
    }

    /**
     * Returns the code of the parameter.
     *
     * @return code of the parameter.
     */
    public String getCode() {
        return code;
    }

    /**
     * Returns the short name of the parameter.
     *
     * @return shortname of the parameter.
     */
    public String getShortname() {
        return shortname;
    }

    /**
     * Returns the description of the parameter.
     *
     * @return description of the parameter.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the parameter category.
     *
     * @return parameter category.
     */
    public List<ParameterCategory> getPc() {
        return pc;
    }

    public void setTestResult(TestResult testResult) {
        this.testResult = testResult;
    }


    @Override
    public String toString() {
            return "Parameter{" +
                    "code='" + code + '\'' +
                    ", shortname='" + shortname + '\'' +
                    ", description='" + description + '\'' +
                    ", Parameter Category=" + pc +
                    '}' + "Test Result =" + testResult;   }
}
