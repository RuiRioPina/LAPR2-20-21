package app.domain.model;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import app.domain.shared.Utils;

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

    public Client() {

    }

    public Client(long ccn, long nhsNumber, String birthDate, String sex, String email, long tin, long phoneNumber, String name) {
        this.ccn = ccn;
        this.tin = tin;
        this.nhsNumber = nhsNumber;
        this.birthDate = birthDate;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.name = name;
    }

    public Client(long ccn, long nhsNumber, String birthDate, String email, long tin, long phoneNumber, String name) {
        this.ccn = ccn;
        this.nhsNumber = nhsNumber;
        this.birthDate = birthDate;
        this.email = email;
        this.tin = tin;
        this.phoneNumber = phoneNumber;
        this.name = name;
    }

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


    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
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


    public String toString() {
        return String.format("The client is called %s, his ccn is %d, his NHS Number is %d, his birthdate is %s , his sex is %s, his tin is %d, and his email is %s",
                this.name, this.ccn, this.nhsNumber, this.birthDate, this.sex, this.tin, this.email);
    }

    public void validateCcn(long ccn) {
        if (ccn < 1000000000000000L || ccn > 9999999999999999L) {
            throw new IllegalArgumentException("The number of the ccn has to have 16 digits");
        }

    }

    public void validateNhsNumber(long nhsNumber) {
        if (nhsNumber < 1000000000L || nhsNumber > 9999999999L) {
            throw new IllegalArgumentException("The number of the NHS number has to have 10 digits");
        }
    }

    public void validateBirthDate(String birthDate) {
        int day;
        int month;
        int year;
        String[] dateParts = birthDate.split("-");
        int[] checkParts = new int[dateParts.length];
        if (checkParts.length == 0) {
            throw new NumberFormatException("The date must be in the format DD/MM/YY. Please try again");
        }
        day = Integer.parseInt(dateParts[0]);
        month = Integer.parseInt(dateParts[1]);
        year = Integer.parseInt(dateParts[1]);

        if (!Utils.dateValidate(year, month, day)) {
            throw new IllegalArgumentException("Something went wrong with it please try again.");
        }
//        if(month<0 || month>12){
//            throw new IllegalArgumentException("The month can only be in the format MM. Please try again");
//        }
//        if(year<0 || year>99){
//            throw new IllegalArgumentException("The year can only be in the format YY. Please try again");
//        }
    }

    public void validateTin(long tin) {
        if (tin < 1000000000L || tin > 9999999999L) {
            throw new IllegalArgumentException("The number of the TIN has to have 10 digits");
        }
    }

    public void validateSex(String sex) {
        if (sex.equals(".")) {

        } else if (!sex.equalsIgnoreCase("M") && !sex.equalsIgnoreCase("F") && !sex.equalsIgnoreCase("male") && !sex.equalsIgnoreCase("female")) {
            throw new IllegalArgumentException("You have introduced a wrong sex. Please try again!");
        }


    }

    public void validatePhoneNumber(long phoneNumber) {
        if (phoneNumber < 10000000000L || phoneNumber > 99999999999L) {
            throw new IllegalArgumentException("The number of the Phone number has to have 11 digits. Please try again");
        }
    }

    public void validateName(String name) {
        if (name.trim().length() > 35) {
            throw new IllegalArgumentException("The name can have at max 35 characters. Please try again");
        } else if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("The name can't be empty. Please try again with a max of 35 characters.");
        } else if (!Utils.isAlpha(name)) {
            throw new IllegalArgumentException("The name can only contain letters. Please try again with a max of 35 characters.");
        }
    }


}
