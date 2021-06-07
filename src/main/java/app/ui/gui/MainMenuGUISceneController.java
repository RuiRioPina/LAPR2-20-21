package app.ui.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import app.controller.App;
import app.domain.shared.Constants;
import app.ui.gui.utils.Utils;
import auth.UserSession;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class MainMenuGUISceneController implements Initializable {

	private App app;
	@FXML
	private Label lblInitial;
	
	/**
	 * 
	 */
	public MainMenuGUISceneController() {
		this.app = App.getInstance();
	}

	@Override
    public void initialize(URL url, ResourceBundle rb) {
			
    }
	
	private Stage loadLoginUi() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AuthGUIScene.fxml"));
	        Parent root = loader.load();
	        
	        Scene scene = new Scene(root);
	        
	        Stage novoLoginStage = new Stage();
	        novoLoginStage.initModality(Modality.APPLICATION_MODAL);
	        novoLoginStage.setTitle("Login");
	        novoLoginStage.setResizable(false);
	        novoLoginStage.setScene(scene);
	        
	        AuthGUISceneController novoLoginUI = loader.getController();
	        novoLoginUI.associarParentUI(this);
	        
	        return novoLoginStage;
        } catch (IOException ex) {	
        	Utils.criarAlerta(Alert.AlertType.ERROR, "Erro", ex.getMessage());
            return null;
        }
	}
	
	private Stage loadClientUi() {
//		try {
//			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MenuAdminUiScene.fxml"));
//	        Parent root = loader.load();
//	        
//	        Scene scene = new Scene(root);
//	        
//	        Stage novoAdminStage = new Stage();
//	        novoAdminStage.initModality(Modality.APPLICATION_MODAL);
//	        novoAdminStage.setTitle("Administrador");
//	        novoAdminStage.setMaximized(true);
//	        novoAdminStage.setScene(scene);
//	        novoAdminStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//	            @Override
//	            public void handle(WindowEvent event) {
//	            	Stage window = (Stage)lblInicial.getScene().getWindow();
//	                window.show();
//	            }
//	        });
//	        
//	        MenuAdminUISceneController novoAdminUI = loader.getController();
//	        novoAdminUI.associarParentUI(this);
//	        
//	        return novoAdminStage;
//		} catch (IOException ex) {	
//			Utils.criarAlerta(Alert.AlertType.ERROR, "Erro", ex.getMessage());
//            return null;
//        }
		return null;
	}
	
	private Stage loadAdminUi() {
//		try {
//			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MenuAdminUiScene.fxml"));
//	        Parent root = loader.load();
//	        
//	        Scene scene = new Scene(root);
//	        
//	        Stage novoAdminStage = new Stage();
//	        novoAdminStage.initModality(Modality.APPLICATION_MODAL);
//	        novoAdminStage.setTitle("Administrador");
//	        novoAdminStage.setMaximized(true);
//	        novoAdminStage.setScene(scene);
//	        novoAdminStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//	            @Override
//	            public void handle(WindowEvent event) {
//	            	Stage window = (Stage)lblInicial.getScene().getWindow();
//	                window.show();
//	            }
//	        });
//	        
//	        MenuAdminUISceneController novoAdminUI = loader.getController();
//	        novoAdminUI.associarParentUI(this);
//	        
//	        return novoAdminStage;
//		} catch (IOException ex) {	
//			Utils.criarAlerta(Alert.AlertType.ERROR, "Erro", ex.getMessage());
//            return null;
//        }
		return null;
	}
	
	@FXML
    private void menuExitAction(ActionEvent event) {
        Window window = lblInitial.getScene().getWindow();
        window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
    }
	
	@FXML
    private void menuLoginAction(ActionEvent event) {
        Stage stage1 = loadLoginUi();
        if(stage1 == null) {
        	return;
        }
        stage1.showAndWait();
        
        UserSession sessao = this.app.getCurrentUserSession();
        if(sessao == null) {
        	return;
        }
        
        Stage stage = null;
        if(sessao.isLoggedInWithRole(Constants.ROLE_CLIENT)) {
        	stage = loadClientUi();
        } else if(sessao.isLoggedInWithRole(Constants.ROLE_ADMIN)) {
        	stage = loadAdminUi();
        }
        if(stage == null) {
        	return;
        }
       
        stage.show();
        
        Window window = lblInitial.getScene().getWindow();
        window.hide();
    }
	
	public Label getLabel() {
		return this.lblInitial;
	}
	@FXML
    private void menuDevTeamAction(ActionEvent event) {
		String x = "Development Team:\n\t Jo�o Moreira - 1190709@isep.ipp.pt \n\t Jorge Ferreira - 1201564@isep.ipp.pt \n\t Rafael Leite - 1201566@isep.ipp.pt \n"
				+ "\t Rui Pina - 1201568@isep.ipp.pt \n\t Santiago Azevedo - 1201623@isep.ipp.pt \n";
        Utils.criarAlerta(Alert.AlertType.INFORMATION, "Acerca",
                x);
    }
}
