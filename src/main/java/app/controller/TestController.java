package app.controller;

import app.domain.model.*;
import app.domain.store.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TestController {
private Company company;
private Test test;
private Client clt;

    public TestController() {
        this.company = App.getInstance().getCompany();
    }

    public List<Test> getTests(){
        TestStore ts = this.company.getTestStore();
        return ts.getTests();
    }

    public List<Client> getClients() {
        ClientList cList = this.company.getClientList();
        return cList.getClients();
    }

    public List<TestType> getTestTypes(){
        TestTypeStore ttStore = this.company.getTestTypeStore();
        return ttStore.getTestTypes();
    }

    public List <Parameter> getParameters(){
        ParameterStore ps = this.company.getParameterStore();
        return  ps.getParameters();
    }
    public List <Parameter> getParameterByCategory(ParameterCategory cat){
        ParameterStore ps = this.company.getParameterStore();
        return  ps.getParameterByCategory(cat);
    }

    public List <ParameterCategory> getCategories(){
        ParameterCategoryStore pcs = this.company.getParameterCategoryStore();
        return  pcs.getParameterCategories();
    }

    public List<ParameterCategory> getCategoriesByList(List <ParameterCategory> cat) {
        ParameterCategoryStore pcStore = this.company.getParameterCategoryStore();
        return  pcStore.getParameterCategoriesByList(cat);
    }

    public Test createTest(String nhsCode, String internalCode, Client client, TestType testType, List<ParameterCategory>
            parameterCategory, List<Parameter> parameter, Calendar registrationDate) throws IllegalArgumentException{

        TestStore tStore = this.company.getTestStore();
        Test t = tStore.createTest(nhsCode, internalCode, client, testType,parameterCategory, parameter, registrationDate);
        this.test = t;
        return this.test;
    }

    public void saveTest(Test t) {
        TestStore testStore = this.company.getTestStore();
        testStore.saveTest(t);
    }

    public void saveClient(Client c) {
        ClientList cList = this.company.getClientList();
        cList.saveClient(c);
    }

    public Client createClient(long ccn, long nhsNumber, String birthDate,
                               long tin, long phoneNumber, String email, String name) {
        ClientList cl = this.company.getClientList();

        this.clt = cl.createClient(ccn, nhsNumber, birthDate, email, phoneNumber, tin, name);
        return this.clt;
    }

    public List <ClinicalAnalysisLaboratory> getLabs(){
        ClinicalAnalysisLaboratoryStore claStore = this.company.getClinicalAnalysisLaboratoryStore();
        return claStore.getLabs();
    }

    public boolean validateTest (Test t) {
        TestStore ts = this.company.getTestStore();
        return ts.validateImportedTests(t);
    }
}
