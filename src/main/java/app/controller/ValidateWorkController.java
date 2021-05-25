package app.controller;

import app.domain.model.Company;

public class ValidateWorkController {
    private Company company;
    public ValidateWorkController(){
        this.company=App.getInstance().getCompany();
    }
    public void showUnvalidatedTests(){
        for (int i=0;i<company.getTestStore().getUnvalidatedTests().size();i++){
            int plusOne=i+1;
            System.out.println(plusOne +" " +App.getInstance().getCompany().getTestStore().getUnvalidatedTests().get(i).getWorkDatesString());
        }
    }
}
