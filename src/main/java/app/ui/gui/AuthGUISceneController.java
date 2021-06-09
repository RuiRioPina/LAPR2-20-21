package app.ui.gui;

import app.controller.App;
import app.domain.shared.Constants;
import auth.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class AuthGUISceneController {

    private MainMenuGUISceneController menuUI;
    private App app;

    @FXML
    private Label lblLogin;
    @FXML
    private TextField txtLogin;
    @FXML
    private TextField txtPassword;

    public AuthGUISceneController() {
        this.app = App.getInstance();
    }

    @FXML
    private void menuLoginSairAction(ActionEvent event) {
        Window window = lblLogin.getScene().getWindow();
        window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
    }
	
	public void associarParentUI(MainMenuGUISceneController mainMenuGUISceneController) {
        this.menuUI = mainMenuGUISceneController;
    }
	
	public void menuLoginConfirmarAction(ActionEvent event) {
        String user = txtLogin.getText();
        String pass = txtPassword.getText();
        boolean sucesso = false;
        try {
        	sucesso = app.doLogin(user, pass);
        } catch(Exception ex) {
        	Alert erro = new Alert(AlertType.ERROR, ex.getMessage());
        	erro.showAndWait();
        	return;
        }

        if(!sucesso) {
        	Alert erro = new Alert(AlertType.ERROR, "User or Password is incorrect.");
        	erro.showAndWait();
        	return;
        }

        menuLoginSairAction(event);
    }




}
