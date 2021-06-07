package app.ui.gui.utils;

import javafx.scene.control.Alert;

public class Utils {

	/**
	 * Mostrar um alerta
	 * @param tipoAlerta Tipo do alerta
	 * @param cabecalho Cabe�alho do alerta
	 * @param mensagem Mensagem do alerta
	 */
	static public void criarAlerta(Alert.AlertType tipoAlerta, String cabecalho, String mensagem) {
        Alert alerta = new Alert(tipoAlerta);
        
        alerta.setTitle("Aplica��o");
        alerta.setHeaderText(cabecalho);
        alerta.setContentText(mensagem);
        
        alerta.showAndWait();
    }
}
