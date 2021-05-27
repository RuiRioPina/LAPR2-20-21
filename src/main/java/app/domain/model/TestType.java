package app.domain.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Properties;

public class TestType {

    /**
     * Object oriented Class to the specification of a test type in a company context.
     */

    private final String code;
    private final String description;
    private final String collectingMethod;
    private final List<ParameterCategory> parameterCategories;
    private final Properties props = new Properties();

    /**
     * Constructor for the test type.
     *
     * @param code                - code of test type.
     * @param description         - description of test type.
     * @param collectingMethod    - collecting method of test type.
     * @param parameterCategories - list of parameter categories.
     */
    public TestType(String code, String description, String collectingMethod,
                    List<ParameterCategory> parameterCategories) {
        this.code = code;
        this.description = description;
        this.collectingMethod = collectingMethod;
        this.parameterCategories = parameterCategories;
    }

    /**
     * Returns the code of the test type.
     *
     * @return code of the test type.
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Returns the description of the test type.
     *
     * @return description of the test type.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the collecting method of the test type.
     *
     * @return collecting method of the test type.
     */
    public String getCollectingMethod() {
        return this.collectingMethod;
    }

    /**
     * Returns the parameter category.
     *
     * @return parameter category.
     */
    public List<ParameterCategory> getParameterCategories() {
        return this.parameterCategories;
    }

    /**
     * Turns the test type into string.
     *
     * @return result - result of the string of the test type.
     */
    public String toString() {
        StringBuilder result = new StringBuilder(String.format("%s - %s - %s", this.code, this.description, this.collectingMethod));
        for (ParameterCategory pc : this.parameterCategories) {
            result.append(String.format(" - %s", pc));
        }
        return result.toString();
    }

    public ReferenceValue checkExternalModuleBasedOnTestType(Parameter parameterCode) {
        Class<?> oClass = null;
        try {
            oClass = Class.forName("app.domain.model.ExternalAdapter2");
            ExternalModule em = (ExternalModule) oClass.getDeclaredConstructor().newInstance();
            return em.getReferenceValue(parameterCode);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    //private ExternalModule readPropertiesFile() {
    //
    //        ExternalModule em = null;
    //        try (InputStream in = new FileInputStream("config.properties")) {
    //            props.load(in);
    //        } catch (IOException exception) {
    //            exception.getMessage();
    //        }
    //        String classToUse = props.getProperty("Automatic.ValidationAPI");
    //        Class<?> oClass = null;
    //        try {
    //            oClass = Class.forName(classToUse);
    //            em = (ExternalModule) oClass.getDeclaredConstructor().newInstance();
    //        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
    //            e.printStackTrace();
    //        }
    //        return em;
    //    }

}
