package app.domain.store;

import app.domain.model.ClinicalAnalysisLaboratory;
import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;
import app.domain.model.TestType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ClinicalAnalysisLaboratoryStoreTest {

    @Test
    public void registerClinicalAnalysisLaboratory() {
        List<ParameterCategory> parameterCategories = new ArrayList<>();
        ParameterCategory p1 = new ParameterCategory("54321", "HEMOGRAM");
        parameterCategories.add(p1);

        List<TestType> testTypes = new ArrayList<>();
        TestType t1 = new TestType("13579", "TESTDESCRIPTION", "abcde", parameterCategories);
        testTypes.add(t1);

        ClinicalAnalysisLaboratoryStore calStore = new ClinicalAnalysisLaboratoryStore();

        calStore.registerClinicalAnalysisLaboratory("123ab", "LABORATORY",
                "LabADRESS", 99999999999L, 1234567890L, testTypes);

    }

    @Test
    public void validateClinicalAnalysisLaboratory() {
        String laboratoryID = "123ab";
        String name = "LABORATORY";
        String adress = "LabADRESS";
        long phoneNumber = 99999999999L;
        long tin = 1234567890L;

        List<ParameterCategory> parameterCategories = new ArrayList<>();
        ParameterCategory p1 = new ParameterCategory("54321", "HEMOGRAM");
        parameterCategories.add(p1);

        List<TestType> testTypes = new ArrayList<>();
        TestType t1 = new TestType("13579", "TESTDESCRIPTION", "abcde", parameterCategories);
        testTypes.add(t1);

        ClinicalAnalysisLaboratoryStore calStore = new ClinicalAnalysisLaboratoryStore();

        ClinicalAnalysisLaboratory cal1 = calStore.registerClinicalAnalysisLaboratory(laboratoryID, name, adress, phoneNumber, tin, testTypes);
        ClinicalAnalysisLaboratory cal2 = calStore.registerClinicalAnalysisLaboratory("456cd", "LABORATORY",
                "LabOTHERADRESS", 99999999998L, 1234567891L, testTypes);

        calStore.validateClinicalAnalysisLaboratory(cal1);
        calStore.validateClinicalAnalysisLaboratory(cal2);
        calStore.saveClinicalAnalysisLaboratory(cal1);
        calStore.saveClinicalAnalysisLaboratory(cal2);

        boolean expected = true;

        boolean actual1 = calStore.isClinicalAnalysisLaboratoryInList(cal1);
        boolean actual2 = calStore.isClinicalAnalysisLaboratoryInList(cal2);

        assertEquals(expected, actual1);
        assertEquals(expected, actual2);
    }

    @Test
    public void saveClinicalAnalysisLaboratory() {
        String laboratoryID = "123ab";
        String name = "LABORATORY";
        String adress = "LabADRESS";
        long phoneNumber = 99999999999L;
        long tin = 1234567890L;

        List<ParameterCategory> parameterCategories = new ArrayList<>();
        ParameterCategory p1 = new ParameterCategory("54321", "HEMOGRAM");
        parameterCategories.add(p1);

        List<TestType> testTypes = new ArrayList<>();
        TestType t1 = new TestType("13579", "TESTDESCRIPTION", "abcde", parameterCategories);
        testTypes.add(t1);

        ClinicalAnalysisLaboratoryStore calStore = new ClinicalAnalysisLaboratoryStore();

        ClinicalAnalysisLaboratory cal1 = calStore.registerClinicalAnalysisLaboratory(laboratoryID, name, adress, phoneNumber, tin, testTypes);
        ClinicalAnalysisLaboratory cal2 = calStore.registerClinicalAnalysisLaboratory("456cd", "LABORATORY",
                "LabOTHERADRESS", 99999999998L, 1234567891L, testTypes);

        calStore.saveClinicalAnalysisLaboratory(cal1);
        calStore.saveClinicalAnalysisLaboratory(cal2);

        boolean expected = true;

        boolean actual1 = calStore.isClinicalAnalysisLaboratoryInList(cal1);
        boolean actual2 = calStore.isClinicalAnalysisLaboratoryInList(cal2);

        assertEquals(expected, actual1);
        assertEquals(expected, actual2);

    }

    @Test
    public void testisClinicalAnalysisLaboratoryInList() {
        String laboratoryID = "123ab";
        String name = "LABORATORY";
        String adress = "LabADRESS";
        long phoneNumber = 99999999999L;
        long tin = 1234567890L;

        List<ParameterCategory> parameterCategories = new ArrayList<>();
        ParameterCategory p1 = new ParameterCategory("54321", "HEMOGRAM");
        parameterCategories.add(p1);

        List<TestType> testTypes = new ArrayList<>();
        TestType t1 = new TestType("13579", "TESTDESCRIPTION", "abcde", parameterCategories);
        testTypes.add(t1);

        ClinicalAnalysisLaboratoryStore calStore = new ClinicalAnalysisLaboratoryStore();

        ClinicalAnalysisLaboratory cal1 = calStore.registerClinicalAnalysisLaboratory(laboratoryID, name, adress, phoneNumber, tin, testTypes);
        ClinicalAnalysisLaboratory cal2 = calStore.registerClinicalAnalysisLaboratory("456cd", "LABORATORY",
                "LabOTHERADRESS", 99999999998L, 1234567891L, testTypes);

        calStore.saveClinicalAnalysisLaboratory(cal1);
        calStore.isClinicalAnalysisLaboratoryInList(cal1);

        boolean actual1 = calStore.isClinicalAnalysisLaboratoryInList(cal1);
        boolean actual2 = calStore.isClinicalAnalysisLaboratoryInList(cal2);
        boolean expected = true;
        boolean unexpected = false;

        assertEquals(expected, actual1);
        assertEquals(unexpected, actual2);

    }

    @Test(expected = IllegalArgumentException.class)
    public void checkLaboratoryID() {
        List<ParameterCategory> parameterCategories = new ArrayList<>();
        ParameterCategory p1 = new ParameterCategory("54321", "HEMOGRAM");
        parameterCategories.add(p1);

        List<TestType> testTypes = new ArrayList<>();
        TestType t1 = new TestType("13579", "TESTDESCRIPTION", "abcde", parameterCategories);
        testTypes.add(t1);

        ClinicalAnalysisLaboratoryStore calStore = new ClinicalAnalysisLaboratoryStore();

        ClinicalAnalysisLaboratory cal = calStore.registerClinicalAnalysisLaboratory("", "LABORATORY",
                "LabADRESS", 99999999999L, 1234567890L, testTypes);

        calStore.checkLaboratoryIDRules(cal.getLaboratoryID());

    }

    @Test(expected = IllegalArgumentException.class)
    public void checkName() {
        List<ParameterCategory> parameterCategories = new ArrayList<>();
        ParameterCategory p1 = new ParameterCategory("54321", "HEMOGRAM");
        parameterCategories.add(p1);

        List<TestType> testTypes = new ArrayList<>();
        TestType t1 = new TestType("13579", "TESTDESCRIPTION", "abcde", parameterCategories);
        testTypes.add(t1);

        ClinicalAnalysisLaboratoryStore calStore = new ClinicalAnalysisLaboratoryStore();

        ClinicalAnalysisLaboratory cal = calStore.registerClinicalAnalysisLaboratory("123ab", "",
                "LabADRESS", 99999999999L, 1234567890L, testTypes);

        calStore.checkNameRules(cal.getName());

    }

    @Test(expected = IllegalArgumentException.class)
    public void checkAdressRules() {
        List<ParameterCategory> parameterCategories = new ArrayList<>();
        ParameterCategory p1 = new ParameterCategory("54321", "HEMOGRAM");
        parameterCategories.add(p1);

        List<TestType> testTypes = new ArrayList<>();
        TestType t1 = new TestType("13579", "TESTDESCRIPTION", "abcde", parameterCategories);
        testTypes.add(t1);

        ClinicalAnalysisLaboratoryStore calStore = new ClinicalAnalysisLaboratoryStore();

        ClinicalAnalysisLaboratory cal = calStore.registerClinicalAnalysisLaboratory("123ab", "LABORATORY",
                "", 99999999999L, 1234567890L, testTypes);

        calStore.checkAdressRules(cal.getAdress());

    }

    @Test(expected = IllegalArgumentException.class)
    public void checkPhoneNumberRules() {
        List<ParameterCategory> parameterCategories = new ArrayList<>();
        ParameterCategory p1 = new ParameterCategory("54321", "HEMOGRAM");
        parameterCategories.add(p1);

        List<TestType> testTypes = new ArrayList<>();
        TestType t1 = new TestType("13579", "TESTDESCRIPTION", "abcde", parameterCategories);
        testTypes.add(t1);

        ClinicalAnalysisLaboratoryStore calStore = new ClinicalAnalysisLaboratoryStore();

        ClinicalAnalysisLaboratory cal = calStore.registerClinicalAnalysisLaboratory("123ab", "LABORATORY",
                "LabADRESS", 999999999999L, 1234567890L, testTypes);

        calStore.checkPhoneNumberRules(cal.getPhoneNumber());

    }

    @Test(expected = IllegalArgumentException.class)
    public void checkTinRules() {
        List<ParameterCategory> parameterCategories = new ArrayList<>();
        ParameterCategory p1 = new ParameterCategory("54321", "HEMOGRAM");
        parameterCategories.add(p1);

        List<TestType> testTypes = new ArrayList<>();
        TestType t1 = new TestType("13579", "TESTDESCRIPTION", "abcde", parameterCategories);
        testTypes.add(t1);

        ClinicalAnalysisLaboratoryStore calStore = new ClinicalAnalysisLaboratoryStore();

        ClinicalAnalysisLaboratory cal = calStore.registerClinicalAnalysisLaboratory("123ab", "LABORATORY",
                "LabADRESS", 999999999999L, 12345678901L, testTypes);

        calStore.checkTINRules(cal.getTin());

    }

    @Test(expected = IllegalArgumentException.class)
    public void checkTestTypesRules() {
        List<ParameterCategory> parameterCategories = new ArrayList<>();
        ParameterCategory p1 = new ParameterCategory("54321", "HEMOGRAM");
        parameterCategories.add(p1);

        List<TestType> testTypes = new ArrayList<>();


        ClinicalAnalysisLaboratoryStore calStore = new ClinicalAnalysisLaboratoryStore();

        ClinicalAnalysisLaboratory cal = calStore.registerClinicalAnalysisLaboratory("123ab", "LABORATORY",
                "LabADRESS", 999999999999L, 1234567890L, testTypes);

        calStore.checkTestTypesRules(cal.getTestTypes());

    }

    @Test
    public void testGetRepeatedLabID() {
        List<ParameterCategory> parameterCategories = new ArrayList<>();
        ParameterCategory p1 = new ParameterCategory("54321", "HEMOGRAM");
        parameterCategories.add(p1);

        List<TestType> testTypes = new ArrayList<>();
        TestType t1 = new TestType("13579", "TESTDESCRIPTION", "abcde", parameterCategories);
        testTypes.add(t1);

        ClinicalAnalysisLaboratoryStore calStore = new ClinicalAnalysisLaboratoryStore();

        ClinicalAnalysisLaboratory cal1 = calStore.registerClinicalAnalysisLaboratory("123ab", "LABORATORY",
                "LabADRESS", 999999999999L, 1234567890L, testTypes);

        List<ClinicalAnalysisLaboratory> clinicalAnalysisLaboratories = new ArrayList<>();

        clinicalAnalysisLaboratories.add(cal1);
        calStore.getRepeatedLabID("123ab");

        String laboratoryID = "123ab";
        assertEquals(calStore.getRepeatedLabID("123ab"), calStore.getRepeatedLabID(laboratoryID));
    }

    @Test
    public void testGetRepeatedAdress() {
        List<ParameterCategory> parameterCategories = new ArrayList<>();
        ParameterCategory p1 = new ParameterCategory("54321", "HEMOGRAM");
        parameterCategories.add(p1);

        List<TestType> testTypes = new ArrayList<>();
        TestType t1 = new TestType("13579", "TESTDESCRIPTION", "abcde", parameterCategories);
        testTypes.add(t1);

        ClinicalAnalysisLaboratoryStore calStore = new ClinicalAnalysisLaboratoryStore();

        ClinicalAnalysisLaboratory cal1 = calStore.registerClinicalAnalysisLaboratory("123ab", "LABORATORY",
                "LabADRESS", 999999999999L, 1234567890L, testTypes);

        List<ClinicalAnalysisLaboratory> clinicalAnalysisLaboratories = new ArrayList<>();

        clinicalAnalysisLaboratories.add(cal1);
        calStore.getRepeatedAdress("LabADRESS");

        String adress = "LabADRESS";
        assertEquals(calStore.getRepeatedAdress("LabADRESS"), calStore.getRepeatedAdress(adress));
    }

    @Test
    public void testGetRepeatedPhoneNumber() {
        List<ParameterCategory> parameterCategories = new ArrayList<>();
        ParameterCategory p1 = new ParameterCategory("54321", "HEMOGRAM");
        parameterCategories.add(p1);

        List<TestType> testTypes = new ArrayList<>();
        TestType t1 = new TestType("13579", "TESTDESCRIPTION", "abcde", parameterCategories);
        testTypes.add(t1);

        ClinicalAnalysisLaboratoryStore calStore = new ClinicalAnalysisLaboratoryStore();

        ClinicalAnalysisLaboratory cal1 = calStore.registerClinicalAnalysisLaboratory("123ab", "LABORATORY",
                "LabADRESS", 999999999999L, 1234567890L, testTypes);

        List<ClinicalAnalysisLaboratory> clinicalAnalysisLaboratories = new ArrayList<>();

        clinicalAnalysisLaboratories.add(cal1);
        calStore.getRepeatedPhoneNumber(999999999999L);

        long phoneNumber = 999999999999L;
        assertEquals(calStore.getRepeatedPhoneNumber(999999999999L), calStore.getRepeatedPhoneNumber(999999999999L));
    }

    @Test
    public void testGetRepeatedTin() {
        List<ParameterCategory> parameterCategories = new ArrayList<>();
        ParameterCategory p1 = new ParameterCategory("54321", "HEMOGRAM");
        parameterCategories.add(p1);

        List<TestType> testTypes = new ArrayList<>();
        TestType t1 = new TestType("13579", "TESTDESCRIPTION", "abcde", parameterCategories);
        testTypes.add(t1);

        ClinicalAnalysisLaboratoryStore calStore = new ClinicalAnalysisLaboratoryStore();

        ClinicalAnalysisLaboratory cal1 = calStore.registerClinicalAnalysisLaboratory("123ab", "LABORATORY",
                "LabADRESS", 999999999999L, 1234567890L, testTypes);

        List<ClinicalAnalysisLaboratory> clinicalAnalysisLaboratories = new ArrayList<>();

        clinicalAnalysisLaboratories.add(cal1);
        calStore.getRepeatedTIN(1234567890L);

        long tin = 1234567890L;
        assertEquals(calStore.getRepeatedTIN(1234567890L), calStore.getRepeatedTIN(1234567890L));
    }

}