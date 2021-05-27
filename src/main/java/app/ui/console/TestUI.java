package app.ui.console;

import app.controller.TestController;
import app.domain.model.*;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class TestUI implements Runnable {

    private final TestController testController;
    private final Company cmp;


    public TestUI() {
        this.testController = new TestController();
        this.cmp = new Company("Company");
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        int internalCode = cmp.getTestCode();
        internalCode++;
        cmp.setTestCode(internalCode);
        String testCode = String.format("%012d", internalCode);

        String nhsCode;
        boolean val = false;
        List <Test> test = testController.getTests();
        do{
            System.out.println("Insert test NHS code.");
            nhsCode = sc.nextLine();

            if (nhsCode.length() != 12 ||!nhsCode.matches("^[a-zA-Z0-9]*$")) {
                System.out.println("NHS code has 12 alphanumeric characters. Try again.");
            }

            for(int i=0;i<test.size();i++){
                if (nhsCode.equals(test.get(i).getNhsCode())) {
                    val = true;
                    System.out.println("NHS code is already linked to a test. Try again.");
                    break;
                }else{
                    val = false;
                }
            }
        } while (nhsCode.length() != 12 || !nhsCode.matches("^[a-zA-Z0-9]*$")|| val);



        List<Client> clist = this.testController.getClients();
        Client client = null;
        long tin;
        String str;
        boolean valid = false;
        do {
            System.out.println("Insert client's TIN.");
            tin = sc.nextLong();
            str = "" + tin;

            if (str.length() != 10){
                System.out.println("TIN is a 10 digit number.");
            }

            for (int i = 0; i < clist.size(); i++) {
                if (tin == clist.get(i).getTin()) {
                    client = clist.get(i);
                    valid = true;
                    break;
                } else {
                    System.out.println("There is no client registered with this TIN. Try again.");
                    valid = false;
                }
            }
        } while(str.length() != 10|| !valid);

        String cl = "" + client;
        System.out.println('\n' + cl);
        System.out.println('\n' +"Lab Order Information" + '\n');

        //TEST TYPES
        TestType testtype = null;
        List <TestType> ttlist = this.testController.getTestTypes();

        int opt = 0;
        opt = Utils.showAndSelectIndex(ttlist, "Select the Test Type.");

        if ( (opt >= 0) && (opt < ttlist.size()))
        {
            testtype = ttlist.get(opt);
        }

        assert testtype != null;
        List <ParameterCategory> cat = testtype.getParameterCategories();

        List <ParameterCategory> categories = testController.getCategoriesByList(cat);
        int opT = 0;

        List<Parameter> plist;
        int op = 0;

        List <ParameterCategory> catselected = new ArrayList<>();
        List<Parameter> parselected = new ArrayList<>();
        ParameterCategory categorySelected = null;
        Parameter parameter;

        do {
            opT = Utils.showAndSelectIndex(categories, "Select the Category.");

            if ((opT >= 0) && (opT < categories.size()))
            {
                categorySelected = categories.get(opT);
                catselected.add(categorySelected);
                categories.remove(opT);
            }

            plist = testController.getParameterByCategory(categorySelected);
            do {
                op = Utils.showAndSelectIndex(plist, "Select the Parameters.");

                if ((op >= 0) && (op < plist.size())) {
                    parameter = plist.get(op);
                    parselected.add(parameter);
                    plist.remove(op);
                }
            } while (!plist.isEmpty() && op != -1);
        } while (opT != -1 && !categories.isEmpty());

        //TEST
        Test t;
        try {
            Date data = new Date(System.currentTimeMillis());

            t = this.testController.createTest(nhsCode, testCode, client, testtype, catselected, parselected, data);
            System.out.println(t);
            Utils.confirm("Confirm this TEST? (y/n)");
            testController.saveTest(t);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }
}