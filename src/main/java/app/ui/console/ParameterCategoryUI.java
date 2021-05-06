package app.ui.console;

import java.util.Scanner;

import app.controller.ParameterCategoryController;
import app.domain.model.ParameterCategory;
import app.ui.console.utils.Utils;

public class ParameterCategoryUI implements Runnable {

	private ParameterCategoryController parameterCategoryController;
	
	public ParameterCategoryUI() {
		this.parameterCategoryController = new ParameterCategoryController();
	}
	
	public void run() {
		Scanner x = new Scanner(System.in);
		System.out.println("Beginning to register a new Parameter Category.\n");
		System.out.println("Insert the code of the parameter category.");
		String code = x.nextLine();
		System.out.println("Insert the name of the parameter category.");
		String name = x.nextLine();
        
        ParameterCategory pc;
        try {
        	pc = this.parameterCategoryController.createParameterCategory(code, name);
        } catch (IllegalArgumentException ex) {
        	System.out.println(ex.getMessage());
        	return;
        }
        
        System.out.println("Confirmation: \n");
        System.out.printf("-Code: %s\n", pc.getCode());
        System.out.printf("-Name: %s\n", pc.getName());
        
        if(!Utils.confirm("Confirm parameter category creation (s/n)?")){
        	return;
        }
        
        try {
        	this.parameterCategoryController.saveParameterCategory();
        } catch (IllegalArgumentException ex) {
        	System.out.println(ex.getMessage());
        }
	}
}