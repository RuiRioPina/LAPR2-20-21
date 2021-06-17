package app.ui;

import app.controller.App;
import app.ui.console.MainMenuUI;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Paulo Maio [pam@isep.ipp.pt]
 */
public class Main extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainMenuGUIScene.fxml"));
		Parent root = loader.load();

		Scene scene = new Scene(root);
		scene.getStylesheets().add("/styles/Styles.css");

		stage.setTitle("Main Menu");
		stage.setScene(scene);
		stage.setMaximized(true);
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				App.getInstance().fechar(event);
			}
		});
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void Fechar(WindowEvent event) {
		Alert aviso = new Alert(AlertType.CONFIRMATION, "Deseja mesmo sair?", ButtonType.YES, ButtonType.NO);
		aviso.setHeaderText("Confirma��o da a��o");
		aviso.showAndWait();
		ButtonType resultado = aviso.getResult();
		if(resultado == ButtonType.NO) {
			event.consume();
		}
	}
//    public static void main(String[] args) {
//        try {
//            MainMenuUI menu = new MainMenuUI();
//
//            menu.run();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
