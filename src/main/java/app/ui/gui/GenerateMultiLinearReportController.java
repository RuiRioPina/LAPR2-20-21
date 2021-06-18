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
    private TextField txfOlderDate;

    @FXML
    private TextField txfNewerDate;

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

    }

    @FXML
    void menuExitAction(ActionEvent event) {

    }

}
