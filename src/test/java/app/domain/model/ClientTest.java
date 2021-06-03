package app.domain.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.Assert.*;


public class ClientTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    LocalDate currentDate = LocalDate.now();

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

    @Test(expected = IllegalArgumentException.class)
    public void validateCcn() {

        Client client4 = new Client();
        Client client5 = new Client();
        client5.setCcn(1000000000000000L);

        client4.validateCcn(1000000000000000L);
        client4.validateCcn(9999999999999999L);
        client4.validateCcn(999999999999999L);
        client4.validateCcn(99999999999999999L);


    }

    @Test(expected = IllegalArgumentException.class)
    public void validateNhsNumber() {
        Client client4 = new Client();

        client4.validateNhsNumber(1000000000L);
        client4.validateNhsNumber(99999999999L);
        client4.validateNhsNumber(9999999999L);
        client4.validateNhsNumber(99999999999999999L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateBirthDate() {

        Client client = new Client(9999999999999999L, 9999999999L, currentDate.getDayOfMonth() + "-" + currentDate.getMonth() + "-" + (currentDate.getYear() + 1), "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        Client client2 = new Client(999999999999999L, 9999999999L, "10-03-1871", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        Client client3 = new Client(999999999999999L, 9999999999L, "10-0-1890", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        Client client4 = new Client(999999999999999L, 9999999999L, "10-03-187", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        Client client5 = new Client(999999999999999L, 9999999999L, "1-03-1990", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        Client client6 = new Client(999999999999999L, 9999999999L, "38-11-1880", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        Client client7 = new Client(999999999999999L, 9999999999L, "10-13-1880", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        Client client8 = new Client(999999999999999L, 9999999999L, "", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        Client client9 = new Client(9999999999999999L, 9999999999L, currentDate.getDayOfMonth() + "-" + currentDate.getMonth() + "-" + (currentDate.getYear()), "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        Client client10 = new Client(9999999999999999L, 9999999999L, "10/10/1921", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");


        client.validateBirthDate(client.getBirthDate());
        client2.validateBirthDate(client2.getBirthDate());
        client3.validateBirthDate(client3.getBirthDate());
        client4.validateBirthDate(client4.getBirthDate());
        client5.validateBirthDate(client5.getBirthDate());
        client6.validateBirthDate(client6.getBirthDate());
        client7.validateBirthDate(client7.getBirthDate());
        client8.validateBirthDate(client8.getBirthDate());
        client9.validateBirthDate(client9.getBirthDate());
        client10.validateBirthDate(client10.getBirthDate());
        client.validateBirthDate("04-05-1871");
        client.validateBirthDate("              ");
        client.validateBirthDate("10-12-1999");
        client.validateBirthDate("10--1-1999");
        client.validateBirthDate("10-13-1999");
        client.validateBirthDate("-");
        client.validateBirthDate("");
        client.validateBirthDate("12-01-2070");
        client.validateBirthDate("12-01-1300");
        client.validateBirthDate(currentDate.getDayOfMonth() + "-" + currentDate.getMonth() + "-" + (currentDate.getYear() - 150));
    }


    @Test(expected = IllegalArgumentException.class)
    public void validateTin() {
        Client client4 = new Client();

        client4.validateTin(1000000000L);
        client4.validateTin(99999999999L);
        client4.validateTin(9999999999L);
        client4.validateTin(99999999999999999L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateSex() {
        Client client4 = new Client();

        client4.validateSex("M");
        client4.validateSex("table");
        client4.validateSex("");
        client4.validateSex("Jorge");


    }

    @Test(expected = IllegalArgumentException.class)
    public void validatePhoneNumber() {
        Client client4 = new Client();

        client4.validatePhoneNumber(10000000000L);
        client4.validatePhoneNumber(999999999999L);
        client4.validatePhoneNumber(99999999999L);
        client4.validatePhoneNumber(999999999999999999L);
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

    @Test(expected = IllegalArgumentException.class)
    public void validateName() {

        Client client = new Client(9999999999999999L, 9999999999L, "10-10-2010", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        Client client1 = new Client(9999999999999999L, 9999999999L, "10-10-2010", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina312312");
        Client client2 = new Client(9999999999999999L, 9999999999L, "10-10-2010", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "       ");
        client.validateName(client.getName());
        client1.validateName(client1.getName());
        client2.validateName(client2.getName());
        client2.validateName("Hippopotomonstrosesquippedaliophob      ");
        client2.validateName("Hippopotomonstrosesquippedaliophobia");
        client2.validateName("Hippopotomonstrosesquippedaliophobi");








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
        String expected = "The client is called Rui Pina, his ccn is 9999999999999999, his NHS Number is 9999999999, his birthdate is 10-10-2010 , his sex is M, his tin is 9999999999, his phonenumber 99999999999 and his email is ruipina@mail.com";
        String expected1 = "The client is called Rui Pina, his ccn is 9999999999999999, his NHS Number is 9999999999, his birthdate is 10-10-2010, his tin is 9999999999, his phonenumber 99999999999 and his email is ruipina@mail.com";
        Assert.assertEquals(expected, client.toString());
        Assert.assertEquals(expected1, client1.toString());

    }

    @Test
    public void getPassword() {

        Client client = new Client(9999999999999999L, 9999999999L, "10-10-2010", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        Client client1 = new Client(9999999999999999L, 9999999999L, "10-10-2010", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        String expected = "The client is called Rui Pina, his ccn is 9999999999999999, his NHS Number is 9999999999, his birthdate is 10-10-2010 , his sex is M, his tin is 9999999999, his phonenumber 99999999999 and his email is ruipina@mail.com";
        String expected1 = "The client is called Rui Pina, his ccn is 9999999999999999, his NHS Number is 9999999999, his birthdate is 10-10-2010, his tin is 9999999999, his phonenumber 99999999999 and his email is ruipina@mail.com";
        Assert.assertEquals(10, client.getPassword().length());
        Assert.assertEquals(10, client1.getPassword().length());

    }
}