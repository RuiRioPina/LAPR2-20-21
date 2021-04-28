package test.java;

import app.domain.model.Client;
import auth.domain.model.Password;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClientTest {

    @Test
    public void generatePassword() {
        Client client = new Client(3123, 31231, 2302, "M",
                "yau@sa.com", 1234, 123, "Ruo");

        String actual = client.generatePassword();
        String expected=client.getPassword();
        Password pass = new Password(client.getPassword());
        assertEquals(expected, actual);

        assertTrue(pass.checkPassword(actual));

        assertFalse(pass.checkPassword("3123"));
        assertFalse(pass.checkPassword("31FEGakwqe"));
        assertFalse(pass.checkPassword("31235akwqe"));

    }

    @Test
    public void addClientRole() {
        //TODO
    }
}