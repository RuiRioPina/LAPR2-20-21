package app.domain.model;

import app.ui.console.utils.Utils;
import auth.AuthFacade;

import java.util.Collections;
import java.util.List;

public class Client {
    private int ccn;
    private int nhsNumber;
    private int birthDate;
    private int tin;
    private int phoneNumber;
    private String sex;
    private String email;
    private String name;
    private String password;


    public Client(int ccn, int nhsNumber, int birthDate, String sex, String email, int tin, int phoneNumber, String name) {
        this.ccn = ccn;
        this.nhsNumber = nhsNumber;
        this.birthDate = birthDate;
        this.sex = sex;
        this.email = email;
        this.tin = tin;
        this.phoneNumber = phoneNumber;
        this.name = name;
    }

    public int getCcn() {
        return ccn;
    }

    public void setCcn(int ccn) {
        this.ccn = ccn;
    }

    public int getNhsNumber() {
        return nhsNumber;
    }

    public void setNhsNumber(int nhsNumber) {
        this.nhsNumber = nhsNumber;
    }

    public int getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(int birthDate) {
        this.birthDate = birthDate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getTin() {
        return tin;
    }

    public void setTin(int tin) {
        this.tin = tin;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String generatePassword() {
        password = "";
        List<Character> list = Utils.randomCharacter();
        Collections.shuffle(list);
        StringBuilder stringBuilder = new StringBuilder();
        for (Character l : list) {
            password = String.valueOf(stringBuilder.append(l));
        }
        return password;
    }

    public void addClientRole() {
        AuthFacade authFacade = new AuthFacade();
        authFacade.addUserRole("Client", "This is the Client that has access to his results");
        authFacade.addUserWithRole(name, email, password, "Client");
    }


}
