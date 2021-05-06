package app.ui.console;


import app.controller.ParameterController;
import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParameterUI implements Runnable{

    private ParameterController parameterController;

    public ParameterUI() {
        this.parameterController = new ParameterController();
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Beginning to specify a new parameter and categorize it \n");
        System.out.println("Insert the code of the parameter.");
        String code = sc.nextLine();
        System.out.println("Insert the shortname of the parameter.");
        String shortname = sc.nextLine();
        System.out.println("Insert the description of the parameter.");
        String description = sc.nextLine();

        List<String> parameterCategoryCodes = new ArrayList<String>();

        List<ParameterCategory> list = this.parameterController.getParameterCategories();

        int option = 0;

            option = Utils.showAndSelectIndex(list, "Select parameter category.");

            if ( (option >= 0) && (option < list.size()))
            {
                parameterCategoryCodes.add(list.get(option).getCode());
                list.remove(option);
            }

        Parameter p;
        try {
            p = this.parameterController.createParameter(code, shortname,description, parameterCategoryCodes);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            return;
        }

        System.out.println("Confirmation: \n");
        System.out.printf("-Code: %s\n", p.getCode());
        System.out.printf("-Shortname: %s\n", p.getShortname());
        System.out.printf("-Description: %s\n", p.getDescription());
        System.out.println("-Parameter Category: ");
        for(ParameterCategory pc : p.getPc()) {
            System.out.printf("\t%s - %s\n", pc.getCode(), pc.getName());
        }

        if(!Utils.confirm("Confirm parameter creation (s/n)?")){
            return;
        }

        try {
            this.parameterController.saveParameter();
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
