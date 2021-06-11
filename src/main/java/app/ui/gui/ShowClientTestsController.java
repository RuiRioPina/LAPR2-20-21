package app.ui.gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import app.controller.App;
import app.domain.model.Client;
import app.domain.model.Test;
import app.ui.gui.utils.Utils;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ShowClientTestsController implements Initializable {

    private ShowListOfClientsController menuCctUI;
    private App app;
    private List<Test> listOfTestsFromClient;
    private Test testSelected;
    private Client client;

    public Test getTestSelected() {
        return testSelected;
    }

    @FXML
    private Label lblTest;

    @FXML
    private TableView<Test> tableView;

    @FXML
    private TableColumn<Test, String> columnnNhsCode;

    @FXML
    private TableColumn<Test, Long> columnInternalNumber;


    public ShowClientTestsController() {
        this.app = App.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

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
        /*collumNameClient.setCellValueFactory(new PropertyValueFactory<>("name"));

        collumTinNumber.setCellValueFactory(new PropertyValueFactory<>("tin"));

        tableView.setItems(getClient());
        tableView.getColumns().addAll(collumNameClient,collumTinNumber);
        vBox.getChildren().addAll(tableView);*/
    }

    public void sendTestToOtherScene(Test testSelected) {
        this.testSelected = testSelected;
    }


    @FXML
    void clickTestDetails(ActionEvent event) throws IOException {
        Stage stage1 = loadViewTestsUi();
        if (stage1 == null) {
            return;
        }
        stage1.showAndWait();
    }


    public void associarParentUI(ShowListOfClientsController menuCctGUISceneController, Client client) {
        this.menuCctUI = menuCctGUISceneController;
        this.client = client;
        listOfTestsFromClient = App.getInstance().getCompany().getTestStore().getTestsFromClient(client);
        lblTest.setText(String.format("%s tests", app.getCurrentUserSession().getUserName()));


        //Temporario até houver testes validados associados a clientes.

        //listOfTestsFromClient = App.getInstance().getCompany().getTestStore().getTests();

        //set up the columns
        columnnNhsCode.setCellValueFactory(new PropertyValueFactory<>("nhsCode"));
        columnInternalNumber.setCellValueFactory(new PropertyValueFactory<>("internalCode"));
        //load data
        tableView.setItems(getClientsTests());

    }

    private Stage loadViewTestsUi() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ShowTestsDetailsFromSelectedTest.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage novoViewTestsStage = new Stage();
            novoViewTestsStage.initModality(Modality.APPLICATION_MODAL);
            novoViewTestsStage.setTitle("Tests");
            novoViewTestsStage.setResizable(false);
            novoViewTestsStage.setScene(scene);

            Test test = tableView.getSelectionModel().getSelectedItem();

            ShowTestDetailsFromTestSelectedController novoViewTestsUI = loader.getController();
            novoViewTestsUI.associarParentUI(this, test);

            return novoViewTestsStage;
        } catch (IOException ex) {
            Utils.criarAlerta(Alert.AlertType.ERROR, "Erro", ex.getMessage());
            System.out.println(ex.getMessage());
            return null;
        }
    }


    public void btnTestDetails(ActionEvent actionEvent) {

    }
}
