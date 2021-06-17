package app.domain.model.sortingAlgorithms;

import app.domain.model.Client;

import java.util.List;

public interface SortingAlgorithms {
    void sortMethod(List<String> arrayNames, List<Long> arrayTins, List<Client> clients, int dummy);
}
