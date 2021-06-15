package app.domain.model;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import app.controller.App;
import app.domain.shared.Utils;
import auth.domain.model.Email;


public class Client {
    private long ccn;
    private long nhsNumber;
    private String birthDate;
    private long tin;
    private long phoneNumber;
    private String sex;
    private String email;
    private String name;
    private String password;
    private String address;
    private final Company cmp = App.getInstance().getCompany();


    LocalDate currentDate = LocalDate.now();

    /**
     * Creates an empty Client.
     */
    public Client() {

    }

    /**
     * Another constructor for class Client
     * It is here that is generated the password.
     *
     * @param ccn         The Citizen card number of the client
     * @param nhsNumber   National Health Service number of the client
     * @param birthDate   The birth date of the client
     * @param sex         The sex of the client
     * @param email       The email of the client
     * @param tin         The tax identification number of the client
     * @param phoneNumber Phone number of the client
     * @param name        The name of the client
     */

    public Client(long ccn, long nhsNumber, String birthDate, String sex, String email, long tin, long phoneNumber, String name) {
        this.ccn = ccn;
        this.tin = tin;
        this.nhsNumber = nhsNumber;
        this.birthDate = birthDate;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.name = name;
        this.address = "No address";
        this.password = generatePassword();
    }

    /**
     * Another constructor for class Client
     * It is here that is generated the password.
     *
     * @param ccn         The client's Citizen card number
     * @param nhsNumber   National Health Service number of the client
     * @param birthDate   The birth date of the client
     * @param email       The email of the client
     * @param tin         The tax identification number of the client
     * @param phoneNumber Phone number of the client
     * @param name        The name of the client
     */

    public Client(long ccn, long nhsNumber, String birthDate, String email, long tin, long phoneNumber, String name) {
        this.ccn = ccn;
        this.nhsNumber = nhsNumber;
        this.birthDate = birthDate;
        this.email = email;
        this.tin = tin;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.password = generatePassword();

    }


    /**
     * @return current ccn
     */
    public long getCcn() {
        return ccn;
    }

    /**
     * @return current NHS number
     */
    public long getNhsNumber() {
        return nhsNumber;
    }

    /**
     * @return current birth date
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * @return current tin
     */
    public long getTin() {
        return tin;
    }

    /**
     * @return current phone number
     */
    public long getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @return current sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @return current email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return current name
     */
    public String getName() {
        return name;
    }

    /**
     * @return current password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return address of the client
     */
    public String getAddress() {
        return address;
    }

    /**
     * Present in the console the output contain all the information about the client
     *
     * @return the phrase with all the information about the client
     */
    @Override
    public String toString() {
        if (this.sex == null || this.sex.equals(".")) {
            return String.format("The client is called %s, his ccn is %d, his NHS Number is %d, his birthdate is %s, his tin is %d, his phonenumber %d and his email is %s",
                    this.name, this.ccn, this.nhsNumber, this.birthDate, this.tin, this.phoneNumber, this.email);
        }
        return String.format("The client is called %s, his ccn is %d, his NHS Number is %d, his birthdate is %s , his sex is %s, his tin is %d, his phonenumber %d and his email is %s",
                this.name, this.ccn, this.nhsNumber, this.birthDate, this.sex, this.tin, this.phoneNumber, this.email);
    }


    /**
     * @return true if <i>this client</i> is compared with itself
     * false if <i>the client</i> is equal to <i>null</i>
     * false if <i>this client</i> is different to <i>the other client</i>
     * false if <i>this client</i> is different to <i>the other client</i>
     * true if <i>this client</i> is equal to <i>other client</i>
     */
    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        Client c = (Client) o;
        return Objects.equals(this.ccn, c.ccn) && Objects.equals(nhsNumber, c.nhsNumber) && Objects.equals(this.sex, c.sex)
                && Objects.equals(this.birthDate, c.birthDate) && Objects.equals(this.email, c.email) &&
                Objects.equals(this.tin, c.tin) && Objects.equals(this.phoneNumber, c.phoneNumber)
                && Objects.equals(this.name, c.name);
    }

    public void setCcn(long ccn) {
        this.ccn = ccn;
    }

    public void setNhsNumber(long nhsNumber) {
        this.nhsNumber = nhsNumber;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setTin(long tin) {
        this.tin = tin;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Validates the ccn of the Client checking if it has 16 digits
     *
     * @param ccn The Citizen card number of the client
     */

    public void validateCcn(long ccn) {
        String ccnInString = Long.toString(ccn);
        if (ccnInString.length() != 16) {
            throw new IllegalArgumentException("The ccn must have 16 numbers.");
        }
        List<Client> clients = cmp.getClientList().getClients();
        for (Client client : clients) {
            if (Long.toString(client.getCcn()).equals(ccnInString)) {
                throw new IllegalArgumentException("This ccn is already associated to a client. Please introduce " +
                        "an unique ccn.");
            }
        }
    }


    /**
     * Validates the nhs Number of the Client checking if it has 10 digits
     *
     * @param nhsNumber The NHS number of the client
     */

    public void validateNhsNumber(long nhsNumber) {
        String nhsNumberInString = Long.toString(nhsNumber);
        if (nhsNumberInString.length() != 10) {
            throw new IllegalArgumentException("The Nhs Number must have 10 numbers.");
        }
        List<Client> clients = cmp.getClientList().getClients();
        for (Client client : clients) {
            if (Long.toString(client.getNhsNumber()).equals(nhsNumberInString)) {
                throw new IllegalArgumentException("This NHS Number is already associated to a client. Please introduce " +
                        "an unique NHS Number.");
            }
        }
    }

    /**
     * Validates the birth date of the Client checking if it is in the DD-MM-YYYY format and if it doesn't represent a
     * date in future or a date that would make the client be more than 150 years.
     *
     * @param birthDate The birth date of the client
     * @return true if <i>the birthDate</i> has <i>no error, accordingly to the criteria</i>
     */

    public void validateBirthDate(String birthDate) {

        int day = 0;
        int month = 0;
        int year = 0;
        if (birthDate.trim().isEmpty()) {
            throw new IllegalArgumentException("The date must be in the format DD/MM/YYYY. Please try again");
        }
            String[] dateParts;
            dateParts = birthDate.trim().split("-");
            int[] checkParts = new int[dateParts.length];
            if (checkParts.length == 0) {
                throw new IllegalArgumentException("The date must be in the format DD-MM-YYYY. Please try again");
            }
            if (dateParts[0].length() != 0 && dateParts[1].length() != 0 && dateParts[2].length() != 0) {
                day = Integer.parseInt(dateParts[0]);
                month = Integer.parseInt(dateParts[1]);
                year = Integer.parseInt(dateParts[2]);
            }

            if (dateParts[0].length() != 2) {
                throw new IllegalArgumentException("You have inserted the day incorrectly. Please insert the day as DD!");
            }

            if (dateParts[1].length() != 2) {
                throw new IllegalArgumentException("You have inserted the month incorrectly. Please insert the month as MM!");

            }

            if (dateParts[2].length() != 4) {
                throw new IllegalArgumentException("You have inserted the year incorrectly. Please insert the day as YYYY!");

            }

            if (!Utils.dayValidation(year, month, day)) {
                throw new IllegalArgumentException("You have introduced an invalid day.Please try again");

            }
            if (month < 0 || month > 12) {
                throw new IllegalArgumentException("The month be from 01 to 12. Please try again");

            }
            if (year < (currentDate.getYear() - 150) || year > currentDate.getYear()) {
                throw new IllegalArgumentException("The client can't be born in that year. Please try again with a different birth date");

            }
            if ((year == (currentDate.getYear() - 150) && month <= currentDate.getMonthValue() && day <= currentDate.getDayOfMonth())) {
                throw new IllegalArgumentException("The client can not born be older than 150 years old. Please try again with a different birth date");
            }

            if ((year == currentDate.getYear() && month >= currentDate.getMonthValue() && day > currentDate.getDayOfMonth())) {
                throw new IllegalArgumentException("The client can not be born in the future! Please try again with a different birth date");
            }
    }

    /**
     * Validates the tin of the Client checking if it has 10 digits
     *
     * @param tin The tax identification number of the client
     * @return true if <i>the nhsNumber</i> has <i>10 digits</i>
     */

    public void validateTin(long tin) {
        String tinInString = Long.toString(tin);
        if (tinInString.length() != 10) {
            throw new IllegalArgumentException("The Tax Identification Number must have 10 numbers.");
        }

        List<Client> clients = cmp.getClientList().getClients();
        for (Client client : clients) {
            if (Long.toString(client.getTin()).equals(tinInString)) {
                throw new IllegalArgumentException("This Tax Identification Number is already associated to a client. " +
                        "Please introduce an unique NHS Number.");
            }
        }
    }

    /**
     * Validates the sex of the Client checking if it is one of the possible options
     *
     * @param sex The sex of the client
     */

    public void validateSex(String sex) {

        if(!(sex.trim().isEmpty() || sex.equals("") || sex.equalsIgnoreCase("M") || sex.equalsIgnoreCase("F") || sex.equalsIgnoreCase("male") || sex.equalsIgnoreCase("female"))){
            throw new IllegalArgumentException("Please introduce a valid sex (M/F or press enter in case it is optional). ");
        }

    }

    /**
     * Validates the phone Number of the Client checking if it has 11 digits
     *
     * @param phoneNumber The phone number of the client
     */

    public void validatePhoneNumber(long phoneNumber) {
        String phoneNumberInString = Long.toString(phoneNumber);
        if (phoneNumberInString.length() != 11) {
            throw new IllegalArgumentException("The Phone Number must have 11 numbers.");
        }

        List<Client> clients = cmp.getClientList().getClients();
        for (Client client : clients) {
            if (Long.toString(client.getPhoneNumber()).equals(phoneNumberInString)) {
                throw new IllegalArgumentException("This Phone Number is already associated to a client. " +
                        "Please introduce an unique NHS Number.");
            }
        }
    }

    /**
     * Validates the email of the Client using the class Email.
     *
     * @param email The email of the client
     */

    public void validateEmail(String email) {

        new Email(email);

        List<Client> clients = cmp.getClientList().getClients();
        for (Client client : clients) {
            if (client.getEmail().equals(email) && client.getEmail()!=null) {
                throw new IllegalArgumentException("This Email already associated to a client. Please introduce an unique email.");
            }
        }

    }

    /**
     * Validates the name of the Client.
     *
     * @param name The name of the client
     */
    public void  validateName(String name) {

        if (name.trim().length() > 35) {
            throw new IllegalArgumentException("The name can have at max 35 characters. Please try again");

        } else if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("The name can't be empty. Please try again with a max of 35 characters.");

        } else if (!Utils.isAlpha(name)) {
            throw new IllegalArgumentException("The name can only contain letters. Please try again with a max of 35 characters.");

        }

    }


    /**
     * This method generates a random password containing 10 alphanumeric characters
     */

    public String generatePassword() {
        password = "";
        List<Character> list = Utils.randomCharacter(10);
        Collections.shuffle(list);
        StringBuilder stringBuilder = new StringBuilder();
        for (Character l : list) {
            password = String.valueOf(stringBuilder.append(l));
        }
        return password;
    }

    public void setPassword(String password) {
    	this.password = password;
    }
}
