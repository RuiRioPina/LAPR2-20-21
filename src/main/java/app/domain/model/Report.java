package app.domain.model;

import java.io.Serializable;

public class Report implements Serializable {
    /**
     * Object oriented class to describe a report in a company text
     */
    private final String reportText;

    /**
     * Constructor for the Report
     * @param reportText
     */

    public Report(String reportText){

        this.reportText = reportText;

    }

    /**
     * Returns the report
     * @return the report
     */

    public String getReport(){
        return reportText;
    }



}
