package app.ui.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import app.controller.App;
import app.ui.gui.utils.Utils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
	
	private Stage loadViewTestsUi() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ViewTestsGUIScene.fxml"));
	        Parent root = loader.load();
	        
	        Scene scene = new Scene(root);
	        
	        Stage novoViewTestsStage = new Stage();
	        novoViewTestsStage.initModality(Modality.APPLICATION_MODAL);
	        novoViewTestsStage.setTitle("Tests");
	        novoViewTestsStage.setResizable(false); 
	        novoViewTestsStage.setMaximized(true);
	        novoViewTestsStage.setScene(scene);
	        
	        ViewTestsGUISceneController novoViewTestsUI = loader.getController();
	        novoViewTestsUI.associarParentUI(this);
	        
	        return novoViewTestsStage;
		} catch (IOException ex) {	
			Utils.criarAlerta(Alert.AlertType.ERROR, "Erro", ex.getMessage());
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
