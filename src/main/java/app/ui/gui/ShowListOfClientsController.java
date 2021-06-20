package app.ui.gui;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import app.controller.App;
import app.domain.model.Client;
import app.domain.model.sortingAlgorithms.SortingAlgorithms;
import app.domain.shared.Configuration;
import app.ui.gui.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controller of the Scene which shows all clients that have at least one validated test. It implements the Initializable class so that it is initializes the overridden method whenever it this class is instantiated
 */

public class ShowListOfClientsController implements Initializable {
    /**
     * the controller which is the parent of this one
     */
    private MenuCctGUISceneController menuCctUI;
    /**
     * Declaration of the App class
     */
    private final App app;
    /**
     * Declaration of a list of clients
     */
    private List<Client> listOfClients;
    /**
     * Instantiation of a list containing the names of the clients which will then be used to sort the clients.
     */
    List<String> names = new ArrayList<>();
    /**
     * Instantiation of a list containing the tins of the clients which will then be used to sort the clients.
     */
    List<Long> tins = new ArrayList<>();
    /**
     * A label saying tests
     */
    @FXML
    private Label lblTest;
    /**
     * The table that appears on the scene.
     */
    @FXML
    private TableView<Client> tableView;

    /**
     * This method is used to do the sort of the table. It uses Java Reflection to get the sorting algorithm defined by the configuration file
     */
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

    /**
     * the column containing the names of the clients
     */
    @FXML
    private TableColumn<Client, String> collumNameClient;

    /**
     * the column containing the tins of the clients
     */
    @FXML
    private TableColumn<Client, Long> collumTinNumber;

    /**
     * The default constructor instantiates the app and gets its instance
     */

    public ShowListOfClientsController() {
        this.app = App.getInstance();
    }

    /**
     * Whenever this is instanciates it
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param rb The resources used to localize the root object, or null if the root object was not localized.
     */
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

    /**
     * This is used to show the next ui when the client is selected
     * @param client the client is passed to the other controller so that it can be used to get its tests
     */

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

    /**
     * It goes to the next ui in the flow of the functionality
     * @param client the client selected
     * @return the stage of the next ui it will go to
     */
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

    /**
     * it associates this ui with its parent ui
     * @param menuCctGUISceneController the parent ui controller
     */

    public void associarParentUI(MenuCctGUISceneController menuCctGUISceneController) {
        this.menuCctUI = menuCctGUISceneController;
    }


}
