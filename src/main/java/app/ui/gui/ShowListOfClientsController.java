package app.ui.gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import app.controller.App;
import app.domain.model.Client;
import app.domain.model.SelectionSort;
import app.domain.model.Test;
import app.ui.gui.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ShowListOfClientsController implements Initializable {

    private MenuCctGUISceneController menuCctUI;
    private App app;
    private List<Client> listOfClients;
    Client client;
    private SelectionSort selectionSort = new SelectionSort();


    @FXML
    private Label lblTest;


    @FXML
    private Button btnShowTests;

    @FXML
    private Button clientsTest;

    @FXML
    private TableView<Client> tableView;

    @FXML
    private TableColumn<Client, String> collumNameClient;

    @FXML
    private TableColumn<Client, Long> collumTinNumber;


    public ShowListOfClientsController() {
        this.app = App.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblTest.setText(String.format("%s tests", app.getCurrentUserSession().getUserName()));

        listOfClients = App.getInstance().getCompany().getClientList().getClients();
        //listOfClients = App.getInstance().getCompany().getTestStore().getClientsThatHaveAtLeastOneTestValidated();

        //set up the columns
        collumNameClient.setCellValueFactory(new PropertyValueFactory<>("name"));
        collumTinNumber.setCellValueFactory(new PropertyValueFactory<>("tin"));
        //load data
        tableView.setItems(getClient());

    }

    /**
     * This method will return an ObservableList of Client objects
     * @return observable list containg client objects
     */
    public ObservableList<Client> getClient() {
        ObservableList<Client> clients = FXCollections.observableArrayList();
        clients.addAll(listOfClients);
        return clients;
        /*collumNameClient.setCellValueFactory(new PropertyValueFactory<>("name"));

        collumTinNumber.setCellValueFactory(new PropertyValueFactory<>("tin"));

        tableView.setItems(getClient());
        tableView.getColumns().addAll(collumNameClient,collumTinNumber);
        vBox.getChildren().addAll(tableView);*/
    }

    @FXML
    void showTestsFromSelectedClient(ActionEvent event, Client client) {

    }

    @FXML
    void clickShowTests(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("/fxml/ShowTestsFromSelectedClientsScene.fxml"))));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        clientSelected(event);
        stage.setScene(scene);
        stage.show();

    }



    @FXML
    void clientSelected(ActionEvent event) {
        Client client = tableView.getSelectionModel().getSelectedItem();
        if(client!=null) {
            sendClientToOtherScene(client);
        }else{
            Utils.criarAlerta(Alert.AlertType.ERROR, "Error", "There was a problem with the selection " +
                    "of the client. Please report it to the administrator of the software");
        }

    }


    public void sendClientToOtherScene(Client client) {
         this.client = client;
    }

    public Client getClientSelected() {
        return client;
    }




    public void associarParentUI(MenuCctGUISceneController menuCctGUISceneController) {
        this.menuCctUI = menuCctGUISceneController;
    }


}
