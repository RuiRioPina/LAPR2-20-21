package app.ui.console;

import app.controller.ValidateWorkController;
import app.ui.console.utils.Utils;

import java.util.Scanner;

public class ValidateWorkUI implements Runnable {
    private ValidateWorkController validateWorkController;
    public ValidateWorkUI(){
        this.validateWorkController=new ValidateWorkController();
    }
    @Override
    public void run(){
        String confirmation="";
        String selectedTests="";
        boolean validation=false;
        if (validateWorkController.showUnvalidatedTests()) {
            System.out.println("Please type the numbers of the tests you wish to validate(separated by a space(or type ALL to validate all of them)");
            Scanner sc = new Scanner(System.in);
            while (!validation) {
                selectedTests = sc.nextLine();
                validation = checkStringRules(selectedTests);
                if (!checkStringRules(selectedTests)){
                    System.out.println("Input has wrong format. Please try again.");
                }
            }
            validateWorkController.askConfirmation(selectedTests);
            if (!Utils.confirm("Are you sure you want to confirm the previous tests(y/n)?")) {
                return;
            }
            validateWorkController.validateTests(selectedTests);
        }
//validateWorkController.showAllTests(); DEBUGGING FEATURE
    }
    private boolean checkStringRules(String inputString){
        return inputString.matches("^[0-9\\s ]*$")||inputString.matches("ALL");

    }
}
