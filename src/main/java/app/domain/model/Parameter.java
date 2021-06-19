package app.domain.model;

import java.io.Serializable;
import java.util.List;

public class Parameter implements Serializable {

    /**
     * Object oriented Class to the specification of a parameter in a company context.
     */
    private final String code;
    private final String shortname;
    private final String description;
    private TestResult testResult;
    private final List<ParameterCategory> pc;

    public TestResult getTestResult() {
        return testResult;
    }

    public Parameter(Parameter another) {
        this.code = another.getCode();
        this.shortname = another.getShortname();
        this.description = another.getDescription();
        this.pc = another.getPc();
        this.testResult = null;
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

    /**
     * Setter of the test Result, associated to the parameter
     * @param testResult the test result to be associated to the parameter
     */
    public void setTestResult(TestResult testResult) {
        this.testResult = testResult;
    }
    /**
     * Method toString overwritten in order to be able to view the relevant information in regards to the parameter
     * @return String containing the information about the parameter.
     */

    @Override
    public String toString() {
        if(testResult!=null) {
            return   code + " - " + description + " - " + pc + " - " + shortname + " - " + testResult;

        }else {
            return code + " - " + description + " - " + pc + " - " + shortname;
        }
    }
}
