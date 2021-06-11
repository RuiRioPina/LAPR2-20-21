package app.ui.gui;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import app.controller.App;
import app.domain.model.Client;
import app.domain.model.Test;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewTestDetailsGUISceneController implements Initializable {
	
	private ViewTestsGUISceneController menuViewTestsGUI;
	private App app;
	private Test test;

	public ViewTestDetailsGUISceneController() {
		this.app = App.getInstance();
	}
	
	@Override
    public void initialize(URL url, ResourceBundle rb){
        
    }
	
	public void associarParentUI(ViewTestsGUISceneController menuViewTestsGUISceneController, Test t) {
		this.menuViewTestsGUI = menuViewTestsGUISceneController;
		this.test = t;
	}
}
