package app.controller;

import app.domain.model.*;
import app.domain.store.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Controller class to register and import Tests.
 */
public class TestController {
    private Company company;
    private Test test;
    private String labId;
    private String nhsCode;
    private String internalCode;
    private Client clt;
    private TestType tt;
    private List<ParameterCategory> catList;
    private List<Parameter> parList;
    private Calendar RegDate;

    public TestController() {
        this.company = App.getInstance().getCompany();
    }

    public List<Test> getTests() {
        TestStore ts = this.company.getTestStore();
        return ts.getTests();
    }

    public List<Client> getClients() {
        ClientList cList = this.company.getClientList();
        return cList.getClients();
    }

    public List<TestType> getTestTypes() {
        TestTypeStore ttStore = this.company.getTestTypeStore();
        return ttStore.getTestTypes();
    }

    public List<Parameter> getParameters() {
        ParameterStore ps = this.company.getParameterStore();
        return ps.getParameters();
    }

    public List<Parameter> getParameterByCategory(ParameterCategory cat) {
        ParameterStore ps = this.company.getParameterStore();
        return ps.getParameterByCategory(cat);
    }

    public List<ParameterCategory> getCategories() {
        ParameterCategoryStore pcs = this.company.getParameterCategoryStore();
        return pcs.getParameterCategories();
    }

    public List<ParameterCategory> getCategoriesByList(List<ParameterCategory> cat) {
        ParameterCategoryStore pcStore = this.company.getParameterCategoryStore();
        return pcStore.getParameterCategoriesByList(cat);
    }

    public Test createTest(String nhsCode, String internalCode, Client client, TestType testType, List<ParameterCategory>
            parameterCategory, List<Parameter> parameter, Calendar registrationDate) throws IllegalArgumentException {

        TestStore tStore = this.company.getTestStore();
        Test t = tStore.createTest(nhsCode, internalCode, client, testType, parameterCategory, parameter, registrationDate);
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

    public Client client(long ccn, long nhsNumber, String birthDate,
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

    public Parameter getParameterByCode (String p){
        ParameterStore pStore = this.company.getParameterStore();
        return pStore.getParameterByCode(p);
    }

    public List <Parameter> addTestResul(String [] header, String [] text, List<Parameter> pList) {
        List <Parameter> parSelected = new ArrayList<>();
        for (int i = 0; i < header.length; i++) {
            Parameter p = this.company.getParameterStore().getParameterByCode(header[i]);
            for (int n = 11; n < text.length; n++) {
                if (p != null && p.getCode().equals(header[n]) && !text[n].equals("NA")) {
                    Parameter cloned = new Parameter(p);
                    text[n] = text[n].replace(",", ".");
                    cloned.setTestResult(new TestResult(cloned, Double.parseDouble(text[n]), new TestType().checkExternalModuleBasedOnTestType(cloned)));
                    parSelected.add(cloned);
                }
            }

        }
        return parSelected;
    }

    public List<Parameter> getParametersByHeader(int posTt, int posRegDate, String[] header) {
        List<Parameter> parameterList = this.company.getParameterStore().getParameters();
        List<Parameter> parList = new ArrayList<>();
        for (int i = 0; i < parameterList.size(); i++) {
            Parameter p = parameterList.get(i);
            for (int j = posTt; j < posRegDate; j++) {
                if (header[j].equals(p.getCode())) {
                    parList.add(p);
                }
            }
        }
        this.parList = parList;
        return parList;
    }

    public List<ParameterCategory> getCategoriesByName(String[] text) {
        List<ParameterCategory> categoriesList = this.company.getParameterCategoryStore().getParameterCategories();
        List<ParameterCategory> catList = new ArrayList<>();
        for (int i = 0; i < categoriesList.size(); i++) {
            ParameterCategory c = categoriesList.get(i);
            for (int j = 0; j < text.length; j++) {
                if (text[j].equals(c.getName())) {
                    catList.add(c);
                }
            }
        }
        this.catList = catList;
        return catList;
    }

    public Test createeTest(Calendar d1, Calendar d2, Calendar d3, Calendar d4){
        TestStore tStore = this.company.getTestStore();
        Test t = tStore.createTest(nhsCode, internalCode, clt, tt, catList, parList, d1);
        t.setSamplesCollectionDate(d1);
        t.setChemicalAnalysisDate(d2);
        t.setDiagnosisDate(d3);
        t.setValidationDate(d4);
        this.test = t;
        return t;
    }
}