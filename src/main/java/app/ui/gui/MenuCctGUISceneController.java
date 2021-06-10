package app.ui.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import app.controller.App;
import app.ui.gui.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuCctGUISceneController implements Initializable {

    private MainMenuGUISceneController menuUI;
    private App app;
    @FXML
    private Label lblNameClient;

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

    private Stage loadViewTestsUi() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ShowListOfClientsScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage novoViewTestsStage = new Stage();
            novoViewTestsStage.initModality(Modality.APPLICATION_MODAL);
            novoViewTestsStage.setTitle("Tests");
            novoViewTestsStage.setResizable(false);
            novoViewTestsStage.setScene(scene);

            ShowListOfClientsController novoViewTestsUI = loader.getController();
            novoViewTestsUI.associarParentUI(this);

            return novoViewTestsStage;
        } catch (IOException ex) {
            Utils.criarAlerta(Alert.AlertType.ERROR, "Erro", ex.getMessage());
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @FXML
    private void menuViewTestsAction(ActionEvent event) {
        Stage stage1 = loadViewTestsUi();
        if(stage1 == null) {
            return;
        }

        stage1.showAndWait();
    }
}
