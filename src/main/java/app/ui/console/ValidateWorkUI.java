package app.ui.console;

import app.controller.ValidateWorkController;

import java.util.Scanner;

public class ValidateWorkUI implements Runnable {
    private ValidateWorkController validateWorkController;
    public ValidateWorkUI(){
        this.validateWorkController=new ValidateWorkController();
    }
    @Override
    public void run(){
        validateWorkController.showUnvalidatedTests();
        System.out.println("Please type the numbers of the tests you wish to validate(separated by a space(or type ALL to validate all of them)");
        Scanner sc= new Scanner(System.in);
        String selectedTests=sc.nextLine();
    }
}
