package app.controller;

import app.domain.model.Company;

public class ValidateWorkController {
    private Company company;
    public ValidateWorkController(){
        this.company=App.getInstance().getCompany();
    }
    public void showUnvalidatedTests(){

    }
}
