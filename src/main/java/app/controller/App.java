package app.controller;

import app.domain.model.*;
import app.domain.shared.Constants;
import app.domain.store.*;
import auth.AuthFacade;
import auth.UserSession;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class App {

    private Company company;
    private AuthFacade authFacade;

    private App() {
        Properties props = getProperties();
        this.company = new Company(props.getProperty(Constants.PARAMS_COMPANY_DESIGNATION));
        this.authFacade = this.company.getAuthFacade();
        bootstrap();
    }

    public Company getCompany() {
        return this.company;
    }


    public UserSession getCurrentUserSession() {
        return this.authFacade.getCurrentUserSession();
    }

    public boolean doLogin(String email, String pwd) {
        return this.authFacade.doLogin(email, pwd).isLoggedIn();
    }

    public void doLogout() {
        this.authFacade.doLogout();
    }

    private Properties getProperties() {
        Properties props = new Properties();

        // Add default properties and values
        props.setProperty(Constants.PARAMS_COMPANY_DESIGNATION, "Many Labs");


        // Read configured values
        try {
            InputStream in = new FileInputStream(Constants.PARAMS_FILENAME);
            props.load(in);
            in.close();
        } catch (IOException ex) {

        }
        return props;
    }


    private void bootstrap() {


        this.authFacade.addUserRole(Constants.ROLE_ADMIN, Constants.ROLE_ADMIN);
        this.authFacade.addUserRole(Constants.ROLE_CLIENT, Constants.ROLE_CLIENT);
        this.authFacade.addUserRole(Constants.ROLE_RECEPTIONIST, Constants.ROLE_RECEPTIONIST);
        this.authFacade.addUserRole(Constants.ROLE_CLINICAL_CHEMISTRY_TECHNOLOGIST, Constants.ROLE_CLINICAL_CHEMISTRY_TECHNOLOGIST);
        this.authFacade.addUserRole(Constants.ROLE_MEDICAL_LAB_TECHNICIAN, Constants.ROLE_MEDICAL_LAB_TECHNICIAN);
        this.authFacade.addUserRole(Constants.ROLE_LABORATORY_COORDINATOR, Constants.ROLE_LABORATORY_COORDINATOR);
        this.authFacade.addUserRole(Constants.ROLE_SPECIALIST_DOCTOR, Constants.ROLE_SPECIALIST_DOCTOR);

        this.authFacade.addUserWithRole("Main Administrator", "admin@lei.sem2.pt", "123456", Constants.ROLE_ADMIN);
        this.authFacade.addUserWithRole("Receptionist", "receptionist@lei.sem2.pt", "12", Constants.ROLE_RECEPTIONIST);
        this.authFacade.addUserWithRole("Clinical Chemistry Technologist", "cct@lei.sem2.pt", "12", Constants.ROLE_CLINICAL_CHEMISTRY_TECHNOLOGIST);
        this.authFacade.addUserWithRole("MedicalLabTechnician1", "medlabt@lei.sem2.pt", "123456", Constants.ROLE_MEDICAL_LAB_TECHNICIAN);
        this.authFacade.addUserWithRole("LabCoordinator", "labcoord@lab.pt", "123", Constants.ROLE_LABORATORY_COORDINATOR);
        this.company.setNumberOfEmployees(0);
        this.company.setTestCode(0);

        Sample sample = new Sample("99999999999");
        Sample sample1 = new Sample("11111111111");
        ParameterCategoryStore cs = this.company.getParameterCategoryStore();

        List<ParameterCategory> pc = new ArrayList<>();
        ParameterCategory p1 = new ParameterCategory("CAT00", "Category00");
        ParameterCategory P2 = new ParameterCategory("CAT01", "Category01");
        ParameterCategory P3 = new ParameterCategory("CAT02", "Category02");
        pc.add(p1);
        pc.add(P2);

        List<ParameterCategory> p = new ArrayList<>();
        p.add(P3);

        List<ParameterCategory> cat = new ArrayList<>();
        cat.add(pc.get(0));

        List<ParameterCategory> cat1 = new ArrayList<>();
        cat1.add(P2);

        List<ParameterCategory> cat2 = new ArrayList<>();
        cat2.add(P3);

        cs.saveParameterCategory(p1);
        cs.saveParameterCategory(P2);
        cs.saveParameterCategory(P3);
        ParameterStore ps = this.company.getParameterStore();

        ps.saveParameter(new Parameter("HB000", "HB", "Haemoglobin", cat));
        ps.saveParameter(new Parameter("WBC00", "WBC", "White Cell Count", cat));
        ps.saveParameter(new Parameter("PLT00", "PLT", "Platelet Count", cat));
        ps.saveParameter(new Parameter("RBC00", "RBC", "Red Blood Count", cat));
        ps.saveParameter(new Parameter("MCV00", "MCV", "Mean Cell Volume", cat1));
        ps.saveParameter(new Parameter("MCH00", "MCH", "Mean Cell Heaemoglob", cat1));
        ps.saveParameter(new Parameter("MCHC0", "MCHC", "MCH Concentration", cat1));
        ps.saveParameter(new Parameter("ESR00", "ESR", "Erythrocyte Sed Rate", cat1));
        ps.saveParameter(new Parameter("IgGAN", "IgC", "Antibodies", cat2));

        List<Parameter> par = new ArrayList<>();
        par.add(ps.getParameters().get(0));
        par.add(ps.getParameters().get(1));
        par.add(ps.getParameters().get(2));
        par.add(ps.getParameters().get(3));

        TestTypeStore tts = this.company.getTestTypeStore();
        TestType tt1 = new TestType("BLT00", "Blood Test", "Venipuncture", pc);
        TestType tt2 = new TestType("CVD00", "Covid-19 Test", "Nasopharyngeal", p);
        tts.saveTestType(tt1);
        tts.saveTestType(tt2);

        List <TestType> ttList = new ArrayList<>();
        ttList.add(tt1);
        ttList.add(tt2);

        ClientList cl = this.company.getClientList();
        Client c = new Client(1234567890123456L, 1234567890, "22-01-2002", "jorge@gmail.com", 1111111111L, 22222222222L, "Jorge Ferreira");
        cl.saveClient(c);

        TestStore ts = this.company.getTestStore();
        Date data = new Date(System.currentTimeMillis());

        Test testSemSample1 = new Test("123454abcdef", "932992999999", c, tts.getTestTypes().get(0), pc, par, data);
        Test testSemSample2 = new Test("123454abcdef", "991992999999", c, tts.getTestTypes().get(0), pc, par, data);
        ts.saveTest(testSemSample1);
        ts.saveTest(testSemSample2);
        
        Test test = new Test("123454abcdef", "999992999999", c, tts.getTestTypes().get(0), pc, par, data);
        Test test1 = new Test("121454abcdef", "991992999999", c, tts.getTestTypes().get(0), pc, par, data);
        ts.saveTest(test);
        ts.saveTest(test1);

        ts.saveSample(test, sample);
        ts.saveSample(test1, sample1);

        ClinicalAnalysisLaboratoryStore clas = this.company.getClinicalAnalysisLaboratoryStore();
        ClinicalAnalysisLaboratory cla = new ClinicalAnalysisLaboratory("12345","LAB","London",99999979999L,9999999999L,ttList);
        ClinicalAnalysisLaboratory cla2 = new ClinicalAnalysisLaboratory("12344","LABor","Manchester",88888888888L,8888888888L,ttList);
        clas.saveClinicalAnalysisLaboratory(cla);
        clas.saveClinicalAnalysisLaboratory(cla2);

        RoleStore lRole = this.company.getRoleStore();
        lRole.add(lRole.create("Receives the client", Constants.ROLE_RECEPTIONIST, "REC"));
        lRole.add(lRole.create("Performs Chemical Analysis and records results", Constants.ROLE_CLINICAL_CHEMISTRY_TECHNOLOGIST, "CCT"));
        lRole.add(lRole.create("Has the responsibility of interacting with the software on a deeper level", Constants.ROLE_MEDICAL_LAB_TECHNICIAN, "MDT"));
        lRole.add(lRole.create("Coordinates the activity on the laboratory", Constants.ROLE_LABORATORY_COORDINATOR, "LC"));
        lRole.add(lRole.create("Responsible for interacting with the client and their tests", Constants.ROLE_SPECIALIST_DOCTOR, "SD"));

    }

    // Extracted from https://www.javaworld.com/article/2073352/core-java/core-java-simply-singleton.html?page=2
    private static App singleton = null;

    public static App getInstance() {
        if (singleton == null) {
            synchronized (App.class) {
                singleton = new App();
            }
        }
        return singleton;
    }
}
