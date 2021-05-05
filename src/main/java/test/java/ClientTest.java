package test.java;

import app.domain.model.Client;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClientTest {

    @Test
    public void generatePassword() {
        //Arrange
        Client client = new Client(9999999999999999L,99999999999L,"10-10-10","M","ruiriopina02@mail.com",99999999999L,999999999999L,"Rui Pina");
        //Act
        String actual = client.generatePassword();
        int expected=10;
        //Assert
        assertEquals(expected, actual.length(),0.01);
        assertFalse( actual.length()!=10);

    }

    @Test
    public void testEquals() {
        //Arrange
        Client client = new Client(9999999999999999L,99999999999L,"10-10-10","M","ruiriopina02@mail.com",99999999999L,999999999999L,"Rui Pina");
        Client client1 = new Client(9999999999999999L,99999999999L,"10-10-10","M","ruiriopina02@mail.com",99999999999L,999999999999L,"Rui Pina");
        Client client2 = new Client(1,99999999999L,"10-10-10","M","ruiriopina02@mail.com",99999999999L,999999999999L,"Rui Pina");
        //Act
        boolean expected = true;
        boolean expected2 = false;
        boolean actual = client.equals(client1);
        boolean actual2 = client.equals(client2);



        //Assert

        assertEquals(expected,actual);
        assertEquals(expected2,actual2);

    }

}