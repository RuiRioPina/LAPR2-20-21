package app.domain.model;

import app.domain.store.ClinicalAnalysisLaboratoryStore;
import org.junit.Test;

import java.util.ArrayList;

import java.util.List;

import static org.junit.Assert.*;

public class ClinicalAnalysisLaboratoryTest {


     @Test
     public void getLaboratoryID() {
         List<ParameterCategory> parameterCategories = new ArrayList<>();
         ParameterCategory p1 = new ParameterCategory("54321","HEMOGRAM");
        parameterCategories.add(p1);

        List<TestType> testTypes = new ArrayList<>();
        TestType t1 = new TestType("13579","TESTDESCRIPTION", "abcde", parameterCategories);
        testTypes.add(t1);

        ClinicalAnalysisLaboratory cal= new ClinicalAnalysisLaboratory("123ab", "LABORATORY",
                               "LabADRESS", 99999999999L, 1234567890L, testTypes);

        String laboratoryID = "123ab";
        String wrongLaboratoryID = "456cd";

        assertEquals(laboratoryID,cal.getLaboratoryID());
        assertNotEquals(wrongLaboratoryID,cal.getLaboratoryID());

     }

    @Test

    public void getName() {
         List<ParameterCategory> parameterCategories = new ArrayList<>();
         ParameterCategory p1 = new ParameterCategory("54321","HEMOGRAM");
         parameterCategories.add(p1);

         List<TestType> testTypes = new ArrayList<>();
         TestType t1 = new TestType("13579","TESTDESCRIPTION", "abcde", parameterCategories);
         testTypes.add(t1);

         ClinicalAnalysisLaboratory cal= new ClinicalAnalysisLaboratory("123ab", "LABORATORY",
                                "LabADRESS", 99999999999L, 1234567890L, testTypes);

         String name = "LABORATORY";
         String wrongName = "LAB";

         assertEquals(name,cal.getName());
         assertNotEquals(wrongName, cal.getName());

    }


    @Test
    public void getAdress() {
         List<ParameterCategory> parameterCategories = new ArrayList<>();
         ParameterCategory p1 = new ParameterCategory("54321","HEMOGRAM");
         parameterCategories.add(p1);

         List<TestType> testTypes = new ArrayList<>();
         TestType t1 = new TestType("13579","TESTDESCRIPTION", "abcde", parameterCategories);
         testTypes.add(t1);

         ClinicalAnalysisLaboratory cal= new ClinicalAnalysisLaboratory("123ab", "LABORATORY",
                               "LabADRESS", 99999999999L, 1234567890L, testTypes);

         String adress = "LabADRESS";
         String wrongAdress = "NOTLabADRESS";

         assertEquals(adress,cal.getAdress());
         assertNotEquals(wrongAdress, cal.getAdress());


    }


    @Test
    public void getPhoneNumber() {
         List<ParameterCategory> parameterCategories = new ArrayList<>();
         ParameterCategory p1 = new ParameterCategory("54321","HEMOGRAM");
         parameterCategories.add(p1);

         List<TestType> testTypes = new ArrayList<>();
         TestType t1 = new TestType("13579","TESTDESCRIPTION", "abcde", parameterCategories);
         testTypes.add(t1);

         ClinicalAnalysisLaboratory cal= new ClinicalAnalysisLaboratory("123ab", "LABORATORY",
                                "LabADRESS", 99999999999L, 1234567890L, testTypes);

         long phoneNumber = 99999999999L;
         long wrongPhoneNumber = 11111111111L;

         assertEquals(phoneNumber,cal.getPhoneNumber());
         assertNotEquals(wrongPhoneNumber, cal.getPhoneNumber());


    }


    @Test
    public void getTin() {
         List<ParameterCategory> parameterCategories = new ArrayList<>();
         ParameterCategory p1 = new ParameterCategory("54321","HEMOGRAM");
         parameterCategories.add(p1);

         List<TestType> testTypes = new ArrayList<>();
         TestType t1 = new TestType("13579","TESTDESCRIPTION", "abcde", parameterCategories);
         testTypes.add(t1);

         ClinicalAnalysisLaboratory cal= new ClinicalAnalysisLaboratory("123ab", "LABORATORY",
                               "LabADRESS", 99999999999L, 1234567890L, testTypes);

         long tin = 1234567890L;
         long wrongTin = 1234567891L;

         assertEquals(tin,cal.getTin());
         assertNotEquals(wrongTin, cal.getTin());


    }


    @Test
   public void getTestTypes() {
         List<ParameterCategory> parameterCategories = new ArrayList<>();
         ParameterCategory p1 = new ParameterCategory("54321","HEMOGRAM");
         parameterCategories.add(p1);

         List<TestType> testTypes = new ArrayList<>();
         TestType t1 = new TestType("13579","TESTDESCRIPTION", "abcde", parameterCategories);
         testTypes.add(t1);

         ClinicalAnalysisLaboratory cal= new ClinicalAnalysisLaboratory("123ab", "LABORATORY",
                                "LabADRESS", 99999999999L, 1234567890L, testTypes);

         assertEquals(testTypes,cal.getTestTypes());


   }

}