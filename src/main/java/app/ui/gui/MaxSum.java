package app.ui.gui;

import java.util.*;

import app.controller.App;
import app.controller.IntervalController;
import app.domain.model.Test;
import com.isep.mdis.Sum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.stage.Window;
import javafx.stage.WindowEvent;


public class MaxSum {

    private Calendar dStart;

    private Calendar dEnd;

    @FXML
    private Label lblTestView;
    @FXML
    private TextField txtBrute;
    @FXML
    private TextField txtBench;

    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;

    @FXML
    private LineChart<?, ?> lineChart;


    private App app;

    List<Test> tests = App.getInstance().getCompany().getAllTest();

    private final IntervalController intervalController;

    public MaxSum() {
        this.intervalController = new IntervalController();
    }

    public void loadGraphDataYear() {
        if (this.dStart != null && this.dEnd != null) {
            this.lineChart.getData().clear();
            Map<String, Integer> testsReady = this.intervalController.getReadyTestsByYear(dStart, dEnd);
            Map<String, Integer> testsDiagnosis = this.intervalController.getDiagnosisTestsByYear(dStart, dEnd);
            Map<String, Integer> testsMissingResults = this.intervalController.getMissingResultsTestsByYear(dStart, dEnd);
            Map<String, Integer> testsClients = this.intervalController.getClientsByYear(dStart, dEnd);

            this.xAxis.setLabel("Years");
            this.fillLineChart(testsReady, testsDiagnosis, testsMissingResults, testsClients);
        }

    }

    public void loadGraphDataMonth() {
        if (this.dStart != null && this.dEnd != null) {
            this.lineChart.getData().clear();
            Map<String, Integer> testsReady = this.intervalController.getReadyTestsByMonth(dStart, dEnd);
            Map<String, Integer> testsDiagnosis = this.intervalController.getDiagnosisTestsByMonth(dStart, dEnd);
            Map<String, Integer> testsMissingResults = this.intervalController.getMissingResultsTestsByMonth(dStart, dEnd);
            Map<String, Integer> testsClients = this.intervalController.getClientsByMonth(dStart, dEnd);

            this.xAxis.setLabel("Months");
            this.fillLineChart(testsReady, testsDiagnosis, testsMissingResults, testsClients);
        }
    }

    public void loadGraphDataWeek() {
        if (this.dStart != null && this.dEnd != null) {
            this.lineChart.getData().clear();
            Map<String, Integer> testsReady = this.intervalController.getReadyTestsByWeek(dStart, dEnd);
            Map<String, Integer> testsDiagnosis = this.intervalController.getDiagnosisTestsByWeek(dStart, dEnd);
            Map<String, Integer> testsMissingResults = this.intervalController.getMissingResultsTestsByWeek(dStart, dEnd);
            Map<String, Integer> testsClients = this.intervalController.getClientsByWeek(dStart, dEnd);

            this.xAxis.setLabel("Weeks");
            this.fillLineChart(testsReady, testsDiagnosis, testsMissingResults, testsClients);
        }
    }

    public void loadGraphDataDay() {
        if (this.dStart != null && this.dEnd != null) {
            this.lineChart.getData().clear();
            Map<String, Integer> testsReady = this.intervalController.getReadyTestsByDay(dStart, dEnd);
            Map<String, Integer> testsDiagnosis = this.intervalController.getDiagnosisTestsByDay(dStart, dEnd);
            Map<String, Integer> testsMissingResults = this.intervalController.getMissingResultsTestsByDay(dStart, dEnd);
            Map<String, Integer> testsClients = this.intervalController.getClientsByDay(dStart, dEnd);

            this.xAxis.setLabel("Days");
            this.fillLineChart(testsReady, testsDiagnosis, testsMissingResults, testsClients);
        }
    }

    public void fillLineChart(Map<String, Integer> testsReady, Map<String, Integer> testsDiagnosis, Map<String, Integer> testsMissingResults, Map<String, Integer> lstClients) {
        var seriesReady = new XYChart.Series();
        seriesReady.setName("Number of Ready Tests");

        SortedSet<String> keys = new TreeSet<>(testsReady.keySet());
        for (String key : keys) {
            seriesReady.getData().add(new XYChart.Data(key, testsReady.get(key)));
            // do something
        }

        var seriesDiagnosis = new XYChart.Series();
        seriesDiagnosis.setName("Number of Diagnosis Tests");

        keys = new TreeSet<>(testsDiagnosis.keySet());
        for (String key : keys) {
            seriesDiagnosis.getData().add(new XYChart.Data(key, testsDiagnosis.get(key)));
            // do something
        }

        var seriesMissingResults = new XYChart.Series();
        seriesMissingResults.setName("Number of Missing Results Tests");

        keys = new TreeSet<>(testsMissingResults.keySet());
        for (String key : keys) {
            seriesMissingResults.getData().add(new XYChart.Data(key, testsMissingResults.get(key)));
            // do something
        }

        var seriesNumberClients = new XYChart.Series();
        seriesNumberClients.setName("Number of Clients");

        keys = new TreeSet<>(lstClients.keySet());
        for (String key : keys) {
            seriesNumberClients.getData().add(new XYChart.Data(key, lstClients.get(key)));
            // do something
        }

        this.yAxis.setLabel("Number of Tests/Clients");
        lineChart.getData().add(seriesReady);
        lineChart.getData().add(seriesDiagnosis);
        lineChart.getData().add(seriesMissingResults);
        lineChart.getData().add(seriesNumberClients);
    }


    public void associarParentUI(TwoDatesInterval twoDatesInterval, String dStart, String dEnd) {
        // this.twoDatesInterval = twoDatesInterval;
        this.dStart = IntervalController.tStringToCalendar(dStart);
        this.dEnd = IntervalController.tStringToCalendar(dEnd);

        if (dStart == null) {
            txtBrute.setText("Starting Date invalid");
            txtBench.setText("Starting Date invalid");
        }
        if (dEnd == null) {
            txtBrute.setText("Ending Date invalid");
            txtBench.setText("Ending Date invalid");
        }
        if (dStart == null && dEnd == null) {
            txtBrute.setText("Both dates are invalid");
            txtBench.setText("Both dates are invalid");
        }
        if (dStart != null && dEnd != null) {
            int[] arrayMax = this.intervalController.getArrayBeforeMax(dStart, dEnd);
            if (arrayMax == null) {
                txtBrute.setText("Both dates are invalid");
            } else {
                int[] list1 = this.intervalController.getArrayMax(arrayMax);
                int[] list2 = Sum.Max(arrayMax);
                txtBrute.setText(Arrays.toString(list1));
                txtBench.setText(Arrays.toString(list2));
            }
        }
    }

    public IntervalController getController() {
        return this.intervalController;
    }


    @FXML
    private void menuExitAction(ActionEvent event) {
        Window window = lblTestView.getScene().getWindow();
        window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
    }


}

