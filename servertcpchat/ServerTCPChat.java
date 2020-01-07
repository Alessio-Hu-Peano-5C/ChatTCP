/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servertcpchat;

import java.io.IOException;
import java.net.*;

/**
 *
 * @author hu.alessio
 */
public class ServerTCPChat {

    public static void main(String[] args) {

        int porta = 5489;

        try {
            ServerSocket server = new ServerSocket(porta);
            System.out.println("Server chatTCP in esecuzione...");

            while (true) {
                Users w;
                try {
                    Socket s = server.accept();
                    w = new Users(s);
                    Thread t = new Thread(w);
                    t.start();
                } catch (IOException e) {
                    System.out.println("Connessione NON riuscita con client: ");
                }
            }
        } catch (IOException e) {
            System.out.println("Error! Porta: " + porta + " non disponibile");
        }

    }

}
