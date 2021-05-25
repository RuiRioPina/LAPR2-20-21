package app.domain.store;

import app.domain.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestStore {

    private final List<Test> test;

    public TestStore() {
        this.test = new ArrayList<>();
    }

    public Test createTest(String nhsCode, String internalCode, Client client, TestType testType, String sampleCollectionMethod,
                           List<ParameterCategory> parameterCategory, List<Parameter> parameter, Date registrationDate) {

        return new Test(nhsCode, internalCode, client, testType, sampleCollectionMethod,
                parameterCategory, parameter,registrationDate);
    }

    public void saveTest(Test t) {
        validateTest(t);
        addTest(t);
    }

    private void validateTest(Test t) {
        checkNhsCode(t.getNhsCode());
    }

    private void checkNhsCode(String nhsCode) {
        if (!nhsCode.matches("[A-Za-z0-9]+")) {
            throw new IllegalArgumentException("Code must be alphanumeric.");
        }
        if (nhsCode.length() != 12)
            throw new IllegalArgumentException("Code must have 12 chars.");
    }

    private void addTest(Test t) {
        this.test.add(t);
    }

    public List<Test> getTests() {
        List<Test> tests = new ArrayList<>();
        tests.addAll(this.test);
        return tests;
    }

	public List<Test> getTestsWithoutSamples() {
		List<Test> result = new ArrayList<Test>();
		for(Test t : this.test) {
			if(t.getSamplesCollectionDate() == null) {
				result.add(t);
			}
		}
		return result;
	}

	public Test getTestByInternalCode(String testCode) {
		for(Test t : this.test) {
			if(t.getInternalCode().equals(testCode)) {
				return t;
			}
		}
		return null;
	}

	public Sample createSample(String barcode) {
		return new Sample(barcode);
	}

	public void saveSample(Test t, Sample s) {
		validateSample(s);
        addSample(t, s);
	}
	
	private void validateSample(Sample s) {
        if (!s.getBarcode().matches("[0-9]+")) {
            throw new IllegalArgumentException("Barcode must be numeric.");
        }
        if (s.getBarcode().length() != 11) {
            throw new IllegalArgumentException("Barcode must have 11 chars.");
        }
	}
	
	private void addSample(Test t, Sample s) {
		t.getSamples().add(s);
		t.setSamplesCollectionDate(new Date(System.currentTimeMillis()));
	}
}
