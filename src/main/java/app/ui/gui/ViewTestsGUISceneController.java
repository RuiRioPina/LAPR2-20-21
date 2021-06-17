package app.ui.gui;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import app.controller.App;
import app.domain.model.Client;
import app.domain.model.Test;
import app.ui.gui.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class ViewTestsGUISceneController implements Initializable {
	
	private MenuClientGUISceneController menuClientUI;
	private final App app;
	@FXML
	private Label lblTest;
	@FXML
	private TableView<Test> tbvListTest;
	@FXML
	private TableColumn<Test, String> tbcCode;
	@FXML
	private TableColumn<Test, Client> tbcClient;
	@FXML
	private TableColumn<Test, String> tbcDate;
	
	public ViewTestsGUISceneController() {
		this.app = App.getInstance();
	}
	
	@Override
    public void initialize(URL url, ResourceBundle rb){
		String email = String.valueOf(app.getCurrentUserSession().getUserId());
        Client client = app.getCompany().getClientList().getClientByEmail(email);
        lblTest.setText(String.format("%s tests", client.getName()));
        tbvListTest.setItems(loadData());
        tbcCode.setCellValueFactory(new PropertyValueFactory<Test, String>("internalCode"));
        tbcClient.setCellValueFactory(new PropertyValueFactory<Test, Client>("nhsCode"));
        tbcDate.setCellValueFactory(new PropertyValueFactory<Test, String>("registrationDateStr"));
        tbcCode.setSortable(false);
        tbcClient.setSortable(false);
        tbcDate.setSortable(false);
        
		tbvListTest.setRowFactory( tv -> {
		    TableRow<Test> row = new TableRow<Test>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		            Test rowData = row.getItem();
		            showTest(rowData);
		        }
		    });
		    return row ;
		});
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
	        novoViewTestDetailsStage.setMaximized(true);
	        novoViewTestDetailsStage.setScene(scene);
	        
	        ViewTestDetailsGUISceneController novoViewTestsUI = loader.getController();
	        novoViewTestsUI.associarParentUI(this, t);
	        
	        return novoViewTestDetailsStage;
		} catch (IOException ex) {	
			Utils.createAlert(Alert.AlertType.ERROR, "Erro", ex.getMessage());
            return null;
        }
	}
	
	private ObservableList<Test> loadData() {
		String email = app.getCurrentUserSession().getUserId().getEmail();
		Client client = app.getCompany().getClientList().getClientByEmail(email);
		List<Test> tests = app.getCompany().getTestStore().getValidatedTestsFromClient(client);
		ObservableList<Test> data = FXCollections.observableArrayList();
		data.addAll(tests);
        data.sort(Comparator.comparing(Test::getRegistrationDate).reversed());

        return data;
	}
	
	public void seeTest(ActionEvent event) {
		Test t = tbvListTest.getSelectionModel().getSelectedItem();
		if(t == null) {
			Utils.createAlert(AlertType.WARNING, "Invalid", "Please select a test.");
			return;
		}
		
		showTest(t);
	}
	
	private void showTest(Test t) {
		Stage stage1 = loadViewTestDetailUi(t);
        if(stage1 == null) {
        	return;
        }
        
        stage1.showAndWait();
	}
	
	@FXML
    private void menuExitAction(ActionEvent event) {
        Window window = lblTest.getScene().getWindow();
        window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
    }
}
