package app.ui.console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import app.controller.TestTypeController;
import app.domain.model.ParameterCategory;
import app.domain.model.TestType;
import app.ui.console.utils.Utils;

public class TestTypeUI implements Runnable {

	private TestTypeController testTypeController;
	
	public TestTypeUI() {
		this.testTypeController = new TestTypeController();
	}
	
	public void run() {
		Scanner x = new Scanner(System.in);
		System.out.println("Beginning to register a new type of Test.\n");
		System.out.println("Insert the code of the test type.");
		String code = x.nextLine();
		System.out.println("Insert the description of the test type.");
		String description = x.nextLine();
		System.out.println("Insert the collecting method of the test type.");
		String collectingMethod = x.nextLine();
		
		List<String> parameterCategoryCodes = new ArrayList<String>();
		
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
        
        TestType tt;
        try {
        	tt = this.testTypeController.createTestType(code, description, collectingMethod, parameterCategoryCodes);
        } catch (IllegalArgumentException ex) {
        	System.out.println(ex.getMessage());
        	return;
        }
        
        System.out.println("Confirmation: \n");
        System.out.printf("-Code: %s\n", tt.getCode());
        System.out.printf("-Description: %s\n", tt.getDescription());
        System.out.printf("-Collecting Method: %s\n", tt.getCollectingMethod());
        System.out.println("-Parameter Categories: ");
        for(ParameterCategory pc : tt.getParameterCategories()) {
        	System.out.printf("\t%s - %s\n", pc.getCode(), pc.getName());
        }
        
        if(!Utils.confirm("Confirm test type creation (s/n)?")){
        	return;
        }
        
        try {
        	this.testTypeController.saveTestType();
        } catch (IllegalArgumentException ex) {
        	System.out.println(ex.getMessage());
        }
	}
}
