package app.domain.model;

import java.io.Serializable;

public class ParameterCategory implements Serializable {

    private final String code;
	private final String name;
	
    public ParameterCategory(String code, String name) {
        this.code = code;
        this.name = name;
    }

	public String getName() {
		return this.name;
	}

	public String getCode() {
		return this.code;
	}

	public String toString() {
		return String.format("%s | %s", this.code, this.name);
	}
}