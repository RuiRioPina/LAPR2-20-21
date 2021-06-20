package app.ui.gui;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import app.controller.App;
import app.domain.model.Client;
import app.domain.model.Test;
import app.domain.model.sortingAlgorithms.SortingAlgorithms;
import app.domain.shared.Configuration;
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
    private final App app;
    private List<Client> listOfClients;
    List<String> names = new ArrayList<>();
    List<Long> tins = new ArrayList<>();
    @FXML
    private Label lblTest;


    @FXML
    private Button btnShowTests;

    @FXML
    private Button clientsTest;


    // even though it says that tableView can be final, it can't as otherwise it wouldn't be able to show the clients nor the tests
    @FXML
    private TableView<Client> tableView;

    public void sort() {
        Class<?> oClass;
        SortingAlgorithms sortingMethod;
        try {
            oClass = Class.forName(Configuration.getSortingAlogrithm());
            sortingMethod = (SortingAlgorithms) oClass.getDeclaredConstructor().newInstance();
            sortingMethod.sortMethod(names, tins, listOfClients, 1);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


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

        listOfClients = App.getInstance().getCompany().getTestStore().getClientsThatHaveAtLeastOneTestValidated();

        for (Client clts : listOfClients) {
            names.add(clts.getName());
            tins.add(clts.getTin());
        }
        //set up the columns
        collumNameClient.setCellValueFactory(new PropertyValueFactory<>("name"));
        collumTinNumber.setCellValueFactory(new PropertyValueFactory<>("tin"));
        //load data
        tableView.setItems(getClient());
        tableView.sort();
        tableView.setRowFactory( tv -> {
		    TableRow<Client> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		            Client rowData = row.getItem();
		            showClient(rowData);
		        }
		    });
		    return row ;
		});

    }

    private void showClient(Client client) {
		Stage stage = loadViewTestsUi(client);
		if(stage == null) {
			return;
		}
		
		stage.showAndWait();
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
