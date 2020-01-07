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
public class ClientTCPChat {

    public static void main(String[] args) {
        String ip = "127.0.0.1";
        int porta = 1234;
        try {
            InetAddress address = InetAddress.getByName(ip);
            Socket s = new Socket(address, porta);
            Listener listener;
            try {
                listener = new Listener(s);
                Thread thread = new Thread(listener);
                thread.start();
            } catch (Exception e) {
                System.out.println("Connessione NON riuscita con server: ");
            }
            PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String userInput;
            System.out.print(">");
            while ((userInput = br.readLine()) != null) {
                pw.println(userInput);
                System.out.println("Messaggio spedito al server: " + userInput);
                System.out.print(">");
            }
            s.close();
            System.out.println("connessione terminata!");
        } catch (IOException e) {
            System.out.println("Connessione terminata dal server: ");
        }
    }
}
