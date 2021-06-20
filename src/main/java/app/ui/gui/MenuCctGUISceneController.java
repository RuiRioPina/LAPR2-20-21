package app.ui.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import app.controller.App;
import app.ui.console.RecordTestResultsUI;
import app.ui.gui.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This class is the controller of the CCT menu in which you select the option of the US to see.It implements the Initializable class so that it is initializes the overridden method whenever it this class is instantiated.
 */
public class MenuCctGUISceneController implements Initializable {
    /**
     * The parent UI of this controller
     */
    private MainMenuGUISceneController menuUI;
    /**
     * Declaration of the App class
     */
    private App app;
    /**
     * label containg the name of the client
     */
    @FXML
    private Label lblNameClient;
    /**
     * button containg the tests results
     */
    @FXML
    private Button btnTestResults;


    /**
     * Default constructor which instanciates app
     */
    public MenuCctGUISceneController() {
        this.app = App.getInstance();
    }
    /**
     * Whenever this is instanciates it
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param rb The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
    }

    /**
     * Lable saying Clinical Chemistry Technologist
     */
    @FXML
    private Label lblCct;

    /**
     * Associates this class with its parent UI
     * @param mainMenuGUISceneController
     */

    public void associarParentUI(MainMenuGUISceneController mainMenuGUISceneController) {
        this.menuUI = mainMenuGUISceneController;
    }

    /**
     * Load the ShowClientsUI
     * @return stage with the next UI to be shown
     */

    private Stage loadShowClientsUi() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ShowListOfClientsScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage novoShowClientsStage = new Stage();
            novoShowClientsStage.initModality(Modality.APPLICATION_MODAL);
            novoShowClientsStage.setTitle("Tests");
            novoShowClientsStage.setResizable(false);
            novoShowClientsStage.setScene(scene);

            ShowListOfClientsController novoViewTestsUI = loader.getController();
            novoViewTestsUI.associarParentUI(this);

            return novoShowClientsStage;
        } catch (IOException ex) {
            Utils.createAlert(Alert.AlertType.ERROR, "Erro", ex.getMessage());
            System.out.println(ex.getMessage());
            return null;
        }
    }

    /**
     * Whenever this button is pressed the next stage is loaded
     * @param event the button being selected
     */
    @FXML
    void menuViewTestsAction(ActionEvent event) {
        Stage stage = loadShowClientsUi();
        if(stage == null) {
            return;
        }

        stage.showAndWait();
    }

    /**
     * Whenever this button is pressed the next us is run in the console
     * @param event the button being selected
     */
    @FXML
    void menuRecordTestsAction(ActionEvent event) {
        RecordTestResultsUI recordTestResultsUI = new RecordTestResultsUI();
        recordTestResultsUI.run();
    }
}
