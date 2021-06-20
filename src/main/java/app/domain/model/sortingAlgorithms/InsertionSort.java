package app.domain.model.sortingAlgorithms;

import app.domain.model.Client;

import java.util.List;

/**
 * Sort algorithm Insertion Sort which implements sorting algorithm and overrides its methods
 */
public class InsertionSort implements SortingAlgorithms{

    /**
     * This sorts the clients by name
     * @param clients clients with more than one test validated
     * @param dummy just to not use the other sorting algorithm
     * @return a list of clients containg all the tests sorted by name
     */
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

    /**
     * This sorts the clients by tin
     * @param clients clients with more than one test validated
     * @return a list of clients containg all the tests sorted by tin
     */
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

    /**
     * Overridden method which sorts the clients
     * @param arrayNames names of the clients
     * @param arrayTins tins of the clients
     * @param clients clients in the company
     * @param dummy just to differentiate from string to tin
     */
    @Override
    public void sortMethod(List<String> arrayNames,List<Long> arrayTins, List<Client> clients,int dummy) {
        insertionSortArrayList(clients,dummy);
        insertionSortArrayList(clients);
    }
}



