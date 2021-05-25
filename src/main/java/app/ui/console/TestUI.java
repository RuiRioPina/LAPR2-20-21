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


    public TestUI() {
        this.testController = new TestController();
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        String internalCode = "000000000001";
        String nhsCode;
        do{
            System.out.println("Insert test NHS code.");
            nhsCode = sc.nextLine();
            if (nhsCode.length() != 12 ||!nhsCode.matches("^[a-zA-Z0-9]*$")) {
                System.out.println("NHS code has 12 alphanumeric characters. Try again.");
            }
        } while (nhsCode.length() != 12 || !nhsCode.matches("^[a-zA-Z0-9]*$"));

        long ccn;
        String str;
        do {
            System.out.println("Insert client citizen card number.");
            ccn = sc.nextLong();
            str = "" + ccn;
            if (str.length() != 16){
                System.out.println("CCN is a 16 digit number. Try again.");
            }
        } while (str.length() != 16);

        // CLIENT
        List<Client> clist = this.testController.getClients();
        Client client = new Client();

        for(int i=0;i<clist.size();i++){
            if(ccn == clist.get(i).getCcn()){
                System.out.println();
                System.out.println(clist.get(i));
                Utils.confirm("Confirm this client? (s/n)"); //change Utils to y/n
                client = clist.get(i);
            }
        }

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

        String sampleCollectionMethod = testtype.getCollectingMethod();
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

        t = this.testController.createTest(nhsCode, internalCode, client, testtype, sampleCollectionMethod, catselected, parselected, data);
        System.out.println(t);
        Utils.confirm("Confirm this client? (s/n)");
        testController.saveTest(t);
    } catch (IllegalArgumentException ex) {
        System.out.println(ex.getMessage());
    }
}
}
