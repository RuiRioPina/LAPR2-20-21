package app.controller;

import app.domain.model.Client;
import app.domain.model.Company;
import app.domain.shared.Constants;
import app.domain.store.ClientList;
import auth.AuthFacade;

import java.io.IOException;

public class RegisterClientController {

    Client clt;
    Company cmp;
    AuthFacade authFacade = new AuthFacade();

    /**
     * Constructor of the Class RegisterClientController
     */
    public RegisterClientController() {
        if (!isUserLoggedInReceptionist()) {
            System.out.println("You are not a receptionist and therefore can't access this menu");
        }
        this.clt = new Client();
        this.cmp = App.getInstance().getCompany();
    }

    /**
     * Brings the method from the class ClientList so that there's less coupling between the UI and domain layers.
     *
     * @param ccn         The client's Citizen card number
     * @param nhsNumber   National Health Service number of the client
     * @param birthDate   The birth date of the client
     * @param sex         The sex of the client
     * @param email       The email of the client
     * @param tin         The tax identification number of the client
     * @param phoneNumber Phone number of the client
     * @param name        The name of the client
     * @return an instance of the class Client containing the information passed by parameters
     */

    public Client createClient(long ccn, long nhsNumber, String birthDate, String sex,
                               long tin, long phoneNumber, String email, String name) {
        ClientList cl = this.cmp.getClientList();

        this.clt = cl.createClient(ccn, nhsNumber, birthDate, sex,
                email, phoneNumber, tin, name);
        return this.clt;

    }

    /**
     * Checks if the User Logged in is a receptionist
     *
     * @return true if it is a receptionist handling the platform
     */
    private boolean isUserLoggedInReceptionist() {

        return !authFacade.getCurrentUserSession().isLoggedInWithRole(Constants.ROLE_RECEPTIONIST);

    }

    /**
     * Brings the method from the class ClientList so that there's less coupling between the UI and domain layers.
     *
     * @param ccn         The client's Citizen card number
     * @param nhsNumber   National Health Service number of the client
     * @param birthDate   The birth date of the client
     * @param email       The email of the client
     * @param tin         The tax identification number of the client
     * @param phoneNumber Phone number of the client
     * @param name        The name of the client
     * @return an instance of the class Client containing the information passed by parameters
     */

    public Client createClient(long ccn, long nhsNumber, String birthDate,
                               long tin, long phoneNumber, String email, String name) {
        ClientList cl = this.cmp.getClientList();

        this.clt = cl.createClient(ccn, nhsNumber, birthDate, email, phoneNumber, tin, name);
        return this.clt;
    }

    /**
     * Brings the method from the class Company so that there's less coupling between the UI and domain layers.
     *
     * @param c instance of the class Client
     */
    public boolean saveClient(Client c) {
        return cmp.saveClient(c);
    }

    /**
     * Shows the information on the object c using the method toString made available by the class Client
     *
     * @param c instance of the class Client
     */

    public void showClient(Client c) {
        System.out.println(c);
    }

    /**
     * Brings the method validateCcn from the class Client so that there is less coupling between the UI and domain layers.
     *
     * @param ccn The client's Citizen card number
     */

    public boolean validateCcn(long ccn) {
        return clt.validateCcn(ccn);
    }

    /**
     * Brings the method validateNhsNumber from the class Client so that there is less coupling between the UI and domain layers.
     *
     * @param nhsNumber The client's NHS number
     */

    public boolean validateNhsNumber(long nhsNumber) {
        return clt.validateNhsNumber(nhsNumber);
    }

    /**
     * Brings the method validateCcn from the class Client so that there is less coupling between the UI and domain layers.
     *
     * @param birthDate The client's birth date
     */
    public boolean validateBirthDate(String birthDate) {
        return clt.validateBirthDate(birthDate);
    }

    /**
     * Brings the method validateTin from the class Client so that there is less coupling between the UI and domain layers.
     *
     * @param tin The client's Tax identification number
     */

    public boolean validateTin(long tin) {
        return clt.validateTin(tin);
    }

    /**
     * Brings the method validateSex from the class Client so that there is less coupling between the UI and domain layers.
     *
     * @param sex The client's sex.
     */

    public boolean validateSex(String sex) {
        return clt.validateSex(sex);
    }

    /**
     * Brings the method validatePhoneNumber from the class Client so that there is less coupling between the UI and domain layers.
     *
     * @param phoneNumber The client's phone number
     */
    public boolean validatePhoneNumber(long phoneNumber) {
        return clt.validatePhoneNumber(phoneNumber);
    }

    /**
     * Brings the method validateName from the class Client so that there is less coupling between the UI and domain layers.
     *
     * @param name The client's name.
     */

    public boolean validateName(String name) {
        return clt.validateName(name);
    }

    /**
     * Brings the method validateEmail from the class Client so that there is less coupling between the UI and domain layers.
     *
     * @param email The client's email adress.
     */
    public void validateEmail(String email) {
        clt.validateEmail(email);
    }

    /**
     * Brings the method isClientInList from the class ClientList so that there is less coupling between the UI and domain layers.
     *
     * @param c instance of the Class
     * @return true if the Client is the ArrayList
     */
    public boolean isClientInList(Client c) {
        return cmp.getClientList().isClientInList(c);
    }

    /**
     * Brings the method sendEmailToClient from the class Company so that there is less coupling between the UI and domain layers.
     *
     * @param c instance of the Class
     * @return true if the "Email" was sent
     */

    public boolean sendEmailToClient(Client c) throws IOException, InterruptedException {
        cmp.sendEmailToClient(c);
        return true;
    }

    /**
     * Brings the method getClientList from the class ClientList so that there is less coupling between the UI and domain layers.
     *
     * @return the instance of the Class ClientList containing the current listing of the ClientList.
     */

    public ClientList getClientList() {
        return App.getInstance().getCompany().getClientList();
    }

}
