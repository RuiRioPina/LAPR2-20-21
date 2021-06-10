package app.ui.gui;

import app.controller.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

import java.io.File;


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
    }

}
