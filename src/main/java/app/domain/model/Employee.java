package app.domain.model;

import auth.domain.model.Email;
import auth.domain.model.Password;
import auth.domain.model.User;
import auth.domain.model.UserRole;

public class Employee {

    private User employee;
    private String name;
    private int tin;
    private static final int TINSIZE=9;
    private String adress;
    private int dayOfBirth;
    private int monthOfBirth;
    private int yearOfBirth;
    private int sex;
    private int phoneNumber;
    public Employee(String name, int tin, String adress, int dayOfBirth,int monthOfBirth, int yearOfBirth, int sex, int phoneNumber,Email email,Password password,String userName,String roleID,String roleDescription){
        this.name=name;
        this.adress=adress;
        this.tin=tin;
        this.dayOfBirth=dayOfBirth;
        this.monthOfBirth=monthOfBirth;
        this.yearOfBirth=yearOfBirth;
        this.sex=sex;
        this.phoneNumber=phoneNumber;
        employee= new User(email,password,userName);
        employee.addRole(new UserRole(roleID,roleDescription));

    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public int getTim() {
        return tin;
    }

    public void setTim(int tim) {
        this.tin = tim;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getMonthOfBirth() {
        return monthOfBirth;
    }

    public void setMonthOfBirth(int monthOfBirth) {
        this.monthOfBirth = monthOfBirth;
    }

    public int getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(int dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getEmployee() {
        return employee;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String sexToString(){
        if (sex==0){
            return "other";
        }else if (sex==1){
            return "male";
        }else if (sex==2){
            return "female";
        }else return "invalidSex";

    }
    private int digits(int num){
        int digits=0;
        do {
            num=num/10;
            digits++;
        }while (num!=0);
        return digits;
    }
    private boolean isLeapYear(int year){
        if (year % 4 != 0) {
            return false;
        } else if (year % 400 == 0) {
            return true;
        } else if (year % 100 == 0) {
            return false;
        } else {
            return true;
        }
    }
    public boolean validateEmployee(){
        if (digits(tin)!=TINSIZE){
            return false;
        }
        if (yearOfBirth<=0){
            return false;
        }
        if (monthOfBirth<=0 || monthOfBirth >12){
            return false;
        }
        if (dayOfBirth<=0){
            return false;
        }
        switch (monthOfBirth){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                if (dayOfBirth>31){
                    return false;
                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                if (dayOfBirth>30){
                    return false;
                }
                break;
            case 2:
                if (dayOfBirth>29){
                    return false;
                }else if (dayOfBirth==29 && !isLeapYear(yearOfBirth)){
                    return false;
                }
                break;
        }
        if (sex<0||sex>2){
            return false;
        }


return true;

    }



}
