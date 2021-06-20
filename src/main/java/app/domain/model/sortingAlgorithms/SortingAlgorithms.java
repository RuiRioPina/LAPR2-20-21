package app.domain.model.sortingAlgorithms;

import app.domain.model.Client;

import java.util.List;

public interface SortingAlgorithms {
    /**
     * The method to be overridden by sorting methods
     * @param arrayNames names of the clients
     * @param arrayTins tins of the clients
     * @param clients clients in the company
     * @param dummy just to differentiate from string to tin
     */
    void sortMethod(List<String> arrayNames, List<Long> arrayTins, List<Client> clients, int dummy);
}
