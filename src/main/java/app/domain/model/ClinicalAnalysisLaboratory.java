package app.domain.model;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

public class ClinicalAnalysisLaboratory {

    private String laboratoryID;
    private String name;
    private String adress;
    private int phoneNumber;
    private int tin;

    public ClinicalAnalysisLaboratory(String laboratoryID, String name, String adress, int phoneNumber, int tin){
        checklaboratoryID(laboratoryID);
        checkname(name);
        checkadress(adress);
        checkphoneNumber(phoneNumber);
        checkTIN(tin);
        this.laboratoryID=laboratoryID;
        this.name=name;
        this.adress=adress;
        this.tin=tin;
        this.phoneNumber=phoneNumber;

    }

    public String getLaboratoryID(){
        return laboratoryID;
    }

    public void setLaboratoryID(String laboratoryID){
        this.laboratoryID=laboratoryID;
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

    public int getPhoneNumber(){
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber){
        this.phoneNumber=phoneNumber;
    }

    public int getTin() {
        return tin;
    }

    public void setTin(int tin) {
        this.tin = tin;
    }

    private void checkname(String name){
        if ((name.length()) < 1 || name.length() > 20){
            throw new IllegalArgumentException("Name must have 1 to 20 chars.");
        }
    }

    private void checklaboratoryID(String laboratoryID){
        if (laboratoryID.length() != 1 ){
            throw new IllegalArgumentException("LaboratoryID must have 5 chars.");
        }
    }

    private void checkadress(String adress){
        if ((adress.length()) < 1 || adress.length() > 30){
            throw new IllegalArgumentException("Adress must have 1 to 30 chars.");
        }
    }

    private void checkphoneNumber(int phoneNumber){
        if (digits(phoneNumber) != 11){
            throw new IllegalArgumentException("Phone number must have 11 digits.");
        }
    }

    private void checkTIN(int tin){
        if (digits(tin) != 10){
            throw new IllegalArgumentException("TIN number must have 10 digits.");
        }
    }

    private int digits(int num){
        int digits=0;
        do {
            num=num/10;
            digits++;
        }while (num!=0);
        return digits;
    }
}
