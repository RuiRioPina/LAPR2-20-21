package test.java;


import app.domain.model.Employee;
import app.domain.model.Role;
import org.junit.Test;


import static org.junit.Assert.*;
public class EmployeeTest {

    @Test
    public void generateID() {
        Role r1=new Role("Receives Clients","Receptionist","Rec");
        Employee e1= new Employee("Tiago Santa Cruz","Porto","1234",937845773,("tiagosantacruz@gmail.com"),"xd",2,r1,null);
        String expectedID = "TSC00002";
        assertEquals(expectedID,e1.getID());

    }
    @Test
    public void  equals(){
        Role r1=new Role("Receives Clients","Receptionist","Rec");
        Employee e1= new Employee("Tiago Santa Cruz","Porto","1234",937845773,("tiagosantacruz@gmail.com"),"Tiago",2,r1,null);
        Employee e2= new Employee("Tiago Santa Cruz","Porto","1234",937845773,("tiagosantacruz@gmail.com"),"Tiago",2,r1,null);
        assertTrue(e1.equals(e2));
        e2.setAdress("Lisboa");
        assertFalse(e1.equals(e2));
    }
}