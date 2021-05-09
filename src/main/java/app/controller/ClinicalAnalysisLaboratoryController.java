package app.controller;

import app.domain.model.ClinicalAnalysisLaboratory;
import app.domain.model.Company;
import app.domain.model.TestType;
import app.domain.store.ClinicalAnalysisLaboratoryStore;
import app.domain.store.TestTypeStore;

import java.util.ArrayList;
import java.util.List;


public class ClinicalAnalysisLaboratoryController {

    /**
     *  Controller class for the register clinical analysis laboratory funcion [US-08 of the integrative project of 1st year ISEP DEI students].
     */
    private Company company;
    private ClinicalAnalysisLaboratory clinicalAnalysisLaboratory;

    /**
     * Constructor for the Controller class. Gets the company that is using the software.
     */

    public ClinicalAnalysisLaboratoryController(){
        this.company =App.getInstance().getCompany();
    }

    /**
     *Returns the type of tests list of the company that is using the software.
     * @return List with types of tests.
     */

    public List<TestType> getTestTypes() {
        TestTypeStore ts = this.company.getTestTypeStore();
        return ts.getTestTypes();
    }

    /**
     /**
     * Registers an object for the ClinicalAnalysisLaboratory class
     * @param laboratoryID - laboratoryID of a Clinical analysis laboratory
     * @param name  - name of a Clinical analysis laboratory
     * @param adress - adress of a Clinical analysis laboratory
     * @param phoneNumber - phone number of a Clinical analysis laboratory
     * @param tin - tin number of a Clinical analysis laboratory
     * @param testTypeCodes - codes of type of tests that a Clinical analysis laboratory performs
     * @return Object of the ClinicalAnalysisLaboratory class
     * @throws IllegalArgumentException
     */

    public ClinicalAnalysisLaboratory registerClinicalAnalysisLaboratory(String laboratoryID, String name, String adress, long phoneNumber, long tin, List<String> testTypeCodes) throws IllegalArgumentException{
        TestTypeStore ts=this.company.getTestTypeStore();

        List<TestType> testTypes = new ArrayList<TestType>();
        for(String testTypeCode : testTypeCodes) {
            testTypes.add(ts.getTestTypeByCode(testTypeCode));
        }
        ClinicalAnalysisLaboratoryStore cs = this.company.getClinicalAnalysisLaboratoryStore();
        ClinicalAnalysisLaboratory cal = cs.registerClinicalAnalysisLaboratory(laboratoryID, name, adress, phoneNumber, tin, testTypes);
        cs.validateClinicalAnalysisLaboratory(cal);

        this.clinicalAnalysisLaboratory = cal;
        return this.clinicalAnalysisLaboratory;
    }

    /**
     * Saves a clinical analysis laboratory in the ClinicalAnalysisLaboratoryStore
     * @param cal - clinicalanalysislaboratory
     * @throws IllegalArgumentException
     */

    public void saveClinicalAnalysisLaboratory(ClinicalAnalysisLaboratory cal) throws IllegalArgumentException {
        ClinicalAnalysisLaboratoryStore cs = this.company.getClinicalAnalysisLaboratoryStore();
        cs.saveClinicalAnalysisLaboratory(cal);
    }

}
