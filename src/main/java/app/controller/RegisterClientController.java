package app.controller;

import app.domain.model.Client;
import app.domain.shared.Constants;
import app.domain.store.ClientList;
import auth.AuthFacade;

import java.util.List;

public class RegisterClientController {

    ClientList clientList = new ClientList();
    Client clt = new Client();
    AuthFacade authFacade = new AuthFacade();

    public RegisterClientController() {
        if (!isUserLoggedInReceptionist()) {
            System.out.println("You are not a receptionist and therefore can't access this menu");
        }
    }

    public Client createClient(long ccn, long nhsNumber, String birthDate, String sex,
                               long tin, long phoneNumber, String email, String name) {
        return clientList.createClient(ccn, nhsNumber, birthDate, sex, email, tin, phoneNumber, name);

    }

    private boolean isUserLoggedInReceptionist() {

        return !authFacade.getCurrentUserSession().isLoggedInWithRole(Constants.ROLE_RECEPTIONIST);

    }

    public Client createClient(long ccn, long nhsNumber, String birthDate,
                               long tin, long phoneNumber, String email, String name) {
        return clientList.createClient(ccn, nhsNumber, birthDate, email, tin, phoneNumber, name);

    }

    public void saveClient(Client c) {
        clientList.saveClient(c);
    }

    public void showClient(Client c) {
        System.out.println(c);
    }

    public void validateCcn(long ccn) {
        clt.validateCcn(ccn);
    }

    public void validateNhsNumber(long nhsNumber) {
        clt.validateNhsNumber(nhsNumber);
    }

    public void validateBirthDate(String birthDate) {
        clt.validateBirthDate(birthDate);
    }

    public void validateTin(long tin) {
        clt.validateTin(tin);
    }

    public void validateSex(String sex) {
        clt.validateSex(sex);
    }

    public void validatePhoneNumber(long phoneNumber) {
        clt.validatePhoneNumber(phoneNumber);
    }

    public void validateName(String name) {
        clt.validateName(name);
    }

    public void validateEmail(String email) {
        clt.validateEmail(email);
    }

    public boolean isClientInList(Client c) {
        return clientList.isClientInList(c);
    }


}
