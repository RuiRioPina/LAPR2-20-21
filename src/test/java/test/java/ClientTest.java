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
            Client client1 = new Client(9999999999999999L, 99999999999L, "10-10-2010", "M", "ruiriopina02@mail.com", 99999999999L, 999999999999L, "Rui Pina");
            Client client2 = new Client(9999999999999999L, 99999999999L, "10-10-2010", "M", "ruiriopina02@mail.com", 99999999999L, 999999999999L, "Rui Pina");
            Client client3 = new Client(1111111111111111L, 99999999999L, "10-10-2010", "M", "ruiriopina02@mail.com", 99999999999L, 999999999999L, "Rui Pina");
            Object obj = new Object();
            Assert.assertNotEquals(client1, obj);
            Assert.assertNotEquals(client1, client3);
            Assert.assertNotEquals(client1, null);
            Assert.assertEquals(client1, client2);
            Assert.assertEquals(client1, client1);

        }

        @Test
        public void validateCcn() {

            ExpectedException exceptionRule = ExpectedException.none();
            Client client1 = new Client(9999999999999999L, 99999999999L, "10-10-2010", "M", "ruiriopina02@mail.com", 99999999999L, 999999999999L, "Rui Pina");
            Client client = new Client(999999999999999L, 99999999999L, "10-10-2010", "M", "ruiriopina02@mail.com", 99999999999L, 999999999999L, "Rui Pina");
            try {
                client1.validateCcn(client1.getCcn());
                client.validateCcn(client.getCcn());
            } catch (IllegalArgumentException e) {
                exceptionRule.expect(IllegalArgumentException.class);
            }


        }

        @Test
        public void validateNhsNumber() {
            ExpectedException exceptionRule = ExpectedException.none();
            Client client1 = new Client(9999999999999999L, 1000000000L, "10-10-2010", "M", "ruiriopina02@mail.com", 99999999999L, 999999999999L, "Rui Pina");
            Client client = new Client(9999999999999999L, 9999999L, "10-10-2010", "M", "ruiriopina02@mail.com", 99999999999L, 999999999999L, "Rui Pina");
            try {
                client1.validateNhsNumber(client1.getNhsNumber());
                client.validateNhsNumber(client.getNhsNumber());
            } catch (IllegalArgumentException e) {
                exceptionRule.expect(IllegalArgumentException.class);
            }
        }

        @Test
        public void validateBirthDate() {
            ExpectedException exceptionRule = ExpectedException.none();

            Client client = new Client(9999999999999999L, 99999999999L, "10-10-2021", "M", "ruiriopina02@mail.com", 99999999999L, 999999999999L, "Rui Pina");
            Client client2 = new Client(999999999999999L, 99999999999L, "10-03-1871", "M", "ruiriopina02@mail.com", 99999999999L, 999999999999L, "Rui Pina");
            Client client3 = new Client(999999999999999L, 99999999999L, "10-0-1890", "M", "ruiriopina02@mail.com", 99999999999L, 999999999999L, "Rui Pina");
            Client client4 = new Client(999999999999999L, 99999999999L, "10-03-187", "M", "ruiriopina02@mail.com", 99999999999L, 999999999999L, "Rui Pina");
            Client client5 = new Client(999999999999999L, 99999999999L, "1-03-1990", "M", "ruiriopina02@mail.com", 99999999999L, 999999999999L, "Rui Pina");
            Client client6 = new Client(999999999999999L, 99999999999L, "38-12-1880", "M", "ruiriopina02@mail.com", 99999999999L, 999999999999L, "Rui Pina");
            Client client7 = new Client(999999999999999L, 99999999999L, "10-13-1880", "M", "ruiriopina02@mail.com", 99999999999L, 999999999999L, "Rui Pina");
            Client client8 = new Client(999999999999999L, 99999999999L, "", "M", "ruiriopina02@mail.com", 99999999999L, 999999999999L, "Rui Pina");
            Client client9 = new Client(9999999999999999L, 99999999999L, "10-10-2021", "M", "ruiriopina02@mail.com", 99999999999L, 999999999999L, "Rui Pina");
            Client client10 = new Client(9999999999999999L, 99999999999L, "10/10/1921", "M", "ruiriopina02@mail.com", 99999999999L, 999999999999L, "Rui Pina");
            try {
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

            } catch (NumberFormatException e) {
                exceptionRule.expect(NumberFormatException.class);
            } catch (IllegalArgumentException e) {
                exceptionRule.expect(IllegalArgumentException.class);
            }
        }


        @Test
        public void validateTin() {
            ExpectedException exceptionRule = ExpectedException.none();
            Client client1 = new Client(9999999999999999L, 99999999999L, "10-10-2010", "M", "ruiriopina02@mail.com", 99999999999L, 999999999999L, "Rui Pina");
            Client client2 = new Client(9999999999999999L, 99999999999L, "10-10-2010", "M", "ruiriopina02@mail.com", 999999999992L, 999999999999L, "Rui Pina");
            try {
                client1.validateTin(client1.getTin());
                client2.validateTin(client2.getTin());
            } catch (IllegalArgumentException e) {
                exceptionRule.expect(IllegalArgumentException.class);
            }
        }

        @Test
        public void validateSex() {
            ExpectedException exceptionRule = ExpectedException.none();

            Client client = new Client(9999999999999999L, 99999999999L, "10-10-2010", "F", "ruiriopina02@mail.com", 99999999999L, 999999999999L, "Rui Pina");
            Client client1 = new Client(9999999999999999L, 99999999999L, "10-10-2010", "True", "ruiriopina02@mail.com", 99999999999L, 999999999999L, "Rui Pina");
            try {
                client.validateSex(client.getSex());
                client1.validateSex(client1.getSex());
            } catch (IllegalArgumentException e) {
                exceptionRule.expect(IllegalArgumentException.class);
            }
        }

        @Test
        public void validatePhoneNumber() {
            ExpectedException exceptionRule = ExpectedException.none();

            Client client = new Client(9999999999999999L, 99999999999L, "10-10-2010", "M", "ruiriopina02@mail.com", 99999999999L, 99999999999L, "Rui Pina");
            try {
                client.validatePhoneNumber(client.getPhoneNumber());
            } catch (IllegalArgumentException e) {
                exceptionRule.expect(IllegalArgumentException.class);
            }
        }

        @Test
        public void validateEmail() {
            ExpectedException exceptionRule = ExpectedException.none();

            Client client = new Client(9999999999999999L, 99999999999L, "10-10-2010", "M", "ruiriopina02mail.com", 99999999999L, 99999999999L, "Rui Pina");
            Client client1 = new Client(9999999999999999L, 99999999999L, "10-10-2010", "M", "ruiriopina02mail.com", 99999999999L, 99999999999L, "Rui Pina");
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

            Client client = new Client(9999999999999999L, 99999999999L, "10-10-2010", "M", "ruiriopina02mail.com", 99999999999L, 99999999999L, "Rui Pina");
            Client client1 = new Client(9999999999999999L, 99999999999L, "10-10-2010", "M", "ruiriopina02mail.com", 99999999999L, 99999999999L, "Rui Pina312312");
            Client client2 = new Client(9999999999999999L, 99999999999L, "10-10-2010", "M", "ruiriopina02mail.com", 99999999999L, 99999999999L, "       ");
            try {
                client.validateName(client.getName());
                client1.validateName(client1.getName());
                client2.validateName(client2.getName());
            } catch (IllegalArgumentException e) {
                exceptionRule.expect(IllegalArgumentException.class);
            }
        }

        @Test
        public void getCcn() {
        }

        @Test
        public void getNhsNumber() {
        }

        @Test
        public void getBirthDate() {
        }

        @Test
        public void getTin() {
        }

        @Test
        public void getPhoneNumber() {
        }

        @Test
        public void getSex() {
        }

        @Test
        public void getEmail() {
        }

        @Test
        public void getName() {
        }

        @Test
        public void getCurrentDate() {
        }

        @Test
        public void getPassword() {
        }
    }