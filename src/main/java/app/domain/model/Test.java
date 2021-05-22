package app.domain.model;

import java.util.Date;
import java.util.List;

public class Test {

    private String nhsCode;
    private String internalCode;
    private Client client;
    private TestType testType;
    private String sampleCollectionMethod;
    private List <ParameterCategory>  parameterCategory;
    private List <Parameter> parameter;
    private Date registrationDate;
    private Date samplesCollectionDate;
    private Date chemicalAnalysisDate;
    private Date diagnosisDate;
    private Date validationDate;


    public Test(String nhsCode, String internalCode, Client client, TestType testType, String sampleCollectionMethod,
                List<ParameterCategory> parameterCategory, List<Parameter> parameter, Date registrationDate,
                Date samplesCollectionDate, Date chemicalAnalysisDate, Date diagnosisDate, Date validationDate) {
        this.nhsCode = nhsCode;
        this.internalCode = internalCode;
        this.client = client;
        this.testType = testType;
        this.sampleCollectionMethod = sampleCollectionMethod;
        this.parameterCategory = parameterCategory;
        this.parameter = parameter;
        this.registrationDate = registrationDate;
        this.samplesCollectionDate = samplesCollectionDate;
        this.chemicalAnalysisDate = chemicalAnalysisDate;
        this.diagnosisDate = diagnosisDate;
        this.validationDate = validationDate;
    }

    public String getNhsCode() {
        return nhsCode;
    }

    public String getInternalCode() {
        return internalCode;
    }

    public Client getClient() {
        return client;
    }

    public TestType getTestType() {
        return testType;
    }

    public String getSampleCollectionMethod() {
        return sampleCollectionMethod;
    }

    public List<ParameterCategory> getParameterCategory() {
        return parameterCategory;
    }

    public List<Parameter> getParameter() {
        return parameter;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public Date getSamplesCollectionDate() {
        return samplesCollectionDate;
    }

    public Date getChemicalAnalysisDate() {
        return chemicalAnalysisDate;
    }

    public Date getDiagnosisDate() {
        return diagnosisDate;
    }

    public Date getValidationDate() {
        return validationDate;
    }

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
}
