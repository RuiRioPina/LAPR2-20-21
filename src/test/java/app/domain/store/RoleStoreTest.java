package app.domain.store;

import app.domain.model.Role;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class RoleStoreTest {
    @Test
    public void create(){
        Role r1=new Role("Receives Clients","Receptionist","Rec");
        RoleStore lRole = new RoleStore();
        Role r2=lRole.create("Receives Clients","Receptionist","Rec");
        assertEquals(r1,r2);

    }
    @Test
    public void getlRole(){
        Role r1=new Role("Receives Clients","Receptionist","Rec");
        RoleStore lRole = new RoleStore();
        lRole.add(r1);
        ArrayList<Role> roleList= new ArrayList<Role>();
        roleList.add(r1);
        assertEquals(lRole.getlRole(),roleList);
    }
    @Test
    public void add(){
        Role r1=new Role("Receives Clients","Receptionist","Rec");
        RoleStore lRole = new RoleStore();
        lRole.add(r1);
        ArrayList<Role> roleList= new ArrayList<Role>();
        roleList.add(r1);
        Role r2=new Role("Receives Clients","Receptionist","Recverylongnamethatgivesnospace");
        assertFalse(lRole.add(r2));
        assertEquals(lRole.getlRole(),roleList);
    }
    @Test
    public void remove(){
        Role r1=new Role("Receives Clients","Receptionist","Rec");
        RoleStore lRole = new RoleStore();
        lRole.add(r1);
        ArrayList<Role> roleList= new ArrayList<Role>();
        roleList.add(r1);
        assertEquals(lRole.getlRole(),roleList);
        assertTrue(lRole.remove(r1));
        lRole.remove(r1);

        assertNotEquals(lRole.getlRole(),roleList);
        Role r3 =null;
        assertFalse(lRole.remove(r3));
    }
    @Test
    public void get(){
        Role r1=new Role("Receives Clients","Receptionist","Rec");
        RoleStore lRole = new RoleStore();
        lRole.add(r1);
        assertEquals(lRole.get(0),r1);
    }

}