package app.domain.model.sortingAlgorithms;

import app.domain.model.Client;

import java.util.List;

public class InsertionSort implements SortingAlgorithms{


    public static List<Client> insertionSortArrayList(List<Client> clients, int dummy) {
        for (int j = 1; j < clients.size(); j++) {
            Client current = clients.get(j);
            int i = j - 1;
            while ((i > -1) && (clients.get(i).getName().compareTo(current.getName()) > 0)) {
                clients.set(i + 1, clients.get(i));
                i--;
            }
            clients.set(i + 1, current);
        }
        return clients;
    }


    public static List<Client> insertionSortArrayList(List<Client> clients) {
        for (int j = 1; j < clients.size(); j++) {
            Client current = clients.get(j);
            int i = j - 1;
            while ((i > -1) && (clients.get(i).getTin() > (current.getTin()))) {
                clients.set(i + 1, clients.get(i));
                i--;
            }
            clients.set(i + 1, current);
        }
        return clients;
    }
    @Override
    public void sortMethod(List<String> arrayNames,List<Long> arrayTins, List<Client> clients,int dummy) {
        insertionSortArrayList(clients,dummy);
        insertionSortArrayList(clients);
    }
}



