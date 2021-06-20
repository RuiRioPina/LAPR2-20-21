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
import java.util.List;
import java.util.ResourceBundle;

/**
 * In this controller the view for the test details is being controlled. It implements the Initializable class so that it is initializes the overridden method whenever it this class is instantiated.
 */

public class ShowTestDetailsFromTestSelectedController implements Initializable {

    /**
     * The parent UI of this controller
     */
    private ShowClientTestsController menuCctUI;
    /**
     * Declaration of the App class
     */
    private App app;
    /**
     * Declaration of a list of parameters from the test selected in its parent ui
     */
    private List<Parameter> listOfParametersFromTest;

    /**
     * ComboBox containg all the parameters in the test selected that can be selected by the user
     */
    @FXML
    private ComboBox<Parameter> cmbBoxParameters;
    /**
     * Label where the parameter result will be shown
     */
    @FXML
    private Label lblResult;
    /**
     * Label where the parameter code will be shown
     */
    @FXML
    private Label lblParameter;
    /**
     * Label where the parameter reference min value will be shown
     */
    @FXML
    private Label lblMinValue;
    /**
     * Label where the parameter reference max value will be shown
     */

    @FXML
    private Label lblMaxValue;
    /**
     * The test that was selected
     */
    private Test testSelected;
    /**
     * The constructor instantiates the app and gets its instance
     *
     * @param testSelected the test selected in the parent UI
     */
    public ShowTestDetailsFromTestSelectedController(Test testSelected) {
        this.app = App.getInstance();
    }

    /**
     * The default constructor instantiates the app and gets its instance
     *
     *
     */
    public ShowTestDetailsFromTestSelectedController() {

    }

    /**
     * Whenever this is instanciates it
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param rb The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblParameter.setText("");
        lblResult.setText("");
        lblMaxValue.setText("");
        lblMinValue.setText("");
    }

    /**
     * This method will return an ObservableList of Parameter objects
     *
     * @return observable list containing parameter objects
     */
    public ObservableList<Parameter> getParametersFromTest(Test test) {
        listOfParametersFromTest = App.getInstance().getCompany().getTestStore().getTestByInternalCode(test.getInternalCode()).getParameter();
        ObservableList<Parameter> parameterObservableList = FXCollections.observableArrayList();
        parameterObservableList.addAll(listOfParametersFromTest);
        return parameterObservableList;

    }

    /**
     * This is used to associate the parent ui to this controller
     * @param menuCctGUISceneController the parentUI
     * @param test the test selected before
     */
    public void associarParentUI(ShowClientTestsController menuCctGUISceneController, Test test) {
        this.menuCctUI = menuCctGUISceneController;
        this.testSelected = test;
        ObservableList<Parameter> parameterObservableList = getParametersFromTest(test);
        cmbBoxParameters.setItems(parameterObservableList);
        cmbBoxParameters.setPromptText("Select the parameter you want to see.");


    }

    /**
     * When a parameter is selected this method is executed. It shows the information about the parameter selected
     * @param event being selected
     */
    @FXML
    private void selectFromComboBox(ActionEvent event) {
        Parameter parameterSelected = cmbBoxParameters.getSelectionModel().getSelectedItem();
        String resultString = "";
        String maxValueString = "";
        String minValueString = "";
        if (!parameterSelected.getCode().equals("HDL00")) {

            resultString = String.format("%s %s", parameterSelected.getTestResult().getResult(), getMetricsFor(parameterSelected.getCode()));

            maxValueString = String.format("%s %s", parameterSelected.getTestResult().getReferenceValue().getMaxValue(), getMetricsFor(parameterSelected.getCode()));

            minValueString = String.format("%s %s", parameterSelected.getTestResult().getReferenceValue().getMinValue(), getMetricsFor(parameterSelected.getCode()));
        }else {
            resultString = String.format("%s %s", parameterSelected.getTestResult().getResult(), getMetricsFor(parameterSelected.getCode()));
            maxValueString = "Doesn't contain reference value";
            minValueString = "Doesn't contain reference value";
        }

        lblParameter.setText(parameterSelected.getCode());
        lblResult.setText(resultString);
        lblMaxValue.setText(maxValueString);
        lblMinValue.setText(minValueString);

    }

    /**
     * String containing the metrics for a parameter
     * @param parameter the parameter selected
     * @return a string containing the metrics for the parameter selected
     */

    private String getMetricsFor(String parameter) {
        TestType testType = new TestType();
        return testType.getMetricsBasedOnTestType(parameter);
    }

}


