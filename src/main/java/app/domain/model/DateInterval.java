package app.domain.model;

import java.io.Serializable;

public class DateInterval implements Serializable {

    private String sDate;
    private String eDate;

    public DateInterval(String sDate,String eDate){

        this.sDate = sDate;
        this.eDate = eDate;
    }

    public String getSDate(){
        return sDate;
    }

    public String getEDate(){
        return eDate;
    }

}
