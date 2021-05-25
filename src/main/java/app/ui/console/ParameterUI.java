package app.ui.console;


import app.controller.ParameterController;
import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParameterUI implements Runnable{
    /**
     * Class to create the UI used to specify a new Parameter and categorize it.
     */
    private ParameterController parameterController;

    /**
     * Constructor for the UI to create the corresponding controller.
     */
    public ParameterUI() {
        this.parameterController = new ParameterController();
    }

    /**
     * Run method that presents the UI to the user.
     */
    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        String code = "";
        String shortname = "";
        String description = "";
        List<String> parameterCategoryCodes = new ArrayList<>();
        boolean validation = false;

        System.out.println("Beginning to specify a new parameter and categorize it \n");

        while (!validation) {
            System.out.println("Insert the code of the parameter.");
            code = sc.nextLine();
            validation = checkCodeRules(code);
            if (!validation) {
                System.out.println("The code is invalid");
            }
        }
        validation = false;

        while (!validation) {
            System.out.println("Insert the short name of the parameter.");
            shortname = sc.nextLine();
            validation = checkShortnameRules(shortname);
            if (!validation) {
                System.out.println("The shortname is invalid");
            }
        }
        validation = false;

        while (!validation) {
            System.out.println("Insert the description of the parameter.");
            description = sc.nextLine();
            validation = checkDescriptionRules(description);
            if (!validation) {
                System.out.println("The description is invalid");
            }
        }
        validation = false;

        while (!validation) {
            List<ParameterCategory> list = this.parameterController.getParameterCategories();
            int option = 0;

                option = Utils.showAndSelectIndex(list, "Select parameter category.");

                if ((option >= 0) && (option < list.size())) {
                    parameterCategoryCodes.add(list.get(option).getCode());
                    list.remove(option);
                }

            validation = checkParameterCategoryRules(parameterCategoryCodes);
            if (!validation) {
                System.out.println("The Parameter Category list is invalid");
            }
        }

        Parameter p;
        try {
            p = this.parameterController.createParameter(code, shortname, description, parameterCategoryCodes);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            return;
        }

        System.out.println("Confirmation: %n");
        System.out.printf("-Code: %s %n", p.getCode());
        System.out.printf("-Shortname: %s %n", p.getShortname());
        System.out.printf("-Description: %s %n", p.getDescription());
        System.out.println("-Parameter Category: ");
        for (ParameterCategory pc : p.getPc()) {
            System.out.printf("\t %s - %s %n", pc.getCode(), pc.getName());
        }

        if (!Utils.confirm("Confirm parameter creation (s/n)?")) {
            return;
        }

        try {
            this.parameterController.saveParameter(p);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Checks parameter code rules.
     * @param code - code of the parameter.
     * @return validation - the validation.
     */
        private boolean checkCodeRules(String code) {
            boolean validation = true;
            if (code.length() != 5) {
                validation = false;
            }

            if (!code.matches("^[a-zA-Z0-9]*$")) {
                validation = false;
            }

            return validation;
        }

    /**
     * Checks parameter short name rules.
     * @param shortname - shortname of the parameter.
     * @return validation - the validation.
     */
        private boolean checkShortnameRules(String shortname) {
            boolean validation = true;
            if ((shortname.length() < 1) || (shortname.length() > 8)) {
                validation = false;
            }

            return validation;
        }

    /**
     * Checks parameter description rules.
     * @param description - description of the parameter.
     * @return validation - the validation.
     */
        private boolean checkDescriptionRules(String description) {
            boolean validation = true;
            if ((description.length()) < 1 || description.length() > 20) {
                validation = false;
            }

            return validation;
        }

    /**
     * Checks parameter category rules.
     * @param parameterCategories - parameter category.
     * @return validation - the validation.
     */
        private boolean checkParameterCategoryRules(List<String> parameterCategories) {
            boolean validation = true;
            if (parameterCategories.isEmpty()) {
                validation = false;
            }

            return validation;
        }
    }

