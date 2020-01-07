/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servertcpchat;

import java.io.*;
import java.net.*;

/**
 *
 * @author hu.alessio
 */
public class Users implements Runnable, InviaMessaggio, RiceviMessaggio {

    private static Manager manager = new Manager();
    private Socket client;
    private PrintWriter pw = null;

    Users(Socket client) {
        this.client = client;
        manager.addClient(this);
        System.out.println("Connesso con: " + client);
    }

    @Override
    public void messaggioReceived(String m) {
        this.manager.sendNewMessaggio(m);
    }

    @Override
    public void sendMessaggio(String messaggio) {

        pw.println("Server --> " + messaggio);

    }

    public void run() {

        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            pw = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Errore: in|out fallito");
            System.exit(-1);
        }

        String messaggio = "";
        int clientPort = client.getPort();
        while (messaggio != null) {
            try {
                messaggio = br.readLine();
                messaggioReceived(messaggio);
                System.out.println(clientPort + " --> " + messaggio);
            } catch (IOException e) {
                System.out.println("lettura da socket fallito");
            }
        }
        try {
            client.close();
            System.out.println("Connessione con client: " + client + " terminata!");
        } catch (IOException e) {
            System.out.println("Errore connessione con client: " + client);
        }
    }
}
