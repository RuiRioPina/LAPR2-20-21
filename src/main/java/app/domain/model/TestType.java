package app.domain.model;

import app.domain.shared.Configuration;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class TestType implements Serializable {

    /**
     * Object oriented Class to the specification of a test type in a company context.
     */

    private final String code;
    private final String description;
    private final String collectingMethod;
    private List<ParameterCategory> parameterCategories;

    private static final String CODE_DEFAULT = "No code";
    private static final String DESCRIPTION_DEFAULT = "No description";
    private static final String COLLECTING_METHOD_DEFAULT = "No collecting method";
    private static final List<ParameterCategory> PARAMETER_CATEGORIES_DEFAULT = null;

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

    public TestType() {
        code = CODE_DEFAULT;
        description = DESCRIPTION_DEFAULT;
        collectingMethod = COLLECTING_METHOD_DEFAULT;
        parameterCategories = PARAMETER_CATEGORIES_DEFAULT;

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

    /**
     * Checks what API will be used, using the testType of the parameter at stake to find the correct one.
     *
     * @param parameterCode the parameter from where the test type will be seen
     * @return the reference value of the parameter containing its metric, its maximum value and its minimum value
     */

    public ReferenceValue checkExternalModuleBasedOnTestType(Parameter parameterCode) {
        Class<?> oClass;
        ExternalModule em;
        try {
            if (parameterCode.getCode().equals("IgGAN")) {
                oClass = Class.forName(Configuration.getAutomaticValidationCovid());
            } else {
                oClass = Class.forName(Configuration.getAutomaticValidationBlood());
            }
            em = (ExternalModule) oClass.getDeclaredConstructor().newInstance();
            return em.getReferenceValue(parameterCode);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getMetricsBasedOnTestType(String parameterCode) {
        Class<?> oClass;
        ExternalModule em;
        try {
            if (parameterCode.equals("IgGAN")) {
                oClass = Class.forName(Configuration.getAutomaticValidationCovid());
            } else {
                oClass = Class.forName(Configuration.getAutomaticValidationBlood());
            }
            em = (ExternalModule) oClass.getDeclaredConstructor().newInstance();
            return em.getMetricFor(parameterCode);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setParameterCategories(List<ParameterCategory> parameterCategories) {
        this.parameterCategories = parameterCategories;
    }
}
