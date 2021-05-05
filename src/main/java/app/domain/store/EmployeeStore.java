package app.domain.store;

import app.domain.model.Employee;
import app.domain.model.Role;

import java.util.ArrayList;

public class EmployeeStore {
    private ArrayList<Employee> lEmployee ;
    public EmployeeStore(){
        this.lEmployee=new ArrayList<>();
    }
public Employee createEmployee(String name, String adress, String SOC, long phoneNumber, String email, String userName, int nEmployee, Role role){
    return new Employee(name,adress,SOC,phoneNumber,email,userName,nEmployee,role);
}

    public boolean addEmployee(Employee e){
        if (e!=null){
            if (!exists(e)){
                return this.lEmployee.add(e);
            }
        }
        return false;

    }
    public boolean removeEmployee(Employee e)
    {
        if (e != null)
            return this.lEmployee.remove(e);
        return false;
    }
    public Employee get(int index){
        return lEmployee.get(index);
    }
    public boolean exists(Employee e){
    return this.lEmployee.contains(e);
    }
    public void validateEmployee(Employee e){
        if (exists(e)){
            throw new IllegalArgumentException("The employee you are trying to add is already registered.");
        }
    }
}
