package app.domain.model;

import java.io.IOException;
import java.util.*;

import app.domain.shared.EmailNotificationSender;
import app.domain.store.ResultOfTestStore;


public class Test {

    /**
     * Object oriented Class to the registration of a test.
     */
    private final String nhsCode;
    private final String internalCode;
    private final Client client;
    private final List<ParameterCategory> parameterCategory;
    private final TestType testType;
    private final String sampleCollectionMethod;
    private List<Parameter> parameter = new ArrayList<>();
    private final Date registrationDate;
    private List<Sample> samples;
    private Date samplesCollectionDate;
    private Date chemicalAnalysisDate;
    private Date diagnosisDate;
    private Date validationDate;
    private Report report;
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
        this.parameterCategory = Collections.unmodifiableList(parameterCategory);
        this.parameter = Collections.unmodifiableList(parameter);
        this.registrationDate = new Date(registrationDate.getTime());
        if(getTestResult()==null) {
            this.chemicalAnalysisDate = null;
        }else {
            this.chemicalAnalysisDate = new Date(System.currentTimeMillis());
        }
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
        return new ArrayList<>(parameterCategory);
    }

    /**
     * Returns the test parameters.
     *
     * @return parameters of the test.
     */
    public List<Parameter> getParameter() {
        return new ArrayList<>(parameter);
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
        return new Date(registrationDate.getTime());
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
     *  Returns the test report
     * @return report of the test
     */
    public Report getReport(){return report;}

    public void setReport(Report report){
        this.report=report;
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
                    "Client = " + client.getName() + '\n' +
                    "Test Type = " + testType + '\n' +
                    "Sample Collection Method = " + sampleCollectionMethod + '\n' +
                    "Category(ies) = " + parameterCategory + '\n' +
                    "Parameter(s) = " + parameter + '\n' +
                    "Registration Date = " + registrationDate;
        }

        if (registrationDate!=null && samplesCollectionDate!=null && chemicalAnalysisDate!=null && diagnosisDate== null && validationDate==null){
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
            str= "Internal code: "+ internalCode +" Registration Date:"+registrationDate+" Chemical Analysis Date:"+chemicalAnalysisDate + " Diagnosis Date:"+diagnosisDate;
        }



        return str;
    }

    /**
     * Setter for the sample collection date
     * @param date date to be changed to.
     */

    public void setSamplesCollectionDate(Date date) {
        this.samplesCollectionDate = date;
    }

    /**
     * The method equals overwritten to check if a test is equal to another one
     * @param o the object being compared
     * @return boolean containing the result of the comparison
     */
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
        return Objects.equals(this.samples, t.samples);
    }

    /**
     * Add the test result for the test being handled
     * @param parameter parameter of where the result will be recorded
     * @param result result of the test. Introduced by the user
     * @return allocate this task to the resultOfTestStore assigned to do this task. (High Cohesion Pattern)
     */
    public TestResult addTestResult(Parameter parameter, double result) {

        ReferenceValue referenceValue = testType.checkExternalModuleBasedOnTestType(parameter);

        return resultOfTestStore.addTestResult(parameter, result, referenceValue);
    }

    /**
     * Getter of test results
     * @return the test results of the test
     */

    public List<TestResult> getTestResult() {
        return resultOfTestStore.getResultOfTest();
    }

    /**
     * Setter for samples of a test
     * @param samples a list sample associated to the test
     */


    public void setSamples(List<Sample> samples) {
        this.samples = samples;
    }

    /**
     * Get a copy of the parameters for a test
     * @return a list containing a copy of parameters of a test
     */

    public List<Parameter> getParameterStoreToShow() {
        List<Parameter> parametersToShow = new ArrayList<>();
            parametersToShow.addAll(this.parameter);
        return parametersToShow;
    }

    /**
     * Setter for the ChemicalAnalysisDate.
     * @param newDate- new Date object to replace the old value.
     */
    public void setChemicalAnalysisDate(Date newDate){
        this.chemicalAnalysisDate=newDate;
    }

    /**
     * Setter for the diagnosisDate.
     * @param newDate- new Date object to replace the old value.
     */
    public void setDiagnosisDate(Date newDate){
        this.diagnosisDate=newDate;
    }

    /**
     * Setter for the validationDate.
     * @param newDate- new Date object to replace the old value.
     */
    public void setValidationDate(Date newDate){
        this.validationDate=newDate;
    }

    /**
     * Method used to simulate the sending of an email to the client.
     * @throws IOException          if the file to be written to doesn't exist.
     * @throws InterruptedException if the thread that is sleeping is interrupted.
     */
    public void sendTestCompletedNotification()throws InterruptedException, IOException {
         EmailNotificationSender ens= new EmailNotificationSender(this.client);
         ens.sendTestCompletedNotification();
    }


    public String getDate() {
        return String.format("%s",this.chemicalAnalysisDate);
    }
}
