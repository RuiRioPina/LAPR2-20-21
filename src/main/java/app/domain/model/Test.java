package app.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test {

    /**
     * Object oriented Class to the registration of a test.
     */
    private final String nhsCode;
    private final String internalCode;
    private final Client client;
    private final TestType testType;
    private final String sampleCollectionMethod;
    private final List <ParameterCategory>  parameterCategory;
    private final List <Parameter> parameter;
    private final Date registrationDate;
    private List <Sample> samples;
    private Date samplesCollectionDate;
    private Date chemicalAnalysisDate;
    private Date diagnosisDate;
    private Date validationDate;

    /**
     * Constructor for the test.
     *
     * @param nhsCode - NHS code of the test.
     * @param internalCode - Internal code of the test.
     * @param client - Client that performs the test.
     * @param testType - Test Type of the test.
     * @param sampleCollectionMethod - Sample collection method of the test.
     * @param parameterCategory - Category/Categories of the test.
     * @param parameter - Parameter/Parameters of the test.
     * @param registrationDate - Test registration date.
     * @param samplesCollectionDate - Test collection date.
     * @param chemicalAnalysisDate - Test chemical analysis date.
     * @param diagnosisDate - Test diagnosis date.
     * @param validationDate - Test validation date.
     */
    public Test(String nhsCode, String internalCode, Client client, TestType testType, String sampleCollectionMethod,
                List<ParameterCategory> parameterCategory, List<Parameter> parameter, List<Sample> samples, Date registrationDate,
                Date samplesCollectionDate, Date chemicalAnalysisDate, Date diagnosisDate, Date validationDate) {
        this.nhsCode = nhsCode;
        this.internalCode = internalCode;
        this.client = client;
        this.testType = testType;
        this.sampleCollectionMethod = sampleCollectionMethod;
        this.parameterCategory = parameterCategory;
        this.parameter = parameter;
        this.registrationDate = registrationDate;
        this.chemicalAnalysisDate = chemicalAnalysisDate;
        this.diagnosisDate = diagnosisDate;
        this.validationDate = validationDate;
        
        this.samplesCollectionDate = null;
        this.samples = new ArrayList<Sample>();
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
        return "TEST" + '\n' +
                "NHS Code = " + nhsCode + '\n' +
                "Internal Code = " + internalCode + '\n' +
                "Client = " + client + '\n' +
                "Test Type = " + testType + '\n' +
                "Sample Collection Method = " + sampleCollectionMethod + '\n' +
                "Category(ies) = " + parameterCategory + '\n' +
                "Parameter(s) = " + parameter + '\n' +
                "Registration Date = " + registrationDate + '\n' +
                "Sample Collection Date = " + samplesCollectionDate+ '\n' +
                "Chemical Analysis Date = " + chemicalAnalysisDate + '\n' +
                "Diagnosis Date = " + diagnosisDate + '\n' +
                "Validation Date = " + validationDate + '\n';
    }
    public String getWorkDatesString(){
        return "Test code:" + internalCode +" Registration Date:"+registrationDate+" Chemical Analysis Date:"+chemicalAnalysisDate+" Diagnosis Date:" + diagnosisDate;
    }

	public void setSamplesCollectionDate(Date date) {
		this.samplesCollectionDate = date;
	}
}
