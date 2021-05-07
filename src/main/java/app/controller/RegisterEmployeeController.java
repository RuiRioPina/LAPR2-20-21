package app.controller;

import app.domain.model.Company;
import app.domain.model.Employee;
import app.domain.model.Role;
import app.domain.shared.Utils;
import app.domain.store.EmployeeStore;
import app.domain.store.RoleStore;


import java.util.List;

public class RegisterEmployeeController {
    private Company company;



    public RegisterEmployeeController() {
        this.company = App.getInstance().getCompany();
    }

    public List<Role> getlRole() {
        RoleStore lRole = this.company.getRoleStore();
        return lRole.getlRole();
    }

    public Employee createEmployee(String name, String adress, String SOC, long phoneNumber, String email, String userName, int nEmployees, Role role, String specialistDoctorIndexNumber) {
        EmployeeStore lEmployee = this.company.getEmployeeStore();
        return lEmployee.createEmployee(name, adress, SOC, phoneNumber, email, userName, nEmployees, role,specialistDoctorIndexNumber);
    }

    public void validateSOCInput(String SOC){
        if (Utils.validateSOC(SOC)){
            throw new IllegalArgumentException("SOC must have 4 digits and only numbers");
        }
    }

    public void validateNameInput(String name){
        if (Utils.nameContainsDigits(name)){
            throw new IllegalArgumentException("The name shouldn't have numbers");
        }

    }
    public void saveEmployee(Employee e){
       EmployeeStore lEmployee=this.company.getEmployeeStore();
       lEmployee.saveEmployee(e);

    }
    public  int getRoleIndex(String s){
        switch (s){
            case "REC":
                return 0;
            case "CCT":
                return 1;
            case "MLT":
                return 2;
            case "LC":
                return 3;
            case "SD":
                return 4;
            default:return -1;
        }


    }
}
