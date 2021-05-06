package app.controller;

import app.domain.model.Company;
import app.domain.model.Employee;
import app.domain.model.Role;
import app.domain.shared.Utils;
import app.domain.store.EmployeeStore;
import app.domain.store.RoleStore;

import java.util.ArrayList;
import java.util.List;

public class RegisterEmployeeController {
    private Company company;
    private Employee employee;


    public RegisterEmployeeController() {
        this.company = App.getInstance().getCompany();
    }

    public ArrayList<Role> getlRole() {
        RoleStore lRole = this.company.getRoleStore();
        return lRole.getlRole();
    }

    public Employee createEmployee(String name, String adress, String SOC, long phoneNumber, String email, String userName, int nEmployees, Role role, String specialistDoctorIndexNumber) {
        EmployeeStore lEmployee = this.company.getEmployeeStore();
        Employee e = lEmployee.createEmployee(name, adress, SOC, phoneNumber, email, userName, nEmployees, role,specialistDoctorIndexNumber);
        this.employee = e;
        return e;
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
}
