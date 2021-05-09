package app.controller;

import app.domain.model.ClinicalAnalysisLaboratory;
import app.domain.model.ParameterCategory;
import app.domain.model.TestType;
import app.domain.store.ClinicalAnalysisLaboratoryStore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ClinicalAnalysisLaboratoryControllerTest {

    @Test
    public void getTestTypes() {
        List<TestType> tt = App.getInstance().getCompany().getTestTypeStore().getTestTypes();
        ClinicalAnalysisLaboratoryController clinicalAnalysisLaboratoryController = new ClinicalAnalysisLaboratoryController();

        List<TestType> ts = clinicalAnalysisLaboratoryController.getTestTypes();

        assertArrayEquals(tt.toArray(), ts.toArray());
    }

    @Test
    public void registerClinicalAnalysisLaboratory() {

        List<String>testTypes=new ArrayList<>();

        String ts="ADR23 - COVID";
        testTypes.add(ts);

        ClinicalAnalysisLaboratoryController clinicalAnalysisLaboratoryController=new ClinicalAnalysisLaboratoryController();

        ClinicalAnalysisLaboratory cal1=clinicalAnalysisLaboratoryController.registerClinicalAnalysisLaboratory("123ab","LABORATORY",
                "LabADRESS",99999999999L,1234567890L,testTypes);
        ClinicalAnalysisLaboratory cal2=clinicalAnalysisLaboratoryController.registerClinicalAnalysisLaboratory("456cd","LABORATORY",
                "LabOTHERADRESS",99999999991L,1234567899L,testTypes);
        assertNotEquals(cal1,cal2);

    }

    @Test
    public void saveClinicalAnalysisLaboratory() {
        ClinicalAnalysisLaboratoryController clinicalAnalysisLaboratoryController = new ClinicalAnalysisLaboratoryController();
        ClinicalAnalysisLaboratoryStore calStore = new ClinicalAnalysisLaboratoryStore();

        List<ParameterCategory> parameterCategories = new ArrayList<>();
        ParameterCategory p1 = new ParameterCategory("54321", "HEMOGRAM");
        parameterCategories.add(p1);

        List<TestType> testTypes = new ArrayList<>();
        TestType t1 = new TestType("13579", "TESTDESCRIPTION", "abcde", parameterCategories);
        testTypes.add(t1);



        ClinicalAnalysisLaboratory cal = calStore.registerClinicalAnalysisLaboratory("124ab","LABORATORY",
                "OTHERLabADRESS",99999999997L, 1234567880L,testTypes);

        clinicalAnalysisLaboratoryController.saveClinicalAnalysisLaboratory(cal);
        calStore.saveClinicalAnalysisLaboratory(cal);

        boolean actual1 = calStore.isClinicalAnalysisLaboratoryInList(cal);
        boolean expected = true;
        boolean unexpected = false;

        assertEquals(expected, actual1);
        assertNotEquals(unexpected, actual1);

    }
}