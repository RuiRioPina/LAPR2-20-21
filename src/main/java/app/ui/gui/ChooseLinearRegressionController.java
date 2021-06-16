package app.ui.gui;
import app.ui.gui.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ChooseLinearRegressionController {

    @FXML
    private Label lblChoose;

    @FXML
    private Button btnSimpleLinearRegression;

    @FXML
    private Button btnMultilinearRegression;
    private MenuAdminGUISceneController menuUI;

    @FXML
    void btnMultilinearRegressionClick(ActionEvent event) {
Stage stage1=loadMultiLinearRegressionUI();
if (stage1==null){
    return;
}
stage1.showAndWait();
    }

    @FXML
    void btnSimpleLinearRegressionClick(ActionEvent event) {
Stage stage1=loadLinearRegressionUI();
if (stage1==null){
    return;
}
stage1.showAndWait();
    }
    public void associarParentUI(MenuAdminGUISceneController menuAdminGUISceneController) {
        this.menuUI = menuAdminGUISceneController;
    }
    private Stage loadLinearRegressionUI() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GenerateSimpleLinearNHSReport.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage novoViewTestsStage = new Stage();
            novoViewTestsStage.initModality(Modality.APPLICATION_MODAL);
            novoViewTestsStage.setTitle("Tests");
            novoViewTestsStage.setResizable(false);
            novoViewTestsStage.setScene(scene);

            GenerateSimpleLinearReportController newChooseTestsUi = loader.getController();
            newChooseTestsUi.associarParentUI(this);

            return novoViewTestsStage;
        } catch (IOException ex) {
            Utils.createAlert(Alert.AlertType.ERROR, "Erro", ex.getMessage());
            System.out.println(ex.getMessage());
            return null;
        }
    }
    private Stage loadMultiLinearRegressionUI() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GenerateMultiLinearNHSReport.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage novoViewTestsStage = new Stage();
            novoViewTestsStage.initModality(Modality.APPLICATION_MODAL);
            novoViewTestsStage.setTitle("Tests");
            novoViewTestsStage.setResizable(false);
            novoViewTestsStage.setScene(scene);

            GenerateMultiLinearReportController newChooseTestsUi= loader.getController();
            newChooseTestsUi.associarParentUI(this);

            return novoViewTestsStage;
        } catch (IOException ex) {
            Utils.createAlert(Alert.AlertType.ERROR, "Erro", ex.getMessage());
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
