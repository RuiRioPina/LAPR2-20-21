package app.controller;

import app.domain.model.Client;
import app.domain.model.Company;
import app.domain.model.TestType;
import app.domain.shared.Constants;
import app.domain.store.ClientList;
import app.domain.store.TestTypeStore;
import auth.AuthFacade;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class RegisterClientController {

    Client clt;
    Company cmp;
    AuthFacade authFacade = new AuthFacade();

    public RegisterClientController() {
        if (!isUserLoggedInReceptionist()) {
            System.out.println("You are not a receptionist and therefore can't access this menu");
        }
        this.clt = new Client();
        this.cmp = App.getInstance().getCompany();
    }

    public Client createClient(long ccn, long nhsNumber, String birthDate, String sex,
                               long tin, long phoneNumber, String email, String name) {
    	ClientList cl = this.cmp.getClientList();
		Client client = cl.createClient(ccn, nhsNumber, birthDate, sex,
				email, phoneNumber, tin, name);
		
		this.clt = client;
		return this.clt;

    }

    private boolean isUserLoggedInReceptionist() {

        return !authFacade.getCurrentUserSession().isLoggedInWithRole(Constants.ROLE_RECEPTIONIST);

    }

    public Client createClient(long ccn, long nhsNumber, String birthDate,
                               long tin, long phoneNumber, String email, String name) {
    	ClientList cl = this.cmp.getClientList();
		Client client = cl.createClient(ccn, nhsNumber, birthDate, email, phoneNumber, tin, name);
		
		this.clt = client;
		return this.clt;
    }

    public void saveClient(Client c) {
    	ClientList cl = this.cmp.getClientList();
		cl.saveClient(c);
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
        return cmp.getClientList().isClientInList(c);
    }

    public void sendEmailToClient(Client c) throws IOException, InterruptedException {
        cmp.sendEmailToClient(c);
    }


}
