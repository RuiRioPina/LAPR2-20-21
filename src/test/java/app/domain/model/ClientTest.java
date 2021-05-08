package app.domain.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;


public class ClientTest {

    @Test
    public void generatePassword() {
        //Arrange
        Client client = new Client(9999999999999999L, 9999999999L, "10-10-10", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        //Act
        String actual = client.generatePassword();
        int expected = 10;
        //Assert

        assertEquals(expected, actual.length(), 0.01);
        assertFalse(actual.length() != 10);


    }

    @Test
    public void testEquals() {
        Client client1 = new Client(9999999999999999L, 9999999999L, "10-10-2010", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        Client client2 = new Client(9999999999999999L, 9999999999L, "10-10-2010", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        Client client3 = new Client(1111111111111111L, 9999999999L, "10-10-2010", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        Client client4 = new Client(9999999999999999L, 9999999L, "10-10-2010", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        Client client5 = new Client();
        Object obj = new Object();
        Assert.assertNotEquals(client1, obj);
        Assert.assertNotEquals(client1, client3);
        Assert.assertNotEquals(client1, null);
        Assert.assertNotEquals(client5, client4);
        Assert.assertEquals(client1, client2);
        Assert.assertEquals(client1, client1);
        Assert.assertEquals(client4, client4);

    }

    @Test
    public void validateCcn() {

        Client client4 = new Client();

        boolean actual1 = client4.validateCcn(1000000000000000L);
        boolean actual2 = client4.validateCcn(9999999999999999L);
        boolean actual3 = client4.validateCcn(999999999999999L);
        boolean actual4 = client4.validateCcn(99999999999999999L);
        assertTrue(actual1);
        assertFalse(actual2);
        assertTrue(actual3);
        assertFalse(actual4);


    }

    @Test
    public void validateNhsNumber() {
        Client client4 = new Client();

        boolean actual1 = client4.validateNhsNumber(1000000000L);
        boolean actual2 = client4.validateNhsNumber(99999999999L);
        boolean actual3 = client4.validateNhsNumber(9999999999L);
        boolean actual4 = client4.validateNhsNumber(99999999999999999L);
        assertTrue(actual1);
        assertFalse(actual2);
        assertTrue(actual3);
        assertFalse(actual4);
    }

    @Test
    public void validateBirthDate() {
        ExpectedException exceptionRule = ExpectedException.none();

        Client client = new Client(9999999999999999L, 9999999999L, "10-10-2021", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        Client client2 = new Client(999999999999999L, 9999999999L, "10-03-1871", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        Client client3 = new Client(999999999999999L, 9999999999L, "10-0-1890", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        Client client4 = new Client(999999999999999L, 9999999999L, "10-03-187", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        Client client5 = new Client(999999999999999L, 9999999999L, "1-03-1990", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        Client client6 = new Client(999999999999999L, 9999999999L, "38-12-1880", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        Client client7 = new Client(999999999999999L, 9999999999L, "10-13-1880", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        Client client8 = new Client(999999999999999L, 9999999999L, "", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        Client client9 = new Client(9999999999999999L, 9999999999L, "10-10-2021", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        Client client10 = new Client(9999999999999999L, 9999999999L, "10/10/1921", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");


        boolean actual1 = client.validateBirthDate(client.getBirthDate());
        boolean actual2 = client2.validateBirthDate(client2.getBirthDate());
        boolean actual3 = client3.validateBirthDate(client3.getBirthDate());
        boolean actual4 = client4.validateBirthDate(client4.getBirthDate());
        boolean actual5 = client5.validateBirthDate(client5.getBirthDate());
        boolean actual6 = client6.validateBirthDate(client6.getBirthDate());
        boolean actual7 = client7.validateBirthDate(client7.getBirthDate());
        boolean actual8 = client8.validateBirthDate(client8.getBirthDate());
        boolean actual9 = client9.validateBirthDate(client9.getBirthDate());
        boolean actual10 = client10.validateBirthDate(client10.getBirthDate());
        boolean actual11 = client.validateBirthDate("04-05-1871");
        boolean actual12 = client.validateBirthDate("              ");


        assertFalse(actual1);
        assertFalse(actual2);
        assertFalse(actual3);
        assertFalse(actual4);
        assertFalse(actual5);
        assertFalse(actual6);
        assertFalse(actual7);
        assertFalse(actual8);
        assertFalse(actual9);
        assertTrue(actual10);
        assertFalse(actual11);
        assertTrue(actual12);


    }


    @Test
    public void validateTin() {
        Client client4 = new Client();

        boolean actual1 = client4.validateTin(1000000000L);
        boolean actual2 = client4.validateTin(99999999999L);
        boolean actual3 = client4.validateTin(9999999999L);
        boolean actual4 = client4.validateTin(99999999999999999L);
        assertTrue(actual1);
        assertFalse(actual2);
        assertTrue(actual3);
        assertFalse(actual4);
    }

    @Test
    public void validateSex() {
        Client client4 = new Client();

        boolean actual1 = client4.validateSex("M");
        boolean actual2 = client4.validateSex("table");
        boolean actual3 = client4.validateSex(".");
        boolean actual4 = client4.validateSex("Jorge");
        assertTrue(actual1);
        assertFalse(actual2);
        assertTrue(actual3);
        assertFalse(actual4);
    }

    @Test
    public void validatePhoneNumber() {
        Client client4 = new Client();

        boolean actual1 = client4.validatePhoneNumber(10000000000L);
        boolean actual2 = client4.validatePhoneNumber(999999999999L);
        boolean actual3 = client4.validatePhoneNumber(99999999999L);
        boolean actual4 = client4.validatePhoneNumber(999999999999999999L);
        assertTrue(actual1);
        assertFalse(actual2);
        assertTrue(actual3);
        assertFalse(actual4);
    }

    @Test
    public void validateEmail() {
        ExpectedException exceptionRule = ExpectedException.none();

        Client client = new Client(9999999999999999L, 9999999999L, "10-10-2010", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        Client client1 = new Client(9999999999999999L, 9999999999L, "10-10-2010", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        try {
            client.validateEmail(client.getEmail());
            client1.validateEmail(client1.getEmail());
        } catch (IllegalArgumentException e) {
            exceptionRule.expect(IllegalArgumentException.class);
        }
    }

    @Test
    public void validateName() {
        ExpectedException exceptionRule = ExpectedException.none();

        Client client = new Client(9999999999999999L, 9999999999L, "10-10-2010", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        Client client1 = new Client(9999999999999999L, 9999999999L, "10-10-2010", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina312312");
        Client client2 = new Client(9999999999999999L, 9999999999L, "10-10-2010", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "       ");
        boolean actual1 = client.validateName(client.getName());
        boolean actual2 = client1.validateName(client1.getName());
        boolean actual3 = client2.validateName(client2.getName());
        boolean actual4 = client2.validateName("Hippopotomonstrosesquippedaliophobias      ");
        assertTrue(actual1);
        assertFalse(actual2);
        assertFalse(actual3);
        assertFalse(actual4);


    }

    @Test
    public void getCcn() {
        Client client = new Client(9999919999999999L, 9999999999L, "10-10-2010", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");

        long expected = 9999919999999999L;
        long notExpected = 1111111111111111L;
        long actual = client.getCcn();

        assertEquals(expected, actual);
        assertNotEquals(notExpected, actual);

    }

    @Test
    public void getNhsNumber() {
        Client client = new Client(9999999999999989L, 9999999999L, "10-10-2010", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");

        long expected = 9999999999L;
        long notExpected = 1111111111L;
        long actual = client.getNhsNumber();

        assertEquals(expected, actual);
        assertNotEquals(notExpected, actual);

    }

    @Test
    public void getBirthDate() {
        Client client = new Client(9993999999999999L, 9999999999L, "10-10-2010", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");

        String expected = "10-10-2010";
        String notExpected = "10-10-2009";
        String actual = client.getBirthDate();

        assertEquals(expected, actual);
        assertNotEquals(notExpected, actual);


    }

    @Test
    public void getTin() {
        Client client = new Client(9999999999199999L, 9999999999L, "10-10-2010", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");

        long expected = 9999999999L;
        long notExpected = 1111111111L;
        long actual = client.getTin();

        assertEquals(expected, actual);
        assertNotEquals(notExpected, actual);

    }

    @Test
    public void getPhoneNumber() {
        Client client = new Client(9992999999999999L, 9999999999L, "10-10-2010", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");

        long expected = 99999999999L;
        long notExpected = 11111111111L;
        long actual = client.getPhoneNumber();

        assertEquals(expected, actual);
        assertNotEquals(notExpected, actual);

    }

    @Test
    public void getSex() {
        Client client = new Client(9999999999939999L, 9999999999L, "10-10-2010", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");

        String expected = "M";
        String notExpected = "F";
        String actual = client.getSex();

        assertEquals(expected, actual);
        assertNotEquals(notExpected, actual);

    }

    @Test
    public void getEmail() {
        Client client = new Client(9999999999999999L, 9999999999L, "10-10-2010", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");

        String expected = "ruipina@mail.com";
        String notExpected = "jorgesousa@mail.com";
        String actual = client.getEmail();

        assertEquals(expected, actual);
        assertNotEquals(notExpected, actual);

    }

    @Test
    public void getName() {
        Client client = new Client(9999999999999999L, 9999999999L, "10-10-2010", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");

        String expected = "Rui Pina";
        String notExpected = "Rui ";
        String actual = client.getName();

        assertEquals(expected, actual);
        assertNotEquals(notExpected, actual);

    }

    @Test
    public void testToString() {

        Client client = new Client(9999999999999999L, 9999999999L, "10-10-2010", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        Client client1 = new Client(9999999999999999L, 9999999999L, "10-10-2010", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        String expected = "The client is called Rui Pina, his ccn is 9999999999999999, his NHS Number is 9999999999, his birthdate is 10-10-2010 , his sex is M, his tin is 9999999999, and his email is ruipina@mail.com";
        String expected1 = "The client is called Rui Pina, his ccn is 9999999999999999, his NHS Number is 9999999999, his birthdate is 10-10-2010, his tin is 9999999999, and his email is ruipina@mail.com";
        Assert.assertEquals(expected, client.toString());
        Assert.assertEquals(expected1, client1.toString());

    }
}