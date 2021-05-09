package app.ui.console;

import app.controller.App;
import app.controller.RegisterEmployeeController;

import app.domain.model.Employee;
import app.domain.model.Role;




import java.util.ArrayList;
import java.util.Scanner;

public class RegisterEmployeeUI implements Runnable {
    private RegisterEmployeeController registerEmployeeController;

    public RegisterEmployeeUI() {
        this.registerEmployeeController = new RegisterEmployeeController();

    }

    @Override
    public void run() {
        int nEmployees= App.getInstance().getCompany().getNumberOfEmployees();
        int roleIndex;
        nEmployees++;
        App.getInstance().getCompany().setNumberOfEmployees(nEmployees);
        String specialistDoctorIndexNumber=null;
        Scanner sc = new Scanner(System.in);
        System.out.println("What type of Employee do you wish to register?\n Write REC to register a receptionist.\n Write CCT to register a clinical chemistry technologist\n Write MLT to register a medical lab technician\n Write LC to register a laboratory coordinator\n Write SD to register a specialist doctor");
        String employeeRole=sc.nextLine();

        Role role = this.registerEmployeeController.getlRole().get(this.registerEmployeeController.getRoleIndex(employeeRole));
        System.out.println("Please type the name of your employee(Less than 35 characters):");
        String name = sc.nextLine();
        System.out.println("Please type the adress of your employee:");
        String adress = sc.nextLine();
        System.out.println("Please type the Standard Ocupational Code(SOC) of your employee(4 digits):");
        String SOC = sc.nextLine();
        System.out.println("Please type the phone Number of your employee:");
        long phoneNumber = sc.nextLong();
        sc.nextLine();
        System.out.println("Please type the Email of your client:");
        String email = sc.nextLine();
        System.out.println("Please type the user Name of your Employee:");
        String userName = sc.nextLine();
        if (this.registerEmployeeController.getRoleIndex(employeeRole)==4){
            System.out.println("Please type the doctor index number of your Employee(6 digits):");
            specialistDoctorIndexNumber=sc.nextLine();
        }
        Employee e = registerEmployeeController.createEmployee(name, adress, SOC, phoneNumber, email, userName, nEmployees, role,specialistDoctorIndexNumber);
        System.out.println(e);
        System.out.println("Do you wish to add the Employee you've registered? (S/N)");
        String confirmation=sc.nextLine();
if (confirmation.equals("S")||confirmation.equals("s")){
    try {
        registerEmployeeController.saveEmployee(e);
    }catch (IllegalArgumentException exc){
        System.out.println(exc.getMessage());
        System.out.println("Ignore the next message.");
        nEmployees--;

    }


    System.out.println("The employee has been created");
}else {
    System.out.println("The employee was not created.");
    nEmployees--;
}

    }

}
