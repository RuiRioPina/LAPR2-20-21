package app.ui.gui;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import app.controller.App;
import app.domain.model.Client;
import app.domain.model.ParameterCategory;
import app.domain.model.Test;
import app.domain.model.TestResult;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	private TableView<ParameterTableDetail> tbvParameterValues;
	@FXML
	private TableColumn<ParameterTableDetail, String> tbcCategory;
	@FXML
	private TableColumn<ParameterTableDetail, String> tbcParameter;
	@FXML
	private TableColumn<ParameterTableDetail, Double> tbcResult;
	@FXML
	private TableColumn<ParameterTableDetail, String> tbcMetric;
	@FXML
	private TableColumn<ParameterTableDetail, Double> tbcMinValue;
	@FXML
	private TableColumn<ParameterTableDetail, Double> tbcMaxValue;
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
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
		txfDate.setText(format.format(this.test.getRegistrationDate().getTime()));
		tbvParameterValues.setItems(loadData());
        tbcCategory.setCellValueFactory(new PropertyValueFactory<ParameterTableDetail, String>("category"));
        tbcParameter.setCellValueFactory(new PropertyValueFactory<ParameterTableDetail, String>("parameter"));
        tbcResult.setCellValueFactory(new PropertyValueFactory<ParameterTableDetail, Double>("result"));
        tbcMetric.setCellValueFactory(new PropertyValueFactory<ParameterTableDetail, String>("metric"));
        tbcMinValue.setCellValueFactory(new PropertyValueFactory<ParameterTableDetail, Double>("minValue"));
        tbcMaxValue.setCellValueFactory(new PropertyValueFactory<ParameterTableDetail, Double>("maxValue"));
		txaReport.setText(this.test.getReport().getReport());
		txfInternalCode.setEditable(false);
		txfNhsCode.setEditable(false);
		txfTestType.setEditable(false);
		txfDate.setEditable(false);
		tbcCategory.setSortable(false);
		tbcParameter.setSortable(false);
		tbcResult.setSortable(false);
		tbcMetric.setSortable(false);
		tbcMinValue.setSortable(false);
		tbcMaxValue.setSortable(false);
		txaReport.setEditable(false);
	}
	
	private ObservableList<ParameterTableDetail> loadData() {
		ObservableList<ParameterTableDetail> data = FXCollections.observableArrayList();
		for(ParameterCategory pc : this.test.getParameterCategory()) {
			for(TestResult result : this.test.getTestResult()) {
				if(result.getParameter().getPc().contains(pc)) {
					String category = pc.getName();
					ParameterTableDetail item = new ParameterTableDetail(category, 
							result.getParameter().getDescription(), 
							result.getResult(), 
							result.getReferenceValue().getMetric(), 
							result.getReferenceValue().getMinValue(), 
							result.getReferenceValue().getMaxValue());
					data.add(item);	
				}
			}
		}

        return data;
	}
	
	@FXML
    private void menuExitAction(ActionEvent event) {
        Window window = lblTestView.getScene().getWindow();
        window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
    }
	
	public class ParameterTableDetail {
		private String category;
		private String parameter;
		private double result;
		private String metric;
		private double minValue;
		private double maxValue;
		
		public ParameterTableDetail(String category, String parameter, double result, String metric,
				double minValue, double maxValue) {
			this.category = category;
			this.parameter = parameter;
			this.result = result;
			this.metric = metric;
			this.minValue = minValue;
			this.maxValue = maxValue;
		}

		public String getCategory() {
			return category;
		}

		public String getParameter() {
			return parameter;
		}

		public double getResult() {
			return result;
		}

		public String getMetric() {
			return metric;
		}

		public double getMinValue() {
			return minValue;
		}

		public double getMaxValue() {
			return maxValue;
		}
	}
}
