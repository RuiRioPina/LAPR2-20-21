package app.domain.store;

import app.controller.App;
import app.domain.model.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientList {
    private final List<Client> listOfClients;

    /**
     * empty constructor of the class ClientList which initializes the arraylist.
     */
    public ClientList() {
        this.listOfClients = new ArrayList<>();
    }

    /**
     * Create an instance of the Class Client using the constructor from the class Client.
     *
     * @param ccn         The Citizen card number of the client
     * @param nhsNumber   National Health Service number of the client
     * @param birthDate   The birth date of the client
     * @param sex         The sex of the client
     * @param email       The email of the client
     * @param tin         The tax identification number of the client
     * @param phoneNumber Phone number of the client
     * @param name        The name of the client
     * @return the object Client containing the information passed through parameters.
     */
    public Client createClient(long ccn, long nhsNumber, String birthDate, String sex, String email, long tin, long phoneNumber, String name) {
        return new Client(ccn, nhsNumber, birthDate, sex, email, tin, phoneNumber, name);
    }

    /**
     * Create an instance of the Class Client using the constructor from the class Client.
     *
     * @param ccn         The Citizen card number of the client
     * @param nhsNumber   National Health Service number of the client
     * @param birthDate   The birth date of the client
     * @param email       The email of the client
     * @param tin         The tax identification number of the client
     * @param phoneNumber Phone number of the client
     * @param name        The name of the client
     * @return the object Client containing the information passed through parameters.
     */
    public Client createClient(long ccn, long nhsNumber, String birthDate, String email, long tin, long phoneNumber, String name) {
        return new Client(ccn, nhsNumber, birthDate, email, tin, phoneNumber, name);
    }

    /**
     * Gets a copy of the arrayList containing the clients.
     *
     * @return List of Clients containing all clients stored in this class.
     */
    public List<Client> getClients() {
        List<Client> c = new ArrayList<>();
        c.addAll(this.listOfClients);
        return c;
    }

    public Client getClientByEmail(String email) {
    	for(Client c : this.listOfClients) {
        	if(c.getEmail().equalsIgnoreCase(email)) {
        		return c;
        	}
        }
    	return null;
    }

    private void add(Client c) {
        if (!listOfClients.contains(c)) {
            this.listOfClients.add(c);
        }
    }

    /**
     * Uses the private method add(Client c) to add it to the Class ClientList.
     *
     * @param c the object of the Class client
     */
    public void saveClient(Client c) {
        this.add(c);
    }


    /**
     * Checks if the Client to be added already exists in the arrayList or not.
     *
     * @param c object of the class Client
     * @return true if the Client already exists in the arrayList.
     */
    public boolean isClientInList(Client c) {
        return this.listOfClients.contains(c);
    }

}