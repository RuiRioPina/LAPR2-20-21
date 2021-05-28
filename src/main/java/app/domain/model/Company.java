package app.domain.model;

import app.controller.App;
import app.domain.shared.Constants;
import app.domain.store.*;
import auth.AuthFacade;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Company {

    private int numberOfEmployees;
    private long lastBarcode;
    private int testCode;
    private String designation;
    private AuthFacade authFacade;
    private ParameterCategoryStore parameterCategoryStore;
    private TestTypeStore testTypeStore;
    private ParameterStore parameterStore;
    private EmployeeStore employeeStore;
    private ResultOfTestStore resultOfTestStore;
    private RoleStore roleStore;
    private ClientList clientList;
    private ClinicalAnalysisLaboratoryStore clinicalAnalysisLaboratoryStore;
    private TestStore testStore;
    private ReportStore reportStore;
    private ClinicalAnalysisLaboratory cla;

    public Company(String designation) {
        if (StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");

        this.designation = designation;
        this.authFacade = new AuthFacade();
        this.parameterCategoryStore = new ParameterCategoryStore();
        this.testTypeStore = new TestTypeStore();
        this.parameterStore = new ParameterStore();
        this.clientList = new ClientList();
        this.employeeStore = new EmployeeStore();
        this.resultOfTestStore = new ResultOfTestStore();
        this.roleStore = new RoleStore();
        this.employeeStore = new EmployeeStore();
        this.roleStore = new RoleStore();
        this.clinicalAnalysisLaboratoryStore = new ClinicalAnalysisLaboratoryStore();
        this.testStore = new TestStore();
        this.numberOfEmployees = 0;
        this.lastBarcode = 0;
        this.testCode = 0;
        this.reportStore = new ReportStore();
        this.cla = null;
    }
    public void setCLA (ClinicalAnalysisLaboratory cla){
        this.cla = cla;
    }
    public ClinicalAnalysisLaboratory getCLA (){
        return this.cla;
    }
    public int getTestCode() {
        return testCode;
    }
    public void setTestCode(int number) {
        this.testCode = number;
    }
    /**
     * Getter for the number of Employees in the company.
     * @return number of employees.
     */
    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    /**
     * Setter for the number of Employees in the company
     * @param numberOfEmployees- new number of Employees.
     */
    public void setNumberOfEmployees(int numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;

    }

    public ResultOfTestStore getResultOfTestStore() {
    	return this.resultOfTestStore;
    }
    
    public String getDesignation() {
        return designation;
    }

    public AuthFacade getAuthFacade() {
        return authFacade;
    }

    public ParameterCategoryStore getParameterCategoryStore() {
        return this.parameterCategoryStore;
    }

    public TestTypeStore getTestTypeStore() {
        return this.testTypeStore;
    }

    public ParameterStore getParameterStore() {
        return this.parameterStore;
    }

    /**
     * Getter for the Employee Store that the company is using.
     * @return Employee Store that contains the Employees in the company.
     */
    public EmployeeStore getEmployeeStore() {
        return this.employeeStore;
    }

    public ClientList getClientList() {
        return this.clientList;
    }

    public TestStore getTestStore(){return  this.testStore;}

    public ReportStore getReportStore(){return this.reportStore;}

    /**
     * Getter for the Role Store that the company is using.
     * @return Role Store that contains the Roles in the company.
     */
    public RoleStore getRoleStore() {
        return this.roleStore;
    }

    public ClinicalAnalysisLaboratoryStore getClinicalAnalysisLaboratoryStore() {
        return this.clinicalAnalysisLaboratoryStore;
    }

    /**
     * Simulates sending an email to the Client by writing the password and some other information to a txt file.
     *
     * @param c instance of the Client class
     * @return true if it was able to create and write to the file
     * @throws IOException          if the file to be written to doesn't exist
     * @throws InterruptedException if the thread that is sleeping is interrupted
     */
    public boolean sendEmailToClient(Client c) throws IOException, InterruptedException {
        String nomeficheiro = "emailAndSMSMessages.txt";
        try (PrintWriter out = new PrintWriter(nomeficheiro)) {
            out.println("Welcome to the Application.");
            out.println("You have been sucessfully registered.");
            out.println();
            out.println("Your password to be used in the application is :" + c.getPassword());
        } catch (IOException e) {
            System.out.println("The file has not been created since there was an error. Please try again.");
        }
        File file = new File(nomeficheiro);
        if (file.createNewFile()) {
            System.out.println("Email sent!");
        }
        Desktop desktop = Desktop.getDesktop();
        Thread.sleep(500); // Faz com que o java tenha tempo de criar o ficheiro antes de o ler.
        desktop.open(file);
        return true;
    }

    /**
     * Checks whether an object passed by parameter already exists in the database.
     *
     * @param c instance of the class Client
     * @return true if the Client already exists in the ArrayList on the Class ClientList.
     */
    private boolean validateClient(Client c) {
        return !clientList.isClientInList(c);
    }

    /**
     * Saves the Client to the doing verifications beforehand
     *
     * @param c instance of the class Client
     */
    public boolean saveClient(Client c) {
    	int i = 0;
        if (validateClient(c)) {
            ClientList cl = this.getClientList();
            cl.saveClient(c);
        } else {
            System.out.println("The client needs to have at least one unique attribute. Please try again.");
            i = 1;
        }
        addClientToSystem(c);
        return i != 1;
    }

    /**Adds the Client instance to the platform, so that he can login with the credentials given.
     *
     * @param c instance of the class Client
     */
    private void addClientToSystem(Client c) {
        App.getInstance().getCompany().getAuthFacade().addUserWithRole(c.getName(), c.getEmail(), c.getPassword(), Constants.ROLE_CLIENT);
    }

	public String getNextBarcode() {
		this.lastBarcode++;
		return String.format("%011d", this.lastBarcode);
	}
}
