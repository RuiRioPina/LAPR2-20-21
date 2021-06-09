package app.ui.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import app.controller.App;
import app.domain.model.Client;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class SeeTestsFromClientController implements Initializable {

    private MenuCctGUISceneController menuCctUI;
    private App app;
    private List<Client> listOfClients;

    @FXML
    private Label lblTest;

    @FXML
    private TableView<Client> tableView;

    @FXML
    private TableColumn<Client, String> collumNameClient;

    @FXML
    private TableColumn<Client, Long> collumTinNumber;


    public SeeTestsFromClientController() {
        this.app = App.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblTest.setText(String.format("%s tests", app.getCurrentUserSession().getUserName()));

        listOfClients = App.getInstance().getCompany().getClientList().getClients();

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


    public void associarParentUI(MenuCctGUISceneController menuCctGUISceneController) {
        this.menuCctUI = menuCctGUISceneController;
    }


}
