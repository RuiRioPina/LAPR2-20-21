package app.ui.gui;

import java.net.URL;
import java.util.ResourceBundle;

import app.controller.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class MenuClientGUISceneController implements Initializable {
	
	private MainMenuGUISceneController menuUI;
	private App app;
	@FXML
	private Label lblNameClient;
	
	public MenuClientGUISceneController() {
		this.app = App.getInstance();
	}
	
	@Override
    public void initialize(URL url, ResourceBundle rb){
        lblNameClient.setText(String.format("Welcome, %s", app.getCurrentUserSession().getUserName()));
    }

	public void associarParentUI(MainMenuGUISceneController mainMenuGUISceneController) {
		this.menuUI = mainMenuGUISceneController;
	}
}
