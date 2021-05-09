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
    private String designation;
    private AuthFacade authFacade;
    private ParameterCategoryStore parameterCategoryStore;
    private TestTypeStore testTypeStore;
    private ParameterStore parameterStore;
    private EmployeeStore employeeStore;
    private RoleStore roleStore;
    private ClientList clientList;
    private ClinicalAnalysisLaboratoryStore clinicalAnalysisLaboratoryStore;


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
        this.roleStore = new RoleStore();
        this.employeeStore = new EmployeeStore();
        this.roleStore = new RoleStore();
        this.clinicalAnalysisLaboratoryStore = new ClinicalAnalysisLaboratoryStore();
        this.numberOfEmployees = 0;
    }

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(int numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;

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

    public EmployeeStore getEmployeeStore() {
        return this.employeeStore;
    }

    public ClientList getClientList() {
        return this.clientList;
    }

    public RoleStore getRoleStore() {
        return this.roleStore;
    }

    public ClinicalAnalysisLaboratoryStore getClinicalAnalysisLaboratoryStore() {
        return this.clinicalAnalysisLaboratoryStore;
    }


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

    private boolean validateClient(Client c) {
        return !clientList.isClientInList(c);
    }

    public void saveClient(Client c) {
        if (validateClient(c)) {
            ClientList cl = this.getClientList();
            cl.saveClient(c);
        } else {
            System.out.println("The client needs to have at least one unique attribute. Please try again.");
        }
        addClientToSystem(c);
    }

    private void addClientToSystem(Client c) {
        App.getInstance().getCompany().getAuthFacade().addUserWithRole(c.getName(), c.getEmail(), c.getPassword(), Constants.ROLE_CLIENT);
    }
}
