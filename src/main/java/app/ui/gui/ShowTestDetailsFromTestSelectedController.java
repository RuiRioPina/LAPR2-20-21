package app.ui.gui;

import app.controller.App;
import app.domain.model.Parameter;
import app.domain.model.Test;
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

public class ShowTestDetailsFromTestSelectedController implements Initializable {

    private ShowClientTestsController menuCctUI;
    private App app;
    private List<Parameter> listOfParametersFromTest;

    @FXML
    private Label lblVerificationDate;

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


    public ShowTestDetailsFromTestSelectedController() {
        this.app = App.getInstance();
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
        ShowClientTestsController showClientTestsController = new ShowClientTestsController();
        ObservableList<Parameter> parameterObservableList = FXCollections.observableArrayList();
        listOfParametersFromTest = showClientTestsController.getTestSelected().getParameter();
        parameterObservableList.addAll(listOfParametersFromTest);
        return parameterObservableList;

    }





    public void associarParentUI(ShowClientTestsController menuCctGUISceneController) {
        this.menuCctUI = menuCctGUISceneController;
    }


    public void btnTestDetails(ActionEvent actionEvent) {

    }
}


