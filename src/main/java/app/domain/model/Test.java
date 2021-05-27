package app.domain.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import app.domain.shared.EmailNotificationSender;
import app.domain.store.ParameterStore;
import app.domain.store.ResultOfTestStore;


public class Test {

    /**
     * Object oriented Class to the registration of a test.
     */
    private String nhsCode;
    private String internalCode;
    private Client client;
    private List<ParameterCategory> parameterCategory;
    private TestType testType;
    private String sampleCollectionMethod;
    private List<Parameter> parameter = new ArrayList<>();
    private Date registrationDate;
    private List<Sample> samples;
    private Date samplesCollectionDate;
    private Date chemicalAnalysisDate;
    private Date diagnosisDate;
    private Date validationDate;
    private final ResultOfTestStore resultOfTestStore = new ResultOfTestStore();


    /**
     * Constructor for the test.
     *
     * @param nhsCode           - NHS code of the test.
     * @param internalCode      - Internal code of the test.
     * @param client            - Client that performs the test.
     * @param testType          - Test Type of the test.
     * @param parameterCategory - Category/Categories of the test.
     * @param parameter         - Parameter/Parameters of the test.
     * @param registrationDate  - Test registration date.
     */
    public Test(String nhsCode, String internalCode, Client client, TestType testType, List<ParameterCategory>
            parameterCategory, List<Parameter> parameter, Date registrationDate) {
        this.nhsCode = nhsCode;
        this.internalCode = internalCode;
        this.client = client;
        this.testType = testType;
        this.sampleCollectionMethod = this.testType.getCollectingMethod();
        this.parameterCategory = parameterCategory;
        this.parameter = parameter;
        this.registrationDate = registrationDate;
        this.chemicalAnalysisDate = null;
        this.diagnosisDate = null;
        this.validationDate = null;
        this.samplesCollectionDate = null;
        this.samples = new ArrayList<>();
    }

    /**
     * Returns the test NHS code.
     *
     * @return nhs code of the test.
     */
    public String getNhsCode() {
        return nhsCode;
    }

    /**
     * Returns the test internal code.
     *
     * @return internal code of the test.
     */
    public String getInternalCode() {
        return internalCode;
    }

    /**
     * Returns the client that performs the test.
     *
     * @return client to performed the test.
     */
    public Client getClient() {
        return client;
    }

    /**
     * Returns the test test type.
     *
     * @return test type of the test.
     */
    public TestType getTestType() {
        return testType;
    }

    /**
     * Returns the test sample collection method.
     *
     * @return sample collection method of the test.
     */
    public String getSampleCollectionMethod() {
        return sampleCollectionMethod;
    }

    /**
     * Returns the test categories.
     *
     * @return categories of the test.
     */
    public List<ParameterCategory> getParameterCategory() {
        return parameterCategory;
    }

    /**
     * Returns the test parameters.
     *
     * @return parameters of the test.
     */
    public List<Parameter> getParameter() {
        return parameter;
    }

    /**
     * Returns the test samples.
     *
     * @return samples of the test.
     */
    public List<Sample> getSamples() {
        return this.samples;
    }

    /**
     * Returns the test registration date.
     *
     * @return registration date of the test.
     */
    public Date getRegistrationDate() {
        return registrationDate;
    }

    /**
     * Returns the test sample collection date.
     *
     * @return sample collection date of the test.
     */
    public Date getSamplesCollectionDate() {
        return samplesCollectionDate;
    }

    /**
     * Returns the test chemical analysis date.
     *
     * @return chemical analysis date of the test.
     */
    public Date getChemicalAnalysisDate() {
        return chemicalAnalysisDate;
    }

    /**
     * Returns the test diagnosis date.
     *
     * @return diagnosis date of the test.
     */
    public Date getDiagnosisDate() {
        return diagnosisDate;
    }

    /**
     * Returns the test validation date.
     *
     * @return validation date of the test.
     */
    public Date getValidationDate() {
        return validationDate;
    }

    /**
     * Returns a string with test information.
     *
     * @return string with the information of the test.
     */
    @Override
    public String toString() {
        String str = "";
        if (samples != null || samplesCollectionDate != null ||
                chemicalAnalysisDate != null || diagnosisDate != null || validationDate != null) {
            str = "TEST" + '\n' +
                    "NHS Code = " + nhsCode + '\n' +
                    "Internal Code = " + internalCode + '\n' +
                    "Client = " + client + '\n' +
                    "Test Type = " + testType + '\n' +
                    "Sample Collection Method = " + sampleCollectionMethod + '\n' +
                    "Category(ies) = " + parameterCategory + '\n' +
                    "Parameter(s) = " + parameter + '\n' +
                    "Registration Date = " + registrationDate;
        }
        if (registrationDate!=null && samplesCollectionDate!=null && chemicalAnalysisDate!=null && diagnosisDate!= null && validationDate==null){
            str= internalCode + " Registration Date:"+registrationDate+" Chemical Analysis Date:"+chemicalAnalysisDate + "Diagnosis Date:"+diagnosisDate;
        }

        if (registrationDate!=null && samplesCollectionDate!=null && chemicalAnalysisDate!=null && diagnosisDate== null && validationDate==null){
            str= internalCode + " Registration Date:"+registrationDate+" Chemical Analysis Date:"+chemicalAnalysisDate + "Diagnosis Date:"+diagnosisDate;
        }

        return str;
    }

    public void setSamplesCollectionDate(Date date) {
        this.samplesCollectionDate = date;
    }

    private String barcode;

    public Test(ParameterStore parameterStore) {
    }

    public String getBarcode() {
        return barcode;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        Test t = (Test) o;
        return Objects.equals(this.barcode, t.barcode);
    }
    public TestResult addTestResult(Parameter parameter, double result) {

        ReferenceValue referenceValue = testType.checkExternalModuleBasedOnTestType(parameter);

        return resultOfTestStore.addTestResult(parameter, result, referenceValue);
    }

    public List<TestResult> getTestResult() {
        return resultOfTestStore.getResultOfTest();
    }


    public List<Parameter> getParameterStore() {
        return this.parameter;
    }

    public List<Parameter> getParameterStoreToShow() {
        List<Parameter> parametersToShow = new ArrayList<>();
            parametersToShow.addAll(this.parameter);
        return parametersToShow;
    }

    List<Parameter> parametersToShowAndRemove = getParameterStoreToShow();

    public List<Parameter> removeItemFromParameterStore(int option) {
        List<Parameter> parameterToRemove = parametersToShowAndRemove;
        parameterToRemove.remove(option);
        return parameterToRemove;
    }
    public void setChemicalAnalysisDate(Date newDate){
        this.chemicalAnalysisDate=newDate;
    }
    public void setDiagnosisDate(Date newDate){
        this.diagnosisDate=newDate;
    }
    public void setValidationDate(Date newDate){
        this.validationDate=newDate;
    }
    public void sendTestCompletedNotification()throws InterruptedException, IOException {
         EmailNotificationSender ens= new EmailNotificationSender(this.client);
         ens.sendTestCompletedNotification();
    }


    public void getDate() {
        System.out.println(this.chemicalAnalysisDate);
    }
}
