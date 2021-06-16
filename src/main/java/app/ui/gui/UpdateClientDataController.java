package app.ui.gui;

import app.controller.App;
import app.domain.model.Client;
import app.ui.Main;
import auth.domain.model.Email;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

public class UpdateClientDataController implements Initializable {
    private MenuClientGUISceneController menuClientUI;
    private App app;

    public void associarParentUI(MenuClientGUISceneController menuClientGUISceneController) {
        this.menuClientUI = menuClientGUISceneController;
    }
    public UpdateClientDataController() {
        app = App.getInstance();
    }


    @FXML
    private TextField updateTwo;

    @FXML
    private TextField updateThree;

    @FXML
    private Label labelThree;

    @FXML
    private Label labelOne;

    @FXML
    private Button btnUpdate;

    @FXML
    private TextField updateFour;

    @FXML
    private Label labelFour;

    @FXML
    private TextField updateOne;

    @FXML
    private Label labelTwo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String email = String.valueOf(app.getCurrentUserSession().getUserId());
        Client client = app.getCompany().getClientList().getClientByEmail(email);
        labelOne.setText(client.getName());
        labelTwo.setText(String.valueOf(client.getPhoneNumber()));
        labelThree.setText(client.getAddress());
        labelFour.setText(client.getSex());
        updateOne.setText(client.getName());
        updateTwo.setText(String.valueOf(client.getPhoneNumber()));
        updateThree.setText(client.getAddress());
        updateFour.setText(client.getSex());
    }

    @FXML
    public void updateInfo(javafx.event.ActionEvent actionEvent) throws MalformedURLException {
        String name = updateOne.getText();
        String phoneNumber = updateTwo.getText();
        String address = updateThree.getText();
        String sex = updateFour.getText();
        String email = String.valueOf(app.getCurrentUserSession().getUserId());
        Client client = app.getCompany().getClientList().getClientByEmail(email);
        boolean validation = true;
        if (name.length() > 35 || name.length() == 0){
            validation = false;
        }
        if (phoneNumber.length() != 11){
            validation = false;
        }
        if ( address.length() > 90 || address.length() == 0){
            validation = false;
        }
            if (validation) {
                client.setName(name);
                client.setPhoneNumber(Long.parseLong(phoneNumber));
                client.setAddress(address);
                client.setSex(sex);
                JOptionPane.showMessageDialog(null, "Client Data has been updated.");
                Stage stage = (Stage) btnUpdate.getScene().getWindow();
                stage.close();
                System.out.println(App.getInstance().getCurrentUserSession().getUserName());
            } else {
                JOptionPane.showMessageDialog(null,"Client Data has not been updated." + '\n' + '\n'+
                        "Be aware that: " + '\n' + '\n' +
                        "Name can not have more than 35 characters." + '\n' +
                        "Phone number is a 11 digit number." + '\n' +
                        "Adress can not have more than 90 characters.");
            }

         }
        }



