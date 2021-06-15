package app.controller;

import app.domain.model.Company;
import app.domain.model.DateInterval;
import app.domain.model.Report;
import app.domain.model.Test;
import app.domain.store.LabCoordinatorStore;
import app.domain.store.TestStore;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class IntervalController {

    List<Test> tests = App.getInstance().getCompany().getAllTest();

    private Company company;
    private DateInterval dateInterval;

    public String getSDate() {
        return this.dateInterval!=null?this.dateInterval.getSDate():null;
    }

    public String getEDate() {
        return this.dateInterval!=null?this.dateInterval.getEDate():null;
    }

    public Calendar getDate(String txt) {
        LabCoordinatorStore lcs = this.company.getLabCoorStore();
        return lcs.tStringToCalendar(txt);
    }

    public String getOk() {
        return this.dateInterval!=null?this.dateInterval.getSDate():null;
    }

    public String getOk2() {
        return this.dateInterval!=null?this.dateInterval.getSDate():null;
    }

    public String getMax(Calendar s, Calendar e, int[] sum){
        s.add(Calendar.MONTH,+1);
        e.add(Calendar.MONTH,+1);
        StringBuilder max = new StringBuilder();
        long count=0;
        int bHour=8;
        int eHour=20;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Calendar j = new GregorianCalendar(s.get(Calendar.YEAR),s.get(Calendar.MONTH),s.get(Calendar.DAY_OF_MONTH),bHour,30,0);
        for (; s.before(e); s.add(Calendar.MINUTE, +30)) {
            j.add(Calendar.MINUTE,30);
            if ((s.get(Calendar.HOUR_OF_DAY)==eHour && (s.get(Calendar.MINUTE)==0)) || s.get(Calendar.DAY_OF_WEEK)==0){
                s.add(Calendar.DAY_OF_MONTH,+1);
                s.set(Calendar.HOUR_OF_DAY,bHour);
                j.add(Calendar.DAY_OF_MONTH,+1);
                j.set(Calendar.HOUR_OF_DAY,bHour);
            }else{
                count++;
                if (sum[0]==count){
                    max.append(sdf.format(s.getTime()));
                    max.append(" - ");
                }
                if (sum[sum.length-1]==count){
                    max.append(sdf.format(j.getTime()));
                }
            }
        }
        return max.toString();
    }

    public IntervalController(){
        this.company=App.getInstance().getCompany();
    }

    public int[] maxPlaces(String s, String e) {
        LabCoordinatorStore lcs = this.company.getLabCoorStore();
        Calendar st = lcs.tStringToCalendar(s);
        st.add(Calendar.HOUR,+8);
        Calendar end = lcs.tStringToCalendar(e);
        end.add(Calendar.HOUR,+20);
        int[]lm= lcs.listMax(st,end);
        return lcs.maxSubArraySum(lm);
    }

    public boolean validation(String Date){
        this.company.getLabCoorStore().newCategory(Date);
        return true;
    }

}
