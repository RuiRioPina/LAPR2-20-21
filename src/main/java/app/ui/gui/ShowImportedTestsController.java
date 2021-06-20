package app.ui.gui;


import app.controller.App;
import app.domain.model.Test;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Scene controller to show Imported Tests.
 */
public class ShowImportedTestsController  implements Initializable {
    private MenuLabCooGUISceneController menu;
    private App app;
    private List<Test> importedTests;
    public ShowImportedTestsController() {
        this.app = App.getInstance();
    }

    /**
     * Method of association.
     * @param menuLabCooGUISceneController - menu Lab Coordinator.
     */
    public void associarParentUI(MenuLabCooGUISceneController menuLabCooGUISceneController) {
    this.menu = menuLabCooGUISceneController;
    }
    @FXML
    private ListView<Test> listView;

    /**
     * Method to set java fx elements.
     * @param url - The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle - The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        importedTests = App.getInstance().getCompany().getImportedTests();

        ObservableList<Test> obsTest;
        obsTest = FXCollections.observableArrayList(importedTests);
        listView.setItems(obsTest);
        List <Test> newImpTests = new ArrayList<>();
        App.getInstance().getCompany().setImportedTests(newImpTests);
    }
}
