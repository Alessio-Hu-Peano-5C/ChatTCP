/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienttcpchat;

import java.io.*;
import java.net.*;

/**
 *
 * @author hu.alessio
 */
public class TCPRicezione implements Runnable {

    private Socket client;

    TCPRicezione(Socket client) {
        this.client = client;
        System.out.println("Connesso con: " + client);
    }

    public void run() {

        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (IOException e) {
            System.err.println("IO Error");
        }

        String messaggioDalServer = "";
        try {
            while ((messaggioDalServer = br.readLine()) != null) {
                System.out.println(messaggioDalServer);
                if (messaggioDalServer.contains("Bye")) {
                    client.close();
                    break;
                }
            }
        } catch (IOException e) {
            try {
                System.out.println("Connessione terminata dal Server");
                client.close();
                System.exit(-1);
            } catch (IOException ex) {
                System.err.println("Error nella chiusura del Socket");
            }
        }
    }
}
