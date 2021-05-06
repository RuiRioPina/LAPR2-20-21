package app.ui.console;

import app.controller.App;
import app.controller.RegisterEmployeeController;
import app.domain.model.Company;
import app.domain.model.Employee;
import app.domain.model.Role;
import app.domain.store.EmployeeStore;
import app.ui.console.utils.Utils;


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
        nEmployees++;
        App.getInstance().getCompany().setNumberOfEmployees(nEmployees);
        String specialistDoctorIndexNumber=null;
        Scanner sc = new Scanner(System.in);
        ArrayList<Role> lRole = registerEmployeeController.getlRole();
        int option = 0;
        option = Utils.showAndSelectIndex(lRole, "What type of Employee do you wish to register?");
        Role role = this.registerEmployeeController.getlRole().get(option);
        System.out.println("Please type the name of your employee:");
        String name = sc.nextLine();
        try {
            registerEmployeeController.validateNameInput(name);
        }catch (IllegalArgumentException e){

            do {
                System.out.println(e.getMessage());
                name=sc.nextLine();
            }while (name.matches(".*\\d.*"));
        }
        System.out.println("Please type the adress of your employee:");
        String adress = sc.nextLine();
        System.out.println("Please type the Standard Ocupational Code(SOC) of your employee:");
        String SOC = sc.nextLine();
        try {
            registerEmployeeController.validateSOCInput(SOC);
        } catch (IllegalArgumentException e) {
            do {
                System.out.println(e.getMessage());
                SOC = sc.nextLine();
            } while (SOC.length()!=4 && !SOC.matches("[0-9]+"));
        }
        System.out.println("Please type the phone Number of your employee:");
        long phoneNumber = sc.nextLong();
        sc.nextLine();
        System.out.println("Please type the Email of your client:");
        String email = sc.nextLine();
        System.out.println("Please type the user Name of your Employee:");
        String userName = sc.nextLine();
        if (option==4){
            System.out.println("Please type the doctor index number of your Employee:");
            specialistDoctorIndexNumber=sc.nextLine();
        }
        Employee e = registerEmployeeController.createEmployee(name, adress, SOC, phoneNumber, email, userName, nEmployees, role);
        System.out.println(e);
        System.out.println("Do you wish to add the Employee you've registered? (S/N)");
        String confirmation=sc.nextLine();
if (confirmation.equals("S")||confirmation.equals("s")){
    registerEmployeeController.saveEmployee(e);
    System.out.println("The employee has been created");
}else System.out.println("The employee was not created.");

        // method for debugging purposes.EmployeeStore.printStore(App.getInstance().getCompany().getEmployeeStore());
    }

}
