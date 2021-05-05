package app.domain.model;

import app.domain.store.ParameterStore;
import auth.AuthFacade;
import org.apache.commons.lang3.StringUtils;

import app.domain.store.ParameterCategoryStore;
import app.domain.store.TestTypeStore;

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

    public Company(String designation)
    {
        if (StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");

        this.designation = designation;
        this.authFacade = new AuthFacade();
        this.parameterCategoryStore = new ParameterCategoryStore();
        this.testTypeStore = new TestTypeStore();
        this.parameterStore = new ParameterStore();
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
}
