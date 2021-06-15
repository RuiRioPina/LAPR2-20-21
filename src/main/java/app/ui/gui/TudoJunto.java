package app.ui.gui;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import app.controller.App;
import app.domain.model.Client;
import app.domain.model.DateInterval;
import app.domain.model.Test;
import app.domain.store.LabCoordinatorStore;
import app.ui.gui.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import javax.xml.crypto.Data;

public class TudoJunto {

    private MaxSum maxSum;
    @FXML
    private Label lblTestView;
    @FXML
    private TextField txtStart;
    @FXML
    private TextField txtEnd;

    private App app;
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
    public void associarParentUI(MaxSum maxSum) {
        this.maxSum = maxSum;


    }

    public void showInformation(String a, String b) {
        Calendar s=app.getCompany().getLabCoorStore().tStringToCalendar(a);
        Date data=s.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String strDate = dateFormat.format(data);
        System.out.println(strDate);

        txtStart.setText(strDate);



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
