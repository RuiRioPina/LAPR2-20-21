package app.ui.gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import app.controller.App;
import app.domain.model.Client;
import app.domain.model.Test;
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

/**
 * This class controls the UI that shows all the tests from a selected client.It implements the Initializable class so that it is initializes the overridden method whenever it this class is instantiated.
 */
public class ShowClientTestsController implements Initializable {
    /**
     * Its parent controller
     */
    private ShowListOfClientsController menuCctUI;
    /**
     * Declaration of the App class
     */
    private App app;
    /**
     * Declaration of a list of tests from the client selected in its parent ui
     */
    private List<Test> listOfTestsFromClient;
    /**
     * The test Selected
     */
    private Test testSelected;
    /**
     * The client Selected
     */
    private Client client;

    /**
     * Getter for the test selected
     * @return Test
     */
    public Test getTestSelected() {
        return testSelected;
    }

    /**
     * A label that says tests
     */
    @FXML
    private Label lblTest;
    /**
     * The table where the tests are shown
     */
    @FXML
    private TableView<Test> tableView;
    /**
     * The column containing the NhsCode of a test
     */
    @FXML
    private TableColumn<Test, String> columnnNhsCode;
    /**
     * The column containing the Internal Code of a test
     */
    @FXML
    private TableColumn<Test, Long> columnInternalNumber;

    /**
     * Default constructor which instanciates app
     */
    public ShowClientTestsController() {
        this.app = App.getInstance();
    }

    /**
     * Whenever this is instanciates it
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param rb The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//
    }


    /**
     * This method will return an ObservableList of Client objects
     *
     * @return observable list containg client objects
     */
    public ObservableList<Test> getClientsTests() {
        ObservableList<Test> testsFromClient = FXCollections.observableArrayList();
        testsFromClient.addAll(listOfTestsFromClient);
        return testsFromClient;
    }

    /**
     * This is used to associate the parent ui to this controller
     * @param menuCctGUISceneController the parentUI
     * @param client the client selected before
     */

    public void associarParentUI(ShowListOfClientsController menuCctGUISceneController, Client client) {
        this.menuCctUI = menuCctGUISceneController;
        this.client = client;
        listOfTestsFromClient = App.getInstance().getCompany().getTestStore().getTestsFromClient(client);
        lblTest.setText(String.format("%s tests", app.getCurrentUserSession().getUserName()));

        columnnNhsCode.setCellValueFactory(new PropertyValueFactory<>("nhsCode"));
        columnInternalNumber.setCellValueFactory(new PropertyValueFactory<>("internalCode"));
        //load data
        tableView.setItems(getClientsTests());
        tableView.setRowFactory( tv -> {
		    TableRow<Test> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		            Test rowData = row.getItem();
		            showTest(rowData);
		        }
		    });
		    return row ;
		});

    }

    private void showTest(Test test) {
    	Stage stage = loadViewTestsUi(test);
		if(stage == null) {
			return;
		}
		
		stage.showAndWait();
	}

    /**
     * Loads the next Ui to be shown
     * @param test the test selected
     * @return stage containing the next view to be shown
     */
	private Stage loadViewTestsUi(Test test) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ShowTestsDetailsFromSelectedTest.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage novoViewTestsStage = new Stage();
            novoViewTestsStage.initModality(Modality.APPLICATION_MODAL);
            novoViewTestsStage.setTitle("Tests");
            novoViewTestsStage.setResizable(false);
            novoViewTestsStage.setScene(scene);
            
            ShowTestDetailsFromTestSelectedController novoViewTestsUI = loader.getController();
            novoViewTestsUI.associarParentUI(this, test);
                
            return novoViewTestsStage;
        } catch (IOException ex) {
            Utils.createAlert(Alert.AlertType.ERROR, "Error", ex.getMessage());
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
