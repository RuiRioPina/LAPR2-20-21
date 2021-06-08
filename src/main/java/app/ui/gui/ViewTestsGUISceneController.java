package app.ui.gui;

import java.net.URL;
import java.util.ResourceBundle;

import app.controller.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class ViewTestsGUISceneController implements Initializable {
	
	private MenuClientGUISceneController menuClientUI;
	private App app;
	@FXML
	private Label lblTest;
	
	public ViewTestsGUISceneController() {
		this.app = App.getInstance();
	}
	
	@Override
    public void initialize(URL url, ResourceBundle rb){
        lblTest.setText(String.format("%s tests", app.getCurrentUserSession().getUserName()));
    }
	
	public void associarParentUI(MenuClientGUISceneController menuClientGUISceneController) {
		this.menuClientUI = menuClientGUISceneController;
	}
	
	
}
