package app.domain.model;

import java.util.List;


public class ClinicalAnalysisLaboratory {

    /**
     * Object oriented Class to describe a Clinical analysis laboratory in a company context.
     */

    private String laboratoryID;
    private String name;
    private String adress;
    private long phoneNumber;
    private long tin;
    private List<TestType> testTypes;

    /**
     * Constructor for the Clinical analysis laboratory
     * @param laboratoryID - laboratoryID of a Clinical analysis laboratory
     * @param name  - name of a Clinical analysis laboratory
     * @param adress - adress of a Clinical analysis laboratory
     * @param phoneNumber - phone number of a Clinical analysis laboratory
     * @param tin - tin number of a Clinical analysis laboratory
     * @param testTypes - type of tests that a Clinical analysis laboratory performs
     */

    public ClinicalAnalysisLaboratory(String laboratoryID, String name, String adress, long phoneNumber, long tin, List<TestType> testTypes) {
        this.laboratoryID = laboratoryID;
        this.name = name;
        this.adress = adress;
        this.tin = tin;
        this.phoneNumber = phoneNumber;
        this.testTypes = testTypes;

    }

    /**
     * Returns the laboratoryID of the Clinical analysis laboratory
     * @return laboratoryID of the Clinical analysis laboratory
     */

    public String getLaboratoryID() {
        return laboratoryID;
    }

    /**
     * Returns the name of the Clinical analysis laboratory
     * @return name of the Clinical analysis laboratory
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the adress of the Clinical analysis laboratory
     * @return adress of the Clinical analysis laboratory
     */

    public String getAdress() {
        return adress;
    }

    /**
     * Returns the phone number of the Clinical analysis laboratory
     * @return phone number of the Clinical analysis laboratory
     */

    public long getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Returns the tin number of the Clinical analysis laboratory
     * @return tin number of the Clinical analysis laboratory
     */

    public long getTin() {
        return tin;
    }

    /**
     * Returns the type of tests performed by the Clinical analysis laboratory
     * @return type of tests performed by the Clinical analysis laboratory
     */
    public List<TestType> getTestTypes() {
        return testTypes;
    }
}


