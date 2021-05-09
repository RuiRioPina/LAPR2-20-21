package app.domain.model;

import java.util.List;

public class Parameter {

    /**
     * Object oriented Class to the specification of a parameter in a company context.
     */
    private String code;
    private String shortname;
    private String description;
    private List<ParameterCategory> pc;

    /**
     * Constructor for the parameter.
     * @param code - code of parameter.
     * @param shortname - shortname of parameter.
     * @param description - description of parameter.
     * @param pc - parameter category.
     */
    public Parameter(String code, String shortname, String description, List<ParameterCategory>  pc) {
        this.code = code;
        this.shortname = shortname;
        this.description = description;
        this.pc = pc;
    }

    /**
     * Returns the code of the parameter.
     * @return code of the parameter.
     */
    public String getCode() {
        return code;
    }

    /**
     * Returns the short name of the parameter.
     * @return shortname of the parameter.
     */
    public String getShortname() {
        return shortname;
    }

    /**
     * Returns the description of the parameter.
     * @return description of the parameter.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the parameter category.
     * @return parameter category.
     */
    public List<ParameterCategory> getPc() {
        return pc;
    }
}
