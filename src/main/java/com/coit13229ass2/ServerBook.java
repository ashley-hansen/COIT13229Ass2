package com.coit13229ass2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerBook {

    public static void main(String[] args) {
        try {
            //Set Socket port number
            int serverPort = 7622;

            ServerSocket listenSocket = new ServerSocket(serverPort);
            //create connection object utilising thread for multiple concurrent connections
            while (true) {
                Socket clientSocket = listenSocket.accept();
                ClientBookConnection connection = new ClientBookConnection(clientSocket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }//End Main method

}//End of class
//class to facilitate connection between server coordinator and serverbook class

class ClientBookConnection extends Thread {

    //initialise in and out data streams
    ObjectInputStream input;
    ObjectOutputStream output;
    Socket clientSocket;

    public ClientBookConnection(Socket aClientSocket) {

        try {
            //creates socket object and data serialization stream
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

        try {
            BookOrder order = (BookOrder) input.readObject();
            String orderTotal = order.getResult();//gets result from bookOrder class
            output.writeObject(orderTotal); //send object to server coordinator
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}//End Class
