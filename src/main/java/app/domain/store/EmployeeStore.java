package app.domain.store;

import app.domain.model.Employee;
import app.domain.model.Role;

import java.util.ArrayList;

public class EmployeeStore {
    private ArrayList<Employee> lEmployee ;

    public ArrayList<Employee> getlEmployee() {
        return lEmployee;
    }

    public EmployeeStore(){
        this.lEmployee=new ArrayList<>();
    }
public Employee createEmployee(String name, String adress, String SOC, long phoneNumber, String email, String userName, int nEmployee, Role role){
    return new Employee(name,adress,SOC,phoneNumber,email,userName,nEmployee,role);
}
    public Employee createEmployee(String name, String adress, String SOC, long phoneNumber, String email, String userName, int nEmployee, Role role,String specialistDoctorIndexNumber){
        return new Employee(name,adress,SOC,phoneNumber,email,userName,nEmployee,role,specialistDoctorIndexNumber);
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
    public void saveEmployee(Employee e){
        if (e.validateEmployee()==true){
            addEmployee(e);
        }

    }
    public static void printStore(EmployeeStore lEmployee){
        for (Employee employee:lEmployee.lEmployee
             ) {
            System.out.println(employee);

        }

    }
}
