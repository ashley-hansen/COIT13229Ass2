
package com.coit13229ass2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerBook {
    public static void main(String[] args){
        try {
            //Set Socket port number
            int serverPort = 7611;
            int serverBookPort = 7622;
            int serverMoviePort = 7633;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            //create connection object utilising thread for multiple concurrent connections
            while (true) {
                Socket clientSocket = listenSocket.accept();
                ClientConnection connection = new ClientConnection(clientSocket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }//End Main method

}//End of class

class ClientConnection extends Thread {
    //initialise in and out data streams
    ObjectInputStream input;
    ObjectOutputStream output;
    Socket clientSocket;

    public ClientConnection(Socket aClientSocket) {

        try {
            clientSocket = aClientSocket;
            input = new ObjectInputStream(clientSocket.getInputStream());
            output = new ObjectOutputStream(clientSocket.getOutputStream());
            //starts server thread
            this.start();
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        }

    }
    //run method starts thread
    public void run() {

        boolean running = true;

        System.out.println("Hello World!");

  
    }

}