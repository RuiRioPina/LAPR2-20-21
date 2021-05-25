package app.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import app.domain.store.ParameterStore;
import app.domain.store.ResultOfTestStore;

public class Test {

    /**
     * Object oriented Class to the registration of a test.
     */
    private String nhsCode;
    private String internalCode;
    private Client client;
    private TestType testType;
    private String sampleCollectionMethod;
    private List <ParameterCategory>  parameterCategory;
    private List<Parameter> parameter = null;
    private Date registrationDate;
    private List <Sample> samples;
    private Date samplesCollectionDate;
    private Date chemicalAnalysisDate;
    private Date diagnosisDate;
    private Date validationDate;
    private ResultOfTestStore resultOfTestStore = new ResultOfTestStore();

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
        this.client = new Client();
		this.internalCode = "";
		this.nhsCode = "";
		this.parameterCategory = null;
		this.registrationDate = new Date();
		this.sampleCollectionMethod = "";
		this.testType = null;
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

	private long barcode;

	public Test(ParameterStore parameterStore) {
		this.client = new Client();
		this.internalCode = "";
		this.nhsCode = "";
		this.parameterCategory = null;
		this.registrationDate = new Date();
		this.sampleCollectionMethod = "";
		this.testType = null;
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
