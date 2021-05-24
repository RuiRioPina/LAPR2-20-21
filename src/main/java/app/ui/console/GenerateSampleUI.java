package app.ui.console;

import java.util.Scanner;

import app.controller.GenerateSampleController;

public class GenerateSampleUI implements Runnable {
	
	private GenerateSampleController generateSampleController;
	
	public GenerateSampleUI() {
		this.generateSampleController = new GenerateSampleController();
	}

	@Override
	public void run() {
		Scanner x = new Scanner(System.in);
		int numberOfSamples = 0;
		
		System.out.println("Beginning to generate a sample.\n");
		
		System.out.println("Select the number of sample to generate: ");
		numberOfSamples = x.nextInt();
	}

}
