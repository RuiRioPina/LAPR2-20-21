package app.controller;

import java.util.List;

import app.domain.model.Company;
import app.domain.model.ParameterCategory;
import app.domain.model.Sample;
import app.domain.model.Test;
import app.domain.store.TestStore;

public class GenerateSampleController {
	
	private Company company;
	private Sample sample;

	public GenerateSampleController() {
		this.company = App.getInstance().getCompany();
	}
	
	public List<Test> getTestsWithoutSamples() {
		TestStore ts = this.company.getTestStore();
		return ts.getTestsWithoutSamples();
	}

	public void createSamples(String testCode, int numberOfSamples) throws Exception {
		TestStore ts = this.company.getTestStore();
		Test t = ts.getTestByInternalCode(testCode);
		for(int i = 0; i < numberOfSamples; i++) {
			String barcode = this.company.getNextBarcode();
			BarcodeController.generateBarcode(barcode);
			Sample s = ts.createSample(barcode);
			ts.saveSample(t, s);
		}
	}

}
