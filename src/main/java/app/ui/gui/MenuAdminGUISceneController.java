package app.ui.gui;
import app.controller.App;
import app.domain.model.ParameterCategory;
import app.ui.console.*;
import app.ui.gui.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuAdminGUISceneController implements Initializable {
    private MainMenuGUISceneController menuUI;
    private App app;
public MenuAdminGUISceneController(){
    this.app=App.getInstance();
}
    @Override
    public void initialize(URL url, ResourceBundle rb){
    }
    public void associarParentUI(MainMenuGUISceneController mainMenuGUISceneController) {
        this.menuUI = mainMenuGUISceneController;
    }
    @FXML
    private Label lblAdmin;

    @FXML
    void menuExitAction(ActionEvent event) {
        Window window = lblAdmin.getScene().getWindow();
        window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    @FXML
    void btnSendReportToNhsClick(ActionEvent event) {
Stage stage1=loadChooseLinearRegressionUI();
if (stage1==null){
    return;
}
stage1.showAndWait();
    }
    private Stage loadChooseLinearRegressionUI() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ChooseLinearRegression.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage novoViewTestsStage = new Stage();
            novoViewTestsStage.initModality(Modality.APPLICATION_MODAL);
            novoViewTestsStage.setTitle("Tests");
            novoViewTestsStage.setResizable(false);
            novoViewTestsStage.setScene(scene);

            ChooseLinearRegressionController newChooseTestsUi = loader.getController();
            newChooseTestsUi.associarParentUI(this);

            return novoViewTestsStage;
        } catch (IOException ex) {
            Utils.createAlert(Alert.AlertType.ERROR, "Erro", ex.getMessage());
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @FXML 
	private void menuSpecifyTestTypeAction(ActionEvent event) {
		Stage stg = (Stage) lblAdmin.getScene().getWindow();
		stg.hide();
		TestTypeUI testTypeUI = new TestTypeUI();
		testTypeUI.run();
		stg.show();
	}

    @FXML
    void btnCat(ActionEvent event) {
        Stage stga = (Stage) lblAdmin.getScene().getWindow();
        stga.hide();
        ParameterCategoryUI pcUI = new ParameterCategoryUI();
        pcUI.run();
        stga.show();
    }

    @FXML
    void btnPar(ActionEvent event) {
        Stage stgb = (Stage) lblAdmin.getScene().getWindow();
        stgb.hide();
        ParameterUI pUI = new ParameterUI();
        pUI.run();
        stgb.show();
    }

    @FXML
    void btnLab(ActionEvent event) {
        Stage stgc = (Stage) lblAdmin.getScene().getWindow();
        stgc.hide();
        ClinicalAnalysisLaboratoryUI labUI = new ClinicalAnalysisLaboratoryUI();
        labUI.run();
        stgc.show();
    }

    @FXML
    void btnEmployee(ActionEvent event) {
        Stage stgd = (Stage) lblAdmin.getScene().getWindow();
        stgd.hide();
        RegisterEmployeeUI eUI = new RegisterEmployeeUI();
        eUI.run();
        stgd.show();
    }
}
