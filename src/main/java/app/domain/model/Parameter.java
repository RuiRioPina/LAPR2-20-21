package app.domain.model;

import java.util.List;

public class Parameter {

    private String code;
    private String shortname;
    private String description;
    private List<ParameterCategory> pc;

    public Parameter(String code, String shortname, String description, List<ParameterCategory>  pc) {
        this.code = code;
        this.shortname = shortname;
        this.description = description;
        this.pc = pc;
    }

    public String getCode() {
        return code;
    }

    public String getShortname() {
        return shortname;
    }

    public String getDescription() {
        return description;
    }

    public List<ParameterCategory> getPc() {
        return pc;
    }
}
