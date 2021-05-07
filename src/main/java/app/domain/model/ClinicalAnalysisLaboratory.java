package app.domain.model;

import java.util.List;

public class ClinicalAnalysisLaboratory {

    private String laboratoryID;
    private String name;
    private String adress;
    private long phoneNumber;
    private long tin;
    private List<TestType> testTypes;

    public ClinicalAnalysisLaboratory(String laboratoryID, String name, String adress, long phoneNumber, long tin, List<TestType> testTypes) {
        this.laboratoryID = laboratoryID;
        this.name = name;
        this.adress = adress;
        this.tin = tin;
        this.phoneNumber = phoneNumber;
        this.testTypes = testTypes;

    }

    public String getLaboratoryID() {
        return laboratoryID;
    }

    public String getName() {
        return name;
    }

    public String getAdress() {
        return adress;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public long getTin() {
        return tin;
    }

    public List<TestType> getTestTypes() {
        return testTypes;
    }
}


