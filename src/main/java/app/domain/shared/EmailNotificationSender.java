package app.domain.shared;

import app.domain.model.Client;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class EmailNotificationSender {
    private Client client;
    public EmailNotificationSender(Client client){
        this.client=client;
    }
    public boolean sendTestCompletedNotification()throws  IOException,InterruptedException{
        String nomeficheiro = "TestCompletedNotification.txt";
        try (PrintWriter out = new PrintWriter(nomeficheiro)) {
            out.println("Hello "+ client.getName());
            out.println("Your Test Results are ready to be received.");
        } catch (IOException e) {
            System.out.println("The file has not been created since there was an error. Please try again.");
        }
        File file = new File(nomeficheiro);
        if (file.createNewFile()) {
            System.out.println("Email sent!");
        }
        Desktop desktop = Desktop.getDesktop();
        Thread.sleep(500);
        desktop.open(file);
        return true;
    }
}
