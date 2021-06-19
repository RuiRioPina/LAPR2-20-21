package app.ui.console;

import java.util.List;
import java.util.Scanner;

import app.controller.GenerateSampleController;
import app.domain.model.Test;
import app.ui.console.utils.Utils;

public class GenerateSampleUI implements Runnable {
	
	private final GenerateSampleController generateSampleController;
	
	public GenerateSampleUI() {
		this.generateSampleController = new GenerateSampleController();
	}

	@Override
	public void run() {
		Scanner x = new Scanner(System.in);
		int numberOfSamples = 0;
		String testCode = "";
		
		Utils.log("Beginning to generate a sample.\n");
		List<Test> lt = this.generateSampleController.getTestsWithoutSamples();
		int option = 0;
        option = Utils.showAndSelectIndex(lt, "Select test.");

        if ( (option >= 0) && (option < lt.size()))
        {
            testCode = lt.get(option).getInternalCode();
        }
		if(option == -1) {
			return;
		}
		do {
			Utils.log("Select the number of sample to generate: ");
			numberOfSamples = x.nextInt();
			if(numberOfSamples == 0) {
				Utils.log("Cannot be 0 please try again.");
			} if (numberOfSamples >= 100) {
				Utils.log("Cannot generate that amount of samples please select another number.");
			}
		} while (numberOfSamples == 0 || numberOfSamples >= 100);
		
		Utils.log("Generating Samples");
		try {
			this.generateSampleController.createSamples(testCode, numberOfSamples);
		} catch (Exception ex) {
			Utils.log(ex.getMessage());
			return;
		}
		
		Utils.log("Samples generated with success!");
	}

}
