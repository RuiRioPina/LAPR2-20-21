package app.controller;

import app.domain.model.*;
import app.domain.store.ClinicalAnalysisLaboratoryStore;
import app.domain.store.ReportStore;
import app.domain.store.TestStore;
import app.domain.store.TestTypeStore;

import java.util.ArrayList;
import java.util.List;


public class WriteReportController {

    TestStore tests = App.getInstance().getCompany().getAllTestCompleted();

    /**
     *  Controller class for the write Report funcion [US-14 of the integrative project of 1st year ISEP DEI students].
     */

    private Company company;
    private Report report;
    private Parameter parameter;

    /**
     * Constructor for the Controller class. Gets the company that is using the software.
     */

    public WriteReportController(){
        this.company=App.getInstance().getCompany();
    }

    /**
     * Gets the test that have a specific code
     * @param testCode - Code of a test
     * @return the test with a specific code
     */

    public Test getTestByInternalCode(String testCode) {
        return tests.getTestByInternalCode(testCode);
    }

    /**
     * Gets tests that don't have the diagnosis made
     * @return the tests that don't have the diagnosis made
     */

    public List<Test> getTestsWithoutDiagnosis(){
        return tests.getTestsWithoutDiagnosis();
    }

    /**
     * Writes an object for the Report class
     * @param testCode - Code of a test
     * @param report - Report of a test
     * @return Object for the Report class
     * @throws IllegalArgumentException
     */

    public Report writeReport(String testCode,String report) throws IllegalArgumentException{
        TestStore ts = this.company.getTestStore();
        Test t = ts.getTestByInternalCode(testCode);
        ReportStore rs=this.company.getReportStore();
        Report rep= rs.writeReport(report);
        this.report=rep;
        return this.report;
    }

    /**
     *  Saves a report of a test in the ReportStore
     * @param t - Test
     * @param report - Report of a test
     */

    public void saveReport(Test t,Report report){
        ReportStore rs=this.company.getReportStore();
        rs.saveReport(t,report);
    }
}
