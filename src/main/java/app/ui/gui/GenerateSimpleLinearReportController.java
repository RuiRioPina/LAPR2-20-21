package app.ui.gui;

import app.controller.App;
import app.domain.model.Company;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.temporal.TemporalField;
import java.util.Calendar;
import java.util.ResourceBundle;

public class GenerateSimpleLinearReportController implements Initializable {
    public GenerateSimpleLinearReportController() {

    }

    @FXML
    private Label lblTestView;

    @FXML
    private Label lblHistoricalPointType;

    @FXML
    private Label lblHistoricalPointNumber;

    @FXML
    private TextField txfNumberOfHistoricalPoints;

    @FXML
    private Label lblIndependentVariable;

    @FXML
    private Label lblRegressionInterval;

    @FXML
    private ComboBox<String> cboxHistoricalPointType;

    @FXML
    private ComboBox<String> cboxIndependentVariable;

    @FXML
    private DatePicker dtpOlderDate;

    @FXML
    private DatePicker dtpNewerDate;

    @FXML
    private Label lblOlderDate;

    @FXML
    private Label lblNewerDate;

    @FXML
    private Label lblBParameterForHypothesis;

    @FXML
    private TextField txfBParameterHypothesisTest;

    @FXML
    private Label lblAParameterForHypothesisTest;

    @FXML
    private TextField txfAParameterHypothesisTest;

    @FXML
    private Label lblBHypothesisSignificance;

    @FXML
    private TextField txfBHypothesisTestSignificance;

    @FXML
    private Label lblAHypothesisTestSignificance;

    @FXML
    private TextField txfAHypothesisTestSignificance;

    @FXML
    private TextField txfConfidenceIntervalSignificance;

    @FXML
    private Label lblConfidenceIntervalSignificance;

    @FXML
    private Label lblFDecisionSignficance;

    @FXML
    private TextField txfFDecisionSignificance;
    @FXML
    private Label lblCurrentDay;
    @FXML
    private DatePicker dtpCurrentDay;
    @FXML
    private Button generateReportBTN;

    @FXML
    void generateReportButton(ActionEvent event) {
        String resultString = "dsada";
        Company company = App.getInstance().getCompany();
        Calendar olderDate = getDateFromDatePicker(dtpOlderDate.getValue().getYear(), dtpOlderDate.getValue().getMonthValue(),dtpOlderDate.getValue().getDayOfMonth());
        Calendar newerDate = getDateFromDatePicker(dtpNewerDate.getValue().getYear(),dtpNewerDate.getValue().getMonthValue(),dtpNewerDate.getValue().getDayOfMonth());
        Calendar currentDate = getDateFromDatePicker(dtpCurrentDay.getValue().getYear(),dtpCurrentDay.getValue().getMonthValue(),dtpCurrentDay.getValue().getDayOfMonth());
        int historicalPoints = Integer.parseInt(txfNumberOfHistoricalPoints.getText());
        double aTestParameter = Double.parseDouble(txfAParameterHypothesisTest.getText());
        double aTestSignificance = Double.parseDouble(txfAHypothesisTestSignificance.getText());
        double bTestParameter = Double.parseDouble(txfBParameterHypothesisTest.getText());
        double bTestSignificance = Double.parseDouble(txfBHypothesisTestSignificance.getText());
        double fTestSignificance = Double.parseDouble(txfFDecisionSignificance.getText());
        double confidenceIntervalSignificance = Double.parseDouble(txfConfidenceIntervalSignificance.getText());
        String historicalPointType = cboxHistoricalPointType.getValue();
        String independentVariableSelected= cboxIndependentVariable.getValue();
        if (historicalPointType.equals("Days")) {
            if (independentVariableSelected.equals("Total tests Performed")) {

                // System.out.println(resultString);

                //  System.out.println("dsadaa");
                resultString = company.generateSimpleNhsReportTestsPerformed(currentDate, historicalPoints, newerDate, olderDate, aTestSignificance, bTestSignificance, bTestParameter, bTestParameter, fTestSignificance, confidenceIntervalSignificance);
                System.out.println(resultString);

            }
            if (independentVariableSelected.equals("Mean Age of Clients")){
                resultString=company.generateSimpleNhsReportMeanAge(currentDate, historicalPoints, newerDate, olderDate, aTestSignificance, bTestSignificance, bTestParameter, bTestParameter, fTestSignificance, confidenceIntervalSignificance);
                System.out.println(resultString);
            }
        }
        if (historicalPointType.equals("Weeks")){
            if (independentVariableSelected.equals("Total tests Performed")){
               resultString= company.generateSimpleNHSReportTestsPerformedWeeks(currentDate, historicalPoints, newerDate, olderDate, aTestSignificance, bTestSignificance, bTestParameter, bTestParameter, fTestSignificance, confidenceIntervalSignificance);
                System.out.println(resultString);
            }
            if (independentVariableSelected.equals("Mean Age of Clients")){
                resultString=company.generateSimpleNHSReportMeanAgeWeeks(currentDate, historicalPoints, newerDate, olderDate, aTestSignificance, bTestSignificance, bTestParameter, bTestParameter, fTestSignificance, confidenceIntervalSignificance);
                System.out.println(resultString);
            }

        }


    }

    @FXML
    void menuExitAction(ActionEvent event) {

    }

    @FXML
    void cBoxHistoricalPointVariable(ActionEvent event) {

    }

    @FXML
    void cboxIndependentVariableOpen(ActionEvent event) {

    }

    private ChooseLinearRegressionController chooseLinearRegressionController;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cboxIndependentVariable.getItems().add("Total tests Performed");
        cboxIndependentVariable.getItems().add("Mean Age of Clients");
        cboxHistoricalPointType.getItems().add("Days");
        cboxHistoricalPointType.getItems().add("Weeks");
        cboxIndependentVariable.getSelectionModel().selectFirst();
        cboxHistoricalPointType.getSelectionModel().selectFirst();
    }

    public void associarParentUI(ChooseLinearRegressionController chooseLinearRegressionController) {
        this.chooseLinearRegressionController = chooseLinearRegressionController;
    }

    private boolean verifySignificanceNumber(String numberString) {
        if (numberString.contentEquals("^[0-9]{1,2}([,.][0-9]{1,2})?$")) {
            return true;
        }
        if (Double.parseDouble(numberString) > 100 || Double.parseDouble(numberString) < 0) {
            return false;
        } else return false;
    }

    private Calendar getDateFromString(String dateString) {
        Calendar calendar = Calendar.getInstance();
        String[] arrString = dateString.split("-");
        calendar.set(Integer.parseInt(arrString[2]), Integer.parseInt(arrString[1]) - 1, Integer.parseInt(arrString[0]));
        return calendar;
    }
    private Calendar getDateFromDatePicker(int year,int month,int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month-1, day);
        return calendar;
    }



}