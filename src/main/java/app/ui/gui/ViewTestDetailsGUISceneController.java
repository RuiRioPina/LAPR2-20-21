package app.ui.gui;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import app.controller.App;
import app.domain.model.Client;
import app.domain.model.Test;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class ViewTestDetailsGUISceneController implements Initializable {
	
	private ViewTestsGUISceneController menuViewTestsGUI;
	private App app;
	private Test test;
	@FXML
	private Label lblTestView;
	@FXML
	private TextField txfInternalCode;
	@FXML
	private TextField txfNhsCode;
	@FXML
	private TextField txfTestType;
	@FXML
	private TextField txfDate;
	@FXML
	private TableView<Test> tbvParameterValues;
	@FXML
	private TableColumn<Test, String> tbcCategory;
	@FXML
	private TableColumn<Test, Client> tbcParameter;
	@FXML
	private TableColumn<Test, Date> tbcResult;
	@FXML
	private TableColumn<Test, Date> tbcMetric;
	@FXML
	private TableColumn<Test, Date> tbcMinValue;
	@FXML
	private TableColumn<Test, Date> tbcMaxValue;
	@FXML
	private TextArea txaReport;

	public ViewTestDetailsGUISceneController() {
		this.app = App.getInstance();
	}
	
	@Override
    public void initialize(URL url, ResourceBundle rb){
		
    }
	
	public void associarParentUI(ViewTestsGUISceneController menuViewTestsGUISceneController, Test t) {
		this.menuViewTestsGUI = menuViewTestsGUISceneController;
		this.test = t;
		txfInternalCode.setText(test.getInternalCode());
		txfNhsCode.setText(this.test.getNhsCode());
		txfTestType.setText(this.test.getTestType().getDescription());
		txfDate.setText(this.test.getDate());
//		tbvListTest.setItems(loadData());
//        tbcCategory.setCellValueFactory(new PropertyValueFactory<Test, String>("parameterCategory"));
//        tbcClient.setCellValueFactory(new PropertyValueFactory<Test, Client>("nhsCode"));
//        tbcDate.setCellValueFactory(new PropertyValueFactory<Test, Date>("registrationDate"));
		txaReport.setText(this.test.getReport().getReport());
		txfInternalCode.setEditable(false);
		txfNhsCode.setEditable(false);
		txfTestType.setEditable(false);
		txfDate.setEditable(false);
//        tbcCode.setSortable(false);
//        tbcClient.setSortable(false);
//        tbcDate.setSortable(false);
		txaReport.setEditable(false);
	}
	
//	private ObservableList<Test> loadData() {
//		String email = app.getCurrentUserSession().getUserId().getEmail();
//		Client client = app.getCompany().getClientList().getClientByEmail(email);
//		List<Test> tests = app.getCompany().getTestStore().getTestsFromClient(client);
//		ObservableList<Test> data = FXCollections.observableArrayList();
//		data.addAll(tests);
//        data.sort(Comparator.comparing(Test::getRegistrationDate).reversed());
//
//        return data;
//	}
	
	@FXML
    private void menuExitAction(ActionEvent event) {
        Window window = lblTestView.getScene().getWindow();
        window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
    }
}
