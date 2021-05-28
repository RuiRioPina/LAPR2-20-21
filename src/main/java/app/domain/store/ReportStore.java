package app.domain.store;

import app.domain.model.Report;
import app.domain.model.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class ReportStore {

    /**
     * Object oriented class used to Store the Reports within the company.
     */

    private List<Report> reports;
    private List<Test> tests;
    private Test test;

    /**
     * Constructor for the Report store.
     */

    public ReportStore(){
        this.reports=new ArrayList<>();

    }

    /**
     *
     * @param report - report of the test
     * @return Object of the Report class
     */

    public Report writeReport(String report){
        return new Report(report);
    }

    /**
     * Saves a report
     * @param t - Test
     * @param r - Report
     * @throws IllegalArgumentException
     */

    public void saveReport(Test t,Report r) throws IllegalArgumentException{
        validateReport(r);
        addReport(t,r);
    }

    /**
     * Adds a report
     * @param t - Test
     * @param r - Report
     */

    public void addReport(Test t,Report r){
        t.setReport(r);
        t.setDiagnosisDate(new Date(System.currentTimeMillis()));
    }

    /**
     * Validates a report
     * @param r - Report
     * @throws IllegalArgumentException
     */

    public void validateReport(Report r) throws IllegalArgumentException{
        checkReportRules(r.getReport());
    }

    /**
     * Checks report rules
     * @param report - Report
     * @throws IllegalArgumentException
     */

    public void checkReportRules(String report) throws IllegalArgumentException{
        if (numberOfWords(report)<1 || numberOfWords(report)>400){
            throw new IllegalArgumentException("Report must have a maximum of 400 words");
        }
    }

    /**
     * Counts the number of words in a String
     * @param report - Report
     * @return - The number of words in a String
     */

    public long numberOfWords(String report){
        long var = report.split(" ", -1).length - 1;
        var=var+1;
        return var;
    }
    

}
