package app.ui.gui;

import app.controller.App;
import app.domain.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ShowTestDetailsFromTestSelectedController implements Initializable {

    private ShowClientTestsController menuCctUI;
    private App app;
    private List<Parameter> listOfParametersFromTest;


    @FXML
    private Label lblValidationDate;

    @FXML
    private Label lblChemicalAnalysisDate;

    @FXML
    private ComboBox<Parameter> cmbBoxParameters;

    @FXML
    private Label lblResult;

    @FXML
    private Label lblParameter;

    @FXML
    private Label lblMinValue;

    @FXML
    private Label lblRegistrationDate;

    @FXML
    private Label lblMaxValue;
    private Test testSelected;


    public ShowTestDetailsFromTestSelectedController(Test testSelected) {
        this.app = App.getInstance();
    }

    ShowClientTestsController showClientTestsController = new ShowClientTestsController();

    public ShowTestDetailsFromTestSelectedController() {

    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cmbBoxParameters.setItems(getParametersFromTest());
        cmbBoxParameters.setPromptText("Select the parameter you want to see.");


    }

    /**
     * This method will return an ObservableList of Client objects
     *
     * @return observable list containg client objects
     */
    public ObservableList<Parameter> getParametersFromTest() {
        listOfParametersFromTest = App.getInstance().getCompany().getTestStore().getTests().get(0).getParameter();
        ObservableList<Parameter> parameterObservableList = FXCollections.observableArrayList();
        parameterObservableList.addAll(listOfParametersFromTest);
        return parameterObservableList;

    }

    public void setTest(Test test) {
    }

    public void associarParentUI(ShowClientTestsController menuCctGUISceneController) {
        this.menuCctUI = menuCctGUISceneController;
    }


    public void btnTestDetails(ActionEvent actionEvent) {

    }

    @FXML
    void selectFromComboBox(ActionEvent event) {
        Parameter parameterSelected = cmbBoxParameters.getSelectionModel().getSelectedItem();

        parameterSelected.setTestResult(new TestResult(parameterSelected, 170, new ReferenceValue("g/L", 200, 150)));

        String resultString = String.format("%s %s", parameterSelected.getTestResult().getResult(), getMetricsFor(parameterSelected.getCode()));

        String maxValueString = String.format("%s %s", parameterSelected.getTestResult().getReferenceValue().getMaxValue(), getMetricsFor(parameterSelected.getCode()));

        String minValueString = String.format("%s %s", parameterSelected.getTestResult().getReferenceValue().getMinValue(), getMetricsFor(parameterSelected.getCode()));


        lblParameter.setText(parameterSelected.getCode());
        lblResult.setText(resultString);
        lblMaxValue.setText(maxValueString);
        lblMinValue.setText(minValueString);
//        lblRegistrationDate.setText(testSelected.getRegistrationDate().toString());
//        lblChemicalAnalysisDate.setText(testSelected.getChemicalAnalysisDate().toString());
//        lblValidationDate.setText(testSelected.getValidationDate().toString());
    }

    private String getMetricsFor(String parameter) {
        TestType testType = new TestType();
        return testType.getMetricsBasedOnTestType(parameter);
    }

}


