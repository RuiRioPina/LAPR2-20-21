package app.controller;

import app.domain.model.Company;
import app.domain.model.Test;
import app.domain.store.LabCoordinatorStore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

public class IntervalController {

    List<Test> tests = App.getInstance().getCompany().getAllTest();

    LabCoordinatorStore lcs;

    private Company company;

    public IntervalController(){
        this.company=App.getInstance().getCompany();
        this.lcs = this.company.getLabCoorStore();
    }


    public int[] getArrayBeforeMax(String dStart, String dEnd){
        Calendar sDate = tStringToCalendar(dStart);
        Calendar eDate = tStringToCalendar(dEnd);
        int dif = eDate.get(Calendar.DAY_OF_MONTH) - sDate.get(Calendar.DAY_OF_MONTH);
        int[] list = LabCoordinatorStore.listMax(sDate, eDate, tests);
        return list;
    }

    public int[] getArrayMax(int [] array){
        return LabCoordinatorStore.maxSubArray(array);
    }

    public Map<String,Integer> getReadyTestsByDay(Calendar dStart,Calendar eStart){
        return this.lcs.getNumberTestsByDay(this.tests,dStart, eStart,"readyTests");
    }

    public Map<String,Integer> getDiagnosisTestsByDay(Calendar dStart,Calendar eStart){
        return this.lcs.getNumberTestsByDay(this.tests,dStart, eStart,"diagnosisTests");
    }

    public Map<String,Integer> getMissingResultsTestsByDay(Calendar dStart,Calendar eStart){
        return this.lcs.getNumberTestsByDay(this.tests,dStart, eStart,"missingResultsTests");
    }

    public Map<String,Integer> getReadyTestsByYear(Calendar dStart,Calendar eStart){
        return this.lcs.getNumberTestsByYear(this.tests,dStart, eStart,"readyTests");
    }

    public Map<String,Integer> getDiagnosisTestsByYear(Calendar dStart,Calendar eStart){
        return this.lcs.getNumberTestsByYear(this.tests,dStart, eStart,"diagnosisTests");
    }

    public Map<String,Integer> getMissingResultsTestsByYear(Calendar dStart,Calendar eStart){
        return this.lcs.getNumberTestsByYear(this.tests,dStart, eStart,"missingResultsTests");
    }

    public Map<String,Integer> getReadyTestsByMonth(Calendar dStart,Calendar eStart){
        return this.lcs.getNumberTestsByMonth(this.tests,dStart, eStart,"readyTests");
    }

    public Map<String,Integer> getDiagnosisTestsByMonth(Calendar dStart,Calendar eStart){
        return this.lcs.getNumberTestsByMonth(this.tests,dStart, eStart,"diagnosisTests");
    }

    public Map<String,Integer> getMissingResultsTestsByMonth(Calendar dStart,Calendar eStart){
        return this.lcs.getNumberTestsByMonth(this.tests,dStart, eStart,"missingResultsTests");
    }

    public Map<String,Integer> getReadyTestsByWeek(Calendar dStart,Calendar eStart){
        return this.lcs.getNumberTestsByWeek(this.tests,dStart, eStart,"readyTests");
    }

    public Map<String,Integer> getDiagnosisTestsByWeek(Calendar dStart,Calendar eStart){
        return this.lcs.getNumberTestsByWeek(this.tests,dStart, eStart,"diagnosisTests");
    }

    public Map<String,Integer> getMissingResultsTestsByWeek(Calendar dStart,Calendar eStart){
        return this.lcs.getNumberTestsByWeek(this.tests,dStart, eStart,"missingResultsTests");
    }

    public Map<String,Integer> getClientsByDay(Calendar dStart,Calendar eStart){
        return this.lcs.getNumberTestsByDay(this.tests,dStart, eStart,"clients");
    }

    public Map<String,Integer> getClientsByWeek(Calendar dStart,Calendar eStart){
        return this.lcs.getNumberTestsByWeek(this.tests,dStart, eStart,"clients");
    }

    public Map<String,Integer> getClientsByMonth(Calendar dStart,Calendar eStart){
        return this.lcs.getNumberTestsByMonth(this.tests,dStart, eStart,"clients");
    }

    public Map<String,Integer> getClientsByYear(Calendar dStart,Calendar eStart){
        return this.lcs.getNumberTestsByYear(this.tests,dStart, eStart,"clients");
    }

    public static Calendar tStringToCalendar(String txt) {
        String message = "Invalid format";
        String save = "";
        try {
            if (!dateValidation(txt)) {
                throw new NumberFormatException();
            }
            String[] data = txt.split("/");
            if (data.length != 3) {
                throw new NumberFormatException();
            }
            save = data[0];
            data[0] = data[2];
            data[2] = save;
            int ano = Integer.parseInt(data[0]);
            int mes = Integer.parseInt(data[1]);
            int dia = Integer.parseInt(data[2]);

            Calendar calendar = new GregorianCalendar();
            calendar.set(Calendar.YEAR, ano);
            calendar.set(Calendar.MONTH, mes);
            calendar.add(Calendar.MONTH, -1);
            calendar.set(Calendar.DAY_OF_MONTH, dia);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateFormat.setLenient(false);

            return calendar;
        } catch (Exception e) {
            System.out.println(message);
            return null;
        }

    }

    private static boolean dateValidation(String date) {
        boolean status = false;

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date);
            status = true;
        } catch (Exception e) {
            status = false;
        }
        return status;
    }

}