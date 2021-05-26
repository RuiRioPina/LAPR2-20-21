package app.domain.model;


public class Report {
    /**
     * Object oriented class to describe a report in a company text
     */
    private String report;

    /**
     * Constructor for the Report
     * @param report
     */

    public Report(String report){

        this.report = report;

    }

    /**
     * Returns the report
     * @return the report
     */

    public String getReport(){
        return report;
    }



}
