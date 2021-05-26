package app.domain.store;

import app.domain.model.Report;
import app.domain.model.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class ReportStore {

    private List<Report> reports;
    private List<Test> tests;
    private Test test;

    public ReportStore(){
        this.reports=new ArrayList<>();

    }

    public Report writeReport(String report){
        return new Report(report);
    }



    public void saveReport(Test tests,Report r){
        validateReport(r);
        addReport(tests,r);
    }

    public void addReport(Test tests,Report r){
        this.reports.add(r);
        this.test.setDiagnosisDate(new Date(System.currentTimeMillis()));
    }

    public void validateReport(Report r){
        checkReport(r.getReport());
    }

    public void checkReport(String report){
        if (numberOfWords(report)<1 || numberOfWords(report)>400){
            throw new IllegalArgumentException("Report must have a maximum of 400 words");
        }
    }

    public long numberOfWords(String report){
        long var = report.split(" ", -1).length - 1;
        var=var+1;
        return var;
    }
    

}
