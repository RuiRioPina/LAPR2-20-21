package app.ui.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import app.controller.App;
import app.domain.model.ClinicalAnalysisLaboratory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ChooseLabGUISceneController implements Initializable {
	
	private MainMenuGUISceneController mainMenu;
	private final App app;
	@FXML
	private Label lblChooseLab;
	@FXML
	private ComboBox cbbLabs;
	
	public ChooseLabGUISceneController() {
		this.app = App.getInstance();
	}

	public void associarParentUI(MainMenuGUISceneController mainMenuGUISceneController) {
		this.mainMenu = mainMenuGUISceneController;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		app.getCompany().setCLA(null);
		List<ClinicalAnalysisLaboratory> labs = app.getCompany().getClinicalAnalysisLaboratoryStore().getLabs();
		this.cbbLabs.getItems().addAll(labs);
		if(labs.size() > 0) {
			this.cbbLabs.setValue(labs.get(0));
		}
	}

	@FXML
	public void selectTestAction (ActionEvent event) {
		Stage stage = (Stage) lblChooseLab.getScene().getWindow();
		ClinicalAnalysisLaboratory cla = (ClinicalAnalysisLaboratory) this.cbbLabs.getValue();
		app.getCompany().setCLA(cla);
		stage.close();
	}
	
}
