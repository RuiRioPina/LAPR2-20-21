package app.ui.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import app.controller.App;
import app.domain.model.Client;
import app.domain.model.Test;
import app.domain.store.ClientList;
import app.ui.gui.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ViewTestsGUISceneController implements Initializable {
	
	private MenuClientGUISceneController menuClientUI;
	private App app;
	@FXML
	private Label lblTest;
	@FXML
	private TableView<Test> tbvListTest;
	@FXML
	private TableColumn<Test, String> tbcCode;
	@FXML
	private TableColumn<Test, Client> tbcClient;
	@FXML
	private TableColumn<Test, Date> tbcDate;
	
	public ViewTestsGUISceneController() {
		this.app = App.getInstance();
	}
	
	@Override
    public void initialize(URL url, ResourceBundle rb){
        lblTest.setText(String.format("%s tests", app.getCurrentUserSession().getUserName()));
        tbvListTest.setItems(loadData());
        tbcCode.setCellValueFactory(new PropertyValueFactory<Test, String>("internalCode"));
        tbcClient.setCellValueFactory(new PropertyValueFactory<Test, Client>("nhsCode"));
        tbcDate.setCellValueFactory(new PropertyValueFactory<Test, Date>("registrationDate"));
    }
	
	public void associarParentUI(MenuClientGUISceneController menuClientGUISceneController) {
		this.menuClientUI = menuClientGUISceneController;
	}
	
	private Stage loadViewTestDetailUi(Test t) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ViewTestDetailsGUIScene.fxml"));
	        Parent root = loader.load();
	        
	        Scene scene = new Scene(root);
	        
	        Stage novoViewTestDetailsStage = new Stage();
	        novoViewTestDetailsStage.initModality(Modality.APPLICATION_MODAL);
	        novoViewTestDetailsStage.setTitle("Test Details");
	        novoViewTestDetailsStage.setResizable(false); 
	        novoViewTestDetailsStage.setScene(scene);
	        
	        ViewTestDetailsGUISceneController novoViewTestsUI = loader.getController();
	        novoViewTestsUI.associarParentUI(this, t);
	        
	        return novoViewTestDetailsStage;
		} catch (IOException ex) {	
			Utils.criarAlerta(Alert.AlertType.ERROR, "Erro", ex.getMessage());
            return null;
        }
	}
	
	private ObservableList<Test> loadData() {
		List<Test> tests = app.getCompany().getAllTest();
		ObservableList<Test> data = FXCollections.observableArrayList();
        Client client = null;
        String email = app.getCurrentUserSession().getUserId().getEmail();
        ClientList lc = app.getCompany().getClientList();
        List<Client> listClient = lc.getClients();
        for(Client c : listClient) {
        	if(c.getEmail().equalsIgnoreCase(email)) {
        		client = c;
        	}
        }		
        for(Test t : tests) {
        	if(t.getClient().equals(client)) {
        		data.add(t);
        	}
        }
        data.sort(Comparator.comparing(Test::getDate));

        return data;
	}
	
	public void seeTest(ActionEvent event) {
		Test t = tbvListTest.getSelectionModel().getSelectedItem();
		Stage stage1 = loadViewTestDetailUi(t);
        if(stage1 == null) {
        	return;
        }
        
        stage1.showAndWait();
	}
}
