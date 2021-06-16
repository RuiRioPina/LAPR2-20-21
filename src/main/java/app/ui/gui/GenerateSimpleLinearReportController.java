package app.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class GenerateSimpleLinearReportController implements Initializable {
    public GenerateSimpleLinearReportController(){

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
    private TextField txfOlderDate;

    @FXML
    private TextField txfNewerDate;

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
    private Button generateReportBTN;

    @FXML
    void generateReportButton(ActionEvent event) {

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
    }
    public void associarParentUI(ChooseLinearRegressionController chooseLinearRegressionController) {
        this.chooseLinearRegressionController = chooseLinearRegressionController;
    }

}