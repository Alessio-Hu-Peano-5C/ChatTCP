/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servertcpchat;

import java.util.ArrayList;

/**
 *
 * @author hu.alessio
 */
public class Manager {

    private String messaggio;
    private ArrayList<Users> utenti = new ArrayList<>();

    void addClient(Users worker) {
        this.utenti.add(worker);
    }

    void removeClient(Users worker) {
        this.utenti.remove(worker);
    }

    synchronized void sendNewMessaggio(String m) {
        this.messaggio = m;
        for (Users utenti : this.utenti) {
            utenti.sendMessaggio(this.messaggio);
        }
    }

}

interface InviaMessaggio {

    public void sendMessaggio(String m);
}

interface RiceviMessaggio {

    public void messaggioReceived(String m);

}
