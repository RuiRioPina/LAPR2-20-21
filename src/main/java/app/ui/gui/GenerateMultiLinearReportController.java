package app.ui.gui;

import app.controller.App;
import app.domain.model.Company;
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
    private Label lblX1ParameterForHypothesis;

    @FXML
    private TextField txfX1ParameterHypothesisTest;

    @FXML
    private Label lblX2ParameterForHypothesisTest;

    @FXML
    private TextField txfX2ParameterHypothesisTest;

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
    private Label lblB0ParameterForHypothesis;

    @FXML
    private Label lblB0HypothesisTestSignificance;

    @FXML
    private TextField txfB0ParameterForHypothesis;

    @FXML
    private TextField txfB0HypothesisTestSignificance;

    @FXML
    private Button generateReportBTN;
    private ChooseLinearRegressionController chooseLinearRegressionController;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cboxHistoricalPointType.getItems().add("Days");
        cboxHistoricalPointType.getItems().add("Weeks");
        cboxHistoricalPointType.getSelectionModel().selectFirst();
    }
    public void associarParentUI(ChooseLinearRegressionController chooseLinearRegressionController) {
        this.chooseLinearRegressionController = chooseLinearRegressionController;
    }

    @FXML
    void generateReportButton(ActionEvent event) {
        String resultString = "dsada";
        Company company = App.getInstance().getCompany();
        Calendar olderDate = getDateFromDatePicker(dtpOlderDate.getValue().getYear(), dtpOlderDate.getValue().getMonthValue(),dtpOlderDate.getValue().getDayOfMonth());
        Calendar newerDate = getDateFromDatePicker(dtpNewerDate.getValue().getYear(),dtpNewerDate.getValue().getMonthValue(),dtpNewerDate.getValue().getDayOfMonth());
        Calendar currentDate = getDateFromDatePicker(dtpCurrentDate.getValue().getYear(),dtpCurrentDate.getValue().getMonthValue(),dtpCurrentDate.getValue().getDayOfMonth());
        int historicalPoints = Integer.parseInt(txfNumberOfHistoricalPoints.getText());
        double x1TestParameter = Double.parseDouble(txfX1ParameterHypothesisTest.getText());
        double x1TestSignificance = Double.parseDouble(txfX1HypothesisTestSignificance.getText());
        double x2TestParameter = Double.parseDouble(txfX2ParameterHypothesisTest.getText());
        double x2TestSignificance = Double.parseDouble(txfX2HypothesisTestSignificance.getText());
        double B0TestParameter= Double.parseDouble(txfB0ParameterForHypothesis.getText());
        double fTestSignificance = Double.parseDouble(txfFDecisionSignificance.getText());
        double confidenceIntervalSignificance = Double.parseDouble(txfConfidenceIntervalSignificance.getText());
        String historicalPointType = cboxHistoricalPointType.getValue();
        if (historicalPointType.equals("Days")){
            resultString=company.generateMultilienearNHSReportDays(currentDate,historicalPoints,newerDate,olderDate,B0TestParameter,x1TestParameter,x2TestParameter,fTestSignificance,confidenceIntervalSignificance);
            System.out.println(resultString);

        }
    }

    @FXML
    void menuExitAction(ActionEvent event) {

    }
    private Calendar getDateFromDatePicker(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month-1, day);
        return calendar;
    }

}
