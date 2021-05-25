package app.controller;

import app.domain.model.*;
import app.domain.store.*;

import java.util.Date;
import java.util.List;

public class TestController {
private Company company;
private Test test;

    public TestController() {
        this.company = App.getInstance().getCompany();
    }

    public List<Test> getTests(){
        TestStore ts = this.company.getTestStore();
        return ts.getTests();
    }

    public List<Client> getClients() {
        ClientList cl = this.company.getClientList();
        return cl.getClients();
    }

    public List<TestType> getTestTypes(){
        TestTypeStore tt = this.company.getTestTypeStore();
        return tt.getTestTypes();
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
        ParameterCategoryStore pcs = this.company.getParameterCategoryStore();
        return  pcs.getParameterCategoriesByList(cat);
    }

    public Test createTest(String nhsCode, String internalCode, Client client, TestType testType, String sampleCollectionMethod,
                           List<ParameterCategory> parameterCategory, List<Parameter> parameter,Date registrationDate) throws IllegalArgumentException{

        TestStore ts = this.company.getTestStore();
        Test t = ts.createTest(nhsCode, internalCode, client, testType, sampleCollectionMethod,
                parameterCategory, parameter, registrationDate);
        this.test = t;
        return this.test;
    }
    public void saveTest(Test t) {
        TestStore ps = this.company.getTestStore();
        ps.saveTest(t);
    }
}
