package com.coit13229ass2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMovie {

    public static void main(String[] args) {
        try {
            //Set Socket port number
            int serverPort = 7633;

            ServerSocket listenSocket = new ServerSocket(serverPort);
            //create connection object utilising thread for multiple concurrent connections
            while (true) {
                Socket clientSocket = listenSocket.accept();
                ClientMovieConnection connection = new ClientMovieConnection(clientSocket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }//End Main method

}//End of class
//class to facilitate connection between server coordinator and serverMovie class

class ClientMovieConnection extends Thread {

    //initialise in and out data streams
    ObjectInputStream input;
    ObjectOutputStream output;
    Socket clientSocket;

    public ClientMovieConnection(Socket aClientSocket) {

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
            MovieOrder order = (MovieOrder) input.readObject();
            String orderTotal = order.getResult();//Gets results from book order class
            output.writeObject(orderTotal);//sends restuls to server coordinator
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
