package app.ui.console;

import java.util.Scanner;

import app.controller.ParameterCategoryController;
import app.domain.model.ParameterCategory;
import app.ui.console.utils.Utils;

public class ParameterCategoryUI implements Runnable {

	private final ParameterCategoryController parameterCategoryController;
	
	public ParameterCategoryUI() {
		this.parameterCategoryController = new ParameterCategoryController();
	}
	
	public void run() {
		Scanner x = new Scanner(System.in);
		Utils.log("Beginning to register a new Parameter Category.\n");
		Utils.log("Insert the code of the parameter category.");
		String code = x.nextLine();
		Utils.log("Insert the name of the parameter category.");
		String name = x.nextLine();
        
        ParameterCategory pc;
        try {
        	pc = this.parameterCategoryController.createParameterCategory(code, name);
        } catch (IllegalArgumentException ex) {
        	Utils.log(ex.getMessage());
        	return;
        }
        
        Utils.log("Confirmation: %n");
        Utils.log(String.format("-Code: %s", pc.getCode()));
        Utils.log(String.format("-Name: %s", pc.getName()));
        
        if(!Utils.confirm("Confirm parameter category creation (y/n)?")){
        	return;
        }
        
        try {
        	this.parameterCategoryController.saveParameterCategory();
        } catch (IllegalArgumentException ex) {
        	Utils.log(ex.getMessage());
        }
	}
}