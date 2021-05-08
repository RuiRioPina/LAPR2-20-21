package app.domain.store;

import app.domain.model.Employee;
import app.domain.model.Role;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class EmployeeStoreTest {
   @Test
    public void getlEmployee(){
        Role r1=new Role("Receives Clients","Receptionist","Rec");
        Employee e1= new Employee("Tiago Santa Cruz","Porto","1234",937845773,"tiagosantacruz@gmail.com","Tiago",2,r1,null);
        EmployeeStore lEmployee = new EmployeeStore();
        lEmployee.addEmployee(e1);
        ArrayList<Employee> EmployeeList= new ArrayList<Employee>();
        EmployeeList.add(e1);
        assertEquals(lEmployee.getlEmployee(),EmployeeList);
    }
    @Test
    public void createEmployee(){
        Role r1=new Role("Receives Clients","Receptionist","Rec");
        Employee e1= new Employee("Tiago Santa Cruz","Porto","1234",937845773,"tiagosantacruz@gmail.com","Tiago",2,r1,null);
        EmployeeStore lEmployee = new EmployeeStore();
        assertEquals(lEmployee.createEmployee("Tiago Santa Cruz","Porto","1234",937845773,"tiagosantacruz@gmail.com","Tiago",2,r1,null),e1);

    }
    @Test
    public void addEmployee(){
        Role r1=new Role("Receives Clients","Receptionist","Rec");
        Employee e1= new Employee("Tiago Santa Cruz","Porto","1234",937845773,"tiagosantacruz@gmail.com","Tiago",2,r1,null);
        EmployeeStore lEmployee = new EmployeeStore();
        Employee e2=null;
        assertFalse(lEmployee.addEmployee(e2));
        assertTrue(lEmployee.addEmployee(e1));
        lEmployee.addEmployee(e1);
        assertFalse(lEmployee.addEmployee(e1));

    }
    @Test
    public void removeEmployee(){
        Role r1=new Role("Receives Clients","Receptionist","Rec");
        Employee e1= new Employee("Tiago Santa Cruz","Porto","1234",937845773,"tiagosantacruz@gmail.com","Tiago",2,r1,null);
        EmployeeStore lEmployee = new EmployeeStore();
        Employee e2=null;
        lEmployee.addEmployee(e1);
        assertFalse(lEmployee.removeEmployee(e2));
        assertTrue(lEmployee.removeEmployee(e1));

    }
    @Test
    public void get(){
        Role r1=new Role("Receives Clients","Receptionist","Rec");
        Employee e1= new Employee("Tiago Santa Cruz","Porto","1234",937845773,"tiagosantacruz@gmail.com","Tiago",2,r1,null);
        EmployeeStore lEmployee = new EmployeeStore();
        lEmployee.addEmployee(e1);
        assertEquals(e1,lEmployee.get(0));
    }
    @Test
    public void exists(){
        Role r1=new Role("Receives Clients","Receptionist","Rec");
        Employee e1= new Employee("Tiago Santa Cruz","Porto","1234",937845773,"tiagosantacruz@gmail.com","Tiago",2,r1,null);
        EmployeeStore lEmployee = new EmployeeStore();
        lEmployee.addEmployee(e1);
        Employee e2= new Employee("Tiago Santa Cru","Porto","1234",937845773,"tiagosantacruz@gmail.com","Tiago",2,r1,null);
        assertTrue(lEmployee.exists(e1));
        assertFalse(lEmployee.exists(e2));
    }

}