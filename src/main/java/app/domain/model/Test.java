package app.domain.model;

import app.domain.store.ParameterStore;
import app.domain.store.ResultOfTestStore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Test {


    private String nhsCode;
    private String internalCode;
    private Client client;
    private String sampleCollectionMethod;
    private List<ParameterCategory> parameterCategory;
    private TestType testType = new TestType("blood", "blood", "dirpa", parameterCategory);

    private List<Parameter> parameter = new ArrayList<>();
    private Date registrationDate;
    private List<Sample> samples;
    private Date samplesCollectionDate;
    private Date chemicalAnalysisDate;
    private Date diagnosisDate;
    private Date validationDate;
    ResultOfTestStore resultOfTestStore = new ResultOfTestStore();


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
        this.samples = samples;
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

    /**
     * Returns the test samples.
     *
     * @return samples of the test.
     */
    public List<Sample> getSamples() {
        return samples;
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
                "Sample Collection Date = " + samplesCollectionDate + '\n' +
                "Chemical Analysis Date = " + chemicalAnalysisDate + '\n' +
                "Diagnosis Date = " + diagnosisDate + '\n' +
                "Validation Date = " + validationDate + '\n';
    }

    public String getWorkDatesString() {
        return "Test code:" + internalCode + " Registration Date:" + registrationDate + " Chemical Analysis Date:" + chemicalAnalysisDate + " Diagnosis Date:" + diagnosisDate;
    }


    private long barcode;

    public Test(ParameterStore parameterStore) {
    }

    public long getBarcode() {
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
    

    public Test() {

        barcode = 123456789012L;
        List<ParameterCategory> parameterCategories = new ArrayList<>();
        ParameterCategory p1 = new ParameterCategory("54321", "HEMOGRAM");
        parameterCategories.add(p1);
        parameter.add(new Parameter("HB000", "HB", "Haemoglobin", parameterCategories));
        parameter.add(new Parameter("WBC00", "WBC", "White Cell Count", parameterCategories));
        parameter.add(new Parameter("PLT00", "PLT", "Platelet Count", parameterCategories));
        parameter.add(new Parameter("RBC00", "RBC", "Red Blood Count", parameterCategories));
        parameter.add(new Parameter("MCV00", "MCV", "Mean Cell Volume", parameterCategories));
        parameter.add(new Parameter("MCH00", "MCH", "MC Haemoglobin", parameterCategories));
        parameter.add(new Parameter("MCHC0", "MCHC", "MCHaemoglobinConcen", parameterCategories));
        parameter.add(new Parameter("ESR00", "ESR", "ErythSedimenRate", parameterCategories));
        parameter.add(new Parameter("IgGAN", "IgC", "Antibodies", parameterCategories));
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

}
