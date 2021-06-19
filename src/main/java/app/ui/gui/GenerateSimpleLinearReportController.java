package app.ui.gui;

import app.controller.App;
import app.domain.model.Company;
import app.ui.gui.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
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
    private TextArea txaShowReport;
    @FXML
    private Label lblSendReport;

    @FXML
    private Button btnYes;



    @FXML
    void generateReportButton(ActionEvent event) {
        String resultString = "";
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
        if (verifySignificanceNumber(txfAHypothesisTestSignificance.getText()) == true && verifySignificanceNumber(txfBHypothesisTestSignificance.getText()) == true && verifySignificanceNumber(txfFDecisionSignificance.getText()) == true && verifySignificanceNumber(txfConfidenceIntervalSignificance.getText() )==true) {
            if (historicalPointType.equals("Days")) {
                if (independentVariableSelected.equals("Total tests Performed")) {
                    resultString = company.generateSimpleNhsReportTestsPerformed(currentDate, historicalPoints, newerDate, olderDate, aTestSignificance, aTestParameter, bTestSignificance, bTestParameter, fTestSignificance, confidenceIntervalSignificance);
                    txaShowReport.setText(resultString);
                    setLblSendReportVisible();
                    setBtnYesVisible();



                }
                if (independentVariableSelected.equals("Mean Age of Clients")) {
                    resultString = company.generateSimpleNhsReportMeanAge(currentDate, historicalPoints, newerDate, olderDate, aTestSignificance, aTestParameter, bTestSignificance, bTestParameter, fTestSignificance, confidenceIntervalSignificance);
                    txaShowReport.setText(resultString);
                    setLblSendReportVisible();
                    setBtnYesVisible();

                }
            }
            if (historicalPointType.equals("Weeks")) {
                if (independentVariableSelected.equals("Total tests Performed")) {
                    resultString = company.generateSimpleNHSReportTestsPerformedWeeks(currentDate, historicalPoints, newerDate, olderDate, aTestSignificance, bTestSignificance, bTestParameter, bTestParameter, fTestSignificance, confidenceIntervalSignificance);
                    System.out.println(resultString);
                    txaShowReport.setText(resultString);
                    setLblSendReportVisible();
                    setBtnYesVisible();

                }
                if (independentVariableSelected.equals("Mean Age of Clients")) {
                    resultString = company.generateSimpleNHSReportMeanAgeWeeks(currentDate, historicalPoints, newerDate, olderDate, aTestSignificance, bTestSignificance, bTestParameter, bTestParameter, fTestSignificance, confidenceIntervalSignificance);
                    System.out.println(resultString);
                    txaShowReport.setText(resultString);
                    setLblSendReportVisible();
                    setBtnYesVisible();

                }

            }
        }else Utils.createAlert(Alert.AlertType.ERROR,"Dados Inválidos","Os numeros de significancia não podem ser maior que um ou zero.");

    }
    @FXML
    void btnYesClick(ActionEvent event) {
App.getInstance().getCompany().sendReportToNHS(txaShowReport.getText());
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
        lblSendReport.setVisible(false);
        btnYes.setVisible(false);
    }

    public void associarParentUI(ChooseLinearRegressionController chooseLinearRegressionController) {
        this.chooseLinearRegressionController = chooseLinearRegressionController;
    }

    private boolean verifySignificanceNumber(String numberString) {
        boolean validation=false;
        try {
            double number = Double.parseDouble(numberString);
            validation=true;
        }catch (IllegalArgumentException e ){
            validation=false;
        }
        if (Double.parseDouble(numberString) > 1|| Double.parseDouble(numberString) < 0) {
            validation=false;
        }
         return validation;
    }

    private Calendar getDateFromDatePicker(int year,int month,int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month-1, day);
        return calendar;
    }
    private void setBtnYesVisible(){
        btnYes.setVisible(true);
    }
    private void setLblSendReportVisible(){
        lblSendReport.setVisible(true);
    }



}