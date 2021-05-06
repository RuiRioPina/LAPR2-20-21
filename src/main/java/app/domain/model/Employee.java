package app.domain.model;

import auth.domain.model.Email;
import auth.domain.model.Password;
import auth.domain.model.User;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Employee {

    private String userName;
    private String password;
    private String email;
    private String name;
    private String ID;
    private String adress;
    private String SOC;
    private long phoneNumber;
    private Role role;
    private String specialistDoctorIndexNumber;





    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    public Employee(String name, String adress, String SOC, long phoneNumber, String email, String userName, int nEmployees, Role role, String specialistDoctorIndexNumber){
        this.name=name;
        this.adress=adress;
        this.SOC = SOC;
        this.phoneNumber=phoneNumber;
        this.userName=userName;
        this.email=email;
        this.password=generateEmployeePassword();
        this.ID=generateID(nEmployees);
        this.role= role;
        this.specialistDoctorIndexNumber=specialistDoctorIndexNumber;
    }
    public String generateEmployeePassword() {
        String password = "";
        List<Character> list = app.domain.shared.Utils.randomCharacter(7,2,3);
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

    public String getSOC() {
        return SOC;
    }

    public void setSOC(String SOC) {
        this.SOC = SOC;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) { this.phoneNumber = phoneNumber; }

    public void validateSOC(){
        if (SOC.length()!=4 && SOC.matches("[0-9]+")){
            throw new IllegalArgumentException("SOC must have 4 digits and only numbers");
        }

    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getSpecialistDoctorIndexNumber() {
        return specialistDoctorIndexNumber;
    }

    public void setSpecialistDoctorIndexNumber(String specialistDoctorIndexNumber) {
        this.specialistDoctorIndexNumber = specialistDoctorIndexNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    return Objects.equals(name,e.name) && Objects.equals(ID,e.ID) && Objects.equals(adress,e.adress) && Objects.equals(SOC,e.SOC) && Objects.equals(phoneNumber,e.phoneNumber)&& Objects.equals(userName,e.userName)&& Objects.equals(role,e.role)&&Objects.equals(email,e.email)&&Objects.equals(password,e.password);

}



@Override
    public String toString(){
        if (specialistDoctorIndexNumber==null){
            return String.format("This employee is named "+this.name+". Their ID is "+ this.ID +". Their adress is "+ this.adress+". Their phone number is "+ this.phoneNumber+". \nTheir SOC is "+this.SOC+". Their username is "+ this.userName+". Their password is "+this.password+". Their role is "+ role.getRoleID()+".");
        }else
        return String.format("This employee is named "+this.name+". Their ID is "+ this.ID +". Their adress is "+ this.adress+". Their phone number is "+ this.phoneNumber+". \nTheir SOC is "+this.SOC+". Their username is "+ this.userName+". Their password is "+this.password+". Their role is "+ role.getRoleID()+". Their doctor Index number is "+this.specialistDoctorIndexNumber);
}
public  boolean validateEmployee(){
        if ((name.matches(".*\\d.*"))){
            return false;
    }
    if (SOC.length()!=4 && !SOC.matches("[0-9]+")){
        return false;
    }
    return true;
}


}
