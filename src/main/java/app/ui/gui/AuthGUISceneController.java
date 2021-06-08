package app.ui.gui;

import app.controller.App;
import auth.AuthFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

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

	public void associarParentUI(MainMenuGUISceneController menuUI) {
		this.menuUI = menuUI;
	}

	public void menuLoginConfirmarAction(ActionEvent event) {
		String user = txtLogin.getText();
		String pass = txtPassword.getText();
		boolean sucesso = false;
		try {
			sucesso = app.doLogin(user, pass);
		} catch(Exception ex) {
			if (!sucesso || user.isBlank() || pass.isBlank())
			{
				Alert erro = new Alert(AlertType.ERROR, "Utilizador ou Palavra-Passe erradas.");
				erro.showAndWait();
				return;
			}
		}

		menuLoginSairAction(event);
	}
}
