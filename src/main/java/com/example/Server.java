package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringaModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;

    public Socket attendi(){
        try{
            System.out.println("Server in esecuzione");
            server = new ServerSocket(6524);
            client = server.accept();

            inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outVersoClient = new DataOutputStream(client.getOutputStream());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server");
        }

        return client;
    }

    public void comunica(){
        try{
            System.out.println("benvenuto client, scrivi una frase e la trasformo in maiuscolo. Attendo ...");
            stringaRicevuta = inDalClient.readLine();
            System.out.println("ricevuta la stringa dal cliente: " + stringaRicevuta);

            stringaModificata = stringaRicevuta.toUpperCase();
            System.out.println("invio la stringa modificata al client");
            outVersoClient.writeBytes(stringaModificata + "\n");

            System.out.println("Server: fine elaborazione");
            client.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
