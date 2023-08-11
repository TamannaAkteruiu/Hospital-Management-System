package Networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import Model.mail;

public class MailServer {

    private static final int PORT = 8089; // Choose a suitable port number
  
    public static void Start(){
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started, waiting for connections...");
            
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // Create an ObjectInputStream to receive the mail object
                ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());

                try {
                    mail receivedMail = (mail) ois.readObject();
                    // Process the received mail, store it in the database, etc.
                    System.out.println("Received mail: " + receivedMail.toString());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                ois.close();
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public static void main(String [] args) {  	
    	Start();
    }
    
    
}
