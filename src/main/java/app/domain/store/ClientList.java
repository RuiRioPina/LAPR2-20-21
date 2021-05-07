package app.domain.store;

import app.domain.model.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientList {
    private List<Client> clientList;

    public ClientList() {
        this.clientList = new ArrayList<>();
    }

    public List<Client> getClientList() {
        return this.clientList;
    }


    public Client createClient(long ccn, long nhsNumber, String birthDate, String sex, String email, long tin, long phoneNumber, String name) {
        return new Client(ccn, nhsNumber, birthDate, sex, email, tin, phoneNumber, name);
    }

    public Client createClient(long ccn, long nhsNumber, String sex, String email, long tin, long phoneNumber, String name) {
        return new Client(ccn, nhsNumber, sex, email, tin, phoneNumber, name);
    }

    private void add(Client c) {
        this.clientList.add(c);
    }

    public void saveClient(Client c) {
        this.add(c);
    }

    public void printList() {
        for (Client c : clientList) {
            System.out.println(c);
        }
    }


    public boolean isClientInList(Client c) {
        return this.clientList.contains(c);
    }

}