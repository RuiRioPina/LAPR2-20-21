package app.ui.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import app.controller.App;
import app.ui.console.RecordTestResultsUI;
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

public class MenuCctGUISceneController implements Initializable {

    private MainMenuGUISceneController menuUI;
    private App app;
    @FXML
    private Label lblNameClient;

    @FXML
    private Button btnTestResults;



    public MenuCctGUISceneController() {
        this.app = App.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
    }
    @FXML
    private Label lblCct;

    public void associarParentUI(MainMenuGUISceneController mainMenuGUISceneController) {
        this.menuUI = mainMenuGUISceneController;
    }

    private Stage loadShowClientsUi() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ShowListOfClientsScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage novoShowClientsStage = new Stage();
            novoShowClientsStage.initModality(Modality.APPLICATION_MODAL);
            novoShowClientsStage.setTitle("Tests");
            novoShowClientsStage.setResizable(false);
            novoShowClientsStage.setScene(scene);

            ShowListOfClientsController novoViewTestsUI = loader.getController();
            novoViewTestsUI.associarParentUI(this);

            return novoShowClientsStage;
        } catch (IOException ex) {
            Utils.createAlert(Alert.AlertType.ERROR, "Erro", ex.getMessage());
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @FXML
    private void menuShowClientsAction(ActionEvent event) {
        Stage stage = loadShowClientsUi();
        if(stage == null) {
            return;
        }

        stage.showAndWait();
    }

    @FXML
    void menuRecordTestsAction(ActionEvent event) {
        RecordTestResultsUI recordTestResultsUI = new RecordTestResultsUI();
        recordTestResultsUI.run();
    }
}
