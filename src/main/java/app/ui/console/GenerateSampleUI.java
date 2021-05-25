package app.ui.console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import app.controller.GenerateSampleController;
import app.domain.model.Test;
import app.ui.console.utils.Utils;

public class GenerateSampleUI implements Runnable {
	
	private GenerateSampleController generateSampleController;
	
	public GenerateSampleUI() {
		this.generateSampleController = new GenerateSampleController();
	}

	@Override
	public void run() {
		Scanner x = new Scanner(System.in);
		int numberOfSamples = 0;
		List<String> tests = new ArrayList<String>();
		
		System.out.println("Beginning to generate a sample.\n");
		List<Test> lt = this.generateSampleController.getTestsRegistered();
		int option = 0;
        option = Utils.showAndSelectIndex(lt, "Select test.");

        if ( (option >= 0) && (option < lt.size()))
        {
            tests.add(lt.get(option).getInternalCode());
            lt.remove(option);
        }
		
		System.out.println("Select the number of sample to generate: ");
		numberOfSamples = x.nextInt();
	}

}
