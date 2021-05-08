package app.domain.model;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import app.domain.shared.Utils;
import auth.domain.model.Email;
import auth.domain.store.UserStore;

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

    UserStore userStore = new UserStore();

    LocalDate currentDate = LocalDate.now();

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
        this.password = generatePassword();
        try {
            userStore.add(userStore.create(this.name, this.email, this.password));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public Client(long ccn, long nhsNumber, String birthDate, String email, long tin, long phoneNumber, String name) {
        this.ccn = ccn;
        this.nhsNumber = nhsNumber;
        this.birthDate = birthDate;
        this.email = email;
        this.tin = tin;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.password = generatePassword();
        try {

            userStore.add(userStore.create(this.name, this.email, this.password));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
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

    @Override
    public String toString() {
        if (this.sex == null || this.sex.equals(".")) {
            return String.format("The client is called %s, his ccn is %d, his NHS Number is %d, his birthdate is %s, his tin is %d, and his email is %s",
                    this.name, this.ccn, this.nhsNumber, this.birthDate, this.tin, this.email);
        }
        return String.format("The client is called %s, his ccn is %d, his NHS Number is %d, his birthdate is %s , his sex is %s, his tin is %d, and his email is %s",
                this.name, this.ccn, this.nhsNumber, this.birthDate, this.sex, this.tin, this.email);
    }

    public boolean validateCcn(long ccn) {
        long length = (int) (Math.log10(ccn) + 1);
        if (length == 16) {
            throw new IllegalArgumentException("The number of the ccn has to have 16 digits");
        }
        return true;
    }

    public void validateNhsNumber(long nhsNumber) {
        long length = (int) (Math.log10(nhsNumber) + 1);
        if (length == 10) {
            throw new IllegalArgumentException("The number of the NHS number has to have 10 digits");
        }
    }

    public void validateBirthDate(String birthDate) {
        int day;
        int month;
        int year;
        String[] dateParts = birthDate.trim().split("-");
        int[] checkParts = new int[dateParts.length];
        if (checkParts.length == 0) {
            throw new NumberFormatException("The date must be in the format DD/MM/YYYY. Please try again");
        }
        day = Integer.parseInt(dateParts[0]);
        month = Integer.parseInt(dateParts[1]);
        year = Integer.parseInt(dateParts[2]);

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
        if (year < 0 || year > currentDate.getYear()) {
            throw new IllegalArgumentException("The client can not be born after " + currentDate.getYear() + ". Please try again with a different birth date");
        }
        if ((year <= (currentDate.getYear() - 150) && month <= currentDate.getMonthValue() && day <= currentDate.getDayOfMonth())) {
            throw new IllegalArgumentException("The client can not be older than 150 years old. Please try again with a different birth date");
        } else if ((year == (currentDate.getYear()) && month >= currentDate.getMonthValue() && day > currentDate.getDayOfMonth())) {
            throw new IllegalArgumentException("The client can not be born in the future! Please try again with a different birth date");
        }
    }

    public void validateTin(long tin) {
        long length = (int) (Math.log10(tin) + 1);
        if (length == 10) {
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
        long length = (int) (Math.log10(ccn) + 1);
        if (length == 11) {
            throw new IllegalArgumentException("The number of the Phone number has to have 11 digits. Please try again");
        }
    }

    public void validateEmail(String email) {

        new Email(email);

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

    public long getCcn() {
        return ccn;
    }

    public long getNhsNumber() {
        return nhsNumber;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public long getTin() {
        return tin;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public String getSex() {
        return sex;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
