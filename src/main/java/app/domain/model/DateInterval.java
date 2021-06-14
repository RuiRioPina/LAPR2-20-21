package app.domain.model;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class DateInterval {

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
