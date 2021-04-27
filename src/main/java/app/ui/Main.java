package app.ui;

import app.domain.model.Client;
import app.ui.console.MainMenuUI;

/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Main {

    public static void main(String[] args) {
        Client client = new Client(3123, 31231, 2302, "M",
                "yau@sa.com", 1234, 123, "Ruo");
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        client.generatePassword();
        System.out.println(client.getPassword());
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

        try {
            MainMenuUI menu = new MainMenuUI();

            menu.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
