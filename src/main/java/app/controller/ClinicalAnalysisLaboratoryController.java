package app.controller;

import app.domain.model.ClinicalAnalysisLaboratory;
import app.domain.model.Company;
import app.domain.model.TestType;
import app.domain.store.ClinicalAnalysisLaboratoryStore;
import app.domain.store.TestTypeStore;

import java.util.ArrayList;
import java.util.List;


public class ClinicalAnalysisLaboratoryController {
    private Company company;
    private ClinicalAnalysisLaboratory clinicalAnalysisLaboratory;

    public ClinicalAnalysisLaboratoryController(){
        this.company =App.getInstance().getCompany();
    }

    public List<TestType> getTestTypes() {
        TestTypeStore ts = this.company.getTestTypeStore();
        return ts.getTestTypes();
    }

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

    public void saveClinicalAnalysisLaboratory(ClinicalAnalysisLaboratory cal) throws IllegalArgumentException {
        ClinicalAnalysisLaboratoryStore cs = this.company.getClinicalAnalysisLaboratoryStore();
        cs.saveClinicalAnalysisLaboratory(cal);
    }

}
