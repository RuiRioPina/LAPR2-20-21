package app.ui.gui;

import app.controller.App;
import app.controller.TestController;
import app.domain.model.*;
import app.ui.console.utils.Utils;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class ImportTests {
    private final TestController importTestsController;
    private final String path;
    private final App app;

    public ImportTests(String path) throws IOException, InterruptedException {
        this.importTestsController = new TestController();
        this.path = path;
        this.app = App.getInstance();
        File arquivoCSV = new File(this.path);
        int j = 1;
        int a = App.getInstance().getCompany().getTestCode();
        String lines;
        Scanner leitor2 = null;
        String[] notImported = new String[10000];
        int z = 0;
        try {
            leitor2 = new Scanner(arquivoCSV);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert leitor2 != null;
        String l = leitor2.nextLine();
        String[] header = l.split(";");
        int posTt = 0;
        int posRegDate = posRegDate(header);
        for (int i = 0; i < header.length; i++) {
            if (header[i].equals("TestType")) {
                posTt = i;
            }
        }

        List<Parameter> pList = this.importTestsController.getParameters();
        List<String> parList = new ArrayList<>();

        for (int i = 0; i < pList.size(); i++) {
            parList.add(pList.get(i).getCode());
        }
        List<String> elements = new ArrayList<>();
        for (int i = posTt + 1; i < posRegDate; i++) {
            elements.add(header[i]);
        }

        while (leitor2.hasNextLine()) {
            lines = leitor2.nextLine();
            j++;
            String[] text = lines.split(";");
            String nhsCode = text[1];
            String labId = text[2];
            String ccn = text[3];
            String nhsNumber = text[4];
            String tin = text[5];
            String bDay = text[6];
            String phoneNumber = text[7];
            String name = text[8];
            String email = text[9];
            String address = text[10];
            String testType = text[11];
            String rDateHour = text[posRegDate];
            String tChemicalDateHour = text[posRegDate + 1];
            String tDoctorDateHour = text[posRegDate + 2];
            String tValidationDateHour = text[posRegDate + 3];

            //Client
            String ccnFormatted = String.format("%010d", Integer.parseInt(ccn));
            Client cl = this.importTestsController.client(Long.parseLong(ccnFormatted), Long.parseLong(nhsNumber), bDay,
                    Long.parseLong(tin), Long.parseLong(phoneNumber), email, name);
            cl.setAddress(address);

            //Information needed
            TestType tt1 = new TestType();
            TestType tt = new TestType();
            List<ClinicalAnalysisLaboratory> claList = importTestsController.getLabs();
            List<TestType> ltt = this.importTestsController.getTestTypes();
            List<ParameterCategory> catList = this.importTestsController.getCategories();

            List<ParameterCategory> catSelected = new ArrayList<>();
            List<Parameter> parSelected = new ArrayList<>();

            //Parameters and Results
            for (int i = 0; i < elements.size(); i++) {
                Parameter p = importTestsController.getParameterByCode(elements.get(i));
                for (int n = posTt; n < posRegDate; n++) {
                    if (p != null && p.getCode().equals(header[n]) && !text[n].equals("NA")) {
                        Parameter cloned = new Parameter(p);
                        text[n] = text[n].replace(",", ".");
                        cloned.setTestResult(new TestResult(cloned, Double.parseDouble(text[n]), tt1.checkExternalModuleBasedOnTestType(cloned)));
                        parSelected.add(cloned);
                    }
                }
            }

            //Test Type
            if (testType.equals("Blood")) {
                for (int i = 0; i < ltt.size(); i++) {
                    if (ltt.get(i).getDescription().equals("Blood")) {
                        tt = ltt.get(i);
                    }
                }
            } else {
                for (int i = 0; i < ltt.size(); i++) {
                    if (ltt.get(i).getDescription().equals("Covid")) {
                        tt = ltt.get(i);
                    }
                }
            }

            //Categories
            for (int i = 0; i < parSelected.size(); i++) {
                Parameter p = parSelected.get(i);
                Parameter cloned = new Parameter(p);
                List<ParameterCategory> clonedCat = new ArrayList<>();
                clonedCat.add(cloned.getPc().get(0));
                for (int m = 0; m < catList.size(); m++) {
                    if (clonedCat.get(0).equals(catList.get(m))) {
                        catSelected.add(catList.get(m));
                    }
                }
            }
            catList.retainAll(catSelected);
            tt.setParameterCategories(catList);

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy HH:mm");

            Calendar calendar1 = Calendar.getInstance();
            Calendar calendar2 = Calendar.getInstance();
            Calendar calendar3 = Calendar.getInstance();
            Calendar calendar4 = Calendar.getInstance();
            try {
                Date dat1 = formatter.parse(rDateHour);
                Date dat2 = formatter.parse(tChemicalDateHour);
                Date dat3 = formatter.parse(tDoctorDateHour);
                Date dat4 = formatter.parse(tValidationDateHour);
                calendar1.setTime(dat1);
                calendar2.setTime(dat2);
                calendar3.setTime(dat3);
                calendar4.setTime(dat4);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            String intCode = String.format("%012d", a);

            Test t = importTestsController.createTest(nhsCode, intCode, cl, tt, catList, parSelected, calendar1);
            t.setSamplesCollectionDate(calendar1);
            t.setChemicalAnalysisDate(calendar2);
            t.setDiagnosisDate(calendar3);
            t.setValidationDate(calendar4);


            //some validations and test save
            for (ClinicalAnalysisLaboratory clinicalAnalysisLaboratory : claList) {
                if (clinicalAnalysisLaboratory.getLaboratoryID().equals(labId)) {
                    t.setLabID(clinicalAnalysisLaboratory.getLaboratoryID());
                }
            }
            boolean val = email.contains("'");
            boolean val1 = email.contains("!");
            boolean val2 = email.contains("#");
            boolean val3 = email.contains("@");

            if (t.getLabID() != null && !val && !val1 && !val2 && val3 && !importTestsController.validateTest(t)) {
                importTestsController.saveClient(cl);
                importTestsController.saveTest(t);
                a++;
                this.app.getCompany().getImportedTests().add(t);
            } else {
                Utils.log("Test in line " + j + " wasn't imported. Invalid Client or Test data.");
            }
        }
        leitor2.close();
        App.getInstance().getCompany().setTestCode(a);

    }

    private int posRegDate(String[] header) {
        int posRegDate = 0;
        for (int i = 0; i < header.length; i++) {
            if (header[i].equals("Test_Reg_DateHour")) {
                posRegDate = i;
            }
        }
        return posRegDate;

    }

}





