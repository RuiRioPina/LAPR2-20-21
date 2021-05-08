package app.domain.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class RoleTest {
@Test
    public void getRoleDescription(){
        Role r1=new Role("Receives Clients","Receptionist","Rec");
        String expected="Receives Clients";
        String notExpected="Receive Clients";
        assertEquals(r1.getRoleDescription(),expected);
        assertNotEquals(r1.getRoleDescription(),notExpected);
    }
    @Test
    public void getRoleShortname(){
        Role r1=new Role("Receives Clients","Receptionist","Rec");
        String expected="Rec";
        String notExpected="Recs";
        assertEquals(r1.getRoleShortname(),expected);
        assertNotEquals(r1.getRoleShortname(),notExpected);
    }
    @Test
     public void roleEquals(){
        Role r1=new Role("Receives Clients","Receptionist","Rec");
        Role r2=new Role("Receives Clients","Receptionist","Rec");
        Role r3=new Role("Receives Clientss","Receptionist","Rec");
        Object o= new Object();
        Role r4=null;
        assertEquals(r1,r1);
        assertNotEquals(r1,r3);
        assertNotEquals(r1,o);
        assertEquals(r1,r2);
        assertNotEquals(r1,r4);
}
@Test
    public void toStringTest(){
    Role r1=new Role("Receives Clients","Receptionist","Rec");
    String expected="Receptionist. Receives Clients. Rec.";
    String notExpected="Receptionist. ReceivesClients. Recs";
    assertEquals(r1.toString(),expected);
    assertNotEquals(r1.toString(),notExpected);
}
@Test
    public void validateRole(){
    Role r1=new Role("Receives Clients","Receptionist","Rec");
    Role r2=new Role("Receives Clients","Receptionist","Receptionistbed");
    Role r3=new Role("Receives Clients","Receptionist","Receptionistbede");
    Role r4=new Role("Receives Clients","Receptionist","Receptionistbedes");
    assertTrue(r1.validateRole());
    assertTrue(r2.validateRole());
    assertFalse(r3.validateRole());
    assertFalse(r4.validateRole());

}

}