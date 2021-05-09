package app.domain.model;

import app.domain.shared.Utils;



import java.util.Objects;
import java.util.Random;

public class Employee {
    /**
     * Object oriented Class to the describe an employee in a company context.
     */
    private static final int PASSWORD_DIGIT_AMOUNT=2;
    private static final int PASSWORD_UPPERCASE_AMOUNT=3;
    private static final int PASSWORD_LOWERCASE_AMOUNT=2;
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


    /**
     * Returns the role of the employee.
     * @return role of the employee
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets the employee's role
     * @param- new role for the employee
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Constructor for the employee.
     * @param name- name of the employee.
     * @param adress- adress of the employee.
     * @param SOC- Standard Occupational Classificational Code.
     * @param phoneNumber- phone number of the employee.
     * @param email- email of the employee.
     * @param userName- userName of the employee.
     * @param nEmployees- the number of employees that exist(used to generate the id).
     * @param role- Role of the employee in the system
     * @param specialistDoctorIndexNumber- if the employee is a specialist doctor it contains their doctor index number if not, if not the value is null
     */
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

    /**
     * Generates the password for the user created for the employee.
     * @return a String with a certain amount of uppercase letters,lowercase letters and digits.
     */
    public String generateEmployeePassword() {
        StringBuilder s = new StringBuilder();
        for (int i=0;i<PASSWORD_DIGIT_AMOUNT;i++){
            Character c=randomCharacter("0123456789");
           s.append(c);
        }
        for (int i=0;i<PASSWORD_UPPERCASE_AMOUNT;i++){
            Character c=randomCharacter("ABCDEFGHIJKLMNOPQRSTUVXYZ");
            s.append(c);
        }
        for (int i=0;i<PASSWORD_LOWERCASE_AMOUNT;i++){
            Character c=randomCharacter("abcdefghijklmnopqrstuvxyz");
            s.append(c);
        }
        String str= s.toString();
        return str;
    }

    /**
     * Getter for the employee's id
     * @return String with the employee's ID
     */
    public String getID() {
        return ID;
    }

    /**
     * Sets  the employee's ID
     * @param ID- String with the new ID of the employee
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * Getter for the StandardOccupationalClassification Code
     * @return String with the SOC Code.
     */
    public String getSOC() {
        return SOC;
    }

    /**
     * Sets the employee's StandardOccupationalClassification Code
     * @param SOC- String with the new SOC Code.
     */
    public void setSOC(String SOC) {
        this.SOC = SOC;
    }

    /**
     * Getter for the employee's phone number
     * @return long with the phone number of the employee
     */
    public long getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the employee's phone number.
     * @param phoneNumber-long wit the new phoneNumber.
     */

    public void setPhoneNumber(long phoneNumber) { this.phoneNumber = phoneNumber; }

    /**
     * Getter for the employee's name
     * @return String with the employee's name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the employee's name.
     * @param name- String with the new name for the employee.
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Getter for the employee's adress.
     * @return String with the adress for the employee.
     */
    public String getAdress() {
        return adress;
    }

    /**
     * Setter for the employee's adress.
     * @param adress-String with the new adress.
     */
    public void setAdress(String adress) {
        this.adress = adress;
    }

    /**
     * Generates the ID of the employee using the number of employees in the company and the employee's name.
     * @param nEmployees- total number of the employees in the company.
     * @return String with the employee's id.
     */
 private String generateID(int nEmployees){
        String id="";
        String employeeName = this.name;
        employeeName=employeeName.trim();
        String[] nameWords = employeeName.split(" ");
     for (String nameWord : nameWords) {
         id = id + nameWord.charAt(0);
     }
     id=id.toUpperCase();
     id=String.format(id+"%05d",nEmployees);
        return id;
}

    /**
     * Getter for the specialist doctor index number.
     * @return String with the specialist doctor index number.
     */
    public String getSpecialistDoctorIndexNumber() {
        return specialistDoctorIndexNumber;
    }

    /**
     * Setter for the specialist doctor index number.
     * @param specialistDoctorIndexNumber-String with the  new specialist doctor index number.
     */
    public void setSpecialistDoctorIndexNumber(String specialistDoctorIndexNumber) {
        this.specialistDoctorIndexNumber = specialistDoctorIndexNumber;
    }

    /**
     * Getter for the employee's userName.
     * @return String with the employee's userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Setter for the userName of the employee.
     * @param userName- String with the new userName for the employee.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Getter for the employee's password.
     * @return String with the employee's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for the employee's password.
     * @param password- String with the new password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter for the employee's email.
     * @return String with the employee's email.
     */

    public String getEmail() {
        return email;
    }

    /**
     * Setter for the employee's email.
     * @param email- String with the new email.
     */

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * New equals method for the Employee Class.Checks to see if the object has the same memory adress or not.
     * Then checks to see if the other object is null.
     * Then checks to see if the other object has the same class.
     * Then compares every parameter of the two employees to see if they are equal and returns true if that is the case.
     * @param o- Another Object.
     * @return boolean value telling if the object is equal or not to this a certain employee.
     */
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
    return Objects.equals(name,e.name) && Objects.equals(ID,e.ID) && Objects.equals(adress,e.adress) && Objects.equals(SOC,e.SOC) && Objects.equals(phoneNumber,e.phoneNumber)&& Objects.equals(userName,e.userName)&& Objects.equals(role,e.role)&&Objects.equals(email,e.email);

}


    /**
     * New toString method for the employee class.
     * @return String with a formatted String presenting the employee information.
     */
    @Override
    public String toString(){
        if (specialistDoctorIndexNumber==null){
            return String.format("This employee is named "+this.name+". Their ID is "+ this.ID +". Their adress is "+ this.adress+". Their phone number is "+ this.phoneNumber+". \nTheir SOC is "+this.SOC+". Their email adress is "+ this.email+". Their username is "+ this.userName+". Their password is "+this.password+". Their role is "+ role.getRoleID()+".");
        }else
        return String.format("This employee is named "+this.name+". Their ID is "+ this.ID +". Their adress is "+ this.adress+". Their phone number is "+ this.phoneNumber+". \nTheir SOC is "+this.SOC+". Their email adress is " +this.email + ". Their username is "+ this.userName+". Their password is "+this.password+". Their role is "+ role.getRoleID()+". Their doctor Index number is "+this.specialistDoctorIndexNumber);
}

    /**
     * Checks to see if the employee has valid date within this program's context.
     * Checks to see if the name has more than 35 characters or has numbers.
     * Checks to see if the SOC code has 4 digits.
     * Checks to see if the employee has a role.
     * Checks to see if the specialist doctor index exists and has 6 digits.
     * If all of the above are true this returns true.
     * @return- boolean value that tells if the employee has valid data.
     */
    public  boolean validateEmployee(){
        if (name.length()>35||Utils.nameContainsDigits(name)){
            return false;
    }
    if (Utils.validateSOC(SOC)){
        return false;
    }
    if (role==null){
        return false;
    }
    if (specialistDoctorIndexNumber!= null && specialistDoctorIndexNumber.length()!=6){
        return false;
    }
    return true;
}

    /**
     * Used to create the password, returns a random character from a String parameter.
     * @param s- String with the characters to choose from.
     * @return Random Character from the String
     */
    private Character randomCharacter(String s){
    Random randomNumber= new Random();
    int randomNo=randomNumber.nextInt(s.length());
    return s.charAt(randomNo);
}


}
