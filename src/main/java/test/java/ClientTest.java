package test.java;

import app.domain.model.Client;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClientTest {

    @Test
    public void generatePassword() {
        //Arrange
        Client client = new Client(9999999999L,99999999999L,10,10,10,"M","ruiriopina02@mail.com",99999999999L,999999999999L,"Rui Pina");
        //Act
        String actual = client.generatePassword();
        int expected=10;
        //Assert
        assertEquals(expected, actual.length(),0.01);
        assertFalse( actual.length()!=10);

    }

}