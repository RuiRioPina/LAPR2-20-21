package app.ui.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import app.controller.App;
import app.domain.model.Client;
import app.domain.model.sortingAlgorithms.SelectionSort;
import app.ui.gui.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class ShowListOfClientsController implements Initializable {

    private MenuCctGUISceneController menuCctUI;
    private App app;
    private List<Client> listOfClients;
    Client client;

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
        List<String> names = new ArrayList<>();
        List<Long> tins = new ArrayList<>();
        for (Client clts:listOfClients) {
            names.add(clts.getName());
            tins.add(clts.getTin());
        }
        //set up the columns
        collumNameClient.setCellValueFactory(new PropertyValueFactory<>("name"));
        collumTinNumber.setCellValueFactory(new PropertyValueFactory<>("tin"));
        //load data
        tableView.setItems(getClient());

    }

    /**
     * This method will return an ObservableList of Client objects
     *
     * @return observable list containg client objects
     */
    public ObservableList<Client> getClient() {
        ObservableList<Client> clients = FXCollections.observableArrayList();
        clients.addAll(listOfClients);
        return clients;
    }



    @FXML
    private void clickShowTests(ActionEvent event) throws IOException {
        Client client = tableView.getSelectionModel().getSelectedItem();
        if(client==null){
            Utils.createAlert(Alert.AlertType.ERROR, "Erro", "You must select a client");
        }else {
            Stage stage1 = loadViewTestsUi(client);
            if (stage1 == null) {
                return;
            }
            stage1.showAndWait();
        }
    }

    private Stage loadViewTestsUi(Client client) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ShowTestsFromSelectedClientsScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage novoViewTestsStage = new Stage();
            novoViewTestsStage.initModality(Modality.APPLICATION_MODAL);
            novoViewTestsStage.setTitle("Tests");
            novoViewTestsStage.setResizable(false);
            novoViewTestsStage.setScene(scene);


            ShowClientTestsController novoViewTestsUI = loader.getController();
            novoViewTestsUI.associarParentUI(this, client);

            return novoViewTestsStage;
        } catch (IOException ex) {
            Utils.createAlert(Alert.AlertType.ERROR, "Error", ex.getMessage());
            return null;
        }
    }

    public void associarParentUI(MenuCctGUISceneController menuCctGUISceneController) {
        this.menuCctUI = menuCctGUISceneController;
    }


}
