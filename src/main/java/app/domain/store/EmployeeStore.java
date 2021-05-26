package app.domain.store;

import app.controller.App;
import app.domain.model.Employee;
import app.domain.model.Role;

import java.util.ArrayList;
import java.util.List;

public class EmployeeStore {
    /**
     * Object oriented class used to Store the Employees within the company.
     */
    private ArrayList<Employee> lEmployee;

    /**
     * Getter for the List that contains Employees.
     *
     * @return List with the employees within the company.
     */
    public List<Employee> getlEmployee() {
        return lEmployee;
    }

    /**
     * Constructor for the Employee Store. It Creates an empty list that stores Employees.
     */
    public EmployeeStore() {
        this.lEmployee = new ArrayList<>();
    }

    /**
     * Creates an object of the employee Class.
     *
     * @param name-                        name of the employee.
     * @param adress-                      adress of the employee.
     * @param soc-                         Standard Occupational Classificational Code.
     * @param phoneNumber-                 phone number of the employee.
     * @param email-                       email of the employee.
     * @param userName-                    userName of the employee.
     * @param nEmployee-                   the number of employees that exist(used to generate the id).
     * @param role-                        Role of the employee in the system
     * @param specialistDoctorIndexNumber- if the employee is a specialist doctor it contains their doctor index number if not, if not the value is null
     * @return Object of the employee Class.
     */
    public Employee createEmployee(String name, String adress, String soc, long phoneNumber, String email, String userName, int nEmployee, Role role, String specialistDoctorIndexNumber) {
        return new Employee(name, adress, soc, phoneNumber, email, userName, nEmployee, role, specialistDoctorIndexNumber);
    }

    /**
     * Adds an Employee to the list if it does not exist in it and is valid.
     *
     * @param e- Employee to be added.
     * @return boolean value to confirm if the employee was added or not.
     */
    public boolean addEmployee(Employee e) {
        if (e != null && !exists(e)) {
            return this.lEmployee.add(e);
        }
        return false;

    }

    /**
     * Removes an Employee within the role list.
     *
     * @param e- Non Null Employee.
     * @return boolean value to confirm if the employee was removed or not.
     */
    public boolean removeEmployee(Employee e) {
        if (e != null)
            return this.lEmployee.remove(e);
        return false;
    }

    /**
     * Uses an index number to get a certain Employee from the list.
     *
     * @param index- index number of the desired Employee.
     * @return object of the Employee Class.
     */
    public Employee get(int index) {
        return lEmployee.get(index);
    }

    /**
     * Checks to see if an Employee already exists in the Employee list.
     *
     * @param e- Employee to be checked.
     * @return boolean value telling if the Employee is in the list or not.
     */
    public boolean exists(Employee e) {
        return this.lEmployee.contains(e);
    }

    /**
     * Adds an Employee to the list if its is valid.
     *
     * @param e- Employee to be added.
     */
    public void saveEmployee(Employee e) {
        if (e.validateEmployee()) {
            addEmployee(e);
            App.getInstance().getCompany().getAuthFacade().addUserWithRole(e.getUserName(), e.getEmail(), e.getPassword(), e.getRole().getRoleID());
        } else
            throw new IllegalArgumentException("You have made a mistake in typing your employee's data.The employee was not added.");

    }


}
