package app.domain.model;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import app.domain.shared.Constants;
import app.domain.shared.Utils;
import auth.AuthFacade;
import auth.domain.model.Email;
import auth.domain.model.Password;
import auth.domain.model.User;
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

    AuthFacade authFacade = new AuthFacade();

    User user;

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
        return length == 16;
    }

    public boolean validateNhsNumber(long nhsNumber) {
        long length = (int) (Math.log10(nhsNumber) + 1);
        return length == 10;
    }

    public boolean validateBirthDate(String birthDate) {
        int i = 0;
        int day = 0;
        int month = 0;
        int year = 0;
        if (birthDate.equals("")) {
            return false;
        }
        try {
            String[] dateParts;
            dateParts = birthDate.trim().split("-");
            int[] checkParts = new int[dateParts.length];
            if (checkParts.length == 0) {
                System.out.println("The date must be in the format DD/MM/YYYY. Please try again");
                i = 1;
            }
            if (dateParts[0].length() != 0 || dateParts[1].length() != 0 || dateParts[2].length() != 0) {
                day = Integer.parseInt(dateParts[0]);
                month = Integer.parseInt(dateParts[1]);
                year = Integer.parseInt(dateParts[2]);
            }

            if (dateParts[0].length() != 2) {
                System.out.println("You have inserted the day incorrectly. Please insert the day as DD!");
                i = 1;
            }

            if (dateParts[1].length() != 2) {
                System.out.println("You have inserted the month incorrectly. Please insert the month as MM!");
                i = 1;
            }

            if (dateParts[2].length() != 4) {
                System.out.println("You have inserted the year incorrectly. Please insert the day as YYYY!");
                i = 1;
            }

            if (!Utils.dayValidation(year, month, day)) {
                System.out.println("You have introduced an invalid day.Please try again");
                i = 1;
            }
            if (month < 0 || month > 12) {
                System.out.println("The month be from 01 to 12. Please try again");
                i = 1;
            }
            if (year < (currentDate.getYear() - 150) || year > currentDate.getYear()) {
                System.out.println("The client can't be born in that year. Please try again with a different birth date");
                i = 1;
            }
            if ((year == (currentDate.getYear() - 150) && month < currentDate.getMonthValue())) {
                System.out.println("The client can not born be older than 150 years old. Please try again with a different birth date");
                i = 1;
            } else if ((month == currentDate.getMonthValue() && day <= currentDate.getDayOfMonth())) {
                System.out.println("The client can not born be older than 150 years old. Please try again with a different birth date");
                i = 1;
            }

            if ((year == currentDate.getYear() && month >= currentDate.getMonthValue() && day > currentDate.getDayOfMonth())) {
                System.out.println("The client can not be born in the future! Please try again with a different birth date");
                i = 1;
            }
        } catch (NumberFormatException e) {
            System.out.println("Só pode introduzir a data entre " + "\"-\"." + "Por favor tente novamente. ");
            i = 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Houve um erro! Tente novamente com outros valores");
            i = 1;
        }
        return i != 1;
    }

    public boolean validateTin(long tin) {
        long length = (int) (Math.log10(tin) + 1);
        return length == 10;
    }

    public boolean validateSex(String sex) {

        return sex.equals(".") || sex.equalsIgnoreCase("M") || sex.equalsIgnoreCase("F") || sex.equalsIgnoreCase("male") || sex.equalsIgnoreCase("female");

    }

    public boolean validatePhoneNumber(long phoneNumber) {
        long length = (int) (Math.log10(phoneNumber) + 1);
        return length == 11;
    }

    public void validateEmail(String email) {

        new Email(email);

    }

    public boolean validateName(String name) {
        int i = 0;
        if (name.trim().length() > 35) {
            System.out.println("The name can have at max 35 characters. Please try again");
            i = 1;
        } else if (name.trim().isEmpty()) {
            System.out.println("The name can't be empty. Please try again with a max of 35 characters.");
            i = 1;
        } else if (!Utils.isAlpha(name)) {
            System.out.println("The name can only contain letters. Please try again with a max of 35 characters.");
            i = 1;
        }
        return i != 1;
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
