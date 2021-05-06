//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package app.ui.console;

import app.controller.RegisterClientController;
import app.domain.model.Client;
import auth.AuthFacade;

import java.util.InputMismatchException;
import java.util.Scanner;

public class RegisterClientUI implements Runnable {
    public RegisterClientUI() {
    }

    public void run() {
        RegisterClientController registerClientController = new RegisterClientController();
        long tin = 0L;
        long phoneNumber = 0L;
        long nhsNumber = 0L;
        long ccn = 0L;
        boolean result = false;
        new AuthFacade();
        String sex = "";
        String birthDate = "";
        String email = "";
        String name = "";
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("Welcome to the Client Registration Page!");
        System.out.println();

        do {
            try {
                System.out.println("Enter the Client's Citizen's card number (16 digits):");
                ccn = sc.nextLong();
                registerClientController.validateCcn(ccn);
                result = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("The CCN can only have numbers. Please try again.");
                sc.nextLine();
            }
        } while (!result);

        result = false;

        do {
            try {
                System.out.println("Enter the Client's NHS Number(10 digits): ");
                nhsNumber = sc.nextLong();
                registerClientController.validateNhsNumber(nhsNumber);
                result = true;
            } catch (IllegalArgumentException e) {
                System.out.println("The NHS number has to have 10 digits");
            }
        } while (!result);

        sc.nextLine();
        result = false;

        do {
            try {
                System.out.println("Enter the Client's birth date(DD-MM-YYYY): ");
                birthDate = sc.nextLine();
                registerClientController.validateBirthDate(birthDate);
                result = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("All information asked must be inputted. Please insert a day of birth, month and year asked in the format (DD-MM-YYYY).");
            }
        } while (!result);

        result = false;

        do {
            try {
                System.out.println("Enter the Client's sex (optional: write .): ");
                sex = sc.nextLine();
                registerClientController.validateSex(sex);
                result = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("The Sex can only have letters. Please try again.");
                sc.nextLine();
            }
        } while (!result);

        result = false;

        do {
            try {
                System.out.println("Enter the Client's Tax Identification Number (10 digits): ");
                tin = sc.nextLong();
                registerClientController.validateTin(tin);
                result = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("The TIN can only have numbers. Please try again.");
                sc.nextLine();
            }
        } while (!result);

        result = false;

        do {
            try {
                System.out.println("Enter the Client's phone number (11 digits): ");
                phoneNumber = sc.nextLong();
                registerClientController.validatePhoneNumber(phoneNumber);
                result = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("The phone number can only have numbers. Please try again.");
            }
        } while (!result);

        result = false;
        sc.nextLine();
        do {
            try {
                System.out.println("Enter the Client's email: ");
                email = sc.nextLine();
                registerClientController.validateEmail(email);
                result = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!result);

        result = false;

        do {
            try {
                System.out.println("Enter the Client's name: ");
                name = sc.nextLine();
                registerClientController.validateName(name);
                result = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!result);

        Client clt;
        if (sex.equals(".")) {
            clt = registerClientController.createClient(ccn, nhsNumber, birthDate, tin, phoneNumber, email, name);
        } else {
            clt = registerClientController.createClient(ccn, nhsNumber, birthDate, sex, tin, phoneNumber, email, name);
        }

        registerClientController.showClient(clt);

        do {
            result = true;
            System.out.println("Do you confirm this data? (y/n)");
            String response = sc.nextLine();
            if (!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("yes")) {
                if (!response.equalsIgnoreCase("n") && !response.equalsIgnoreCase("no")) {
                    System.out.println("Please insert a valid option (Y/N)");
                    result = false;
                } else {
                    clt = new Client();
                }
            } else if (clt != null) {
                registerClientController.saveClient(clt);
            } else {
                System.out.println("It was impossible to save it since there was an empty field.");
            }
        } while (!result);

        System.out.println();
        if (registerClientController.isClientInList(clt)) {
            System.out.println("The Client was sucessfully added!");
        } else {
            System.out.println("You either didn't confirm it or there was an error!");
        }

    }
}
