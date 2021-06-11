package app.ui.gui;


import app.controller.App;
import app.domain.model.Test;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ShowImportedTestsController  implements Initializable {
    private MenuLabCooGUISceneController menu;
    private App app;
    private List<Test> importedTests;
    public ShowImportedTestsController() {
        this.app = App.getInstance();
    }

    public void associarParentUI(MenuLabCooGUISceneController menuLabCooGUISceneController) {
    this.menu = menuLabCooGUISceneController;
    }
    @FXML
    private ListView<Test> listView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        importedTests = App.getInstance().getCompany().getImportedTests();

        ObservableList<Test> obsTest;
        obsTest = FXCollections.observableArrayList(importedTests);
        listView.setItems(obsTest);
        App.getInstance().getCompany().setImportedTests(null);
    }
}
