package app.ui.gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import app.controller.App;
import app.controller.IntervalController;
import app.domain.model.Test;
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
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class TwoDatesInterval implements Initializable {

    private MenuLabCooGUISceneController menu;
    private App app;
    private List<Test> tests;
    private IntervalController controller;

    @FXML
    private Label lblTest;
    @FXML
    private Label lblAlert;
    @FXML
    private TextField txtStart;
    @FXML
    private Button btn;
    @FXML
    private TextField txtEnd;
    @FXML
    private Button cancel;



    /**
     * Initializes the UI class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 //       lblTest.setText(String.format("Introduce two dates"));


    }

    public IntervalController getController() {
        return this.controller;
    }

    public void associarParentUI(MenuLabCooGUISceneController menu) {
        this.menu = menu;
    }



    private Stage loadMaxSumUi(String s, String e) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MaxSum.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage novoViewTestDetailsStage = new Stage();
            novoViewTestDetailsStage.initModality(Modality.APPLICATION_MODAL);
            novoViewTestDetailsStage.setTitle("Max Sum");
            novoViewTestDetailsStage.setMaximized(true);
            novoViewTestDetailsStage.setScene(scene);

            MaxSum maxSum = loader.getController();
            maxSum.associarParentUI(this);
            maxSum.showInformation(txtStart.getText(),txtEnd.getText());
            return novoViewTestDetailsStage;
        } catch (IOException ex) {
            Utils.createAlert(Alert.AlertType.ERROR, "Erro", ex.getMessage());
            return null;
        }
    }

    @FXML
    private void menuView(ActionEvent event) {
        String s=txtStart.getText();
        String e=txtEnd.getText();
        if(s == null || e == null) {
            return;
        }
        Stage stage1 = loadMaxSumUi(s, e);
        if(stage1 == null) {
            return;
        }

        stage1.showAndWait();
    }

    @FXML

    private void menuExitAction(ActionEvent event) {
        Window window = lblTest.getScene().getWindow();
        window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

}
