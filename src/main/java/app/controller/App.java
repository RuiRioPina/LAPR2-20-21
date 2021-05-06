package app.controller;

import app.domain.model.Company;
import app.domain.model.ParameterCategory;
import app.domain.shared.Constants;
import app.domain.store.ParameterCategoryStore;
import app.domain.store.RoleStore;
import auth.AuthFacade;
import auth.UserSession;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class App {

    private Company company;
    private AuthFacade authFacade;

    private App()
    {
        Properties props = getProperties();
        this.company = new Company(props.getProperty(Constants.PARAMS_COMPANY_DESIGNATION));
        this.authFacade = this.company.getAuthFacade();
        bootstrap();
    }

    public Company getCompany()
    {
        return this.company;
    }


    public UserSession getCurrentUserSession()
    {
        return this.authFacade.getCurrentUserSession();
    }

    public boolean doLogin(String email, String pwd)
    {
        return this.authFacade.doLogin(email,pwd).isLoggedIn();
    }

    public void doLogout()
    {
        this.authFacade.doLogout();
    }

    private Properties getProperties()
    {
        Properties props = new Properties();

        // Add default properties and values
        props.setProperty(Constants.PARAMS_COMPANY_DESIGNATION, "Many Labs");


        // Read configured values
        try
        {
            InputStream in = new FileInputStream(Constants.PARAMS_FILENAME);
            props.load(in);
            in.close();
        }
        catch(IOException ex)
        {

        }
        return props;
    }


    private void bootstrap()
    {
        this.authFacade.addUserRole(Constants.ROLE_ADMIN,Constants.ROLE_ADMIN);
        this.authFacade.addUserRole(Constants.ROLE_CLIENT,Constants.ROLE_CLIENT);
        this.authFacade.addUserRole(Constants.ROLE_RECEPTIONIST,Constants.ROLE_RECEPTIONIST);
        this.authFacade.addUserRole(Constants.ROLE_CLINICAL_CHEMISTRY_TECHNOLOGIST,Constants.ROLE_CLINICAL_CHEMISTRY_TECHNOLOGIST);
        this.authFacade.addUserRole(Constants.ROLE_MEDICAL_LAB_TECHNICIAN,Constants.ROLE_MEDICAL_LAB_TECHNICIAN);
        this.authFacade.addUserRole(Constants.ROLE_LABORATORY_COORDINATOR,Constants.ROLE_LABORATORY_COORDINATOR);
        this.authFacade.addUserRole(Constants.ROLE_SPECIALIST_DOCTOR,Constants.ROLE_SPECIALIST_DOCTOR);

        this.authFacade.addUserWithRole("Main Administrator", "admin@lei.sem2.pt", "123456",Constants.ROLE_ADMIN);
        this.authFacade.addUserWithRole("Receptionist", "receptionist@lei.sem2.pt", "12",Constants.ROLE_RECEPTIONIST);
        
        ParameterCategoryStore cs = this.company.getParameterCategoryStore();
        cs.saveParameterCategory(new ParameterCategory("COISA", "Hemogram"));
        cs.saveParameterCategory(new ParameterCategory("X2341", "BCC"));
        cs.saveParameterCategory(new ParameterCategory("X1234", "WCC"));
        RoleStore lRole = this.company.getRoleStore();
        lRole.add(lRole.create("Receives the client",Constants.ROLE_RECEPTIONIST));
        lRole.add(lRole.create("Performs Chemical Analysis and records results",Constants.ROLE_CLINICAL_CHEMISTRY_TECHNOLOGIST));
        lRole.add(lRole.create("Has the responsibility of interacting with the software on a deeper level",Constants.ROLE_MEDICAL_LAB_TECHNICIAN));
        lRole.add(lRole.create("Coordinates the activity on the laboratory",Constants.ROLE_LABORATORY_COORDINATOR));
        lRole.add(lRole.create("Responsible for interacting with the client and their tests",Constants.ROLE_SPECIALIST_DOCTOR));
    }

    // Extracted from https://www.javaworld.com/article/2073352/core-java/core-java-simply-singleton.html?page=2
    private static App singleton = null;
    public static App getInstance()
    {
        if(singleton == null)
        {
            synchronized(App.class)
            {
                singleton = new App();
            }
        }
        return singleton;
    }
}
