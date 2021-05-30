package app.domain.model;

public interface ExternalModule {
    /**
     * methods to be overwriten in the classes that implement this interface
     * @param param parameter where the information from the API will be brought
     * @return the reference value concerning the parameter passed
     */
    ReferenceValue getReferenceValue(Parameter param);

}
