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
        assertEquals(expectedID,e1.getId());

    }
    @Test
    public void  testEquals(){
        Role r1=new Role("Receives Clients","Receptionist","Rec");
        Employee e1= new Employee("Tiago Santa Cruz","Porto","1234",937845773,"tiagosantacruz@gmail.com","Tiago",2,r1,null);
        Employee e2= new Employee("Tiago Santa Cruz","Porto","1234",937845773,"tiagosantacruz@gmail.com","Tiago",2,r1,null);
        Employee e3= new Employee("Tiago Santoss","Porto","1234",937845763,"tiagosantos@gmail.com","TiagoS",3,r1,null);
        Object obj= new Object();
        assertNotEquals(e1,obj);
        assertNotEquals(e1,e3);
        assertNotEquals(e1,null);
        assertEquals(e1,e2);
        assertEquals(e1,e1);
    }
    @Test
    public void getPhoneNumber(){
        Role r1=new Role("Receives Clients","Receptionist","Rec");
        long expected=999999999L;
        long notExpected=111111111L;
        Employee e1= new Employee("Tiago Santa Cruz","Porto","1234",999999999L,"tiagosantacruz@gmail.com","Tiago",2,r1,null);
        long actual=e1.getPhoneNumber();
assertEquals(expected,actual);
assertNotEquals(notExpected,actual);

    }
}