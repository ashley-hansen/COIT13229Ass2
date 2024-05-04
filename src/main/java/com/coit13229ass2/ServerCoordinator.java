package com.coit13229ass2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerCoordinator {

    public static void main(String[] args) {
        try {
            //Set Socket port number
            int orderClientPort = 7611;
            int serverBookPort = 7622;
            int serverMoviePort = 7633;
            ServerSocket listenSocket = new ServerSocket(orderClientPort);
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
    ObjectInputStream clientInput;
    ObjectOutputStream clientOutput;
    ObjectInputStream serverBookInput;
    ObjectOutputStream serverBookOutput;
    ObjectInputStream serverMovieInput;
    ObjectOutputStream serverMovieOutput;

    Socket clientSocket;

    public ClientConnection(Socket aClientSocket) {

        try {
            clientSocket = aClientSocket;
            clientInput = new ObjectInputStream(clientSocket.getInputStream());
            clientOutput = new ObjectOutputStream(clientSocket.getOutputStream());

            String test = "this is a test message mate!";
            String orderType = (String) clientInput.readObject();
            if (orderType.equals("book")) {
                BookOrder bo = (BookOrder) clientInput.readObject();

                System.out.println(orderType);
                System.out.println(bo.getResult());
            }
            if(orderType.equals("movie")){
                MovieOrder mo = (MovieOrder) clientInput.readObject();
                
                System.out.println(orderType);
                System.out.println(mo.getResult());
            }
            clientOutput.writeObject(test);
            System.out.println("stage reached!");

            //starts server thread
            this.start();
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    //run method starts thread
    public void run() {

    }

}
