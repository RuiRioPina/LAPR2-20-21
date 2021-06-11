package app.ui.gui;

import app.controller.App;
import app.ui.console.ImportTests;
import app.ui.gui.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;


public class MenuLabCooGUISceneController {

    private MainMenuGUISceneController menuUI;
    private App app;

    public void associarParentUI(MainMenuGUISceneController mainMenuGUISceneController) {
        menuUI = mainMenuGUISceneController;
    }

    @FXML
    private Button importTests;

    @FXML
    private Label lblNameClient;

    @FXML

    public void menuImportTestsAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(((Node) event.getTarget()).getScene().getWindow());
        String path = file.getAbsolutePath();
        System.out.println(path);
        new ImportTests(path);
        Stage stage1 = loadTestsUi();
        if(stage1 == null) {
            return;
        }
        stage1.showAndWait();
    }

    private Stage loadTestsUi() {
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ShowImpTestsScene.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        Stage novoViewTestsStage = new Stage();
        novoViewTestsStage.initModality(Modality.APPLICATION_MODAL);
        novoViewTestsStage.setTitle("Imported Tests");
        novoViewTestsStage.setResizable(false);
        novoViewTestsStage.setScene(scene);

        ShowImportedTestsController novoViewTestsUI = loader.getController();
        novoViewTestsUI.associarParentUI(this);

        return novoViewTestsStage;
    } catch (IOException ex) {
        Utils.criarAlerta(Alert.AlertType.ERROR, "Erro", ex.getMessage());
        return null;
    }
    }

}
