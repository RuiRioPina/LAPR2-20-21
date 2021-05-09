package app.controller;

import app.domain.model.Company;
import app.domain.model.Employee;
import app.domain.model.Role;
import app.domain.shared.Constants;
import app.domain.shared.Utils;
import app.domain.store.EmployeeStore;
import app.domain.store.RoleStore;
import auth.AuthFacade;


import java.util.List;

public class RegisterEmployeeController {
    /**
     * Controller Class for the Register Employee function[US-07 of the integrative project of 1st year ISEP DEI students].
     */
    private Company company;
    private AuthFacade authFacade = new AuthFacade();


    /**
     * Constructor of the Controller Class. Checks to see if the user is an administator, and gets the company that is using the software.
     */
    public RegisterEmployeeController() {
        if (!isUserLoggedInAdmin()){
            System.out.println("You do not have admin permissions.");
        }
        this.company = App.getInstance().getCompany();
    }

    /**
     * Gets the Role list of the company that is using the software
     * @return List with roles.
     */
    public List<Role> getlRole() {
        RoleStore lRole = this.company.getRoleStore();
        return lRole.getlRole();
    }

    /**
     * Creates an object of the employee Class.
     * @param name- name of the employee.
     * @param adress- adress of the employee.
     * @param SOC- Standard Occupational Classificational Code.
     * @param phoneNumber- phone number of the employee.
     * @param email- email of the employee.
     * @param userName- userName of the employee.
     * @param nEmployees- the number of employees that exist(used to generate the id).
     * @param role- Role of the employee in the system
     * @param specialistDoctorIndexNumber- if the employee is a specialist doctor it contains their doctor index number if not, if not the value is null
     * @return- Object of the employee Class.
     */
    public Employee createEmployee(String name, String adress, String SOC, long phoneNumber, String email, String userName, int nEmployees, Role role, String specialistDoctorIndexNumber) {
        EmployeeStore lEmployee = this.company.getEmployeeStore();
        return lEmployee.createEmployee(name, adress, SOC, phoneNumber, email, userName, nEmployees, role,specialistDoctorIndexNumber);
    }

    /**
     * Method used to direct the saving of an employee to the employee list.
     * @param e- Employee to be saved
     */
    public void saveEmployee(Employee e){
       EmployeeStore lEmployee=this.company.getEmployeeStore();
       lEmployee.saveEmployee(e);

    }

    /**
     * Method used to link the name of the current employee classes to their index number in the role list.
     * @param s- String that is the same as the role's shortname.
     * @return The index number of the desired role.
     */
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

    /**
     * Checks to see if the user is an administrator
     * @return boolean value telling if the user is an admin or not.
     */
    private boolean isUserLoggedInAdmin() {

        return !authFacade.getCurrentUserSession().isLoggedInWithRole(Constants.ROLE_ADMIN);

    }
}
