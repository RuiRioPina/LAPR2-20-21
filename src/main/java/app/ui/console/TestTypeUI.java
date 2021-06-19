package app.ui.console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import app.controller.TestTypeController;
import app.domain.model.ParameterCategory;
import app.domain.model.TestType;
import app.ui.console.utils.Utils;

public class TestTypeUI implements Runnable {

	private final TestTypeController testTypeController;
	
	public TestTypeUI() {
		this.testTypeController = new TestTypeController();
	}
	
	public void run() {
		Scanner x = new Scanner(System.in);
		String code = "";
		String description = "";
		String collectingMethod = "";
		List<String> parameterCategoryCodes = new ArrayList<>();
		boolean validation = false;
		
		Utils.log("Beginning to register a new type of Test.\n");
		
		while(!validation) {
			Utils.log("Insert the code of the test type.");
			code = x.nextLine();
			validation = checkCodeRules(code);
			if(!validation) {
				Utils.log("The code is invalid");
			}
		}
		validation = false;
		
		while(!validation) {
			Utils.log("Insert the description of the test type.");
			description = x.nextLine();
			validation = checkDescriptionRules(description);
			if(!validation) {
				Utils.log("The description is invalid");
			}
		}
		validation = false;
		
		while(!validation) {
			Utils.log("Insert the collecting method of the test type.");
			collectingMethod = x.nextLine();
			validation = checkCollectingMethodRules(collectingMethod);
			if(!validation) {
				Utils.log("The collecting method is invalid");
			}
		}
		validation = false;
		
		while(!validation) {
			List<ParameterCategory> list = this.testTypeController.getParameterCategories();
			int option = 0;
	        do
	        {
	            option = Utils.showAndSelectIndex(list, "Select parameter category.");
	
	            if ( (option >= 0) && (option < list.size()))
	            {
	                parameterCategoryCodes.add(list.get(option).getCode());
	                list.remove(option);
	            }
	        }
	        while (option != -1 && !list.isEmpty());
	        validation = checkParameterCategoryRules(parameterCategoryCodes);
	        if(!validation) {
				Utils.log("The Parameter Category list is invalid");
			}
		}

        TestType tt;
        try {
        	tt = this.testTypeController.createTestType(code, description, collectingMethod, parameterCategoryCodes);
        } catch (IllegalArgumentException ex) {
        	Utils.log(ex.getMessage());
        	return;
        }
        
        Utils.log("Confirmation: %n");
        Utils.log(String.format("-Code: %s", tt.getCode()));
        Utils.log(String.format("-Description: %s", tt.getDescription()));
        Utils.log(String.format("-Collecting Method: %s", tt.getCollectingMethod()));
        Utils.log("-Parameter Categories: ");
        for(ParameterCategory pc : tt.getParameterCategories()) {
        	Utils.log(String.format("\t%s - %s", pc.getCode(), pc.getName()));
        }
        
        if(!Utils.confirm("Confirm test type creation (y/n)?")){
        	return;
        }
        
        try {
        	this.testTypeController.saveTestType();
        } catch (IllegalArgumentException ex) {
        	Utils.log(ex.getMessage());
        }
	}
	
	/**
     * Checks test type code rules.
     * @param code - code of the test type.
     * @return validation - the validation
     */
	private boolean checkCodeRules(String code) {
		boolean validation = true;
        if (code.length() != 5) {
        	validation = false;
        }
        
        if (!code.matches("^[a-zA-Z0-9]*$")) {
        	validation = false;
        }
        
        return validation;
    }
	
	/**
     * Checks test type description rules.
     * @param description - description of the parameter.
     * @return validation - the validation
     */
	private boolean checkDescriptionRules(String description) {
		boolean validation = true;
	    if ((description.length()) < 1 || description.length() > 15) {
	        validation = false;
	    }
	    
	    return validation;
	}
	
	/**
     * Checks test type collecting method rules.
     * @param collectingMethod - collecting method of the test type.
     * @return validation - the validation
     */
	private boolean checkCollectingMethodRules(String collectingMethod) {
		boolean validation = true;
		if ((collectingMethod.length()) < 1 || collectingMethod.length() > 20) {
			validation = false;
		}
		
		return validation;
	}
	
	/**
     * Checks test type parameter categories rules.
     * @param parameterCategories - parameter categories of the test type.
     * @return validation - the validation
     */
	private boolean checkParameterCategoryRules(List<String> parameterCategories) {
		boolean validation = true;
		if (parameterCategories.isEmpty()) {
			validation = false;
		}
		
		return validation;
	}
}
