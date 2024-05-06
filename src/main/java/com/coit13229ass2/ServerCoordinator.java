package com.coit13229ass2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerCoordinator {

    public static void main(String[] args) {
        try {
            //Set Socket port number to esblish connection with client
            int orderClientPort = 7611;

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

}//End of main class
//calss to facilitate server client connection

class ClientConnection extends Thread {

    //initialise in and out data streams
    ObjectInputStream clientInput;
    ObjectOutputStream clientOutput;

    Socket clientSocket;
    Socket bookServerSocket;

    public ClientConnection(Socket aClientSocket) {

        try {
            clientSocket = aClientSocket;

            clientInput = new ObjectInputStream(clientSocket.getInputStream());
            clientOutput = new ObjectOutputStream(clientSocket.getOutputStream());

            //starts server thread
            this.start();
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        }

    }

    //run method starts thread
    public void run() {
        try {
            //recieve ordertype string to determine which action to perform
            String orderType = (String) clientInput.readObject();
            //condition statement for book orders
            if (orderType.equals("book")) {
                BookOrder bo = (BookOrder) clientInput.readObject();
                String bookMessage = sendToBookServer(bo) + "\n";
                clientOutput.writeObject(bookMessage);
            }
            //condition statement for movie orders
            if (orderType.equals("movie")) {
                MovieOrder mo = (MovieOrder) clientInput.readObject();
                String movieMessage = sendToMovieServer(mo) + "\n";
                clientOutput.writeObject(movieMessage);
            }

        } catch (IOException ex) {
            Logger.getLogger(ClientConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("stage reached!");

    }

    //Function to handle book server connectionto send and recieve object data
    public String sendToBookServer(BookOrder bookOrder) throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        ObjectOutputStream out = null;

        int serverPort = 7622;
        Socket socket = new Socket("localhost", serverPort);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        out.writeObject(bookOrder);
        String recievedMessage = (String) in.readObject();

        return recievedMessage;
    }

    //function to handle movie server connection to send a recieve object data
    public String sendToMovieServer(MovieOrder movieOrder) throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        ObjectOutputStream out = null;

        int serverPort = 7633;
        Socket socket = new Socket("localhost", serverPort);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        out.writeObject(movieOrder);
        String recievedMessage = (String) in.readObject();

        return recievedMessage;
    }

}//end client connection class

