package app.controller;

import app.domain.model.Company;
import app.domain.model.Employee;
import app.domain.model.Role;
import app.domain.store.EmployeeStore;
import app.domain.store.RoleStore;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class RegisterEmployeeControllerTest {
    @Test
    public void createEmployee(){
        RegisterEmployeeController controller=new RegisterEmployeeController();
        Role r1=new Role("Receives Clients","Receptionist","Rec");
        Employee e1= new Employee("Tiago Santa Cruz","Porto","1234",937845773,"tiagosantacruz@gmail.com","Tiago",2,r1,null);
        assertEquals(controller.createEmployee("Tiago Santa Cruz","Porto","1234",937845773,"tiagosantacruz@gmail.com","Tiago",2,r1,null),e1);
    }
    @Test
    public void getRoleIndex(){
        RegisterEmployeeController controller=new RegisterEmployeeController();
        assertEquals(controller.getRoleIndex("REC"),0);
        assertEquals(controller.getRoleIndex("CCT"),1);
        assertEquals(controller.getRoleIndex("MLT"),2);
        assertEquals(controller.getRoleIndex("LC"),3);
        assertEquals(controller.getRoleIndex("SD"),4);
        assertEquals(controller.getRoleIndex("RECs"),-1);
    }

}