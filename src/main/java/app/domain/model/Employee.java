package app.domain.model;

import auth.domain.model.Email;
import auth.domain.model.Password;
import auth.domain.model.User;
import auth.domain.model.UserRole;

import java.util.Date;

public class Employee {

    private User employee;
    private String name;
    private int tim;
    private String adress;
    private int dayOfBirth;
    private int monthOfBirth;
    private int yearOfBirth;
    private int sex;
    private int phoneNumber;
    public Employee(String name, int tim, String adress, int dayOfBirth,int monthOfBirth, int yearOfBirth, int sex, int phoneNumber,Email email,Password password,String userName,String roleID,String roleDescription){
        name=this.name;
        adress=this.adress;
        dayOfBirth=this.dayOfBirth;
        monthOfBirth=this.monthOfBirth;
        yearOfBirth=this.yearOfBirth;
        sex=this.sex;
        phoneNumber=this.phoneNumber;
        employee= new User(email,password,userName);
        employee.addRole(new UserRole(roleID,roleDescription));

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



}
