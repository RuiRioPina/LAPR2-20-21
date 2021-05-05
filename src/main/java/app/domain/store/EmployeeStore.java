package app.domain.store;

import app.domain.model.Employee;
import app.domain.model.Role;

import java.util.ArrayList;

public class EmployeeStore {
    private ArrayList<Employee> lEmployee ;
    public EmployeeStore(){
        this.lEmployee=new ArrayList<>();
    }
public Employee create(String name, String adress, long SOC, long phoneNumber, String email, String userName,int nEmployee, Role role){
    return new Employee(name,adress,SOC,phoneNumber,email,userName,nEmployee,role);
}

    public boolean add(Employee e){
        if (e!=null){
            if (!exists(e)){
                return this.lEmployee.add(e);
            }
        }
        return false;

    }
    public boolean remove(Employee e)
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
}
