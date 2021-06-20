package app.ui.gui;

import app.controller.App;
import app.domain.model.Company;
import app.domain.model.SendReportToNHSTask;
import app.ui.gui.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

public class GenerateMultiLinearReportController implements Initializable {

    @FXML
    private Label lblTestView;

    @FXML
    private Label lblHistoricalPointType;

    @FXML
    private Label lblHistoricalPointNumber;

    @FXML
    private TextField txfNumberOfHistoricalPoints;

    @FXML
    private Label lblRegressionInterval;

    @FXML
    private DatePicker dtpCurrentDate;

    @FXML
    private DatePicker dtpOlderDate;

    @FXML
    private DatePicker dtpNewerDate;


    @FXML
    private Label lblOlderDateM;

    @FXML
    private Label lblNewerDate;
    @FXML
    private Label lblX1HypothesisSignificance;

    @FXML
    private TextField txfX1HypothesisTestSignificance;

    @FXML
    private Label lblX2HypothesisTestSignificance;

    @FXML
    private TextField txfX2HypothesisTestSignificance;

    @FXML
    private TextField txfConfidenceIntervalSignificance;

    @FXML
    private Label lblConfidenceIntervalSignificance;

    @FXML
    private Label lblFDecisionSignficance;

    @FXML
    private TextField txfFDecisionSignificance;

    @FXML
    private ComboBox<String> cboxHistoricalPointType;


    @FXML
    private Label lblB0HypothesisTestSignificance;


    @FXML
    private TextField txfB0HypothesisTestSignificance;

    @FXML
    private Button generateReportBTN1;

    @FXML
    private TextArea txaShowReportM;

    @FXML
    private Button btnYesM;

    @FXML
    private Label lblSendReportM;
    private ChooseLinearRegressionController chooseLinearRegressionController;

    /**
     * Initialization for the controller.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cboxHistoricalPointType.getItems().add("Days");
        cboxHistoricalPointType.getItems().add("Weeks");
        cboxHistoricalPointType.getSelectionModel().selectFirst();
        lblSendReportM.setVisible(false);
        btnYesM.setVisible(false);
    }

    /**
     * Associates the Parent UI, in this case , the chooseLinearRegressionController
     * @param chooseLinearRegressionController
     */
    public void associarParentUI(ChooseLinearRegressionController chooseLinearRegressionController) {
        this.chooseLinearRegressionController = chooseLinearRegressionController;
    }

    /**
     * Method for clicking the generateReportButton , it takes all the data and creates a String and then shows it in a text area
     * @param event- clicking the button
     */
    @FXML
    void generateReportButton(ActionEvent event) {
        String resultString = "";
        Company company = App.getInstance().getCompany();
        Calendar olderDate = getDateFromDatePicker(dtpOlderDate.getValue().getYear(), dtpOlderDate.getValue().getMonthValue(),dtpOlderDate.getValue().getDayOfMonth());
        Calendar newerDate = getDateFromDatePicker(dtpNewerDate.getValue().getYear(),dtpNewerDate.getValue().getMonthValue(),dtpNewerDate.getValue().getDayOfMonth());
        Calendar currentDate = getDateFromDatePicker(dtpCurrentDate.getValue().getYear(),dtpCurrentDate.getValue().getMonthValue(),dtpCurrentDate.getValue().getDayOfMonth());
        int historicalPoints = Integer.parseInt(txfNumberOfHistoricalPoints.getText());
        double x1TestSignificance = Double.parseDouble(txfX1HypothesisTestSignificance.getText());
        double x2TestSignificance = Double.parseDouble(txfX2HypothesisTestSignificance.getText());
        double B0TestSignificance= Double.parseDouble(txfB0HypothesisTestSignificance.getText());
        double fTestSignificance = Double.parseDouble(txfFDecisionSignificance.getText());
        double confidenceIntervalSignificance = Double.parseDouble(txfConfidenceIntervalSignificance.getText());
        String historicalPointType = cboxHistoricalPointType.getValue();
        if (verifySignificanceNumber(txfX1HypothesisTestSignificance.getText())==true&&verifySignificanceNumber(txfX2HypothesisTestSignificance.getText())==true&& verifySignificanceNumber(txfB0HypothesisTestSignificance.getText())==true&&verifySignificanceNumber(txfFDecisionSignificance.getText())==true&&verifySignificanceNumber(txfConfidenceIntervalSignificance.getText())==true) {
            if (historicalPointType.equals("Days")) {
                resultString = company.generateMultilinearNHSReportDays(currentDate, historicalPoints, newerDate, olderDate, B0TestSignificance, x1TestSignificance, x2TestSignificance, fTestSignificance, confidenceIntervalSignificance);
                txaShowReportM.setText(resultString);
                setLblSendReportMVisible();
                setBtnYesMVisible();
            }
            if (historicalPointType.equals("Weeks")) {
                try {


                    resultString = company.generateMultilinearNHSReportWeeks(currentDate, historicalPoints, newerDate, olderDate, B0TestSignificance, x1TestSignificance, x2TestSignificance, fTestSignificance, confidenceIntervalSignificance);
                    txaShowReportM.setText(resultString);
                    setLblSendReportMVisible();
                    setBtnYesMVisible();
                }catch (IllegalArgumentException e){
                    Utils.createAlert(Alert.AlertType.ERROR,"Mondays","Due to programmer limitations you may only use mondays to define weeks or you did not type numbers where you should have");
                }
            }
        }
        else Utils.createAlert(Alert.AlertType.ERROR,"Invalid data","The significance numbers cant be bigger than 1 or smaller than 0");
    }

    /**
     * Method for clicking the yes button, sends the text in the text area to the nhs
     * @param event- clicking the yes button
     */
    @FXML
    void btnYesClick(ActionEvent event) {
        App.getInstance().getCompany().sendReportToNHS(txaShowReportM.getText());

    }
    /**
     * Creates a calendar object using a date String
     * @param year-year
     * @param month-month
     * @param day-day
     * @return
     */
    private Calendar getDateFromDatePicker(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month-1, day);
        return calendar;
    }

    /**
     * Sets the yes button visible
     */
    private void setBtnYesMVisible(){
        btnYesM.setVisible(true);
    }

    /**
     * Sets the send report Label Visible
     */
    private void setLblSendReportMVisible(){
        lblSendReportM.setVisible(true);
    }
    /**
     * checks if a string is a number and then checks if that number is bigger than 0 and smaller than 1
     * @param numberString- string with the number
     * @return
     */
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

}
