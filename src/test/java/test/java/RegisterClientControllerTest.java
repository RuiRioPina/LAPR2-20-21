package app.controller;

import app.domain.model.Client;
import app.domain.store.ClientList;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

import static org.junit.Assert.*;

public class RegisterClientControllerTest {

    @Test
    public void createClient() {
        RegisterClientController registerClientController = new RegisterClientController();
        Client expected = new Client(9999999999999999L, 9999999999L, "10-10-2010", "Male", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        Client actual = registerClientController.createClient(9999999999999999L, 9999999999L, "10-10-2010", "Male", 99999999999L, 9999999999L, "ruipina@mail.com", "Rui Pina");
        assertEquals(expected, actual);
    }

    @Test
    public void testCreateClientWithoutSex() {
        RegisterClientController registerClientController = new RegisterClientController();
        Client expected = new Client(9999999999999999L, 9999999999L, "10-10-2010", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        Client actual = registerClientController.createClient(9999999999999999L, 9999999999L, "10-10-2010", 99999999999L, 9999999999L, "ruipina@mail.com", "Rui Pina");
        assertEquals(expected, actual);
    }

    @Test
    public void saveClient() {
        RegisterClientController registerClientController = new RegisterClientController();
        ClientList clientList = new ClientList();
        Client client1 = clientList.createClient(9999999999999999L, 9999999999L, "10-10-2010", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        Client client2 = clientList.createClient(9991999999315259L, 9999999999L, "10-10-2010", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        registerClientController.saveClient(client1);
        clientList.saveClient(client2);
        boolean expected = true;
        boolean notExpected = false;
        boolean actual1 = registerClientController.isClientInList(client1);
        boolean actual2 = clientList.isClientInList(client2);
        assertEquals(expected, actual1);
        assertNotEquals(notExpected, actual2);
    }


    @Test
    public void validateCcn() {
        RegisterClientController registerClientController = new RegisterClientController();

        registerClientController.validateCcn(9999999999999999L);
        registerClientController.validateCcn(9999299999999999L);
        registerClientController.validateCcn(1000000000000000L);
    }

    @Test
    public void validateNhsNumber() {
        RegisterClientController registerClientController = new RegisterClientController();

        registerClientController.validateNhsNumber(9999999999L);
        registerClientController.validateNhsNumber(9999299999L);
        registerClientController.validateNhsNumber(1000000000L);
    }

    @Test
    public void validateBirthDate() {
        ExpectedException exceptionRule = ExpectedException.none();
        RegisterClientController registerClientController = new RegisterClientController();
        try {
            registerClientController.validateBirthDate("08-05-2025");
            registerClientController.validateBirthDate("08-05-1871");
            registerClientController.validateBirthDate("08-05-2021");
            registerClientController.validateBirthDate("08-0-2021");
            registerClientController.validateBirthDate("08-05-201");
            registerClientController.validateBirthDate("8-05-2021");
            registerClientController.validateBirthDate("08/05/2021");
            registerClientController.validateBirthDate("08-05-2002");
            registerClientController.validateBirthDate("08/05/1300");
        } catch (IllegalArgumentException e) {
            exceptionRule.expect(IllegalArgumentException.class);
        }
    }

    @Test
    public void validateTin() {
        RegisterClientController registerClientController = new RegisterClientController();

        registerClientController.validateTin(9999999999L);
        registerClientController.validateTin(9999299999L);
        registerClientController.validateTin(1000000000L);
    }

    @Test
    public void validateSex() {
        ExpectedException exceptionRule = ExpectedException.none();
        RegisterClientController registerClientController = new RegisterClientController();
        try {
            registerClientController.validateSex("M");
            registerClientController.validateSex("Male");
            registerClientController.validateSex("Female");
            registerClientController.validateSex("F");
            registerClientController.validateSex(".");
            registerClientController.validateSex("table");
        } catch (IllegalArgumentException e) {
            exceptionRule.expect(IllegalArgumentException.class);
        }
    }

    @Test
    public void validatePhoneNumber() {
        RegisterClientController registerClientController = new RegisterClientController();

        registerClientController.validatePhoneNumber(99999999999L);
        registerClientController.validatePhoneNumber(99992999999L);
        registerClientController.validatePhoneNumber(10000000000L);
    }

    @Test
    public void validateName() {
        ExpectedException exceptionRule = ExpectedException.none();
        RegisterClientController registerClientController = new RegisterClientController();
        try {
            registerClientController.validateName("Jorge Sousa");
            registerClientController.validateName("Rui26");
            registerClientController.validateName("            ");
        } catch (IllegalArgumentException e) {
            exceptionRule.expect(IllegalArgumentException.class);
        }
    }

    @Test
    public void validateEmail() {
        ExpectedException exceptionRule = ExpectedException.none();
        RegisterClientController registerClientController = new RegisterClientController();
        try {
            registerClientController.validateEmail("rui@g.com");
            registerClientController.validateEmail("rui32@.com");
            registerClientController.validateEmail("rui32@gmcom");
        } catch (IllegalArgumentException e) {
            exceptionRule.expect(IllegalArgumentException.class);
        }

    }

    @Test
    public void isClientInList() {
        RegisterClientController registerClientController = new RegisterClientController();
        Client client = new Client(9999999999999999L, 7999999999L, "10-10-2010", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        registerClientController.saveClient(client);
        boolean expected = true;
        boolean actual = registerClientController.isClientInList(client);
        assertEquals(expected, actual);
    }

    @Test
    public void sendEmailToClient() throws IOException, InterruptedException {
        RegisterClientController registerClientController = new RegisterClientController();
        Client c = new Client(9999999999999999L, 9919999999L, "10-10-2010", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        boolean expected = true;
        boolean actual = registerClientController.sendEmailToClient(c);
        assertEquals(expected, actual);
    }

    @Test
    public void getClientList() {
        RegisterClientController registerClientController = new RegisterClientController();
        ClientList clientList = new ClientList();
        Client c = new Client(9999999999999999L, 9999399999L, "10-10-2010", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        registerClientController.saveClient(c);
        clientList.saveClient(c);
        ClientList expected = App.getInstance().getCompany().getClientList();
        ClientList actual = registerClientController.getClientList();
        assertEquals(expected, actual);
    }
}