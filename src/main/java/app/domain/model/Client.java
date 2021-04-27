package app.domain.model;

import app.controller.AuthController;

public class Client {
    private int ccn;
    private int nhsNumber;
    private int birthDate;
    private String sex;
    private int tif;
    private int phoneNumber;
    private String name;


    public Client(int ccn, int nhsNumber, int birthDate, String sex, int tif, int phoneNumber, String name) {
        this.ccn = ccn;
        this.nhsNumber = nhsNumber;
        this.birthDate = birthDate;
        this.sex = sex;
        this.tif = tif;
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

    public int getTif() {
        return tif;
    }

    public void setTif(int tif) {
        this.tif = tif;
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



}
