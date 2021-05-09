package app.domain.model;




import org.junit.Test;




import static org.junit.Assert.*;
public class EmployeeTest2 {

    @Test
    public void generateID() {
        Role r1=new Role("Receives Clients","Receptionist","Rec");
        Employee e1= new Employee("Tiago Santa Cruz","Porto","1234",937845773,("tiagosantacruz@gmail.com"),"xd",2,r1,null);
        String expectedID = "TSC00002";
        assertEquals(expectedID,e1.getID());

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
    @Test
    public void  getID(){
        Role r1=new Role("Receives Clients","Receptionist","Rec");
        String expected = "TSC00002";
        String notExpected="TSC00001";
        Employee e1= new Employee("Tiago Santa Cruz","Porto","1234",999999999L,"tiagosantacruz@gmail.com","Tiago",2,r1,null);
        String actual=e1.getID();
        assertEquals(expected,actual);
        assertNotEquals(notExpected,actual);

    }
    @Test
    public void generatePassword(){
        int expectedLength=7;
        int notExpectedLength=8;
        Role r1=new Role("Receives Clients","Receptionist","Rec");
        Employee e1= new Employee("Tiago Santa Cruz","Porto","1234",999999999L,"tiagosantacruz@gmail.com","Tiago",2,r1,null);
assertEquals(e1.generateEmployeePassword().length(),expectedLength);
assertNotEquals(e1.generateEmployeePassword().length(),notExpectedLength);
    }
    @Test
    public void setID(){
        Role r1=new Role("Receives Clients","Receptionist","Rec");
        Employee e1= new Employee("Tiago Santa Cruz","Porto","1234",999999999L,"tiagosantacruz@gmail.com","Tiago",2,r1,null);
        String desired="TSC1";
        String old="TSC00002";
        e1.setID("TSC1");
        assertEquals(e1.getID(),desired);
        assertNotEquals(e1.getID(),old);
    }
    @Test
    public void getSOC(){
        Role r1=new Role("Receives Clients","Receptionist","Rec");
        Employee e1= new Employee("Tiago Santa Cruz","Porto","1234",999999999L,"tiagosantacruz@gmail.com","Tiago",2,r1,null);
        String expected="1234";
        String notExpected="1233";
        assertEquals(e1.getSOC(),expected);
        assertNotEquals(e1.getSOC(),notExpected);
    }
    @Test
    public void setSOC(){
        Role r1=new Role("Receives Clients","Receptionist","Rec");
        Employee e1= new Employee("Tiago Santa Cruz","Porto","1234",999999999L,"tiagosantacruz@gmail.com","Tiago",2,r1,null);
        String desired="1233";
        String old="1234";
        e1.setSOC("1233");
        assertEquals(e1.getSOC(),desired);
        assertNotEquals(e1.getSOC(),old);
    }
    @Test
    public void setPhoneNumber(){
        Role r1=new Role("Receives Clients","Receptionist","Rec");
        Employee e1= new Employee("Tiago Santa Cruz","Porto","1234",999999999L,"tiagosantacruz@gmail.com","Tiago",2,r1,null);
        long desired=999999998L;
        long old=999999999L;
        e1.setPhoneNumber(999999998L);
        assertEquals(e1.getPhoneNumber(),desired);
        assertNotEquals(e1.getPhoneNumber(),old);
    }
    @Test
    public void getName(){
        Role r1=new Role("Receives Clients","Receptionist","Rec");
        Employee e1= new Employee("Tiago Santa Cruz","Porto","1234",999999999L,"tiagosantacruz@gmail.com","Tiago",2,r1,null);
        String expected="Tiago Santa Cruz";
        String notExpected="Tiago Santa Crux";
        assertEquals(e1.getName(),expected);
        assertNotEquals(e1.getName(),notExpected);
    }
    @Test
    public void setName(){
        Role r1=new Role("Receives Clients","Receptionist","Rec");
        Employee e1= new Employee("Tiago Santa Cruz","Porto","1234",999999999L,"tiagosantacruz@gmail.com","Tiago",2,r1,null);
        String desired="Tiago Santa Crux";
        String old="Tiago Santa Cruz";
        e1.setName("Tiago Santa Crux");
        assertEquals(e1.getName(),desired);
        assertNotEquals(e1.getName(),old);
    }
    @Test
    public void getAdress(){
        Role r1=new Role("Receives Clients","Receptionist","Rec");
        Employee e1= new Employee("Tiago Santa Cruz","Porto","1234",999999999L,"tiagosantacruz@gmail.com","Tiago",2,r1,null);
        String expected="Porto";
        String notExpected="Portos";
        assertEquals(e1.getAdress(),expected);
        assertNotEquals(e1.getAdress(),notExpected);
    }
    @Test
    public void setAdress(){
        Role r1=new Role("Receives Clients","Receptionist","Rec");
        Employee e1= new Employee("Tiago Santa Cruz","Porto","1234",999999999L,"tiagosantacruz@gmail.com","Tiago",2,r1,null);
        String desired="Portos";
        String old="Porto";
        e1.setAdress("Portos");
        assertEquals(e1.getAdress(),desired);
        assertNotEquals(e1.getAdress()  ,old);
    }
    @Test
    public void getSpecialistDoctorIndexNumber(){
        Role r1=new Role("Specialist Doctor","SPECIALIST DOCTOR","SD");
        Employee e1= new Employee("Tiago Santa Cruz","Porto","1234",999999999L,"tiagosantacruz@gmail.com","Tiago",2,r1,"123456");
        String expected="123456";
        String notExpected="12345";
        assertEquals(e1.getSpecialistDoctorIndexNumber(),expected);
        assertNotEquals(e1.getSpecialistDoctorIndexNumber(),notExpected);
    }
    @Test
    public void setSpecialistDoctorIndex(){
        Role r1=new Role("Specialist Doctor","SPECIALIST DOCTOR","SD");
        Employee e1= new Employee("Tiago Santa Cruz","Porto","1234",999999999L,"tiagosantacruz@gmail.com","Tiago",2,r1,"123456");
        String desired="123455";
        String old="123456";
        e1.setSpecialistDoctorIndexNumber("123455");
        assertEquals(e1.getSpecialistDoctorIndexNumber(),desired);
        assertNotEquals(e1.getSpecialistDoctorIndexNumber()  ,old);
    }
    @Test
    public void getUserName(){
        Role r1=new Role("Receives Clients","Receptionist","Rec");
        Employee e1= new Employee("Tiago Santa Cruz","Porto","1234",999999999L,"tiagosantacruz@gmail.com","Tiago",2,r1,null);
        String expected="Tiago";
        String notExpected="Taigos";
        assertEquals(e1.getUserName(),expected);
        assertNotEquals(e1.getUserName(),notExpected);
    }
    @Test
    public void setUserName(){
        Role r1=new Role("Receives Clients","Receptionist","Rec");
        Employee e1= new Employee("Tiago Santa Cruz","Porto","1234",999999999L,"tiagosantacruz@gmail.com","Tiago",2,r1,null);
        String desired="Tiagos";
        String old="Tiago";
        e1.setUserName("Tiagos");
        assertEquals(e1.getUserName(),desired);
        assertNotEquals(e1.getUserName()  ,old);
    }
    @Test
    public void getEmail(){
        Role r1=new Role("Receives Clients","Receptionist","Rec");
        Employee e1= new Employee("Tiago Santa Cruz","Porto","1234",999999999L,"tiagosantacruz@gmail.com","Tiago",2,r1,null);
        String expected="tiagosantacruz@gmail.com";
        String notExpected="tiagosantacrux@gmail.com";
        assertEquals(e1.getEmail(),expected);
        assertNotEquals(e1.getEmail(),notExpected);
    }
    @Test
    public void setEmail(){
        Role r1=new Role("Receives Clients","Receptionist","Rec");
        Employee e1= new Employee("Tiago Santa Cruz","Porto","1234",999999999L,"tiagosantacruz@gmail.com","Tiago",2,r1,null);
        String desired="tiagosantacrux@gmail.com";
        String old="tiagosantacruz@gmail.com";
        e1.setEmail("tiagosantacrux@gmail.com");
        assertEquals(e1.getEmail(),desired);
        assertNotEquals(e1.getEmail()  ,old);
    }
    @Test
    public void toStringTest(){
        Role r1=new Role("Receives Clients","Receptionist","Rec");
        Employee e1= new Employee("Tiago Santa Cruz","Porto","1234",999999999L,"tiagosantacruz@gmail.com","Tiago",2,r1,null);
        Role r2=new Role("Specialist Doctor","SPECIALIST DOCTOR","SD");
        Employee e2= new Employee("Tiago Santa Cruz","Porto","1234",999999999L,"tiagosantacruz@gmail.com","Tiago",2,r1,"123456");
      String expected1 = "This employee is named Tiago Santa Cruz. Their ID is TSC00002. Their adress is Porto. Their phone number is 999999999. \nTheir SOC is 1234. Their email adress is tiagosantacruz@gmail.com. Their username is Tiago. Their password is "+e1.getPassword()+". Their role is Receptionist.";
      String notExpected1 = "This employee is named Tiagos Santa Cruz. Their ID is TSC00002. Their adress is Porto. Their phone number is 999999999. \nTheir SOC is 1234. Their email adress is tiagosantacruz@gmail.com. Their username is Tiago. Their password is "+e1.getPassword()+". Their role is Receptionist.";
        String expected2 = "This employee is named Tiago Santa Cruz. Their ID is TSC00002. Their adress is Porto. Their phone number is 999999999. \nTheir SOC is 1234. Their email adress is tiagosantacruz@gmail.com. Their username is Tiago. Their password is "+e2.getPassword()+". Their role is Receptionist. Their doctor Index number is 123456";
        String notExpected2 = "This employee is named Tiago Santa Cruz. Their ID is TSC00002. Their adress is Porto. Their phone number is 999999999. \nTheir SOC is 1234. Their email adress is tiagosantacruz@gmail.com. Their username is Tiago. Their password is "+e1.getPassword()+". Their role is Receptionist.";
        assertEquals(e1.toString(),expected1);
        assertNotEquals(e1.toString(),notExpected1);
        assertEquals(e2.toString(),expected2);
        assertNotEquals(e2.toString(),notExpected2);
    }
    @Test
    public void validateEmployee(){
        Role r1=new Role("Receives Clients","Receptionist","Rec");
        Employee e1= new Employee("Tiago Santa Cruz","Porto","1234",999999999L,"tiagosantacruz@gmail.com","Tiago",2,r1,null);
        Employee e2= new Employee("Tiago Santa Cruz Com Nome Muito Grande","Porto","1234",999999999L,"tiagosantacruz@gmail.com","Tiago",2,r1,null);
        Employee e3= new Employee("Tiago Santa Cruz","Porto","12345",999999999L,"tiagosantacruz@gmail.com","Tiago",2,r1,null);
        Employee e4= new Employee("Tiago Santa Cruz","Porto","1234",999999999L,"tiagosantacruz@gmail.com","Tiago",2,r1,"1");
        Employee e5= new Employee("Tiago Santa Cruzaxczvbnmgfdsddddddd","Porto","1234",999999999L,"tiagosantacruz@gmail.com","Tiago",2,r1,"123456");
        Employee e6= new Employee("Tiago Santa Cruz","Porto","1234",999999999L,"tiagosantacruz@gmail.com","Tiago",2,null,null);
        Employee e7= new Employee("Tiago Santa Cruzaxczvbnmgfdsdddddddd","Porto","1234",999999999L,"tiagosantacruz@gmail.com","Tiago",2,r1,"123456");
        Employee e8= new Employee("Tiago Santa Cruzaxczvbnmgfdsddddddddd","Porto","1234",999999999L,"tiagosantacruz@gmail.com","Tiago",2,r1,"123456");
        Employee e9= new Employee("Tiago Santa Cruz2","Porto","1234",999999999L,"tiagosantacruz@gmail.com","Tiago",2,r1,null);

        assertTrue(e1.validateEmployee());
        assertFalse(e2.validateEmployee());
        assertFalse(e3.validateEmployee());
        assertFalse(e4.validateEmployee());
        assertTrue(e5.validateEmployee());
        assertFalse(e6.validateEmployee());
        assertFalse(e7.validateEmployee());
        assertFalse(e8.validateEmployee());
        assertFalse(e9.validateEmployee());

    }
    @Test
    public void getRole(){
        Role r1=new Role("Receives Clients","Receptionist","Rec");
        Employee e1= new Employee("Tiago Santa Cruz","Porto","1234",999999999L,"tiagosantacruz@gmail.com","Tiago",2,r1,null);
        Role expected = new Role("Receives Clients","Receptionist","Rec");
        Role notExpected = new Role("Receives Clients","Receptionis","Res");
        assertEquals(e1.getRole(),expected);
        assertNotEquals(e1.getRole(),notExpected);
    }


}