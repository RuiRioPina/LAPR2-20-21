package app.domain.store;

import app.domain.model.Client;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ClientListTest {

    @Test
    public void createClient() {
        ClientList clientList = new ClientList();
        clientList.createClient(9999999999999999L, 9999999999L, "10-10-2010", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        clientList.createClient(1111111111111111L, 9999999999L, "10-10-2010", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
    }


    @Test
    public void saveClient() {
        ClientList clientList = new ClientList();
        Client client1 = clientList.createClient(9999999999999999L, 9999999999L, "10-10-2010", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        Client client2 = clientList.createClient(1111111111111111L, 9999999999L, "10-10-2010", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        clientList.saveClient(client1);
        clientList.saveClient(client2);
        clientList.saveClient(client1);
        boolean expected = true;
        boolean notExpected = false;
        boolean actual1 = clientList.isClientInList(client1);
        boolean actual2 = clientList.isClientInList(client2);
        assertEquals(expected, actual1);
        assertNotEquals(notExpected, actual2);
    }

    @Test
    public void getClients() {
        List<Client> c = new ArrayList<>();
        ClientList clientList = new ClientList();
        Client client1 = clientList.createClient(9999999999999999L, 9999999999L, "10-10-2010", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        Client client2 = clientList.createClient(1111111111111111L, 9999999999L, "10-10-2010", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui fPina");
        clientList.saveClient(client1);
        List<Client> actual = clientList.getClients();
        c.add(client1);
        assertEquals(c, actual);
        clientList.saveClient(client2);
        List<Client> actual1 = clientList.getClients();
        assertNotEquals(c, actual1);
    }


    @Test
    public void isClientInList() {
        ClientList clientList = new ClientList();
        Client client1 = clientList.createClient(9999999999999999L, 9999999999L, "10-10-2010", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        Client client2 = clientList.createClient(1111111111111111L, 9999999999L, "10-10-2010", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        clientList.saveClient(client1);
        boolean expected = true;
        boolean expected2 = false;
        boolean actual1 = clientList.isClientInList(client1);
        boolean actual2 = clientList.isClientInList(client2);
        assertEquals(expected, actual1);
        assertEquals(expected2, actual2);
        assertNotEquals(expected, actual2);
        assertNotEquals(expected2, actual1);
    }
}