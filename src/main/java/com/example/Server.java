package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    //istanzio il server del socket
    ServerSocket server = null;
    //istanzio un socket
    Socket client = null;
    //istanzio una stringa che conterrà quella ricevuta dal client
    String stringaRicevuta = null;
    //istanzio una stringa che conterrà la stringa modificata da mandare al client
    String stringaModificata = null;
    //istanzio un canale per prendere in input ciò che arriva dal client
    BufferedReader inDalClient;
    //istanzio un canale per mandare una risposta al client
    DataOutputStream outVersoClient;

    public Socket attendi(){
        try{
            System.out.println("Server in esecuzione");
            //creo un socket sulla porta
            server = new ServerSocket(6420);
            //il socket rimane in attesa di un client
            client = server.accept();

            //creo il canale per prendere in input la stringa dal client
            inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            //creo un canale per mandare una risposta al client
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
