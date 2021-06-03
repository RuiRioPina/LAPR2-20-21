package app.domain.model;

import app.controller.App;
import app.domain.store.ClientList;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;


public class ClientTest {
    LocalDate currentDate = LocalDate.now();
    Company cmp = App.getInstance().getCompany();
    ClientList clientList = cmp.getClientList();

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
        Client client6 = new Client();
        client4.setCcn(1L);
        client5.setCcn(1000000000000000L);
        client6.setCcn(1000000000000000L);
        clientList.saveClient(client5);
        clientList.saveClient(client6);

        client4.validateCcn(1L);
        client4.validateCcn(1000000000000000L);
        client4.validateCcn(9999999999999999L);
        client4.validateCcn(999999999999999L);
        client4.validateCcn(99999999999999999L);


    }

    @Test(expected = IllegalArgumentException.class)
    public void validateNhsNumber() {
        Client client4 = new Client();
        Client client5 = new Client();
        client4.setNhsNumber(1000000000L);
        client5.setNhsNumber(1000000000L);
        clientList.saveClient(client4);
        clientList.saveClient(client5);

        client4.validateNhsNumber(1000000000L);
        client4.validateNhsNumber(99999999999L);
        client4.validateNhsNumber(9999999999L);
        client4.validateNhsNumber(99999999999999999L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateBirthDate() {
        Client client = new Client(9999999999999999L, 9999999999L, currentDate.getDayOfMonth() + "-" + currentDate.getMonth() + "-" + (currentDate.getYear() + 1), "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");

        client.validateBirthDate((currentDate.getYear() - 150) + "-" +  (currentDate.getMonthValue()) + "-" +  currentDate.getMonthValue() + "-" + currentDate.getDayOfMonth());
        client.validateBirthDate(currentDate.getYear() + "-" + currentDate.getMonthValue() + "-" +  (currentDate.getDayOfMonth()+1));
        client.validateBirthDate(client.getBirthDate());
        client.validateBirthDate("04-05-1871");
        client.validateBirthDate(client.getBirthDate());

        client.validateBirthDate("12-13-2000");

        client.setBirthDate("04-05-187");
        client.validateBirthDate(client.getBirthDate());

        client.setBirthDate("     ");
        client.validateBirthDate(client.getBirthDate());
        client.validateBirthDate("              ");

        client.validateBirthDate(client.getBirthDate());
        client.validateBirthDate("10-12-1999");

        client.validateBirthDate(client.getBirthDate());
        client.validateBirthDate("10--1-1999");

        client.validateBirthDate(currentDate.getYear() + "-" + currentDate.getMonthValue() + "-" + (currentDate.getDayOfMonth()+1));

        client.validateBirthDate(client.getBirthDate());
        client.validateBirthDate("10-13-1999");

        client.validateBirthDate(client.getBirthDate());
        client.validateBirthDate("-");

        client.validateBirthDate(client.getBirthDate());
        client.validateBirthDate("");

        client.validateBirthDate(client.getBirthDate());
        client.validateBirthDate("12-01-2070");

        client.validateBirthDate(client.getBirthDate());
        client.validateBirthDate("12-01-1300");

        client.validateBirthDate(client.getBirthDate());
        client.validateBirthDate(currentDate.getDayOfMonth() + "-" + currentDate.getMonth() + "-" + (currentDate.getYear() - 150));
    }


    @Test(expected = IllegalArgumentException.class)
    public void validateTin() {
        Client client4 = new Client();
        Client client5 = new Client();
        client4.setTin(1000000000L);
        client5.setTin(1000000000L);

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
        Client client5 = new Client();

        client4.setPhoneNumber(10000000000L);
        client5.setPhoneNumber(10000000000L);

        client4.validatePhoneNumber(10000000000L);
        client4.validatePhoneNumber(999999999999L);
        client4.validatePhoneNumber(99999999999L);
        client4.validatePhoneNumber(999999999999999999L);
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
        client2.setName("Hippopotomonstrosesquippedaliophobiajrtwqweqewqweeqww");
        client2.validateName("Hippopotomonstrosesquippedaliophobiajrtwqweqewqweeqww");
        client2.validateName("Hippopotomonstrosesquippedaliophobi");
        client.setName("");
        client.validateName("");


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
        Assert.assertEquals(10, client.getPassword().length());
        Assert.assertEquals(10, client1.getPassword().length());

    }

    @Test
    public void testSetCcn() {
        Client client = new Client(9999999999999999L, 9999999999L, "10-10-2010", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        Client client1 = new Client(9999999999999999L, 9999999999L, "10-10-2010", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        client.setCcn(1234567890323456L);
        client1.setCcn(987654321098701L);
        long expected1 = 1234567890323456L;
        long expected2 = 987654321098701L;
        assertEquals(expected1, client.getCcn());
        assertEquals(expected2, client1.getCcn());
    }

    @Test
    public void testSetNhsNumber() {
        Client client = new Client(9999999999999999L, 9999999999L, "10-10-2010", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        Client client1 = new Client(9999999999999999L, 9999999999L, "10-10-2010", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        client.setNhsNumber(1234563456L);
        client1.setNhsNumber(987098701L);
        long expected1 = 1234563456L;
        long expected2 = 987098701L;
        assertEquals(expected1, client.getNhsNumber());
        assertEquals(expected2, client1.getNhsNumber());
    }

    @Test
    public void testSetBirthDate() {
        Client client = new Client(9999999999999999L, 9999999999L, "10-10-2010", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        Client client1 = new Client(9999999999999999L, 9999999999L, "10-10-2010", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        client.setBirthDate("20-12-1945");
        client1.setBirthDate("12-01-2000");
        String expected1 = "20-12-1945";
        String expected2 = "12-01-2000";
        assertEquals(expected1, client.getBirthDate());
        assertEquals(expected2, client1.getBirthDate());
    }

    @Test
    public void testSetTin() {
        Client client = new Client(9999999999999999L, 9999999999L, "10-10-2010", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        Client client1 = new Client(9999999999999999L, 9999999999L, "10-10-2010", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        client.setTin(9999919999L);
        client1.setTin(9919999969L);
        long expected1 = 9999919999L;
        long expected2 = 9919999969L;
        assertEquals(expected1, client.getTin());
        assertEquals(expected2, client1.getTin());
    }

    @Test
    public void testSetPhoneNumber() {
        Client client = new Client(9999999999999999L, 9999999999L, "10-10-2010", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        Client client1 = new Client(9999999999999999L, 9999999999L, "10-10-2010", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        client.setPhoneNumber(99919999999L);
        client1.setPhoneNumber(96999996996L);
        long expected1 = 99919999999L;
        long expected2 = 96999996996L;
        assertEquals(expected1, client.getPhoneNumber());
        assertEquals(expected2, client1.getPhoneNumber());
    }

    @Test
    public void testSetSex() {
        Client client = new Client(9999999999999999L, 9999999999L, "10-10-2010", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        Client client1 = new Client(9999999999999999L, 9999999999L, "10-10-2010", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        client.setSex("Male");
        client1.setSex("Female");
        String expected1 = "Male";
        String expected2 = "Female";
        assertEquals(expected1, client.getSex());
        assertEquals(expected2, client1.getSex());
    }

    @Test
    public void testSetEmail() {
        Client client = new Client(9999999999999999L, 9999999999L, "10-10-2010", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        Client client1 = new Client(9999999999999999L, 9999999999L, "10-10-2010", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        client.setEmail("johnpaul@gmail.com");
        client1.setEmail("fabio.berger@btinternet.com");
        String expected1 = "johnpaul@gmail.com";
        String expected2 = "fabio.berger@btinternet.com";
        assertEquals(expected1, client.getEmail());
        assertEquals(expected2, client1.getEmail());
    }

    @Test
    public void testSetName() {
        Client client = new Client(9999999999999999L, 9999999999L, "10-10-2010", "M", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        Client client1 = new Client(9999999999999999L, 9999999999L, "10-10-2010", "ruipina@mail.com", 9999999999L, 99999999999L, "Rui Pina");
        client.setName("Paul Richard");
        client1.setName("John Paul");
        String expected1 = "Paul Richard";
        String expected2 = "John Paul";
        assertEquals(expected1, client.getName());
        assertEquals(expected2, client1.getName());
    }


}