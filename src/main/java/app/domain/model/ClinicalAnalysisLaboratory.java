package app.domain.model;

public class ClinicalAnalysisLaboratory {

    private String laboratoryID;
    private String name;
    private String adress;
    private int phoneNumber;
    private int tin;

    public ClinicalAnalysisLaboratory(String laboratoryID, String name, String adress, int phoneNumber, int tin){
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


}
