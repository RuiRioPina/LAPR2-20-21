package app.domain.model;

import app.ui.console.utils.Utils;
import auth.domain.model.Email;
import auth.domain.model.Password;
import auth.domain.model.User;
import auth.domain.model.UserRole;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Employee {

    private User employee;
    private String name;
    private String ID;
    private String adress;
    private long SOC;
    private long phoneNumber;
    private Role role;
    private long specialistDoctorIndexNumber;


    public void setSOC(long SOC) {
        this.SOC = SOC;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    public Employee(String name, String adress, long SOC, long phoneNumber, String email, String userName, int nEmployees,Role role){

        this.name=name;
        this.adress=adress;
        this.SOC = SOC;
        this.phoneNumber=phoneNumber;
        employee= new User(new Email(email),new Password(generateEmployeePassword()),userName);
        this.ID=generateID(nEmployees);
        this.role= role;

    }
    public String generateEmployeePassword() {
        String password = "";
        List<Character> list = app.domain.shared.Utils.randomCharacter(7);
        Collections.shuffle(list);
        StringBuilder stringBuilder = new StringBuilder();
        for (Character l : list) {
            password = String.valueOf(stringBuilder.append(l));
        }
        return password;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public long getSOC() {
        return SOC;
    }

    public void setSOC(int SOC) {
        this.SOC = SOC;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) { this.phoneNumber = phoneNumber; }





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


    private int digits(int num){
        int digits=0;
        do {
            num=num/10;
            digits++;
        }while (num!=0);
        return digits;
    }

    public boolean validateEmployee() {
return true;
    }


 private String generateID(int nEmployees){
        String id="";
        String name = this.name;
        name.trim();
        String[] nameWords = name.split(" ");
     for (String nameWord : nameWords) {
         id = id + nameWord.charAt(0);
     }
        return id+ nEmployees;
}
@Override
public boolean equals(Object o){
    if (this == o) {
        return true;
    }
    if (o==null){
        return false;
    }
    if (getClass() !=o.getClass()){
        return false;
    }
    Employee e = (Employee) o;
    return Objects.equals(name,e.name) && Objects.equals(ID,e.ID) && Objects.equals(adress,e.adress) && Objects.equals(SOC,e.SOC) && Objects.equals(phoneNumber,e.phoneNumber)&& Objects.equals(employee,e.employee)&& Objects.equals(role,e.role);

}



@Override
    public String toString(){
        return String.format("This employee is named "+ name+". Their ID is "+ ID +". Their adress is "+ adress+". Their phone number is "+ phoneNumber+".");
}


}
