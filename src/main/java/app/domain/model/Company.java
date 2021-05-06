package app.domain.model;

import app.domain.store.*;
import auth.AuthFacade;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Company {

    private String designation;
    private AuthFacade authFacade;
    private ParameterCategoryStore parameterCategoryStore;
    private TestTypeStore testTypeStore;
    private ParameterStore parameterStore;
    private EmployeeStore employeeStore;
    private RoleStore roleStore;
    private int numberOfEmployees;

    public Company(String designation)
    {
        if (StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");

        this.designation = designation;
        this.authFacade = new AuthFacade();
        this.parameterCategoryStore = new ParameterCategoryStore();
        this.testTypeStore = new TestTypeStore();
        this.parameterStore = new ParameterStore();
        this.employeeStore= new EmployeeStore();
        this.roleStore= new RoleStore();
        this.numberOfEmployees=0;
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
	public ParameterStore getParameterStore(){
        return this.parameterStore;
    }
    public EmployeeStore getEmployeeStore(){return this.employeeStore;}
    public RoleStore getRoleStore(){return this.roleStore;}
}
