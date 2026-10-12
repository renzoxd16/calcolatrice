package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        System.out.println("Server avviato e in ascolto sulla porta 3000...");
        
    
        ExecutorService pool = Executors.newCachedThreadPool();

        try (ServerSocket ss = new ServerSocket(3000)) {
            while (true) {
                Socket clientSocket = ss.accept();
                System.out.println("Nuovo client connesso: " + clientSocket.getRemoteSocketAddress());
                
              
                pool.execute(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            System.err.println("Errore del server: " + e.getMessage());
        } finally {
            pool.shutdown();
        }
    }
}


class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String inputLine;
          
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Ricevuto: " + inputLine);
                out.println("Echo: " + inputLine);
            }
        } catch (IOException e) {
            System.err.println("Errore nella gestione del client: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
                System.out.println("Connessione chiusa con il client.");
            } catch (IOException e) {
                System.err.println("Impossibile chiudere il socket del client: " + e.getMessage());
            }
        }
    }
}


