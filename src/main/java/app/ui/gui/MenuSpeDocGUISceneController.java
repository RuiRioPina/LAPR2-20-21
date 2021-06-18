package app.ui.gui;

import app.controller.App;
import app.ui.console.SpecialistDoctorUI;
import app.ui.console.WriteReportUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class MenuSpeDocGUISceneController {

    private MainMenuGUISceneController menuM;
    private final App app;

    public void associarParentUI(MainMenuGUISceneController mainM) {
        this.menuM= mainM;
    }

    public MenuSpeDocGUISceneController() {
        this.app = App.getInstance();
    }

    @FXML
    private Label lblSpeDoc;

    @FXML
    void menuBackAction(ActionEvent event) {
        Window window = lblSpeDoc.getScene().getWindow();
        window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    @FXML
    void menuDiagAndRepAction(ActionEvent event) {
        Stage stg = (Stage) lblSpeDoc.getScene().getWindow();
        stg.hide();
        WriteReportUI sDUI = new WriteReportUI();
        sDUI.run();
        stg.show();
    }

}
