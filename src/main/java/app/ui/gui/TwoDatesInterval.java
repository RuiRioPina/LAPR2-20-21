package app.ui.gui;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

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
import javafx.scene.control.*;
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
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;


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


    private Stage loadMaxSumUi(String ldStart, String ldEnd) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MaxSum.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage novoViewTestDetailsStage = new Stage();
            novoViewTestDetailsStage.initModality(Modality.APPLICATION_MODAL);
            novoViewTestDetailsStage.setTitle("Max Sum");
            novoViewTestDetailsStage.setMaximized(false);
            novoViewTestDetailsStage.setScene(scene);

            MaxSum maxSum = loader.getController();
            maxSum.associarParentUI(this, ldStart, ldEnd);
            return novoViewTestDetailsStage;
        } catch (IOException ex) {
            Utils.createAlert(Alert.AlertType.ERROR, "Erro", ex.getMessage());
            return null;
        }
    }

    @FXML
    private void menuView(ActionEvent event) throws ParseException {
        String dateBegin = null, dateEnd = null;
        LocalDate sDate = startDate.getValue();
        LocalDate eDate = endDate.getValue();

        // Note, MM is months, not mm
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);


        if (sDate != null) {
            Date beginDate = inputFormat.parse(sDate.toString());
            dateBegin = outputFormat.format(beginDate);
        }
        if (eDate != null) {
            Date endDate = inputFormat.parse(eDate.toString());
            dateEnd = outputFormat.format(endDate);
        }
        Stage stage1 = loadMaxSumUi(dateBegin, dateEnd);
        if (stage1 == null) {

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