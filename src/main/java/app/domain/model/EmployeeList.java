package app.domain.model;

import auth.domain.model.Email;
import auth.domain.model.Password;

import java.util.ArrayList;

public class EmployeeList {
    ArrayList<Employee> employeeList= new ArrayList<Employee>();
    public Employee createEmployee(String name, int tim, String adress, int dayOfBirth, int monthOfBirth, int yearOfBirth, int sex, int phoneNumber, Email email, Password password, String userName, String roleID, String roleDescription) {
        return new Employee(name,  tim,  adress,  dayOfBirth,  monthOfBirth,  yearOfBirth, sex, phoneNumber, email, password, userName, roleID, roleDescription);
    }

    public boolean add(Employee e){
        if (e.validateEmployee()){
            employeeList.add(e);
        }
        return true;
    }
    public Employee get(int index){
        return employeeList.get(index);
    }
}
