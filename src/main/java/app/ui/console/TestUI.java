package app.ui.console;

import app.controller.App;
import app.controller.TestController;
import app.domain.model.*;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class TestUI implements Runnable {
    static Scanner sc = new Scanner(System.in);
    private final TestController testController;

    public TestUI() {
        this.testController = new TestController();
    }

    @Override
    public void run() {
        String testCode = generateTestCode();
        String nhsCode = nhsCode();
        Client client = clientByTIN();

        String cl = "" + client;
        System.out.println('\n' + cl);

        System.out.println('\n' +"Lab Order Information" + '\n');

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
        List <ParameterCategory> catselected = new ArrayList<>();
        ParameterCategory categorySelected = null;
        int opT = 0;

        List<Parameter> plist;
        List<Parameter> parselected = new ArrayList<>();
        Parameter parameter;
        int op = 0;

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

    private String generateTestCode() {
        int internalCode = App.getInstance().getCompany().getTestCode();
        internalCode++;
        App.getInstance().getCompany().setTestCode(internalCode);
        return String.format("%012d", internalCode);
    }

    private String nhsCode() {
        List <Test> test = testController.getTests();
        String nhsCode;
        boolean val = false;
        do{
            System.out.println("Insert test NHS code.");
            nhsCode = sc.next();

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
        return nhsCode;
    }

    private Client clientByTIN() {
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
        return client;
    }
}