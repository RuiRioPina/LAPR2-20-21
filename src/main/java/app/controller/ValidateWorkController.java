package app.controller;

import app.domain.model.Company;
import app.domain.model.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Controller class for the functionality of validating work done by the employees[US-15]
 */
public class ValidateWorkController {

    private Company company;

    /**
     * Constructor for the controller (uses the company that is being used by the application).
     */
    public ValidateWorkController(){
        this.company=App.getInstance().getCompany();
    }

    /**
     * Prints out the list of unvalidated tests in the company with a number before it to indicate the selection of each test.
     */
    public boolean showUnvalidatedTests(){
        List<Test> lTests= App.getInstance().getCompany().getTestStore().getUnvalidatedTests();
        if (!lTests.isEmpty()) {
            for (int i = 0; i < lTests.size(); i++) {
                int numToShow = i + 1;
                System.out.println(numToShow + " " + lTests.get(i));
            }
            return true;
        }else {
            System.out.println("There are no tests to be shown");
            return false;
        }
    }

    /**
     * Asks the user for confirmation of the test.
     * @param selectedTestString- String used to get the tests that were selected
     */
    public void askConfirmation(String selectedTestString){
        List<Test> lTestsToBeValidated=getTestsToBeValidated(selectedTestString);
        for (int i =0;i<lTestsToBeValidated.size();i++){
            System.out.println("Internal Code: "+ lTestsToBeValidated.get(i).getInternalCode());
        }

    }

    /**
     * Utilizes a string that contains the numbers correspondent to the shown tests to create a list with only the selected Test Objects.
     * If the word "ALL" is typed it selects all of the tests.
     * @param selectedTestString- String that contains the numbers of the test separated by spaces or the word "ALL".
     * @return - List of the tests selected by the String.
     */
    public List<Test> getTestsToBeValidated(String selectedTestString){
        List<Test> lTests=App.getInstance().getCompany().getTestStore().getUnvalidatedTests();
        if (selectedTestString.equals("ALL")){
            return lTests;
        }
        List<Test> lTestsToBeValidated=new ArrayList<>();
        String[] arrString= selectedTestString.split(" ");
        int [] arrInt= new int[arrString.length];
        for (int i=0;i<arrString.length;i++){
            arrInt[i]=Integer.parseInt(arrString[i])-1;
        }
        for (int i=0;i<arrInt.length;i++){
            lTestsToBeValidated.add(lTests.get(arrInt[i]));
        }
        return lTestsToBeValidated;
    }

    /**
     * Method that changes the validationDate Attribute of the objects of a List<Test>.
     * @param selectedTestString
     */
    public void validateTests(String selectedTestString){
        Calendar currentDate= Calendar.getInstance();
        List<Test> lTestsToBeValidated=getTestsToBeValidated(selectedTestString);
        App.getInstance().getCompany().getTestStore().validateTests(lTestsToBeValidated,currentDate);
    }
    public boolean validateTestValidationInput(String confirmationString)throws IllegalArgumentException{
        if (confirmationString.equals("Y")||confirmationString.equals("y")||confirmationString.equals("N")||confirmationString.equals("n")){
            return true;
        }
        else throw new IllegalArgumentException("You did not type a valid Input");

    }

}
