package app.domain.model;

import java.util.Comparator;
import java.util.List;

public class SelectionSort implements Comparator<Client> {

    @Override
    public int compare(Client client, Client otherClient) {
        return Long.compare(client.getTin(), otherClient.getTin());
        //TODO POR OS ALGORITMOS DO LADO DE FORA DO COMPARE E DEPOIS USAR JAVA REFLECTION PARA IR BUSCAR O ALGORITMO A USAR PARA CADA CASO, PELO CONFIGURATION FILE

    }

    /**
     * Selection sort algorithm to sort the clients by tin
     *
     * @param arr     all the tin numbers associated to all the clients in the list
     * @param clients the list of clients that will be sorted and where the information to be sorted is.
     * @return sorted list of clients
     */
    public List<Client> selectionSort(List<Long> arr, List<Client> clients) {
        List<Client> sortedClients = clients;
        int n = arr.size();

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i + 1; j < n; j++)
                if (arr.get(j) < arr.get(min_idx))
                    min_idx = j;

            // Swap the found minimum element with the first
            // element
            long temp = arr.get(min_idx);
            arr.set(min_idx,arr.get(i));
            arr.set(i,temp);
            swap(sortedClients, min_idx, i);
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
    public List<Client> selectionSort(String[] arr, List<Client> clients) {
        int n = arr.length;

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i + 1; j < n; j++)
                if (arr[j].compareTo(arr[min_idx]) < 0)
                    min_idx = j;

            // Swap the found minimum element with the first
            // element
            String temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
            swap(clients, min_idx, i);
        }

        return clients;
    }

    public static List<Client> swap(List<Client> clients, int indexA, int indexB) {
        Client tmp = clients.get(indexA);
        clients.set(indexA, clients.get(indexB));
        clients.set(indexB, tmp);
        return clients;
    }


}
