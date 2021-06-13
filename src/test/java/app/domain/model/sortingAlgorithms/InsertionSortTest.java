package app.domain.model.sortingAlgorithms;

import app.domain.model.Client;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class InsertionSortTest {

    @Test
    public void insertionSortArrayListByName() {
        List<Client> clients = new ArrayList<>();
        List<Client> sortedClients = new ArrayList<>();
        List<String> names = new ArrayList<>();
        Client c1 = new Client(1234567890123251L, 1231564290, "22-01-2002", "ts@gmail.com", 9111111110L, 22221221222L, "Ana Saul");
        Client c2 = new Client(1234567890123151L, 1234564290, "22-01-2002", "ruipinas@gmail.com", 1111111110L, 22221222222L, "Rui Pina");
        Client c3 = new Client(1234567220123151L, 1234561290, "22-01-2002", "ruipinat@gmail.com", 1111121110L, 22221222212L, "Jorge Ferreira");
        sortedClients.add(c1);
        sortedClients.add(c3);
        sortedClients.add(c2);
        clients.add(c1);
        clients.add(c2);
        clients.add(c3);
        for (Client clt : clients) {
            names.add(clt.getName());
        }
        List<Client> expected =sortedClients ;
        List<Client> actual = InsertionSort.insertionSortArrayList(clients,1);
        assertEquals(expected,actual);
    }

    @Test
    public void testInsertionSortArrayListByTin() {
        SelectionSort sort = new SelectionSort();
        List<Client> clients = new ArrayList<>();
        List<Client> sortedClients = new ArrayList<>();
        List<Long> tins = new ArrayList<>();
        Client c1 = new Client(1234567890123251L, 1231564290, "22-01-2002", "ts@gmail.com", 9111111110L, 22221221222L, "Ana Saul");
        Client c2 = new Client(1234567890123151L, 1234564290, "22-01-2002", "ruipinas@gmail.com", 1111111110L, 22221222222L, "Rui Pina");
        Client c3 = new Client(1234567220123151L, 1234561290, "22-01-2002", "ruipinat@gmail.com", 1111121110L, 22221222212L, "Jorge Ferreira");
        sortedClients.add(c2);
        sortedClients.add(c3);
        sortedClients.add(c1);
        clients.add(c1);
        clients.add(c2);
        clients.add(c3);
        for (Client clt : clients) {
            tins.add(clt.getTin());
        }
        List<Client> expected =sortedClients ;
        List<Client> actual = InsertionSort.insertionSortArrayList(clients);
        assertEquals(expected,actual);
    }
}