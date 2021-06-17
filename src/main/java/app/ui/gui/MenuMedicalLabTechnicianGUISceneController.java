package app.ui.gui;

import app.controller.App;
import app.ui.console.GenerateSampleUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class MenuMedicalLabTechnicianGUISceneController {

	private MainMenuGUISceneController menuMain;
	private final App app;
	@FXML
	private Label lblMedLab;
	
	public MenuMedicalLabTechnicianGUISceneController() {
		this.app = App.getInstance();
	}
	
	public void associarParentUI(MainMenuGUISceneController menuMain) {
		this.menuMain = menuMain;
	}

	@FXML 
	private void menuGenerateSampleAction(ActionEvent event) {
		Stage stg = (Stage) lblMedLab.getScene().getWindow();
		stg.hide();
		GenerateSampleUI generateSampleUI = new GenerateSampleUI();
		generateSampleUI.run();
		stg.show();
	}
	
	@FXML
    private void menuExitAction(ActionEvent event) {
        Window window = lblMedLab.getScene().getWindow();
        window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
    }
}
