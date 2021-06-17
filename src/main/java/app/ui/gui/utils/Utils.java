package app.ui.gui.utils;

import javafx.scene.control.Alert;

public final class Utils {

	private Utils() {
		
	}
	
    static public void createAlert(Alert.AlertType tipoAlerta, String cabecalho, String mensagem) {
        Alert alerta = new Alert(tipoAlerta);

        alerta.setTitle("Aplicação");
        alerta.setResizable(true);
        alerta.setHeaderText(cabecalho);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
