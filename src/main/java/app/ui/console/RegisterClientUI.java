
package app.ui.console;

import app.controller.RegisterClientController;
import app.domain.model.Client;
import app.domain.store.ClientList;
import auth.AuthFacade;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RegisterClientUI implements Runnable {
    public RegisterClientUI() {
        // Do nothing because there is no need to construct the UI layer with anything. This is only used to be able to use the UI when selecting in menus.
    }

    public void run() {
        RegisterClientController registerClientController = new RegisterClientController();
        ClientList clientList;
        long tin = 0L;
        long phoneNumber = 0L;
        long nhsNumber = 0L;
        long ccn = 0L;
        boolean result = false;
        new AuthFacade();
        String sex = "";
        String birthDate = "";
        String email = "";
        String name;
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("Welcome to the Client Registration Page!");
        System.out.println();

        do {
            try {
                System.out.println("Enter the Client's Citizen's card number (16 digits):");
                ccn = sc.nextLong();
                result = registerClientController.validateCcn(ccn);
                if (!result) {
                    System.out.println("The Client's Citizen's card number needs to have 16 digits. Please try again.");
                }
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

                result = registerClientController.validateNhsNumber(nhsNumber);

                if (!result) {
                    System.out.println("The NHS number needs to have 10 digits. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("The NHS number can only have numbers. Please try again.");
                sc.nextLine();
            }
        } while (!result);

        sc.nextLine();
        result = false;

        do {
            try {
                System.out.println("Enter the Client's birth date(DD-MM-YYYY): ");
                birthDate = sc.nextLine();
                result = registerClientController.validateBirthDate(birthDate);

            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("All information asked must be inputted. Please insert a day of birth, month and year asked in the format (DD-MM-YYYY).");
            } catch (NumberFormatException e) {
                System.out.println("The was a problem while introducing the data. Please try again.");
            }
        } while (!result);

        result = false;

        do {
            try {
                System.out.println("Enter the Client's sex (optional: press enter): ");
                sex = sc.nextLine();
                result = registerClientController.validateSex(sex);
                if (!result) {
                    System.out.println("You have introduced an invalid sex. Please try again");
                }
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
                result = registerClientController.validateTin(tin);
                if (!result) {
                    System.out.println("The TIN needs to have 10 digits. Please try again");
                }

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

                result = registerClientController.validatePhoneNumber(phoneNumber);

                if (!result) {
                    System.out.println("The phone number needs to have 11 digits. Please try again");

                }
            } catch (InputMismatchException e) {
                System.out.println("The phone number can only have numbers. Please try again.");
                sc.nextLine();
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


        do {
            System.out.println("Enter the Client's name: ");
            name = sc.nextLine();

            result = registerClientController.validateName(name);

        } while (!result);

        Client clt;
        if (sex.trim().isEmpty()) {
            clt = registerClientController.createClient(ccn, nhsNumber, birthDate, tin, phoneNumber, email, name);
        } else {
            clt = registerClientController.createClient(ccn, nhsNumber, birthDate, sex, tin, phoneNumber, email, name);
        }

        registerClientController.showClient(clt);

        do {
            result = true;
            System.out.println("Do you confirm this data? (y/n)");
            String response = sc.nextLine();
            if (response.equalsIgnoreCase("y") || response.equalsIgnoreCase("yes")) {
                try {
                    clientList = registerClientController.getClientList();
                    for (Client c : clientList.getClients()) {
                        System.out.println(c);
                    }

                    if (registerClientController.saveClient(clt) && clt != null) {
                        System.out.println("The Client was sucessfully added!");
                        registerClientController.sendEmailToClient(clt);
                    }
                } catch (IOException | InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            } else if (response.equalsIgnoreCase("n") || response.equalsIgnoreCase("no")) {
                result = true;
            } else {
                System.out.println("Please insert a valid option (Y/N)");
                result = false;

            }
        } while (!result);
    }
}
