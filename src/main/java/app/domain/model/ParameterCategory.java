package app.domain.model;

public class ParameterCategory {

    private String code;
	private String name;
	
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
		return String.format("%s - %s", this.code, this.name);
	}
}