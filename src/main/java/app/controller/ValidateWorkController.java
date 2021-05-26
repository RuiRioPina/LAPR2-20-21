package app.controller;

import app.domain.model.Company;
import app.domain.model.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ValidateWorkController {
    private Company company;
    public ValidateWorkController(){
        this.company=App.getInstance().getCompany();
    }
    public void showUnvalidatedTests(){
        List<Test> lTests= App.getInstance().getCompany().getTestStore().getUnvalidatedTests();
        for (int i=0;i<lTests.size();i++){
            int numToShow=i+1;
            System.out.println(numToShow + " " + lTests.get(i));
        }
    }
    public void askConfirmation(String selectedTestString){
        List<Test> lTestsToBeValidated=getTestsToBeValidated(selectedTestString);
        System.out.println(lTestsToBeValidated);
        System.out.println("Are you sure you want to confirm the previous tests:(Y/N)");
    }
    public List<Test> getTestsToBeValidated(String selectedTestString){
        List<Test> lTests=App.getInstance().getCompany().getTestStore().getUnvalidatedTests();
        if (selectedTestString.equals("ALL")){
            return lTests;
        }
        List<Test> lTestsToBeValidated=new ArrayList<>();
        String[] arrString= selectedTestString.split(" ");
        int [] arrInt= new int[arrString.length];
        for (int i=0;i<arrString.length;i++){
            arrInt[i]=Integer.parseInt(arrString[i])-1;
        }
        for (int i=0;i<arrInt.length;i++){
            lTestsToBeValidated.add(lTests.get(arrInt[i]));
        }
        return lTestsToBeValidated;
    }
    public void validateTests(String selectedTestString){
        Date currentDate= new Date(System.currentTimeMillis());
        List<Test> lTestsToBeValidated=getTestsToBeValidated(selectedTestString);
        App.getInstance().getCompany().getTestStore().validateTests(lTestsToBeValidated,currentDate);
    }
    public void showAllTests(){
        System.out.println(App.getInstance().getCompany().getTestStore().getTests());
    }
}
