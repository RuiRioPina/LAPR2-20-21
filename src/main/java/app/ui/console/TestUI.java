package app.ui.console;

import app.controller.TestController;
import app.domain.model.*;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class TestUI implements Runnable {

    private TestController testController;


    public TestUI() {
        this.testController = new TestController();
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        String internalCode = "000000000001";
        System.out.println("Insert test NHS code.");
        String nhsCode = sc.nextLine();
        System.out.println("Insert client citizen card number.");
        long ccn = sc.nextLong();
        boolean validation = false;

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
        System.out.println();

        System.out.println("Lab Order Information");

        System.out.println();

        // TEST TYPES
        TestType testtype = new TestType(null,null,null,null);
        List <TestType> ttlist = this.testController.getTestTypes();

        int opt = 0;
        opt = Utils.showAndSelectIndex(ttlist, "Select the Test Type.");

        if ( (opt >= 0) && (opt < ttlist.size()))
        {
            testtype = ttlist.get(opt);

        }

        String sampleCollectionMethod = testtype.getCollectingMethod();
        List <ParameterCategory> cat = testtype.getParameterCategories();

        // CATEGORY
        List<ParameterCategory> categorySelected = new ArrayList<>();

        while(validation == false) {
            List <ParameterCategory> categories = testController.getCategoriesByList(cat);
            int opT = 0;
          do {
              opT = Utils.showAndSelectIndex(categories, "Select the Category.");

              if ((opT >= 0) && (opT < categories.size()))
              {
                  categorySelected.add(categories.get(opT));
                  categories.remove(opT);
              }
          } while (opT != -1 && !categories.isEmpty());
            validation = checkParameterCategoryRules(categorySelected);
            if(validation == false) {
                System.out.println("The Category list is invalid");
            }
          }
        validation = false;


        // PARAMETER
        List <Parameter> parameter = new ArrayList<>();
        while(validation == false) {
            List<Parameter> plist = this.testController.getParameters();
            int op = 0;
            do {
                op = Utils.showAndSelectIndex(plist, "Select the Parameters.");

                if ((op >= 0) && (op < plist.size()))
                {
                    parameter.add(plist.get(op));
                    plist.remove(op);
                }
            }
            while (op != -1 && !plist.isEmpty());
            validation = checkParameterRules(parameter);
            if(validation == false) {
                System.out.println("The Parameter list is invalid");
            }
        }

        //TEST
        Test t;
        try {
            Date data = new Date(System.currentTimeMillis());
            Date val = null;
        t = this.testController.createTest(nhsCode, internalCode, client, testtype, sampleCollectionMethod,
                categorySelected, parameter, data, null,null,null,val);
                System.out.println(t);
    } catch (IllegalArgumentException ex) {
        System.out.println(ex.getMessage());
        return;
    }
}

    private boolean checkParameterCategoryRules(List<ParameterCategory> pc) {
        boolean validation = true;
        if (pc.isEmpty()) {
            validation = false;
        }

        return validation;
    }

    private boolean checkParameterRules(List<Parameter> parameter) {
        boolean validation = true;
        if (parameter.isEmpty()) {
            validation = false;
        }
        return validation;
    }

    }
