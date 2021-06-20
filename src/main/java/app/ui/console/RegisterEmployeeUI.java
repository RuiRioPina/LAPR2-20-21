package app.ui.console;

import app.controller.App;
import app.controller.RegisterEmployeeController;

import app.domain.model.Employee;
import app.domain.model.Role;
import app.ui.console.utils.Utils;

import java.util.Scanner;

public class RegisterEmployeeUI implements Runnable {
    /**
     * Class to create the UI used to register an Employee.
     */
    private final RegisterEmployeeController registerEmployeeController;

    /**
     * Constructor for the UI to create the corresponding controller.
     */
    public RegisterEmployeeUI() {
        this.registerEmployeeController = new RegisterEmployeeController();

    }

    /**
     * Run method that presents the UI to the user.
     */
    @Override
    public void run() {
        int nEmployees= App.getInstance().getCompany().getNumberOfEmployees();
        nEmployees++;
        App.getInstance().getCompany().setNumberOfEmployees(nEmployees);
        String specialistDoctorIndexNumber=null;
        Scanner sc = new Scanner(System.in);
        Utils.log("What type of Employee do you wish to register?\n Write REC to register a receptionist.\n Write CCT to register a clinical chemistry technologist\n Write MLT to register a medical lab technician\n Write LC to register a laboratory coordinator\n Write SD to register a specialist doctor");
        String employeeRole=sc.nextLine();

        Role role = this.registerEmployeeController.getlRole().get(this.registerEmployeeController.getRoleIndex(employeeRole));
        Utils.log("Please type the name of your employee(Less than 35 characters):");
        String name = sc.nextLine();
        Utils.log("Please type the adress of your employee:");
        String adress = sc.nextLine();
        Utils.log("Please type the Standard Ocupational Code(soc) of your employee(4 digits):");
        String soc = sc.nextLine();
        Utils.log("Please type the phone Number of your employee:");
        long phoneNumber = sc.nextLong();
        sc.nextLine();
        Utils.log("Please type the Email of your client:");
        String email = sc.nextLine();
        Utils.log("Please type the user Name of your Employee:");
        String userName = sc.nextLine();
        if (this.registerEmployeeController.getRoleIndex(employeeRole)==4){
            Utils.log("Please type the doctor index number of your Employee(6 digits):");
            specialistDoctorIndexNumber=sc.nextLine();
        }
        Employee e = registerEmployeeController.createEmployee(name, adress, soc, phoneNumber, email, userName, nEmployees, role,specialistDoctorIndexNumber);
        Utils.log(e.toString());
        Utils.log("Do you wish to add the Employee you've registered? (Y/N)");
        String confirmation=sc.nextLine();
if (confirmation.equals("Y")||confirmation.equals("y")){
    try {
        registerEmployeeController.saveEmployee(e);
    }catch (IllegalArgumentException exc){
        Utils.log(exc.getMessage());
        nEmployees--;
        App.getInstance().getCompany().setNumberOfEmployees(nEmployees);

    }

    String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
    if(email.matches(regex)&& e.validateEmployee()) {
    	Utils.log("The employee has been created");
    }
}else {

    Utils.log("The employee was not created.");
    nEmployees--;
    App.getInstance().getCompany().setNumberOfEmployees(nEmployees);
}

    }

}
