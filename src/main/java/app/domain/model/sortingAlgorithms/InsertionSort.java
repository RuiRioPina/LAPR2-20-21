package app.domain.model.sortingAlgorithms;

import app.domain.model.Client;

import java.util.List;

public class InsertionSort {


    public static List<Client> insertionSortArrayList(List<Client> clients, int dummy) {
        List<Client> sortedClients = clients;
        for (int j = 1; j < clients.size(); j++) {
            Client current = sortedClients.get(j);
            int i = j - 1;
            while ((i > -1) && (sortedClients.get(i).getName().compareTo(current.getName()) > 0)) {
                sortedClients.set(i + 1, clients.get(i));
                i--;
            }
            sortedClients.set(i + 1, current);
        }
        return sortedClients;
    }


    public static List<Client> insertionSortArrayList(List<Client> clients) {
        List<Client> sortedClients = clients;
        for (int j = 1; j < clients.size(); j++) {
            Client current = sortedClients.get(j);
            int i = j - 1;
            while ((i > -1) && (sortedClients.get(i).getTin() > (current.getTin()))) {
                sortedClients.set(i + 1, clients.get(i));
                i--;
            }
            sortedClients.set(i + 1, current);
        }
        return sortedClients;
    }
}



