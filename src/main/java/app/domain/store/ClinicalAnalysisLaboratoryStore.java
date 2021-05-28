package app.domain.store;

import app.domain.model.ClinicalAnalysisLaboratory;
import app.domain.model.Test;
import app.domain.model.TestType;

import java.util.ArrayList;
import java.util.List;

public class ClinicalAnalysisLaboratoryStore {

    /**
     * Object oriented class used to Store the Clinical analysis laboratories within the company.
     */
    private List<ClinicalAnalysisLaboratory> clinicalAnalysisLaboratories;

    /**
     * Constructor for the Clinical Analysis Laboratory Store
     */

    public ClinicalAnalysisLaboratoryStore() {
        this.clinicalAnalysisLaboratories = new ArrayList<>();
    }

    /**
     *
     * @param laboratoryID - laboratoryID of a Clinical analysis laboratory
     * @param name  - name of a Clinical analysis laboratory
     * @param adress - adress of a Clinical analysis laboratory
     * @param phoneNumber - phone number of a Clinical analysis laboratory
     * @param tin - tin number of a Clinical analysis laboratory
     * @param testTypes - type of tests that a Clinical analysis laboratory performs
     * @return Object of the ClinicalAnalysisLaboratory class
     */

    public ClinicalAnalysisLaboratory registerClinicalAnalysisLaboratory(String laboratoryID, String name, String adress, long phoneNumber, long tin, List<TestType> testTypes) {
        return new ClinicalAnalysisLaboratory(laboratoryID, name, adress, phoneNumber, tin, testTypes);
    }

    /**
     * Validates a ClinicalAnalysisLaboratory
     * @param cal - clinicalanalysislaboratory
     * @throws IllegalArgumentException
     */

    public void validateClinicalAnalysisLaboratory(ClinicalAnalysisLaboratory cal) throws IllegalArgumentException {
        checkNameRules(cal.getName());
        checkLaboratoryIDRules(cal.getLaboratoryID());
        checkAdressRules(cal.getAdress());
        checkPhoneNumberRules(cal.getPhoneNumber());
        checkTINRules(cal.getTin());
        checkTestTypesRules(cal.getTestTypes());
    }

    /**
     * Saves a ClinicalAnalysisLaboratory
     * @param cal - clinicalanalysislaboratory
     * @throws IllegalArgumentException
     */

    public void saveClinicalAnalysisLaboratory(ClinicalAnalysisLaboratory cal) throws IllegalArgumentException {
        validateClinicalAnalysisLaboratory(cal);
        addClinicalAnalysisLaboratory(cal);
    }

    /**
     * Adds a ClinicalAnalysisLaboratory
     * @param cal - clinicalanalysislaboratory
     */

    private void addClinicalAnalysisLaboratory(ClinicalAnalysisLaboratory cal) {
        this.clinicalAnalysisLaboratories.add(cal);
    }

    /**
     * Checks Clinical analysis laboratory name rules
     * @param name - name of the Clinical analysis laboratory
     * @throws IllegalArgumentException
     */

    public void checkNameRules(String name) throws IllegalArgumentException{
        if ((name.length()) < 1 || name.length() > 20){
            throw new IllegalArgumentException("Name must have 1 to 20 chars.");
        }
        if (!name.matches("[A-Za-z]+")) {
            throw new IllegalArgumentException("Name must be a string.");
        }

    }

    /**
     * Checks Clinical analysis laboratory laboratoryID rules
     * @param laboratoryID - laboratoryID of the Clinical analysis laboratory
     * @throws IllegalArgumentException
     */

    public void checkLaboratoryIDRules(String laboratoryID) throws IllegalArgumentException{
        if (laboratoryID.length() != 5 ){
            throw new IllegalArgumentException("LaboratoryID must have 5 chars.");
        }
        if (!laboratoryID.matches("[A-Za-z0-9]+")) {
            throw new IllegalArgumentException("LaboratoryID must be alphanumeric.");
        }
        if (this.getRepeatedLabID(laboratoryID) != null) {
            throw new IllegalArgumentException("LaboratoryID already exist.");
        }

    }

    /**
     * Checks Clinical analysis laboratory adress rules
     * @param adress - adress of the Clinical analysis laboratory
     * @throws IllegalArgumentException
     */

    public void checkAdressRules(String adress) throws IllegalArgumentException{
        if ((adress.length()) < 1 || adress.length() > 30){
            throw new IllegalArgumentException("Adress must have 1 to 30 chars.");
        }
        if (this.getRepeatedAdress(adress) != null) {
            throw new IllegalArgumentException("Adress already exist.");
        }
    }

    /**
     * Checks Clinical analysis laboratory phone number rules
     * @param phoneNumber - phone number of the Clinical analysis laboratory
     * @throws IllegalArgumentException
     */

    public void checkPhoneNumberRules(long phoneNumber) throws IllegalArgumentException{
        if (digits(phoneNumber) != 11) {
            throw new IllegalArgumentException("Phone number must have 11 digits.");
        }
        if (this.getRepeatedPhoneNumber(phoneNumber) != null) {
            throw new IllegalArgumentException("Phone number already exist.");
        }
    }

    /**
     * Checks Clinical analysis laboratory tin rules
     * @param tin - tin of the Clinical analysis laboratory
     * @throws IllegalArgumentException
     */

    public void checkTINRules(long tin) throws IllegalArgumentException{
        if (digits(tin) != 10){
            throw new IllegalArgumentException("TIN number must have 10 digits.");
            }
        if (this.getRepeatedTIN(tin) != null) {
            throw new IllegalArgumentException("TIN already exist.");
        }
     }

    /**
     * Checks Clinical analysis laboratory type of tests rules
     * @param testTypes - type of tests of the Clinical analysis laboratory
     * @throws IllegalArgumentException
     */

    public void checkTestTypesRules(List<TestType> testTypes) throws IllegalArgumentException {
        if (testTypes.isEmpty()) {
            throw new IllegalArgumentException("Test types cannot be empty.");
        }
    }

    /**
     * Returns the number of digits in a number
     * @param num - number to be analysed
     * @return number of digits in a number
     */

    private long digits(long num){
        int digits=0;
        do {
            num=num/10;
            digits++;
        }while (num!=0);
        return digits;
    }

    public ClinicalAnalysisLaboratory getRepeatedLabID(String laboratoryID) {
        for(ClinicalAnalysisLaboratory cal : this.clinicalAnalysisLaboratories) {
            if(cal.getLaboratoryID().equals(laboratoryID)) {
                return cal;
            }
        }
        return null;
    }

    public ClinicalAnalysisLaboratory getRepeatedAdress(String adress) {
        for(ClinicalAnalysisLaboratory cal : this.clinicalAnalysisLaboratories) {
            if(cal.getAdress().equals(adress)) {
                return cal;
            }
        }
        return null;
    }

    public ClinicalAnalysisLaboratory getRepeatedPhoneNumber(long phoneNumber) {
        for(ClinicalAnalysisLaboratory cal : this.clinicalAnalysisLaboratories) {
            if(cal.getPhoneNumber()==(phoneNumber)) {
                return cal;
            }
        }
        return null;
    }

    public ClinicalAnalysisLaboratory getRepeatedTIN(long tin) {
        for(ClinicalAnalysisLaboratory cal : this.clinicalAnalysisLaboratories) {
            if(cal.getTin()==(tin)) {
                return cal;
            }
        }
        return null;
    }

    /**
     * Check if a ClinicalAnalysisLaboratory is listed
     * @param cal - clinicalanalysislaboratory
     * @return the value of ClinicalAnalysisLaboratory
     */

    public boolean isClinicalAnalysisLaboratoryInList(ClinicalAnalysisLaboratory cal){
        return this.clinicalAnalysisLaboratories.contains(cal);
    }

    public List<ClinicalAnalysisLaboratory> getCLA() {
        List<ClinicalAnalysisLaboratory> cla = new ArrayList<>();
        cla.addAll(this.clinicalAnalysisLaboratories);
        return cla;
    }
}
