package app.ui.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import app.controller.App;
import app.domain.model.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;

public class SeeTestsFromClientController implements Initializable {

    private MenuCctGUISceneController menuCctUI;
    private App app;
    private List<Client> clients;
    @FXML
    private Label lblTest;

    @FXML
    private TableColumn<?, ?> collumNameClient;

    @FXML
    private TableColumn<?, ?> collumTinNumber;

    public SeeTestsFromClientController() {
        this.app = App.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        lblTest.setText(String.format("%s tests", app.getCurrentUserSession().getUserName()));
    }

    public void associarParentUI(MenuCctGUISceneController menuCctGUISceneController) {
        this.menuCctUI = menuCctGUISceneController;
    }




}
