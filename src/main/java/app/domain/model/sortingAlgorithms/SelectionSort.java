package app.domain.model.sortingAlgorithms;

import app.domain.model.Client;

import java.util.*;

public class SelectionSort implements SortingAlgorithms {

    /**
     * Selection sort algorithm to sort the clients by tin
     *
     * @param arr     all the tin numbers associated to all the clients in the list
     * @param clients the list of clients that will be sorted and where the information to be sorted is.
     * @return sorted list of clients
     */
    public static List<Client> selectionSort(List<Long> arr, List<Client> clients) {
        List<Client> sortedClients = clients;
        int n = arr.size();
        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted array
            int minIdx = i;
            for (int j = i + 1; j < n; j++)
                if (arr.get(j) < arr.get(minIdx))
                    minIdx = j;

            // Swap the found minimum element with the first
            // element
            long temp = arr.get(minIdx);
            arr.set(minIdx, arr.get(i));
            arr.set(i, temp);
            sortedClients=swap(sortedClients, minIdx, i);
        }

        return sortedClients;
    }

    /**
     * Selection sort algorithm to sort the clients by name
     *
     * @param arr     all the tin numbers associated to all the clients in the list
     * @param clients the list of clients that will be sorted and where the information to be sorted is.
     * @return sorted list of clients
     */
    public static List<Client> selectionSort(List<String> arr, List<Client> clients, int dummy) {
        int n = arr.size();
        List<Client> sorted = new ArrayList<>();
        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted array
            int minimumIndex = i;
            for (int j = i + 1; j < n; j++)
                if (arr.get(j).compareTo(arr.get(minimumIndex)) < 0)
                    minimumIndex = j;

            // Swap the found minimum element with the first
            // element
            String temp = arr.get(minimumIndex);
            arr.set(minimumIndex, arr.get(i));
            arr.set(i, temp);
            sorted = swap(clients, minimumIndex, i);
        }

        return sorted;
    }

    /**
     * Swap the clients
     * @param clients the clients
     * @param indexA the first client
     * @param indexB the second client
     * @return list with the clients swapped
     */

    public static List<Client> swap(List<Client> clients, int indexA, int indexB) {
        Client tmp = clients.get(indexA);
        clients.set(indexA, clients.get(indexB));
        clients.set(indexB, tmp);
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
        selectionSort(arrayNames,clients,dummy);
        selectionSort(arrayTins,clients);
    }


}

