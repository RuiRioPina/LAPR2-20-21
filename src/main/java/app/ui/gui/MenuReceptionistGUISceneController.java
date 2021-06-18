package app.ui.gui;

import app.controller.App;

import app.ui.console.RegisterClientUI;
import app.ui.console.TestUI;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class MenuReceptionistGUISceneController extends Application {

    private MainMenuGUISceneController menuMain;
    private final App app;

    public void associarParentUI(MainMenuGUISceneController m) {
        this.menuMain = m;
    }

    public MenuReceptionistGUISceneController() {
        this.app = App.getInstance();
    }

    @Override
    public void start(Stage stage) throws Exception {

    }

    @FXML
        private Label lblReceptionist;

        @FXML
        void menuExitAction(ActionEvent event) {
            Window window = lblReceptionist.getScene().getWindow();
            window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
        }

        @FXML
        void menuRegisterClient(ActionEvent event) {
            Stage stg1 = (Stage) lblReceptionist.getScene().getWindow();
            //stg1.hide();
            RegisterClientUI clientUI = new RegisterClientUI();
            clientUI.run();
            //stg1.show();
        }

        @FXML
        void menuRegisterTest(ActionEvent event) {
            Stage stg = (Stage) lblReceptionist.getScene().getWindow();
            stg.hide();
            TestUI testUI = new TestUI();
            testUI.run();
            stg.show();
        }


}
