package app.ui.gui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import app.controller.App;
import app.domain.model.Test;
import app.domain.store.LabCoordinatorStore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class MaxSum {

    private TwoDatesInterval twoDatesInterval;
    @FXML
    private Label lblTestView;
    @FXML
    private TextField txtStart;
    @FXML
    private TextField txtEnd;

    private App app;
    private List<Test> tests;
    /*
    private LabCoordinatorStore labCoordinatorStore;


    @FXML
    private Calendar sDate;
    @FXML
    private Calendar eDate;
    @FXML
    private int[] sum;
    @FXML
    private TextField finalSum;


    public TudoJunto() {
        this.app = App.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){

    }
*/
    public void associarParentUI(TwoDatesInterval twoDatesInterval) {
        this.twoDatesInterval = twoDatesInterval;


    }

    public void showInformation(String start, String end) {
        Calendar sDate = LabCoordinatorStore.tStringToCalendar(start);
        Calendar eDate = LabCoordinatorStore.tStringToCalendar(end);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        if (sDate == null) {
            txtStart.setText("Start Date invalid");
        }
        if (eDate == null) {
            txtStart.setText("Ending Date invalid");
        }
        if (sDate == null && eDate == null) {
            txtStart.setText("Both dates are invalid");
        }
        if (sDate != null && eDate != null) {
            txtStart.setText(dateFormat.format(sDate.getTime()) + " - " + dateFormat.format(eDate.getTime()));
        }

    }





    /*
    public void setMaxSum(MaxSum maxSum) {
        this.maxSum = maxSum;
    }

    public void showCategory() {
        this.lblTestView.setText(LabCoordinatorStore.getMax(sDate,eDate,sum));
        System.out.println(lblTestView);
    }
     */
    @FXML
    private void menuExitAction(ActionEvent event) {
        Window window = lblTestView.getScene().getWindow();
        window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
    }


}
