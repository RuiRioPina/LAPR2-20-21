package app.ui.gui.utils;

import javafx.scene.control.Alert;

public class Utils {

	/**
	 * Mostrar um alerta
	 * @param tipoAlerta Tipo do alerta
	 * @param cabecalho Cabeçalho do alerta
	 * @param mensagem Mensagem do alerta
	 */
	static public void criarAlerta(Alert.AlertType tipoAlerta, String cabecalho, String mensagem) {
        Alert alerta = new Alert(tipoAlerta);
        
        alerta.setTitle("Aplicação");
        alerta.setHeaderText(cabecalho);
        alerta.setContentText(mensagem);
        
        alerta.showAndWait();
    }
}
