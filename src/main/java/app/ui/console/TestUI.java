package app.ui.console;

import app.controller.App;
import app.controller.TestController;
import app.domain.model.*;
import app.ui.console.utils.Utils;

import java.util.*;


public class TestUI implements Runnable {
    static Scanner sc = new Scanner(System.in);
    private final TestController testController;

    public TestUI() {
        this.testController = new TestController();
    }

    @Override
    public void run() {

        String nhsCode = nhsCode();

        List<Client> lcl = this.testController.getClients();
        Client client = clientByTIN(lcl);

        String cl = "Client = " + client.getName();
        System.out.println('\n' + cl);

        System.out.println('\n' + "Lab Order Information" + '\n');

        TestType tt = null;
        List<TestType> ltt = this.testController.getTestTypes();

        int opt = 0;
        opt = Utils.showAndSelectIndex(ltt, "Select the Test Type.");

        if ((opt >= 0) && (opt < ltt.size())) {
            tt = ltt.get(opt);
        }

        assert tt != null;
        List<ParameterCategory> cat = tt.getParameterCategories();

        List<ParameterCategory> lc = testController.getCategoriesByList(cat);
        List<ParameterCategory> lcs = new ArrayList<>();
        ParameterCategory c = null;
        int opT = 0;

        List<Parameter> lp;
        List<Parameter> lps = new ArrayList<>();
        Parameter p;
        int op = 0;

        do {
            opT = Utils.showAndSelectIndex(lc, "Select the Category.");

            if ((opT >= 0) && (opT < lc.size())) {
                c = lc.get(opT);
                lcs.add(c);
                lc.remove(opT);
            }

            lp = testController.getParameterByCategory(c);
            do {
                op = Utils.showAndSelectIndex(lp, "Select the Parameters.");

                if ((op >= 0) && (op < lp.size())) {
                    p = lp.get(op);
                    Parameter cloned = new Parameter(p);
                    lps.add(cloned);
                    lp.remove(op);
                }
            } while (!lp.isEmpty() && op != -1);
        } while (opT != -1 && !lc.isEmpty());
        List<Parameter> parameters = new ArrayList<>(lps);

        String testCode = generateTestCode();
        Test t;
        try {
            Calendar rDate = Calendar.getInstance();
            t = this.testController.createTest(nhsCode, testCode, client, tt, lcs, parameters, rDate);
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
        List<Test> test = App.getInstance().getCompany().getAllTest();
        String nhsCode;
        boolean val = false;
        do {
            System.out.println("Insert test NHS code.");
            nhsCode = sc.next();

            if (nhsCode.length() != 12 || !nhsCode.matches("^[a-zA-Z0-9]*$")) {
                System.out.println("NHS code has 12 alphanumeric characters. Try again.");
            }

            for (int i = 0; i < test.size(); i++) {
                if (nhsCode.equals(test.get(i).getNhsCode())) {
                    val = true;
                    System.out.println("NHS code is already linked to a test. Try again.");
                    break;
                } else {
                    val = false;
                }
            }
        } while (nhsCode.length() != 12 || !nhsCode.matches("^[a-zA-Z0-9]*$") || val);
        return nhsCode;
    }

    private Client clientByTIN(List<Client>lcl) {
        Client client = null;
        long tin;
        String str;
        boolean valid = false;
        do {
            System.out.println("Insert client's TIN.");
            tin = sc.nextLong();
            str = "" + tin;

            if (str.length() != 10) {
                System.out.println("TIN is a 10 digit number.");
            }

            for (int i = 0; i < lcl.size(); i++) {
                if (tin == lcl.get(i).getTin()) {
                    client = lcl.get(i);
                    valid = true;
                    break;
                } else {
                    System.out.println("There is no client registered with this TIN. Try again.");
                    valid = false;
                }
            }
        } while (str.length() != 10 || !valid);
        return client;
    }
}