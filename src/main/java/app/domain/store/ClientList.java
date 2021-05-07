package app.domain.store;

import app.domain.model.Client;
import app.domain.model.ParameterCategory;

import java.util.ArrayList;
import java.util.List;

public class ClientList {
    private List<Client> clientList;

    public ClientList() {
        this.clientList = new ArrayList<Client>();
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

    public List<Client> getClients() {
		List<Client> c = new ArrayList<Client>();
		c.addAll(this.clientList);
		return c;
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