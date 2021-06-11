package app.ui.console;

import app.controller.App;
import app.controller.TestController;
import app.domain.model.*;
import app.domain.shared.Constants;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ImportTests{
    private final TestController importTestsController;
    private final String path;
    private App app;

    public ImportTests(String path) {
        this.importTestsController = new TestController();
        this.path = path;
        this.app = App.getInstance();
        File arquivoCSV = new File(this.path);

        String lines;
        Scanner leitor2 = null;
        try {
            leitor2 = new Scanner(arquivoCSV);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert leitor2 != null;
        String l = leitor2.nextLine();
        String[] check = l.split(";");


        while (leitor2.hasNextLine()) {
            lines = leitor2.nextLine();

            String[] text = lines.split(";");
            String internalCode = text[0];
            String nhsCode = text[1];
            String labId = text[2];
            String ccn = text[3];
            String nhsNumber = text[4];
            String tin = text[5];
            String bDay = text[6];
            String phoneNumber = text[7];
            String name = text[8];
            String email = text[9];
            String testType = text[11];
            String bcat1 = text[12];
            String p1 = text[13];
            String p2 = text[14];
            String p3 = text[15];
            String p4 = text[16];
            String bcat2 = text[17];
            String p5 = text[18];
            String ccat = text[19];
            String p6 = text[20];
            String rDateHour = text[21];
            String tChemicalDateHour = text[22];
            String tDoctorDateHour = text[23];
            String tValidationDateHour = text[24];

            String ccnFormatted = String.format("%010d", Integer.parseInt(ccn));

            Client cl = this.importTestsController.createClient(Long.parseLong(ccnFormatted), Long.parseLong(nhsNumber), bDay,
                    Long.parseLong(tin), Long.parseLong(phoneNumber), email, name);

            TestType tt1 = new TestType();
            TestType tt = null;
            List<ClinicalAnalysisLaboratory> claList = importTestsController.getLabs();
            List<TestType> ltt = this.importTestsController.getTestTypes();
            List<ParameterCategory> catList = this.importTestsController.getCategories();
            List<Parameter> pList = this.importTestsController.getParameters();
            List<ParameterCategory> catSelected = new ArrayList<>();
            List<Parameter> parSelected = new ArrayList<>();

            App.getInstance().getCompany().setCLA(Constants.calNull);
            if (testType.equals("Blood")) {
                for (int i = 0; i < catList.size(); i++) {
                    if (catList.get(i).getName().equals(bcat1)) {
                        catSelected.add(catList.get(i));
                    }
                    if (catList.get(i).getName().equals("Cholestrol")) {
                        catSelected.add(catList.get(i));
                    }
                }
                for (int i = 0; i < ltt.size(); i++) {
                    if (ltt.get(i).getParameterCategories().equals(catSelected)) {
                        tt = ltt.get(i);
                    }
                }
                for (int i = 0; i < pList.size(); i++) {
                    if (pList.get(i).getCode().equals(check[13])) {
                        Parameter p = pList.get(i);
                        Parameter cloned = new Parameter(p);
                        p1 = p1.replace(",",".");
                        cloned.setTestResult(new TestResult(cloned,Double.parseDouble(p1),tt1.checkExternalModuleBasedOnTestType(cloned)));
                        parSelected.add(cloned);
                    }
                    if (pList.get(i).getCode().equals(check[14])) {
                        Parameter p = pList.get(i);
                        Parameter cloned = new Parameter(p);
                        p2 = p2.replace(",",".");
                        cloned.setTestResult(new TestResult(cloned,Double.parseDouble(p2),tt1.checkExternalModuleBasedOnTestType(cloned)));
                        parSelected.add(cloned);
                    }
                    if (pList.get(i).getCode().equals(check[15])) {
                        Parameter p = pList.get(i);
                        Parameter cloned = new Parameter(p);
                        p3 = p3.replace(",",".");
                        cloned.setTestResult(new TestResult(cloned,Double.parseDouble(p3),tt1.checkExternalModuleBasedOnTestType(cloned)));
                        parSelected.add(cloned);
                    }
                    if (pList.get(i).getCode().equals(check[16])) {
                        Parameter p = pList.get(i);
                        Parameter cloned = new Parameter(p);
                        p4 = p4.replace(",",".");
                        cloned.setTestResult(new TestResult(cloned,Double.parseDouble(p4),tt1.checkExternalModuleBasedOnTestType(cloned)));
                        parSelected.add(cloned);
                    }
                    if (pList.get(i).getCode().equals(check[18])) {
                        Parameter p = pList.get(i);
                        Parameter cloned = new Parameter(p);
                        p5 = p5.replace(",",".");
                        cloned.setTestResult(new TestResult(cloned,Double.parseDouble(p5),null));
                        parSelected.add(cloned);
                    }
                }
            } else {
                for (int i = 0; i < catList.size(); i++) {
                    if (catList.get(i).getName().equals(ccat)) {
                        catSelected.add(catList.get(i));
                    }
                }
                for (int i = 0; i < ltt.size(); i++) {
                    if (ltt.get(i).getParameterCategories().equals(catSelected)) {
                        tt = ltt.get(i);
                    }
                }
                for (int i = 0; i < pList.size(); i++) {
                    if (pList.get(i).getCode().equals(check[20])) {
                        Parameter p = pList.get(i);
                        Parameter cloned = new Parameter(p);
                        p6 = p6.replace(",",".");
                        cloned.setTestResult(new TestResult(cloned,Double.parseDouble(p6),tt1.checkExternalModuleBasedOnTestType(cloned)));
                        parSelected.add(cloned);
                    }
                }
            }

            DateFormat formatter = new SimpleDateFormat("dd/MM/yy HH:mm");
            Date dat1 = null;
            Date dat2 = null;
            Date dat3 = null;
            Date dat4 = null;
            try {
                dat1 = formatter.parse(rDateHour);
                dat2 = formatter.parse(tChemicalDateHour);
                dat3 = formatter.parse(tDoctorDateHour);
                dat4 = formatter.parse(tValidationDateHour);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String intCode = String.format("%012d", Integer.parseInt(internalCode));

            Test t = importTestsController.createTest(nhsCode,intCode, cl,tt,catSelected,parSelected,dat1);
            t.setSamplesCollectionDate(dat1);
            t.setChemicalAnalysisDate(dat2);
            t.setDiagnosisDate(dat3);
            t.setValidationDate(dat4);

            for (ClinicalAnalysisLaboratory clinicalAnalysisLaboratory : claList) {
                if (clinicalAnalysisLaboratory.getLaboratoryID().equals(labId)) {
                    t.setLabID(clinicalAnalysisLaboratory.getLaboratoryID());
                }
            }
            boolean val = email.contains("'");
            boolean val1 = email.contains("!");
            boolean val2 = email.contains("#");
            boolean val3 = email.contains("@");

            if (t.getLabID() != null && !val && !val1 && !val2 && val3){
                importTestsController.saveClient(cl);
                importTestsController.saveTest(t);
                this.app.getCompany().getImportedTests().add(t);
            } else {
                System.out.println("Test " + internalCode + " wasn't imported.");
            }
        }
    }
    }







