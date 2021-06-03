
package app.ui.console;

import app.controller.RegisterClientController;
import app.domain.model.Client;
import auth.AuthFacade;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RegisterClientUI implements Runnable {


    private final RegisterClientController registerClientController = new RegisterClientController();

    private boolean passedValidation;
    Client clt;


    private static final Scanner sc = new Scanner(System.in);

    public RegisterClientUI() {
        // Do nothing because there is no need to construct the UI layer with anything. This is only used to be able to use the UI when selecting in menus.
    }

    public void run() {

        long ccn;
        long tin;
        long phoneNumber;
        long nhsNumber;

        new AuthFacade();
        String sex;
        String birthDate;
        String email;
        String name;

        System.out.println();
        System.out.println("Welcome to the Client Registration Page!");
        System.out.println();


        ccn = inputCcn();

        nhsNumber = inputNhsNumber();

        sc.nextLine();

        birthDate = inputBirthDate();

        sex = inputSex();

        tin = inputTin();

        phoneNumber = inputPhoneNumber();

        sc.nextLine();

        email = inputEmail();

        name = inputName();

        if (sex.trim().isEmpty()) {
            clt = registerClientController.createClient(ccn, nhsNumber, birthDate, phoneNumber, tin, email, name);
        } else {
            clt = registerClientController.createClient(ccn, nhsNumber, birthDate, sex, phoneNumber, tin, email, name);
        }

        registerClientController.showClient(clt);

        confirmResult();

    }

    private long inputCcn() {
        long ccn = 0L;

        do {
            try {
                passedValidation = true;
                System.out.println("Enter the Client's Citizen's card number (16 digits):");
                ccn = sc.nextLong();
                registerClientController.validateCcn(ccn);

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
                passedValidation = false;
            } catch (InputMismatchException e) {
                System.out.println("The ccn can only have numbers. Please try again.");
                sc.nextLine();
                passedValidation = false;
            }
        } while (!passedValidation);

        return ccn;
    }

    private long inputNhsNumber() {
        long nhsNumber = 0L;
        do {
            try {
                passedValidation = true;
                System.out.println("Enter the Client's NHS Number(10 digits): ");
                nhsNumber = sc.nextLong();

                registerClientController.validateNhsNumber(nhsNumber);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
                passedValidation = false;
            } catch (InputMismatchException e) {
                System.out.println("The ccn can only have numbers. Please try again.");
                sc.nextLine();
                passedValidation = false;
            }
        } while (!passedValidation);
        return nhsNumber;
    }

    private String inputBirthDate() {
        String birthDate = "";
        do {
            try {
                passedValidation = true;
                System.out.println("Enter the Client's birth date(DD-MM-YYYY): ");
                birthDate = sc.nextLine();
                registerClientController.validateBirthDate(birthDate);

            } catch (IllegalArgumentException e) {
                passedValidation = false;
                System.out.println(e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("There was an error! Please try again with other values");
            }
        } while (!passedValidation);
        return birthDate;
    }

    private String inputSex() {
        String sex = "";
        do {
            try {
                passedValidation = true;
                System.out.println("Enter the Client's sex (optional: press enter): ");
                sex = sc.nextLine();
                registerClientController.validateSex(sex);
            } catch (IllegalArgumentException e) {
                passedValidation = false;
                System.out.println(e.getMessage());
            }
        } while (!passedValidation);
        return sex;
    }

    private long inputTin() {
        long tin = 0L;
        do {
            try {
                passedValidation = true;
                System.out.println("Enter the Client's Tax Identification Number (10 digits): ");
                tin = sc.nextLong();
                registerClientController.validateTin(tin);
            } catch (IllegalArgumentException e) {
                passedValidation = false;
                System.out.println(e.getMessage());
                sc.nextLine();
            } catch (InputMismatchException e) {
                passedValidation = false;
                System.out.println("You have introduce letters. Please try again with only numbers.");
                sc.nextLine();
            }
        } while (!passedValidation);
        return tin;
    }

    private long inputPhoneNumber() {
        long phoneNumber = 0L;
        do {
            try {
                passedValidation = true;
                System.out.println("Enter the Client's phone number (11 digits): ");
                phoneNumber = sc.nextLong();

                registerClientController.validatePhoneNumber(phoneNumber);

            } catch (IllegalArgumentException e) {
                passedValidation = false;
                System.out.println(e.getMessage());
                sc.nextLine();
            } catch (InputMismatchException e) {
                passedValidation = false;
                System.out.println("You have introduce letters. Please try again with only numbers.");
                sc.nextLine();
            }

        } while (!passedValidation);
        return phoneNumber;
    }

    private String inputEmail() {
        String email = "";
        do {
            try {
                System.out.println("Enter the Client's email: ");
                email = sc.nextLine();
                registerClientController.validateEmail(email);
                passedValidation = true;
            } catch (IllegalArgumentException e) {
                passedValidation = false;
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                passedValidation = false;
            }
        } while (!passedValidation);
        return email;
    }

    private String inputName() {
        String name = "";
        do {
            try {
                System.out.println("Enter the Client's name: ");
                name = sc.nextLine();
                registerClientController.validateName(name);
                passedValidation = true;
            } catch (IllegalArgumentException e) {
                passedValidation = false;
                System.out.println(e.getMessage());
            }
        } while (!passedValidation);
        return name;
    }

    public void confirmResult() {

        do {
            passedValidation = true;
            System.out.println("Do you confirm this data? (y/n)");
            String response = sc.nextLine();
            if (response.equalsIgnoreCase("y") || response.equalsIgnoreCase("yes")) {
                try {
                    if (registerClientController.saveClient(clt) && clt != null) {
                        System.out.println("The Client was sucessfully added!");
                        registerClientController.sendEmailToClient(clt);
                    }
                } catch (IOException | InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Please insert a valid option (Y/N)");
                passedValidation = false;
            }
        } while (!passedValidation);
    }


}
